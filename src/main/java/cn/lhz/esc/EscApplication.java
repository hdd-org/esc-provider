package cn.lhz.esc;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@MapperScan("cn.lhz.esc.dao")
@EnableEurekaClient
@SpringBootApplication
public class EscApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(EscApplication.class, args);
    }

    private final Logger logger = LoggerFactory.getLogger(EscApplication.class);

    @Bean("logger")
    public Logger getLogger()
    {
        return logger;
    }
}
