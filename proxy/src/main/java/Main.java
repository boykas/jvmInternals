import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {

        Service service = (Service) Proxy.newProxyInstance(
                TextService.class.getClassLoader(),
                TextService.class.getInterfaces(),
                new EnvVariableProxyReplacer(TextService.class));

        System.out.println(service.variable("server.port = ${port}"));

    }
}
