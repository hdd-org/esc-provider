package cn.lhz.esc.config;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author Neo
 * @date 2019/10/27 0:56
 */
@Configuration
@EnableAsync
@PropertySource(value = {"classpath:config/threadPoolConfig.properties"})
public class ThreadPoolConfig
{
    public ThreadPoolConfig()
    {
    }

    /**
     * 最小线程数(核心线程数)
     */
    @Value("#{${threadPool.corePoolSize}}")
    private int corePoolSize;
    /**
     * 最大线程数
     */
    @Value("#{${threadPool.maxPoolSize}}")
    private int maxPoolSize;
    /**
     * 等待队列(队列最大长度)
     */
    @Value("#{${threadPool.queueCapacity}}")
    private int queueCapacity;

    @Value("${threadPool.threadNamePrefix}")
    private String threadNamePrefix;

    @Value("#{${threadPool.keepAliveSeconds}}")
    private int keepAliveSeconds;

    //@Value("#{${threadPool.waitForTasksToCompleteOnShutdown}}")
   // private boolean waitForTasksToCompleteOnShutdown;

    /**
     * ThredPoolTaskExcutor的处理流程 当池子大小小于corePoolSize，就新建线程，并处理请求
     * 当池子大小等于corePoolSize，把请求放入workQueue中，池子里的空闲线程就去workQueue中取任务并处理
     * 当workQueue放不下任务时，就新建线程入池，并处理请求，如果池子大小撑到了maximumPoolSize，
     * 就用RejectedExecutionHandler来做拒绝处理
     * 当池子的线程数大于corePoolSize时，多余的线程会等待keepAliveTime长时间，如果无请求可处理就自行销毁
     */

    @Bean
    public Executor taskExecutor()
    {
        System.out.println(corePoolSize);
                ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
                //核心线程数量
                threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
                //最大线程数量
                threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
                //队列中最大任务数
                threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
                //线程名称前缀"ThreadPool-"
                threadPoolTaskExecutor.setThreadNamePrefix(threadNamePrefix);
                //当达到最大线程数时如何处理新任务
                threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
                //线程空闲后最大存活时间60
                threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
               // 等待所有任务结束后再关闭线程池
               //threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown);
                //初始化线程池
                threadPoolTaskExecutor.initialize();
                return threadPoolTaskExecutor;

    }

}
