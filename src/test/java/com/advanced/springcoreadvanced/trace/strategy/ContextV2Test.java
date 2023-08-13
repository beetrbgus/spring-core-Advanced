package com.advanced.springcoreadvanced.trace.strategy;

import com.advanced.springcoreadvanced.trace.strategy.code.strategy.ContextV2;
import com.advanced.springcoreadvanced.trace.strategy.code.strategy.Strategy;
import com.advanced.springcoreadvanced.trace.strategy.code.strategy.StrategyLogic1;
import com.advanced.springcoreadvanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {
    /**
     * 메서드를 실행할 때마다 전력을 넣어 줌
     * Context를 생성시마다 조립하는게 아니라
     */
    @Test
    void strategyV1() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    /**
     * 전략 패턴 익명 내부 클래스
     */
    @Test
    void strategyV2() {
        ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1");
            }
        });
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 2");
            }
        });
    }

    /**
     * 람다 사용
     */
    @Test
    void strategyV3() {
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비즈니스 로직 1"));
        context.execute(() -> log.info("비즈니스 로직 2"));
    }
}
