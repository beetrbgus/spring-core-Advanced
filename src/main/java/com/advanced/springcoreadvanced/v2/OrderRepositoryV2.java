package com.advanced.springcoreadvanced.v2;

import com.advanced.springcoreadvanced.trace.TraceId;
import com.advanced.springcoreadvanced.trace.TraceStatus;
import com.advanced.springcoreadvanced.trace.v2.TraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {
    private final TraceV2 trace;

    public void save(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId, "OrderRepository.request()");

            //저장 로직
            if(itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 안해주면 예외를 먹어버림
        }
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
