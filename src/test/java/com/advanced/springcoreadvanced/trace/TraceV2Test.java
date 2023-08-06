package com.advanced.springcoreadvanced.trace;

import com.advanced.springcoreadvanced.trace.v2.TraceV2;
import org.junit.jupiter.api.Test;

class TraceV2Test {
    @Test
    void begin_end() {
        TraceV2 trace = new TraceV2();
        TraceStatus traceStatus1 = trace.begin("hello1");
        TraceStatus traceStatus2 = trace.beginSync(traceStatus1.getTraceId(), "hello2");
        trace.end(traceStatus2);
        trace.end(traceStatus1);

    }

    @Test
    void begin_exception() {
        TraceV2 trace = new TraceV2();
        TraceStatus traceStatus1 = trace.begin("hello1");
        TraceStatus traceStatus2 = trace.beginSync(traceStatus1.getTraceId(), "hello2");
        trace.exception(traceStatus2, new IllegalStateException("에러 발생2!"));
        trace.exception(traceStatus1, new IllegalStateException("에러 발생1!"));
    }

}