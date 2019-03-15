package reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class TestProxy {
    static class  DynamicProxy implements InvocationHandler{

        private Object o;

        public DynamicProxy(Object o) {
            this.o = o;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            System.out.println("proxy: " + proxy.getClass() + "method: " + method + "args: " + Arrays.toString(args));
//            if (args != null) {
//                for (Object arg :
//                        args) {
//                    System.out.println(" " + arg);
//                }
//            }
            System.out.println("before invoke method " + method.getName());
            Object result = method.invoke(o, args);
            System.out.println("after invoke method " + method.getName());
            return result;
        }
    }

    interface  Something {
        void doSomething();
        void somethingElse(String who, String thing);
    }

    static class RealObject implements  Something{
        public  void doSomething(){
            System.out.println("do something");
        }

        public void somethingElse(String who, String thing) {
            System.out.println(who + "  does " + thing);
        }

    }

    public static void main(String[] args) {
        RealObject realObject = new RealObject();
        realObject.doSomething();
        realObject.somethingElse("Nick", "homework");
        Something proxy = (Something)Proxy.newProxyInstance(
                Something.class.getClassLoader(),
                new Class[]{Something.class}, // 类可能不只实现了一个接口，所以是接口列表
                new DynamicProxy(realObject));
        proxy.doSomething();
        proxy.somethingElse("Nick", "homework");
    }
}
