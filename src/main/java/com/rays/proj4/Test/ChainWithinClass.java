package com.rays.proj4.Test;

public class ChainWithinClass {
	ChainWithinClass() {
		System.out.println("\nThis is the no-arg constructor.");
	}

	ChainWithinClass(int y) {
		this();
		int var1 = y;
		System.out.println("You passed one argument: " + var1);
	}

	ChainWithinClass(int a, int b) {
		this(2);
		int var2 = a;
		int var3 = b;
		System.out.println("You passed two arguments: " + var2 + " and " + var3);
	}

	public static void main(String[] args) {
		ChainWithinClass chainObj = new ChainWithinClass(2, 4);
	}
}
