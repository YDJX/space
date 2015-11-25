package org.mythink.clazzloader.learn;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.xml.stream.events.StartDocument;

public class ReflectionTest {

	
	public static void main(String[] args){
		Class<MyObject> aclass = MyObject.class;
		System.out.println("类全名"+aclass.getName());
		
		//method
		Method[] methods = aclass.getMethods();
		for(Method m : methods){
			if(isGetter(m)){				
				System.out.println(" method getter name:"+m.getName()+", return type:"+m.getReturnType().getName());
			}
			if(isSetter(m)){
				System.out.println(" method is setter :"+m.getName()+",param count:"+m.getParameterCount());
			}
		}
		
		int modiflag = aclass.getModifiers();
		boolean isfinalclass = Modifier.isFinal(modiflag);
		System.out.println("aclass "+(isfinalclass?" is ":" is not ")+" final.");
		
		Package pkg = aclass.getPackage();
		System.out.println("package is :"+pkg.getName());
		
		Class<?> superclass = aclass.getSuperclass();
		System.out.println("super class is :"+ superclass.getName());
		
		Class<?>[] interfaces = aclass.getInterfaces();
		for(Class<?> c : interfaces){
			System.out.println("on of implements interface :"+c.getName());
		}
		
		Constructor<?>[] cs = aclass.getConstructors();
		for(Constructor c : cs){
			int i =0;
			for(Class ty :c.getParameterTypes()){
				System.out.println(++i +" param is "+ty.getTypeName());
			}
		}
		
		try {
			Constructor<MyObject> constructor = aclass.getConstructor(String.class);
			MyObject myobj= constructor.newInstance(" newMyObje");
			System.out.println(myobj.getName());
			Field field = aclass.getDeclaredField("name"); //getField("str") can only get public
			if(Modifier.isPrivate(field.getModifiers())){
				field.setAccessible(true);
			}
			System.out.println("field get from instant:"+field.get(myobj));
			field.set(myobj, "new MyObject"); //if modifer is public static ,myobj can be null
			System.out.println(myobj.getName());
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Annotation[] anns = aclass.getAnnotations();
		for(Annotation a : anns){
			System.out.println(" have annotation : " + a.toString());
		}
		
		String className = "org.mythink.clazzloader.learn.MyObject";
		Class clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("running time get name:"+clazz.getName());
		try {
			Method m = clazz.getDeclaredMethod("trans", String.class);
			Object obj =m.invoke(aclass.newInstance(), " sunshine.");
			System.out.println(obj.toString());
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean isGetter(Method method){
		if(!method.getName().startsWith("get")) return false;
		if(method.getParameterTypes().length!=0) return false;
		if(void.class.equals(method.getReturnType())) return false;
		return true;
	}
	
	public static boolean isSetter(Method method){
		if(!method.getName().startsWith("set")) return false;
		if(method.getParameterTypes().length!=1) return false;
		return true;
	}
}
