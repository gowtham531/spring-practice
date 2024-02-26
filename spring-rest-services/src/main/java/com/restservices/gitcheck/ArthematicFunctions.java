package com.restservices.gitcheck;

public class ArthematicFunctions {

	public static void main(String[] args) {
		Add add = new Add();
		int result = add.sun(10, 20);
		System.out.println("sum is::"+result);
        System.out.println("Done");
        int mul=add.mul(2, 3);
        System.out.println("mul is::"+mul);
	}

}
