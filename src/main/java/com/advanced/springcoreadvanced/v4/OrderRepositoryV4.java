package com.advanced.springcoreadvanced.v4;

import com.advanced.springcoreadvanced.trace.AbstractTemplate;
import com.advanced.springcoreadvanced.trace.log.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
    private final LogTrace trace;

    public void save(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                if(itemId.equals("ex")) {
                    throw new IllegalStateException("예외 발생!!");
                }
                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepository.orderItem()");
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
