package com.advanced.springcoreadvanced.trace.v5;

import com.advanced.springcoreadvanced.trace.callback.TraceTemplate;
import com.advanced.springcoreadvanced.trace.log.LogTrace;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {
    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }
    public void save(String itemId) {
        template.execute("OrderRepository.save()", () -> {
            // 저장 로직
            if(itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!!");
            }
            sleep(1000);
            return null;
        });
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
