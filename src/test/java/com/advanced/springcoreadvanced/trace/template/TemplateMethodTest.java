package com.advanced.springcoreadvanced.trace.template;

import com.advanced.springcoreadvanced.trace.template.code.AbstractTemplate;
import com.advanced.springcoreadvanced.trace.template.code.SubClassLogic1;
import com.advanced.springcoreadvanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {
    @Test
    void templateMethodV0() {
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

    /**
     * 템플릿 메서드 패턴 적용
     */
    @Test
    void templateMethodV1() {
        AbstractTemplate abstractTemplate1 = new SubClassLogic1();
        abstractTemplate1.execute();

        AbstractTemplate abstractTemplate2 = new SubClassLogic2();
        abstractTemplate2.execute();
    }

    @Test
    void templateMethodV2() {
        AbstractTemplate abstractTemplate1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        abstractTemplate1.execute();
        log.info("클래스 이름 1 = {}", abstractTemplate1.getClass());
        AbstractTemplate abstractTemplate2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");
            }
        };
        abstractTemplate2.execute();
        log.info("클래스 이름 2 = {}", abstractTemplate2.getClass());
    }
}
