package cn.lhz.esc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan("cn.lhz.esc.dao")
@EnableEurekaClient
@SpringBootApplication
public class EscApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscApplication.class, args);
	}


}
