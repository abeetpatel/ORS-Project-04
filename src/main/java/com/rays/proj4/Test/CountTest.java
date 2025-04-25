package com.rays.proj4.Test;

public class CountTest {

	public static void main(String[] args) {
		/*
		 * String[] stringArray = { "cvbn", "bvc", "bvc", "vcx" }; int count = 0; for
		 * (char c = 'a'; c <= 'z'; c++) {
		 * 
		 * for (int i = 0; i < stringArray.length; i++) {
		 * 
		 * for (int j = 0; j < stringArray[i].length(); j++) {
		 * 
		 * if (stringArray[i].charAt(j) == c) { count++; }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * if (count != 0) {
		 * 
		 * System.out.println(c + "="+ count);
		 * 
		 * count = 0;
		 * 
		 * }
		 * 
		 * }
		 */

		StringBuffer sb = new StringBuffer("jayati");

		sb.append("hello");

		System.out.println(sb.capacity());

		sb.append("java is fabourite language");

		System.out.println(sb.capacity());

		sb.append("anshhhhhhhhhhhhhhuhhhhhlp");

		System.out.println(sb.capacity());
	}

}
