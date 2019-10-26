package cn.lhz.esc.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.*;

/**
 * @author Neo
 * @date 2019/10/27 0:55
 */
@Configuration
public class FastJsonConfig
{
    public FastJsonConfig()
    {
    }

    @Bean
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter(){
            FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
            com.alibaba.fastjson.support.config.FastJsonConfig
                    config = new com.alibaba.fastjson.support.config.FastJsonConfig();
            config.setDateFormat("yyyy-MM-dd HH:mm:ss");
           converter.setFastJsonConfig(config);
            return converter;
        }
}
