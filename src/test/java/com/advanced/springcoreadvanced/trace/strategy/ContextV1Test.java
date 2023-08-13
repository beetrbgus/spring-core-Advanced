package com.advanced.springcoreadvanced.trace.strategy;

import com.advanced.springcoreadvanced.trace.strategy.code.strategy.ContextV1;
import com.advanced.springcoreadvanced.trace.strategy.code.strategy.Strategy;
import com.advanced.springcoreadvanced.trace.strategy.code.strategy.StrategyLogic1;
import com.advanced.springcoreadvanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {
    @Test
    void strategyV0() {
        logic1();
        logic2();
        // 변하는 부분 : 비즈니스 로직
        // 변하지 않는 부분 : 시간을 측정하는 것
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }
    @Test
    void strategyV1() {
        StrategyLogic1 strategy1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategy1);
        contextV1.execute();

        StrategyLogic2 strategy2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategy2);
        contextV2.execute();
    }

    @Test
    void strategyV2() {
        Strategy strategy1 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1 실행");
            }
        };
        ContextV1 context1 = new ContextV1(strategy1);
        context1.execute();
        log.info("strategyLogic1 = {}", strategy1.getClass());

        Strategy strategy2 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 2 실행");
            }
        };
        ContextV1 context2 = new ContextV1(strategy2);
        log.info("strategyLogic2 = {}", strategy2.getClass());
        context2.execute();
    }

    @Test
    void strategyV3() {
        ContextV1 context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1 실행");
            }
        });
        context1.execute();

        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 2 실행");
            }
        });
        context2.execute();
    }

    @Test
    void strategyV4() {
        // 메서드가 한개만 있을 때 람다를 사용할 수 있음
        ContextV1 context1 = new ContextV1(() -> log.info("비즈니스 로직 1 실행"));
        context1.execute();

        ContextV1 context2 = new ContextV1(() -> log.info("비즈니스 로직 2 실행"));
        context2.execute();
    }
}
