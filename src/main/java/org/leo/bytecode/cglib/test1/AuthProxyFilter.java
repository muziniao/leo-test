package org.leo.bytecode.cglib.test1;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.CallbackFilter;

/**
 * proxy过滤
 * 
 */
public class AuthProxyFilter implements CallbackFilter {

    private static final int AUTH_NEED     = 0;
    private static final int AUTH_NOT_NEED = 1;

    /**
     * <pre>
     * 选择使用的proxy
     * 如果调用query函数，则使用第二个proxy
     * 否则，使用第一个proxy
     * </pre>
     */
    @Override
    public int accept(Method method) {
        if ("query".equals(method.getName())) {
            return AUTH_NOT_NEED;
        }
        return AUTH_NEED;
    }

}
