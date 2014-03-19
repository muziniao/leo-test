package org.leo.serialize.compare;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.leo.serialize.compare.serializer.Serializer;

public class TestCase2<T> {

	private Serializer<T> serializer;

	private List<T> objectList;

	private int iterations;

	public TestCase2(Serializer<T> serializer, List<T> objectList,int iterations) {
		this.serializer = serializer;
		this.objectList = objectList;
		this.iterations = iterations;
	}

	public double serialize() throws Exception {
		long start = System.nanoTime();
		for (int i = 0; i < iterations; i++) {
			T object = objectList.get(i);
			@SuppressWarnings("unused")
			byte[] bytes = serializer.serialize(object);
		}
		return iterationTime(System.nanoTime() - start, iterations);
	}

	public double deserialize() throws Exception {
		List<byte[]> objectByteList = new ArrayList<byte[]>();
		for (int i = 0; i < iterations; i++) {
			T object = objectList.get(i);
			byte[] bytes = serializer.serialize(object);
			objectByteList.add(bytes);
		}

		long start = System.nanoTime();
		for (int i = 0; i < iterations; i++) {
			byte[] bytes = objectByteList.get(i);
			@SuppressWarnings("unused")
			T v = serializer.deserialize(bytes);
		}
		return iterationTime(System.nanoTime() - start, iterations);
	}

	public int size() throws Exception {
		byte[] bytes = serializer.serialize(objectList.get(0));
		if (bytes == null)
			return 0;
		FileUtils.writeByteArrayToFile(new File("D:\\Temp\\serializer-" + serializer.getName() + ".out"), bytes);
		return bytes.length;
	}

	protected static double iterationTime(long delta, int iterations) {
		return (double) delta / (double) (iterations);
	}

}
