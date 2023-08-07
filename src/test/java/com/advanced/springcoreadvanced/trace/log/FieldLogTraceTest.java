package com.advanced.springcoreadvanced.trace.log;

import com.advanced.springcoreadvanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class FieldLogTraceTest {
    FieldLogTrace trace = new FieldLogTrace();

    @Test
    public void begin_end_version2() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    public void begin_exception_version2() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.exception(status2, new IllegalStateException("zz"));
        trace.exception(status1, new IllegalStateException("zz"));
    }

}