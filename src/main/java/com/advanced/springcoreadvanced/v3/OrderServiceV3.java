package com.advanced.springcoreadvanced.v3;

import com.advanced.springcoreadvanced.trace.TraceStatus;
import com.advanced.springcoreadvanced.trace.log.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.request()");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 안해주면 예외를 먹어버림
        }
    }
}
