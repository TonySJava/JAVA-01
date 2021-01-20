import com.oracle.tools.packager.IOUtils;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description 第一课作业第二题
 * @Date 2021/1/11 下午4:01
 * @Author Xubujin
 */
public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String classPath= "/Users/Hello.xlass";
        HelloClassLoader helloClassLoader = new HelloClassLoader();
        Class<?> helloClass = helloClassLoader.findClass("Hello", classPath);
        Object o = helloClass.newInstance();
        Method hello = helloClass.getMethod("hello");
        hello.invoke(o);

    }
    protected Class<?> findClass(String className,String classPath) throws ClassNotFoundException, IOException {
        File file = new File(classPath);
        byte[] bytes = IOUtils.readFully(file);
        bytes=convert(bytes);
        if (bytes.length > 0) {
            return defineClass(className, bytes, 0, bytes.length);
        }
        return null;
    }

    public byte[] convert(byte[] bytes){
        for (int i = 0; i < bytes.length; i++) {
            bytes[i]= (byte) (255-bytes[i]);
        }
        return bytes;
    }
}
