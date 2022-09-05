import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<String, Method> methods = new HashMap<>();

    public static void main(String[] args) throws ClassNotFoundException {
        String className = args[0];

        Class c = Class.forName(className);

        for (Method m: c.getMethods()) {
            if (m.isAnnotationPresent(RequestMapping.class)) {
                try {
                    String uri = m.getAnnotation(RequestMapping.class).value();
                    methods.put(uri, m);
                    System.out.println("Added Method: " + uri);
                } catch (Throwable ex) {
                    System.out.println("Error" + ex);
                }

            }
        }

        try {
            HttpServer.start(methods);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
