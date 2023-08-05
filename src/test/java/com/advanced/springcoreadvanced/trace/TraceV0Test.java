package com.advanced.springcoreadvanced.trace;

import org.junit.jupiter.api.Test;

class TraceV0Test {
    @Test
    void begin_end() {
        TraceV0 trace = new TraceV0();
        TraceStatus traceStatus = trace.begin("hello");
        trace.end(traceStatus);
    }

    @Test
    void begin_exception() {
        TraceV0 trace = new TraceV0();
        Long startTime = System.currentTimeMillis();
        TraceStatus traceStatus = trace.begin("hello");
        trace.exception(traceStatus, new IllegalStateException("에러 발생!"));
    }

}