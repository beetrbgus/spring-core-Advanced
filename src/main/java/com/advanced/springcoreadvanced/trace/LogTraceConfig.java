package com.advanced.springcoreadvanced.trace;

import com.advanced.springcoreadvanced.trace.log.LogTrace;
import com.advanced.springcoreadvanced.trace.log.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
