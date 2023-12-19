package br.com.via.application.config.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
@Configuration
public class MessageRelayThreadPoolConfig extends ThreadPoolConfig {

    public static final String THREAD_NAME = "messagerelay-thread-pool";

    @Bean(THREAD_NAME)
    protected ThreadPoolTaskExecutor getMessageRelayThreadPool() {
        var threadPool = getDefaultThreadPoolForRequestContext(THREAD_NAME);
        threadPool.initialize();
        return threadPool;
    }

}
