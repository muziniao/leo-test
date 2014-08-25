package org.leo.nio;

import java.nio.ByteOrder;

import sun.misc.Unsafe;

public class ByteOrderTest {

	public static void main(String[] args) {
		System.out.println(ByteOrder.nativeOrder().toString());
		//int ps = Bits.pageSize();
		final Unsafe unsafe = Unsafe.getUnsafe();
		int pageSize = unsafe.pageSize();
		System.out.println(pageSize);
	}

}
