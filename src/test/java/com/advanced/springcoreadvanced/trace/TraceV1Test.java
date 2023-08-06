package com.advanced.springcoreadvanced.trace;

import com.advanced.springcoreadvanced.trace.v1.TraceV1;
import org.junit.jupiter.api.Test;

class TraceV1Test {
    @Test
    void begin_end() {
        TraceV1 trace = new TraceV1();
        TraceStatus traceStatus = trace.begin("hello");
        trace.end(traceStatus);
    }

    @Test
    void begin_exception() {
        TraceV1 trace = new TraceV1();
        Long startTime = System.currentTimeMillis();
        TraceStatus traceStatus = trace.begin("hello");
        trace.exception(traceStatus, new IllegalStateException("에러 발생!"));
    }

}