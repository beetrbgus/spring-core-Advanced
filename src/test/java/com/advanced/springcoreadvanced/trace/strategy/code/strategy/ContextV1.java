package com.advanced.springcoreadvanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식
 * 변하지 않는 로직을 가지고 있는 템플릿 역할
 * 전략 패턴에서는 Context(문맥)이라고 한다.
 * Context는 변하지 않지만, 그 속에서 Strategy를 통해 일부 전략이 변경된다고 생각
 *
 * Context는 내부에 Strategy strategy 필드를 갖고 있다.
 * 변하는 부분인 Strategy의 구현체를 주입하면 됨.
 */
@Slf4j
public class ContextV1 {
    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        long startTime = System.currentTimeMillis();
        // 비즈니스 로직 실행
        strategy.call();
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime = {}", resultTime);
    }
}
