package com.advanced.springcoreadvanced.trace.v1;

import com.advanced.springcoreadvanced.trace.TraceId;
import com.advanced.springcoreadvanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TraceV1 {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String ERROR_PREFIX = "<X--";

    public TraceStatus begin(String message) {
        TraceId traceId = new TraceId();
        Long startTimeMs = System.currentTimeMillis();
        // 로그 출력
        log.info("[{}] {}{}"
                , traceId.getId()
                , addSpace(START_PREFIX, traceId.getLevel())
                , message);
        return new TraceStatus(traceId, startTimeMs, message);
    }
    public void end(TraceStatus traceStatus) {
        complete(traceStatus, null);
    }

    public void exception(TraceStatus traceStatus, Exception e) {
        complete(traceStatus, e);
    }
    private void complete(TraceStatus traceStatus, Exception exception) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - traceStatus.getStartTimeMs();
        TraceId traceId = traceStatus.getTraceId();

        if(exception == null) {
            log.info("[{}] {}time={}ms"
                    , traceId.getId()
                    , addSpace(COMPLETE_PREFIX, traceId.getLevel())
                    , traceStatus.getMessage()
                    , resultTimeMs);
        } else {
            log.info("[{}] {}time={}ms ex={}"
                    , traceId.getId()
                    , addSpace(ERROR_PREFIX, traceId.getLevel())
                    , traceStatus.getMessage()
                    , resultTimeMs
                    , exception.getMessage());
        }
    }

    public static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1)? "|" + prefix : "|    ");
        }
        return sb.toString();
    }
}
