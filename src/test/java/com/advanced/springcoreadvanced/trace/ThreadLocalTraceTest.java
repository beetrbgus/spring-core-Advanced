package com.advanced.springcoreadvanced.trace;

import com.advanced.springcoreadvanced.trace.log.ThreadLocalLogTrace;
import com.advanced.springcoreadvanced.trace.v2.TraceV2;
import org.junit.jupiter.api.Test;

class ThreadLocalTraceTest {
    @Test
    void begin_end() {
        ThreadLocalLogTrace threadLocalLogTrace = new ThreadLocalLogTrace();
        TraceStatus traceStatus1 = threadLocalLogTrace.begin("hello1");
        TraceStatus traceStatus2 = threadLocalLogTrace.begin("hello2");
        threadLocalLogTrace.end(traceStatus2);
        threadLocalLogTrace.end(traceStatus1);

    }

    @Test
    void begin_exception() {
        ThreadLocalLogTrace threadLocalLogTrace = new ThreadLocalLogTrace();
        TraceStatus traceStatus1 = threadLocalLogTrace.begin("hello1");
        TraceStatus traceStatus2 = threadLocalLogTrace.begin("hello2");
        threadLocalLogTrace.exception(traceStatus2, new IllegalStateException("에러 발생2!"));
        threadLocalLogTrace.exception(traceStatus1, new IllegalStateException("에러 발생1!"));
    }

}