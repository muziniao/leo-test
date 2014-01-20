package org.leo.test;

import java.io.FileOutputStream;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		byte[] code = new byte[]{-54, -2, -70, -66, 0, 0, 0, 49, 0, -81, 1, 0, 12, 83, 101, 114, 105, 97, 108, 105, 122, 101, 114, 95, 49, 7, 0, 1, 1, 0, 16, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 7, 0, 3, 1, 0, 48, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 79, 98, 106, 101, 99, 116, 83, 101, 114, 105, 97, 108, 105, 122, 101, 114, 7, 0, 5, 1, 0, 6, 110, 97, 116, 117, 114, 101, 1, 0, 52, 76, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 74, 97, 118, 97, 66, 101, 97, 110, 83, 101, 114, 105, 97, 108, 105, 122, 101, 114, 59, 1, 0, 20, 110, 97, 109, 101, 95, 97, 115, 109, 95, 102, 105, 101, 108, 100, 80, 114, 101, 102, 105, 120, 1, 0, 24, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 114, 101, 102, 108, 101, 99, 116, 47, 84, 121, 112, 101, 59, 1, 0, 18, 110, 97, 109, 101, 95, 97, 115, 109, 95, 102, 105, 101, 108, 100, 84, 121, 112, 101, 1, 0, 19, 97, 103, 101, 95, 97, 115, 109, 95, 102, 105, 101, 108, 100, 80, 114, 101, 102, 105, 120, 1, 0, 17, 97, 103, 101, 95, 97, 115, 109, 95, 102, 105, 101, 108, 100, 84, 121, 112, 101, 1, 0, 6, 60, 105, 110, 105, 116, 62, 1, 0, 3, 40, 41, 86, 12, 0, 14, 0, 15, 10, 0, 4, 0, 16, 1, 0, 32, 111, 114, 103, 47, 108, 101, 111, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 47, 100, 111, 109, 97, 105, 110, 47, 83, 116, 117, 100, 101, 110, 116, 7, 0, 18, 1, 0, 7, 103, 101, 116, 78, 97, 109, 101, 8, 0, 20, 1, 0, 34, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 117, 116, 105, 108, 47, 65, 83, 77, 85, 116, 105, 108, 115, 7, 0, 22, 1, 0, 13, 103, 101, 116, 77, 101, 116, 104, 111, 100, 84, 121, 112, 101, 1, 0, 61, 40, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 67, 108, 97, 115, 115, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 41, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 114, 101, 102, 108, 101, 99, 116, 47, 84, 121, 112, 101, 59, 12, 0, 24, 0, 25, 10, 0, 23, 0, 26, 12, 0, 11, 0, 10, 9, 0, 2, 0, 28, 1, 0, 6, 103, 101, 116, 65, 103, 101, 8, 0, 30, 12, 0, 13, 0, 10, 9, 0, 2, 0, 32, 1, 0, 5, 119, 114, 105, 116, 101, 1, 0, 111, 40, 76, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 74, 83, 79, 78, 83, 101, 114, 105, 97, 108, 105, 122, 101, 114, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 114, 101, 102, 108, 101, 99, 116, 47, 84, 121, 112, 101, 59, 41, 86, 1, 0, 19, 106, 97, 118, 97, 47, 105, 111, 47, 73, 79, 69, 120, 99, 101, 112, 116, 105, 111, 110, 7, 0, 36, 1, 0, 46, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 74, 83, 79, 78, 83, 101, 114, 105, 97, 108, 105, 122, 101, 114, 7, 0, 38, 1, 0, 9, 103, 101, 116, 87, 114, 105, 116, 101, 114, 1, 0, 51, 40, 41, 76, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 83, 101, 114, 105, 97, 108, 105, 122, 101, 87, 114, 105, 116, 101, 114, 59, 12, 0, 40, 0, 41, 10, 0, 39, 0, 42, 1, 0, 49, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 83, 101, 114, 105, 97, 108, 105, 122, 101, 114, 70, 101, 97, 116, 117, 114, 101, 7, 0, 44, 1, 0, 9, 83, 111, 114, 116, 70, 105, 101, 108, 100, 1, 0, 51, 76, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 83, 101, 114, 105, 97, 108, 105, 122, 101, 114, 70, 101, 97, 116, 117, 114, 101, 59, 12, 0, 46, 0, 47, 9, 0, 45, 0, 48, 1, 0, 47, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 83, 101, 114, 105, 97, 108, 105, 122, 101, 87, 114, 105, 116, 101, 114, 7, 0, 50, 1, 0, 9, 105, 115, 69, 110, 97, 98, 108, 101, 100, 1, 0, 54, 40, 76, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 83, 101, 114, 105, 97, 108, 105, 122, 101, 114, 70, 101, 97, 116, 117, 114, 101, 59, 41, 90, 12, 0, 52, 0, 53, 10, 0, 51, 0, 54, 1, 0, 6, 119, 114, 105, 116, 101, 49, 12, 0, 56, 0, 35, 10, 0, 2, 0, 57, 1, 0, 12, 80, 114, 101, 116, 116, 121, 70, 111, 114, 109, 97, 116, 12, 0, 59, 0, 47, 9, 0, 45, 0, 60, 12, 0, 7, 0, 8, 9, 0, 2, 0, 62, 1, 0, 50, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 74, 97, 118, 97, 66, 101, 97, 110, 83, 101, 114, 105, 97, 108, 105, 122, 101, 114, 7, 0, 64, 1, 0, 20, 40, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 67, 108, 97, 115, 115, 59, 41, 86, 12, 0, 14, 0, 66, 10, 0, 65, 0, 67, 12, 0, 34, 0, 35, 10, 0, 65, 0, 69, 1, 0, 17, 99, 111, 110, 116, 97, 105, 110, 115, 82, 101, 102, 101, 114, 101, 110, 99, 101, 1, 0, 21, 40, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 41, 90, 12, 0, 71, 0, 72, 10, 0, 39, 0, 73, 1, 0, 14, 119, 114, 105, 116, 101, 82, 101, 102, 101, 114, 101, 110, 99, 101, 1, 0, 69, 40, 76, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 74, 83, 79, 78, 83, 101, 114, 105, 97, 108, 105, 122, 101, 114, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 41, 86, 12, 0, 75, 0, 76, 10, 0, 65, 0, 77, 1, 0, 14, 105, 115, 87, 114, 105, 116, 101, 65, 115, 65, 114, 114, 97, 121, 1, 0, 45, 40, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 114, 101, 102, 108, 101, 99, 116, 47, 84, 121, 112, 101, 59, 41, 90, 12, 0, 79, 0, 80, 10, 0, 39, 0, 81, 1, 0, 12, 119, 114, 105, 116, 101, 65, 115, 65, 114, 114, 97, 121, 12, 0, 83, 0, 35, 10, 0, 2, 0, 84, 1, 0, 10, 103, 101, 116, 67, 111, 110, 116, 101, 120, 116, 1, 0, 49, 40, 41, 76, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 83, 101, 114, 105, 97, 108, 67, 111, 110, 116, 101, 120, 116, 59, 12, 0, 86, 0, 87, 10, 0, 39, 0, 88, 1, 0, 10, 115, 101, 116, 67, 111, 110, 116, 101, 120, 116, 1, 0, 86, 40, 76, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 83, 101, 114, 105, 97, 108, 67, 111, 110, 116, 101, 120, 116, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 41, 86, 12, 0, 90, 0, 91, 10, 0, 39, 0, 92, 1, 0, 16, 105, 115, 87, 114, 105, 116, 101, 67, 108, 97, 115, 115, 78, 97, 109, 101, 1, 0, 45, 40, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 114, 101, 102, 108, 101, 99, 116, 47, 84, 121, 112, 101, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 41, 90, 12, 0, 94, 0, 95, 10, 0, 39, 0, 96, 1, 0, 8, 103, 101, 116, 67, 108, 97, 115, 115, 1, 0, 19, 40, 41, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 67, 108, 97, 115, 115, 59, 12, 0, 98, 0, 99, 10, 0, 4, 0, 100, 1, 0, 43, 123, 34, 64, 116, 121, 112, 101, 34, 58, 34, 111, 114, 103, 46, 108, 101, 111, 46, 115, 101, 114, 105, 97, 108, 105, 122, 101, 46, 100, 111, 109, 97, 105, 110, 46, 83, 116, 117, 100, 101, 110, 116, 34, 8, 0, 102, 1, 0, 21, 40, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 41, 86, 12, 0, 34, 0, 104, 10, 0, 51, 0, 105, 1, 0, 43, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 70, 105, 108, 116, 101, 114, 85, 116, 105, 108, 115, 7, 0, 107, 1, 0, 11, 119, 114, 105, 116, 101, 66, 101, 102, 111, 114, 101, 1, 0, 70, 40, 76, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 74, 83, 79, 78, 83, 101, 114, 105, 97, 108, 105, 122, 101, 114, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 67, 41, 67, 12, 0, 109, 0, 110, 10, 0, 108, 0, 111, 1, 0, 4, 110, 97, 109, 101, 8, 0, 113, 1, 0, 9, 97, 112, 112, 108, 121, 78, 97, 109, 101, 1, 0, 87, 40, 76, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 74, 83, 79, 78, 83, 101, 114, 105, 97, 108, 105, 122, 101, 114, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 41, 90, 12, 0, 115, 0, 116, 10, 0, 108, 0, 117, 1, 0, 20, 40, 41, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 12, 0, 20, 0, 119, 10, 0, 19, 0, 120, 1, 0, 5, 97, 112, 112, 108, 121, 1, 0, 105, 40, 76, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 74, 83, 79, 78, 83, 101, 114, 105, 97, 108, 105, 122, 101, 114, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 41, 90, 12, 0, 122, 0, 123, 10, 0, 108, 0, 124, 1, 0, 10, 112, 114, 111, 99, 101, 115, 115, 75, 101, 121, 1, 0, 122, 40, 76, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 74, 83, 79, 78, 83, 101, 114, 105, 97, 108, 105, 122, 101, 114, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 41, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 12, 0, 126, 0, 127, 10, 0, 108, 0, -128, 1, 0, 12, 112, 114, 111, 99, 101, 115, 115, 86, 97, 108, 117, 101, 1, 0, 122, 40, 76, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 74, 83, 79, 78, 83, 101, 114, 105, 97, 108, 105, 122, 101, 114, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 41, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 12, 0, -126, 0, -125, 10, 0, 108, 0, -124, 1, 0, 17, 87, 114, 105, 116, 101, 77, 97, 112, 78, 117, 108, 108, 86, 97, 108, 117, 101, 12, 0, -122, 0, 47, 9, 0, 45, 0, -121, 1, 0, 20, 119, 114, 105, 116, 101, 70, 105, 101, 108, 100, 78, 117, 108, 108, 83, 116, 114, 105, 110, 103, 1, 0, 22, 40, 67, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 41, 86, 12, 0, -119, 0, -118, 10, 0, 51, 0, -117, 1, 0, 4, 40, 67, 41, 86, 12, 0, 34, 0, -115, 10, 0, 51, 0, -114, 1, 0, 14, 119, 114, 105, 116, 101, 70, 105, 101, 108, 100, 78, 97, 109, 101, 12, 0, -112, 0, 104, 10, 0, 51, 0, -111, 1, 0, 18, 119, 114, 105, 116, 101, 87, 105, 116, 104, 70, 105, 101, 108, 100, 78, 97, 109, 101, 1, 0, 63, 40, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 114, 101, 102, 108, 101, 99, 116, 47, 84, 121, 112, 101, 59, 41, 86, 12, 0, -109, 0, -108, 10, 0, 39, 0, -107, 1, 0, 15, 119, 114, 105, 116, 101, 70, 105, 101, 108, 100, 86, 97, 108, 117, 101, 1, 0, 40, 40, 67, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 41, 86, 12, 0, -105, 0, -104, 10, 0, 51, 0, -103, 1, 0, 3, 97, 103, 101, 8, 0, -101, 1, 0, 21, 40, 41, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 73, 110, 116, 101, 103, 101, 114, 59, 12, 0, 30, 0, -99, 10, 0, 19, 0, -98, 1, 0, 20, 119, 114, 105, 116, 101, 70, 105, 101, 108, 100, 78, 117, 108, 108, 78, 117, 109, 98, 101, 114, 12, 0, -96, 0, -118, 10, 0, 51, 0, -95, 1, 0, 10, 119, 114, 105, 116, 101, 65, 102, 116, 101, 114, 12, 0, -93, 0, 110, 10, 0, 108, 0, -92, 1, 0, 50, 40, 76, 99, 111, 109, 47, 97, 108, 105, 98, 97, 98, 97, 47, 102, 97, 115, 116, 106, 115, 111, 110, 47, 115, 101, 114, 105, 97, 108, 105, 122, 101, 114, 47, 83, 101, 114, 105, 97, 108, 67, 111, 110, 116, 101, 120, 116, 59, 41, 86, 12, 0, 90, 0, -90, 10, 0, 39, 0, -89, 1, 0, 11, 119, 114, 105, 116, 101, 83, 116, 114, 105, 110, 103, 1, 0, 22, 40, 76, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 83, 116, 114, 105, 110, 103, 59, 67, 41, 86, 12, 0, -87, 0, -86, 10, 0, 51, 0, -85, 1, 0, 4, 67, 111, 100, 101, 1, 0, 10, 69, 120, 99, 101, 112, 116, 105, 111, 110, 115, 0, 33, 0, 2, 0, 4, 0, 1, 0, 6, 0, 5, 0, 2, 0, 7, 0, 8, 0, 0, 0, 1, 0, 9, 0, 10, 0, 0, 0, 1, 0, 11, 0, 10, 0, 0, 0, 1, 0, 12, 0, 10, 0, 0, 0, 1, 0, 13, 0, 10, 0, 0, 0, 4, 0, 1, 0, 14, 0, 15, 0, 1, 0, -83, 0, 0, 0, 39, 0, 4, 0, 4, 0, 0, 0, 27, 42, -73, 0, 17, 42, 18, 19, 18, 21, -72, 0, 27, -75, 0, 29, 42, 18, 19, 18, 31, -72, 0, 27, -75, 0, 33, -79, 0, 0, 0, 0, 0, 1, 0, 34, 0, 35, 0, 2, 0, -83, 0, 0, 2, 113, 0, 5, 0, 15, 0, 0, 2, 101, 43, -74, 0, 43, 58, 8, 25, 8, -78, 0, 49, -74, 0, 55, -103, 0, 13, 42, 43, 44, 45, 25, 4, -74, 0, 58, -79, 44, -64, 0, 19, 58, 9, 25, 8, -78, 0, 61, -74, 0, 55, -103, 0, 36, 42, -76, 0, 63, -57, 0, 16, 42, -69, 0, 65, 89, 18, 19, -73, 0, 68, -75, 0, 63, 42, -76, 0, 63, 43, 44, 45, 25, 4, -74, 0, 70, -79, 43, 44, -74, 0, 74, -103, 0, 33, 42, -76, 0, 63, -57, 0, 16, 42, -69, 0, 65, 89, 18, 19, -73, 0, 68, -75, 0, 63, 42, -76, 0, 63, 43, 44, -74, 0, 78, -79, 43, 44, 25, 4, -74, 0, 82, -103, 0, 13, 42, 43, 44, 45, 25, 4, -74, 0, 85, -79, 43, -74, 0, 89, 58, 10, 43, 25, 10, 44, 45, -74, 0, 93, 43, 25, 4, 44, -74, 0, 97, -103, 0, 24, 25, 4, 44, -74, 0, 101, -91, 0, 15, 25, 8, 18, 103, -74, 0, 106, 16, 44, -89, 0, 5, 16, 123, 54, 11, 43, 44, 21, 11, -72, 0, 112, 54, 11, 18, 114, 58, 5, 43, 44, 25, 5, -72, 0, 118, -103, 0, -83, 25, 9, -74, 0, 121, 58, 12, 43, 44, 25, 5, 25, 12, -72, 0, 125, -103, 0, -102, 43, 44, 25, 5, 25, 12, -72, 0, -127, 58, 5, 43, 44, 25, 5, 25, 12, 58, 6, 25, 6, -72, 0, -123, 58, 7, 25, 6, 25, 7, -91, 0, 71, 25, 7, -57, 0, 33, 25, 8, -78, 0, -120, -74, 0, 55, -103, 0, 19, 25, 8, 21, 11, 25, 5, -74, 0, -116, 16, 44, 54, 11, -89, 0, 3, -89, 0, 86, 25, 8, 21, 11, -74, 0, -113, 25, 8, 25, 5, -74, 0, -110, 43, 25, 7, 25, 5, 42, -76, 0, 29, -74, 0, -106, 16, 44, 54, 11, -89, 0, 53, 25, 12, -57, 0, 33, 25, 8, -78, 0, -120, -74, 0, 55, -103, 0, 19, 25, 8, 21, 11, 25, 5, -74, 0, -116, 16, 44, 54, 11, -89, 0, 3, -89, 0, 18, 25, 8, 21, 11, 25, 5, 25, 12, -74, 0, -102, 16, 44, 54, 11, 18, -100, 58, 5, 43, 44, 25, 5, -72, 0, 118, -103, 0, -68, 25, 9, -74, 0, -97, 58, 13, 43, 44, 25, 5, 25, 13, -72, 0, 125, -103, 0, -87, 43, 44, 25, 5, 25, 13, -72, 0, -127, 58, 5, 43, 44, 25, 5, 25, 13, 58, 6, 25, 6, -72, 0, -123, 58, 7, 25, 6, 25, 7, -91, 0, 71, 25, 7, -57, 0, 33, 25, 8, -78, 0, -120, -74, 0, 55, -103, 0, 19, 25, 8, 21, 11, 25, 5, -74, 0, -94, 16, 44, 54, 11, -89, 0, 3, -89, 0, 101, 25, 8, 21, 11, -74, 0, -113, 25, 8, 25, 5, -74, 0, -110, 43, 25, 7, 25, 5, 42, -76, 0, 33, -74, 0, -106, 16, 44, 54, 11, -89, 0, 68, 25, 7, -57, 0, 33, 25, 8, -78, 0, -120, -74, 0, 55, -103, 0, 19, 25, 8, 21, 11, 25, 5, -74, 0, -94, 16, 44, 54, 11, -89, 0, 3, -89, 0, 33, 25, 8, 21, 11, -74, 0, -113, 25, 8, 25, 5, -74, 0, -110, 43, 25, 7, 25, 5, 42, -76, 0, 33, -74, 0, -106, 16, 44, 54, 11, 43, 44, 21, 11, -72, 0, -91, 54, 11, 21, 11, 16, 123, -96, 0, 10, 25, 8, 16, 123, -74, 0, -113, 25, 8, 16, 125, -74, 0, -113, 43, 25, 10, -74, 0, -88, -79, 0, 0, 0, 0, 0, -82, 0, 0, 0, 4, 0, 1, 0, 37, 0, 1, 0, 56, 0, 35, 0, 2, 0, -83, 0, 0, 2, 92, 0, 5, 0, 15, 0, 0, 2, 80, 43, -74, 0, 43, 58, 8, 44, -64, 0, 19, 58, 9, 25, 8, -78, 0, 61, -74, 0, 55, -103, 0, 36, 42, -76, 0, 63, -57, 0, 16, 42, -69, 0, 65, 89, 18, 19, -73, 0, 68, -75, 0, 63, 42, -76, 0, 63, 43, 44, 45, 25, 4, -74, 0, 70, -79, 43, 44, -74, 0, 74, -103, 0, 33, 42, -76, 0, 63, -57, 0, 16, 42, -69, 0, 65, 89, 18, 19, -73, 0, 68, -75, 0, 63, 42, -76, 0, 63, 43, 44, -74, 0, 78, -79, 43, 44, 25, 4, -74, 0, 82, -103, 0, 13, 42, 43, 44, 45, 25, 4, -74, 0, 85, -79, 43, -74, 0, 89, 58, 10, 43, 25, 10, 44, 45, -74, 0, 93, 43, 25, 4, 44, -74, 0, 97, -103, 0, 24, 25, 4, 44, -74, 0, 101, -91, 0, 15, 25, 8, 18, 103, -74, 0, 106, 16, 44, -89, 0, 5, 16, 123, 54, 11, 43, 44, 21, 11, -72, 0, 112, 54, 11, 18, -100, 58, 5, 43, 44, 25, 5, -72, 0, 118, -103, 0, -68, 25, 9, -74, 0, -97, 58, 12, 43, 44, 25, 5, 25, 12, -72, 0, 125, -103, 0, -87, 43, 44, 25, 5, 25, 12, -72, 0, -127, 58, 5, 43, 44, 25, 5, 25, 12, 58, 6, 25, 6, -72, 0, -123, 58, 7, 25, 6, 25, 7, -91, 0, 71, 25, 7, -57, 0, 33, 25, 8, -78, 0, -120, -74, 0, 55, -103, 0, 19, 25, 8, 21, 11, 25, 5, -74, 0, -94, 16, 44, 54, 11, -89, 0, 3, -89, 0, 101, 25, 8, 21, 11, -74, 0, -113, 25, 8, 25, 5, -74, 0, -110, 43, 25, 7, 25, 5, 42, -76, 0, 33, -74, 0, -106, 16, 44, 54, 11, -89, 0, 68, 25, 7, -57, 0, 33, 25, 8, -78, 0, -120, -74, 0, 55, -103, 0, 19, 25, 8, 21, 11, 25, 5, -74, 0, -94, 16, 44, 54, 11, -89, 0, 3, -89, 0, 33, 25, 8, 21, 11, -74, 0, -113, 25, 8, 25, 5, -74, 0, -110, 43, 25, 7, 25, 5, 42, -76, 0, 33, -74, 0, -106, 16, 44, 54, 11, 18, 114, 58, 5, 43, 44, 25, 5, -72, 0, 118, -103, 0, -83, 25, 9, -74, 0, 121, 58, 13, 43, 44, 25, 5, 25, 13, -72, 0, 125, -103, 0, -102, 43, 44, 25, 5, 25, 13, -72, 0, -127, 58, 5, 43, 44, 25, 5, 25, 13, 58, 6, 25, 6, -72, 0, -123, 58, 7, 25, 6, 25, 7, -91, 0, 71, 25, 7, -57, 0, 33, 25, 8, -78, 0, -120, -74, 0, 55, -103, 0, 19, 25, 8, 21, 11, 25, 5, -74, 0, -116, 16, 44, 54, 11, -89, 0, 3, -89, 0, 86, 25, 8, 21, 11, -74, 0, -113, 25, 8, 25, 5, -74, 0, -110, 43, 25, 7, 25, 5, 42, -76, 0, 29, -74, 0, -106, 16, 44, 54, 11, -89, 0, 53, 25, 13, -57, 0, 33, 25, 8, -78, 0, -120, -74, 0, 55, -103, 0, 19, 25, 8, 21, 11, 25, 5, -74, 0, -116, 16, 44, 54, 11, -89, 0, 3, -89, 0, 18, 25, 8, 21, 11, 25, 5, 25, 13, -74, 0, -102, 16, 44, 54, 11, 43, 44, 21, 11, -72, 0, -91, 54, 11, 21, 11, 16, 123, -96, 0, 10, 25, 8, 16, 123, -74, 0, -113, 25, 8, 16, 125, -74, 0, -113, 43, 25, 10, -74, 0, -88, -79, 0, 0, 0, 0, 0, -82, 0, 0, 0, 4, 0, 1, 0, 37, 0, 1, 0, 83, 0, 35, 0, 2, 0, -83, 0, 0, 0, 74, 0, 5, 0, 11, 0, 0, 0, 62, 43, -74, 0, 43, 58, 8, 44, -64, 0, 19, 58, 9, 25, 8, 16, 91, -74, 0, -113, 18, -100, 58, 5, 43, 25, 9, -74, 0, -97, 25, 5, 42, -76, 0, 33, -74, 0, -106, 25, 8, 16, 44, -74, 0, -113, 18, 114, 58, 5, 25, 8, 25, 9, -74, 0, 121, 16, 93, -74, 0, -84, -79, 0, 0, 0, 0, 0, -82, 0, 0, 0, 4, 0, 1, 0, 37, 0, 0};
		
		FileOutputStream fos = new FileOutputStream("D:\\Strudent_2.class");
        fos.write(code);
        fos.flush();
        fos.close();
	}

}
