package org.leo.bytecode.asm.aop;

import java.io.InputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

class AopClassLoader extends ClassLoader implements Opcodes {
    public AopClassLoader(ClassLoader parent) {
        super(parent);
    }
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (!name.contains("TestBean_Tmp"))
            return super.loadClass(name);
        try {
            ClassWriter cw = new ClassWriter(0);
            //
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("org/leo/bytecode/asm/aop/TestBean.class");
            ClassReader reader = new ClassReader(is);
            reader.accept(new AopClassAdapter(ASM4, cw), ClassReader.SKIP_DEBUG);
            //
            byte[] code = cw.toByteArray();
            /**
            FileOutputStream fos = new FileOutputStream("D:\\workspace\\leo-test\\target\\classes\\org\\leo\\bytecode\\asm\\aop\\TestBean_Tmp.class");
            fos.write(code);
            fos.flush();
            fos.close();
            */
            
            //Class<?> exampleClass = this.defineClassPublic(name, code, 0, code.length);
            
            return this.defineClass(name, code, 0, code.length);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }
    public static void main(String[] args) throws Exception {

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        contextClassLoader.loadClass(AopClassLoader.class.getName());
        
    	AopClassLoader classLoader = new AopClassLoader(contextClassLoader);
    	
    	@SuppressWarnings("unchecked")
		Class<TestBean> testBeanClass = (Class<TestBean>) classLoader.loadClass("TestBean_Tmp");
    	
    	TestBean tb = testBeanClass.newInstance();
    	tb.halloAop();
	}
}