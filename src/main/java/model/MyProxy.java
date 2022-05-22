package model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxy implements InvocationHandler {
    private final Laptop laptop;

    public MyProxy(Laptop laptop) {
        this.laptop = laptop;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("get")) {
            return method.invoke(laptop, args);
        }
        throw new IllegalAccessException("Not allowed");
    }

    public static Object newProxyInstance(Laptop laptop) {
        return java.lang.reflect.Proxy.newProxyInstance(
                laptop.getClass().getClassLoader(),
                laptop.getClass().getInterfaces(),
                new MyProxy(laptop));
    }
}
