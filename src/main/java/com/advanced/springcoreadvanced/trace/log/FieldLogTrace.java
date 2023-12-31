package com.advanced.springcoreadvanced.trace.log;

import com.advanced.springcoreadvanced.trace.TraceId;
import com.advanced.springcoreadvanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLogTrace implements LogTrace {
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String ERROR_PREFIX = "<X--";

    //traceId 동기화, 동시성 이슈 발생
    private TraceId traceIdHolder;

    private void syncTraceId() {
        if(traceIdHolder == null) traceIdHolder = new TraceId();
        else traceIdHolder = traceIdHolder.createNextId();
    }

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = traceIdHolder;
        Long startTimeMs = System.currentTimeMillis();
        // 로그 출력
        log.info("[{}] {}{}"
                , traceId.getId()
                , addSpace(START_PREFIX, traceId.getLevel())
                , message);
        return new TraceStatus(traceId, startTimeMs, message);
    }
    @Override
    public void end(TraceStatus traceStatus) {
        complete(traceStatus, null);
    }

    @Override
    public void exception(TraceStatus traceStatus, Exception e) {
        complete(traceStatus, e);
    }

    private void complete(TraceStatus traceStatus, Exception exception) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - traceStatus.getStartTimeMs();
        TraceId traceId = traceStatus.getTraceId();

        if(exception == null) {
            log.info("[{}] {}{} time={}ms"
                    , traceId.getId()
                    , addSpace(COMPLETE_PREFIX, traceId.getLevel())
                    , traceStatus.getMessage()
                    , resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}"
                    , traceId.getId()
                    , addSpace(ERROR_PREFIX, traceId.getLevel())
                    , traceStatus.getMessage()
                    , resultTimeMs
                    , exception.getMessage());
        }
        releaseTraceId();
    }

    private void releaseTraceId() {
        if(traceIdHolder.isFirstLevel()) {
            traceIdHolder = null; // destroy
        } else  {
            traceIdHolder = traceIdHolder.createPreviousId();
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
