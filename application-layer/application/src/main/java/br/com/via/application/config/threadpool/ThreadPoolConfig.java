package br.com.via.application.config.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
@EnableAsync
@Configuration
public class ThreadPoolConfig {

    protected ThreadPoolTaskExecutor getDefaultThreadPoolForRequestContext(String threadName) {
        var threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(10);
        threadPool.setQueueCapacity(0);
        threadPool.setThreadNamePrefix(threadName);
        threadPool.setTaskDecorator(new RequestContextCopyingDecorator());
        return threadPool;
    }

    protected ThreadPoolTaskExecutor getDefaultThreadPoolForJobContext(String threadName) {
        var threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(10);
        threadPool.setQueueCapacity(0);
        threadPool.setThreadNamePrefix(threadName);
        threadPool.setTaskDecorator(new NoRequestContextCopyingDecorator());
        return threadPool;
    }

}
