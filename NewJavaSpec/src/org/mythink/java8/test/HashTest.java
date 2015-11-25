package org.mythink.java8.test;

public class HashTest {

	
	static int hash(int h){
		System.out.println("begin h is :"+Integer.toBinaryString(h));
		h^=(h>>>20)^(h>>>12);
		System.out.println("now h is :"+Integer.toBinaryString(h));
		return h^(h>>>7)^(h>>>4);
	}
	
	public static void main(String[] args){
		int res = HashTest.hash(23);
		System.out.println(res+",binary is :"+Integer.toBinaryString(res));
		int a = 14;
		a^= a>>>20;
		System.out.println(a);
		int b = Integer.bitCount(a);
		System.out.println(b);
		String bstr = Integer.toBinaryString(b);
		System.out.println(bstr);
	}
}
