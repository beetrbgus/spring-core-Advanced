package com.advanced.springcoreadvanced.v2;

import com.advanced.springcoreadvanced.trace.TraceStatus;
import com.advanced.springcoreadvanced.trace.v2.TraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderService;
    private final TraceV2 trace;

    @GetMapping("/v2/request")
    public String saveItem(@RequestParam String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(status.getTraceId(), itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 안해주면 예외를 먹어버림
        }

    }
}
