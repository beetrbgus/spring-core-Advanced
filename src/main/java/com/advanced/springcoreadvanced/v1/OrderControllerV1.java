package com.advanced.springcoreadvanced.v1;

import com.advanced.springcoreadvanced.trace.TraceStatus;
import com.advanced.springcoreadvanced.trace.TraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderService;
    private final TraceV1 trace;

    @GetMapping("/v1/request")
    public String saveItem(@RequestParam String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; // 안해주면 예외를 먹어버림
        }

    }
}
