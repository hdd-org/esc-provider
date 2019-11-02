package cn.lhz.esc;

import ch.ethz.ssh2.Connection;
import cn.lhz.esc.dao.UserMapper;
import cn.lhz.esc.entity.*;
import cn.lhz.esc.service.*;
import cn.lhz.esc.utils.EscUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class EscApplicationTests {

	@Resource(name = "logger")
	private Logger log;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private EscService escService;
	@Autowired
	private SshService sshService;
	@Test
	void contextLoads() {
		System.out.println(EscUtil.md5("123456"));
	}

	@Test
	void addUser(){
		User user = new User();
		user.setUserPassword(EscUtil.md5("123456"));
		user.setUserUsername("admin");
		userMapper.addUser(user);
		System.out.println(user.getUserId());
	}

	@Test
	void updateUser(){
		User u = new User();
		u.setUserUsername("admin");
		u.setUserPassword("123456");
		User user = userService.selectUserByUserNamePassword(u);
		//user.setUserPassword("1234567");
		//userService.updateUser(user);
		Esc es = null;
      for (Esc esc:user.getEscs()){
		 es=esc;
		  Connection connection = sshService.login(esc.getEscPublicIp(),esc.getEscUsername(),esc.getEscPassword());
		  user.getConnections().put(es.getEscId(),connection);
		  //user.setConnections(map);
	  }
          log.info("info日志");
		sshService.execute("hostname",user.getConnections().get(es.getEscId()));
		System.out.println(user);
	}

	@Test
	void addEsc(){
		Esc esc = new Esc();
		esc.setEscPublicIp("94.191.119.192");
		esc.setEscUsername("root");
		esc.setEscPort("22");
		esc.setEscPassword("Lhz@971009");
		esc.setEscAlias("jenkins服务器");
		esc.setEscManufactor("阿里云");
		esc.setUserId("be26e5d5-f5ae-11e9-be78-0242ac110002");
		escService.addEsc(esc);
	}



}
