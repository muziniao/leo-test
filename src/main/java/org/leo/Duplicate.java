package org.leo;

import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class Duplicate {

	private Duplicate() {}

	public static void checkDuplicate(Class cls) {
		checkDuplicate(cls.getName().replace('.', '/') + ".class");
	}

	public static void checkDuplicate(String path) {
		try {
			System.out.println(path);
			// 在ClassPath搜文件
			Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(path);
			Set<String> files = new HashSet<String>();
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				if (url != null) {
					String file = url.getFile();
					if (file != null && file.length() > 0) {
						files.add(file);
					}
				}
			}
			System.out.println(files.size());
			// 如果有多个，就表示重复
			if (files.size() > 1) {
				System.err.println("Duplicate class " + path + " in " + files.size() + " jar " + files);
			}
		} catch (Throwable e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Duplicate.checkDuplicate(Enumeration.class);
	}
}
