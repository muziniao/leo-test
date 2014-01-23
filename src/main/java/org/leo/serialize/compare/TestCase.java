package org.leo.serialize.compare;

import org.leo.serialize.compare.serializer.Serializer;

public class TestCase<T> {

	private Serializer<T> serializer;

	public TestCase(Serializer<T> serializer) {
		this.serializer = serializer;
	}

	public double serialize(T object, int iterations) throws Exception {
		 long start = System.nanoTime();
         for (int i = 0; i < iterations; i++) {
        	 @SuppressWarnings("unused")
        	 byte[] bytes = serializer.serialize(object);
        	 //System.out.println(bytes.length);
         }
         return iterationTime(System.nanoTime() - start, iterations);
	}
	
	public double deserialize(T object, int iterations) throws Exception {
		byte[] bytes = serializer.serialize(object);
		long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            @SuppressWarnings("unused")
			T v = serializer.deserialize(bytes);
       	 	//System.out.println(bytes.length);
        }
        return iterationTime(System.nanoTime() - start, iterations);
	}
	
	public int size(T object) throws Exception {
		byte[] bytes = serializer.serialize(object);
		if(bytes == null) return 0;
		return bytes.length;
	}

	protected static double iterationTime(long delta, int iterations) {
		return (double) delta / (double) (iterations);
	}

}
