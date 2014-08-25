package org.leo.java.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

public class CollectionUtilsTest {

	public static void main(String[] args) {
		//String[] arrayA = new String[] { "1", "2", "3", "3", "4", "5" };
		//String[] arrayB = new String[] { "3", "4", "4", "5", "6", "7" };
		
		String[] arrayA = new String[] { "1", "2", "3", "4"};
		String[] arrayB = new String[] { "1", "2", "3" };

		List a = Arrays.asList( arrayA );
		List b = Arrays.asList( arrayB );

		List union = (List)CollectionUtils.union( a, b );
		List intersection = (List)CollectionUtils.intersection( a, b );
		List disjunction = (List)CollectionUtils.disjunction( a, b );
		List subtract = (List)CollectionUtils.subtract( a, b );

		Collections.sort(union);
		Collections.sort(intersection);
		Collections.sort(disjunction);
		Collections.sort(subtract);


		System.out.println( "A: " + ArrayUtils.toString( a.toArray( ) ) );
		System.out.println( "B: " + ArrayUtils.toString( b.toArray( ) ) );
		System.out.println( "Union: " + ArrayUtils.toString( union.toArray( ) ) );
		System.out.println( "Intersection: " + ArrayUtils.toString( intersection.toArray( ) ) );
		System.out.println( "Disjunction: " + ArrayUtils.toString( disjunction.toArray( ) ) );
		System.out.println( "Subtract: " + ArrayUtils.toString( subtract.toArray( ) ) );

	}

}
