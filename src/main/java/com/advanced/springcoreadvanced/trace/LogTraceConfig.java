package com.advanced.springcoreadvanced.trace;

import com.advanced.springcoreadvanced.trace.log.FieldLogTrace;
import com.advanced.springcoreadvanced.trace.log.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace() {
        return new FieldLogTrace();
    }
}
