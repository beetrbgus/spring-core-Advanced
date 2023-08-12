package com.advanced.springcoreadvanced.trace;

import com.advanced.springcoreadvanced.trace.log.LogTrace;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate<T> {
    private final LogTrace logTrace;

    protected AbstractTemplate(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    public T execute(String message) {
        TraceStatus status = null;

        try {
            status = logTrace.begin(message);
            
            // 로직 호출
            T result = call();

            logTrace.end(status);

            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}