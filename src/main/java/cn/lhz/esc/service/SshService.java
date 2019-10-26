package cn.lhz.esc.service;

import ch.ethz.ssh2.Connection;

/**
 * @author Neo
 * @date 2019/10/20 22:40
 */
public interface SshService
{
    Connection login(String ip, String userName, String userPwd);

    void loginOut(Connection connection);

    void putFile(String localFile, String remoteTargetDirectory,Connection connection);

    void getFile(String remoteFile, String path,Connection connection);

    String execute(String cmd,Connection connection);
}