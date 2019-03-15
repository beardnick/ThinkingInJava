package Demo.ChapterFourteen;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by asus on 2017/12/27.
 */


// TODO: 2017/12/27 read this file again and again

interface Interface{
    void doSomething();
    void somethingElse(String arg);
}

class RealObject implements Interface{
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("somethingElse " + arg);
    }
}

class DynamicProxyHandler implements InvocationHandler{//调用处理器
    private Object proxied;

    public DynamicProxyHandler(Object proxied){
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {//调用
        System.out.println("**** proxy : " + proxy.getClass() + " , methond :" + method + " , args :" + args);
        if(args != null){
            for(Object arg : args){
                System.out.println("    " + arg);
            }
        }
        return method.invoke(proxied , args);
    }
}


public class SimpleDynamicProxy {
    public static void consumer(Interface iface){
        iface.doSomething();
        iface.somethingElse("bonobo");
    }
    public static void main(String[] args){
        RealObject real = new RealObject();
        consumer(real);
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader() ,
                new Class[]{Interface.class} ,
                new DynamicProxyHandler(real)
        );
        consumer(proxy);
    }
}
