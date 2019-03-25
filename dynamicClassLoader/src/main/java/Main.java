import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

    public static void main(String[] args) throws InterruptedException, IllegalAccessException, InvocationTargetException {


        Class<?> myClass = TextService.class;
        URL[] urls = {myClass.getProtectionDomain().getCodeSource().getLocation()};
        ClassLoader delegateParent = myClass.getClassLoader().getParent();

        while (true) {
            Method staticText = updateMethod(urls, delegateParent, myClass);
            System.out.println(staticText.invoke(null));
            Thread.sleep(2000);
        }
    }

    private static Method updateMethod(URL[] urls, ClassLoader delegateParent, Class myClass) {
        try {
            try (URLClassLoader cl = new URLClassLoader(urls, delegateParent)) {
                Class<?> reloaded = cl.loadClass(myClass.getName());
                return reloaded.getMethod("staticText");
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}
