package com.advanced.springcoreadvanced.trace.log;

import com.advanced.springcoreadvanced.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
