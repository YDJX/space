package org.mythink.clazzloader.learn;

import java.lang.reflect.Method;

public class MainClass {

	/**
	 * dynamic class loader
	 * @param args
	 */
	public static void main(String[] args){
		ClassLoader classloader = MainClass.class.getClassLoader();
		try {
			Class<?> aclass = classloader.loadClass("org.mythink.clazzloader.learn.MyClass");
			System.out.println(aclass.getName());
			for(Method m :aclass.getMethods()){
				System.out.println("method name:"+m.getName());
				System.out.println("method params:"+m.getParameterTypes());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}


class MyClass {
	private String name;
	private String method;
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getMethod() {
		return method;
	}
	public final void setMethod(String method) {
		this.method = method;
	}
	
}