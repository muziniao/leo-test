package org.leo.proxy.person;

/**
 * 静态代理：在程序运行前，代理类的.class文件就已经存在了
 * 这种方式的最大缺点就是每次我们都需要建立不同的代理对象，灵活性和可复用性都很差，所以我们需要使用到动态代理技术。
 * 
 * Modify Time Dec 24, 2013 4:29:37 PM
 */
public class PersonProxy implements Person {
    private Person person;// 被代理对象

    public PersonProxy(Person p) {
        this.person = p;
    }

    @Override
    public void say() {
        System.out.print("我是-");
        person.say();// 在目标方法前后分别添加操作
        System.out.println("-人");
    }

}
