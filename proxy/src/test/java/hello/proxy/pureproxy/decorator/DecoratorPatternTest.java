package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.Component;
import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.proxy.pureproxy.decorator.code.RealComponent;
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
}
