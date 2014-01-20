package org.leo.proxy.person;

public class TestProxy {

	/**
	 * 
	 * @param args
	 *
	 * Modify Time Dec 24, 2013 4:31:50 PM
	 */
	public static void main(String[] args) {
		Person zhangsan = new PersonImpl();// 被代理的对象

        zhangsan.say();// 如果让代理目标直接执行目标方法，就不能在目标方法前后操作
        System.out.println();

        PersonProxy proxy = new PersonProxy(zhangsan);// 将被代理的对象传递一个代理类
        proxy.say();// 让代理类去执行目标方法，这个时候代理类就在目标方法执行前后乱搞了

        PersonProxyJdk proxyJdk = new PersonProxyJdk();// 创建一个代理对象
        Person zhangsanJdk = (Person) proxyJdk.createProxyInstance(zhangsan);// 将被代理对象传递给代理对象，并且返回被代理接口
        zhangsanJdk.say();// 调运被代理对象的接口，就能动态的去执行代理对象想要执行的操作

        PersonProxyCglib proxyCglib = new PersonProxyCglib();// 创建Cglib代理对象
        Person zhansanCglib = (Person) proxyCglib.createProxyInstance(zhangsan);// 将被代理对象传递给代理对象，并且返回被代理接口
        zhansanCglib.say();// 调运被代理对象的接口，就能动态的去执行代理对象想要执行的操作
        
        // 使用Cglib的时候，目标对象可以不实现任何接口，但是使用JDK代理的时候就不可以
        PersonNoImpl lisi = new PersonNoImpl();
        PersonNoImpl lisiCglib = (PersonNoImpl) proxyCglib.createProxyInstance(lisi);
        lisiCglib.say();

	}

}