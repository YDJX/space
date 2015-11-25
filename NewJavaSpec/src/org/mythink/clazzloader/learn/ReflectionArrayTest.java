package org.mythink.clazzloader.learn;

import java.lang.reflect.Array;

public class ReflectionArrayTest {

	public static void main(String[] args){
		test();
		test1();
		test2();
	}
	
	public static void test(){
		int[] intArray = (int[])Array.newInstance(int.class, 3);
		Array.setInt(intArray, 0, 123);
		Array.setInt(intArray, 1, 456);
		Array.setInt(intArray, 2, 789);
		
		System.out.println(Array.getInt(intArray, 0));
		System.out.println(Array.getInt(intArray, 1));
		System.out.println(Array.getInt(intArray, 2));
		
	}
	
	//failded
	public static void test1(){
		Class stringArrayClass = String[].class;
		System.out.println(stringArrayClass.isPrimitive()+" ,"+stringArrayClass.getName());
//		String[] strAry = null;
//		try {
//			strAry = (String[]) stringArrayClass.newInstance();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}
//		System.out.println(strAry.length);
	}
	
	public static void test2(){
		try {
			Class te = int[].class;
			System.out.println("int[] :"+te.getName());
			Class intArray = Class.forName("[I");
			System.out.println(intArray.getName() + " , is array:"+intArray.isArray());
			String className = "[Ljava.lang.String;";
			Class strclas = Class.forName(className);
			System.out.println(strclas.getComponentType()); //get array element type
			String[] intAry = (String[]) Array.newInstance(strclas.getComponentType(), 3);
			Array.set(intAry, 0, "abc123");
			Array.set(intAry, 1, "abc456");
			Array.set(intAry, 2, "abc789");
			
			System.out.println(Array.get(intAry, 0));
			System.out.println(Array.get(intAry, 1));
			System.out.println(Array.get(intAry, 2));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
