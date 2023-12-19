package br.com.via.application.config.threadpool;

import lombok.NonNull;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;
import java.util.Objects;

public class NoRequestContextCopyingDecorator implements TaskDecorator {

    @NonNull
    @Override
    public Runnable decorate(@NonNull Runnable runnable) {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();

        return () -> {
            try {
                if (!Objects.isNull(contextMap)) {
                    MDC.setContextMap(contextMap);
                }

                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }

}