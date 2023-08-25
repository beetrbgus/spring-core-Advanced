package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.Component;
import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.proxy.pureproxy.decorator.code.RealComponent;
import hello.proxy.pureproxy.decorator.code.TimeDecorator;
import org.junit.jupiter.api.Test;

public class DecoratorPatternTest {
    @Test
    void noDecoratorTest() {
        Component component = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);

        client.execute();
    }
    @Test
    void DecoratorTest1() {
        Component realComponent = new RealComponent();
        Component messageComponent = new MessageDecorator(realComponent);

        DecoratorPatternClient client = new DecoratorPatternClient(messageComponent);

        client.execute();
    }

    @Test
    void DecoratorTest2() {
        Component realComponent = new RealComponent();
        Component messageComponent = new MessageDecorator(realComponent);
        Component timeDecorator = new TimeDecorator(messageComponent);

        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);

        client.execute();
    }
}
