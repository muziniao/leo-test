package org.leo.test;

public class RuntimeTest {

	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
		int processors = runtime.availableProcessors();
		System.out.println("the number of processors available to the Java virtual machine : " + processors);

		
		long totalMemory = runtime.totalMemory();
		System.out.println("the total amount of memory in the Java virtual machine : " + totalMemory);
		
		long freeMemory = runtime.freeMemory();
		System.out.println("the amount of free memory in the Java Virtual Machine : " + freeMemory);
		
		long maxMemory = runtime.maxMemory();
		System.out.println("the maximum amount of memory that the Java virtual machine will attempt to use : " + maxMemory);
	}

}
