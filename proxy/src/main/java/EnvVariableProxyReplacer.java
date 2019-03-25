import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EnvVariableProxyReplacer implements InvocationHandler {

    private final String variable = System.getProperty("port");
    private Object target;

    public EnvVariableProxyReplacer(Class target) {
        try {
            this.target = target.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (variable != null && ((String) args[0]).contains("server.port = ${port}")) {
            args[0] = "server.port = " + variable;
        }
        return method.invoke(target, args);
    }

}
