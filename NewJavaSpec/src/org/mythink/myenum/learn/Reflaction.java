package org.mythink.myenum.learn;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

enum Explore { HERE,THERE}

public class Reflaction {

	public static Set<String> analyze(Class<?> enumClass){
		System.out.println("-------Analyzing "+ enumClass + "-----");
		System.out.println("Interfaces:");
		for(Type t :enumClass.getGenericInterfaces()){
			System.out.println(t);
		}
		System.out.println("Base "+ enumClass.getSuperclass());
		System.out.println("methods: ");
		Set<String> methods = new TreeSet();
		for(Method m : enumClass.getMethods()){
			methods.add(m.getName());
		}
		for(String m : methods){
			System.out.println(m);
		}
		return methods;
	}
	
	public static void main(String[] args) {
		Set<String> explorMethods = analyze(Explore.class);
		Set<String> enumMethods = analyze(Enum.class);
		System.out.println("Explore.containsAll(Enum)? "+explorMethods.containsAll(enumMethods));
		System.out.println("Explore.removeAll(Enum)? "+ explorMethods.removeAll(enumMethods));
		for(String m :explorMethods){
			System.out.println(m);
			
		}
		
	}
}
