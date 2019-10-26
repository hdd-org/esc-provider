package cn.lhz.esc.service.impl;

import ch.ethz.ssh2.*;
import cn.lhz.esc.service.SshService;
import cn.lhz.esc.utils.EscUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;

/**
 * @author Neo
 * @date 2019/10/20 22:47
 */
@Service
public class SshServiceImpl implements SshService
{
    private static final Logger log = LoggerFactory.getLogger(SshServiceImpl.class);
    private static final String DEFAULTCHARSET = "UTF-8";
   @Resource(name = "taskExecutor")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    public SshServiceImpl()
    {
    }


    private Connection getConnection(String ip, String userName, String userPwd)
    {
        boolean flg = false;
        Connection connection = null;
        try
        {
            connection = new Connection(ip);
            connection.connect();//连接
            flg = connection.authenticateWithPassword(userName, userPwd);//认证
            if (flg)
            {
                log.info("=========登录成功=========" + connection);
            }
            else
            {
                log.error("=========用户名或密码错误=========");
            }
        }
        catch (IOException e)
        {
            log.error("=========登录失败=========" + e.getMessage());
        }
        return connection;
    }


    @Override
    public Connection login(String ip, String userName, String userPwd)
    {
        return getConnection(EscUtil.decode(ip), EscUtil.decode(userName), EscUtil.decode(userPwd));
    }

    @Override
    public void loginOut(Connection connection)
    {
        if (connection != null)
        {
            log.error("=========连接断开=========");
            connection.close();
        }
    }

    @Override
    public void putFile(String localFile, String remoteTargetDirectory, Connection connection)
    {


        try
        {
            SCPClient scpClient = getScpClient(connection);
            scpClient.put(localFile, remoteTargetDirectory);
        }
        catch (Exception e)
        {
            log.error("=========scpClinet创建失败!=========");
            e.printStackTrace();
        }

    }

    @Override
    public void getFile(String remoteFile, String path, Connection connection)
    {

        try
        {
            SCPClient scpClient = getScpClient(connection);
            scpClient.get(remoteFile, path);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private SCPClient getScpClient(Connection connection) throws Exception
    {

        if (connection == null)
            throw new RuntimeException("获取scpClient时连接为空！");

        SCPClient scpClient = null;
        try
        {
            scpClient = connection.createSCPClient();
        }
        catch (IOException e)
        {
            log.error("=========scpClinet创建失败!=========");
            e.printStackTrace();
        }

        return scpClient;
    }

    @Async
    public String execute(String cmd, Connection connection)
    {
        String result = "";
        Session session = null;
        try
        {
            if (connection != null)
            {
                session = connection.openSession();//打开一个会话
                session.execCommand(cmd);//执行命令
                result = processStdout(session.getStdout(), DEFAULTCHARSET);
                //如果为得到标准输出为空，说明脚本执行出错了
                if (StringUtils.isBlank(result))
                {
                    log.info("得到标准输出为空,链接conn:" + connection + ",执行的命令：" + cmd);
                    result = processStdout(session.getStderr(), DEFAULTCHARSET);
                }
                else
                {
                    log.info("执行命令成功,链接conn:" + connection + ",执行的命令：" + cmd);
                    log.info(result);
                }

            }
        }
        catch (IOException e)
        {
            log.info("执行命令失败,链接conn:" + connection + ",执行的命令：" + cmd + "  " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            if (session != null)
                session.close();
        }
        return result;
    }

    /**
     * 解析脚本执行返回的结果集
     *
     * @param in      输入流对象
     * @param charset 编码
     * @return 以纯文本的格式返回
     */
    private String processStdout(InputStream in, String charset)
    {
        InputStream stdout = new StreamGobbler(in);
        StringBuffer buffer = new StringBuffer();
        ;
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout, charset));
            String line = null;
            while ((line = br.readLine()) != null)
            {
                buffer.append(line + "\n");
            }
        }
        catch (UnsupportedEncodingException e)
        {
            log.error("解析脚本出错：" + e.getMessage());
            e.printStackTrace();
        }
        catch (IOException e)
        {
            log.error("解析脚本出错：" + e.getMessage());
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
