package org.leo.bytecode.asm.aop;

public class TestBeanAop {
    public void halloAop() {
        AopInterceptor.beforeInvoke();
        System.out.println("Hello Aop");
        AopInterceptor.afterInvoke();
    }
}
