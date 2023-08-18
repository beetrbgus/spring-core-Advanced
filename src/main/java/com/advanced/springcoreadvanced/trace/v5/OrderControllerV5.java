package com.advanced.springcoreadvanced.trace.v5;

import com.advanced.springcoreadvanced.trace.callback.TraceCallBack;
import com.advanced.springcoreadvanced.trace.callback.TraceTemplate;
import com.advanced.springcoreadvanced.trace.log.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {
    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String saveItem(@RequestParam String itemId) {
        return template.execute("OrderController.request()", new TraceCallBack<>() {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        });
    }
}
