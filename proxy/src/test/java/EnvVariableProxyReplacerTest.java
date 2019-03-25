import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Proxy;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class EnvVariableProxyReplacerTest {

    private Service service;

    @Before
    public void setUp() {
        System.setProperty("port", "8080");
        service = (Service) Proxy.newProxyInstance(TextService.class.getClassLoader(),
                TextService.class.getInterfaces(),
                new EnvVariableProxyReplacer(TextService.class));
    }

    @Test
    public void testMethodShouldSubstituteParameter() {
        //when
        String subString =  service.variable("server.port = ${port}");
        //then
        assertEquals("server.port = 8080", subString);
    }

    @Test
    public void testMethodShouldNotSubstituteParameter(){
        //when
        String subString = service.variable("some string");
        //then
        assertEquals("some string", subString);
    }

}