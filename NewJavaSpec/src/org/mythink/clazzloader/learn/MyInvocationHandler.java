package org.mythink.clazzloader.learn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("execute before");
		Object obj = method.invoke(new MyObject(), args);
		System.out.println(method.getReturnType()+":"+obj);
		
		System.out.println("execute after");
		return null;
	}

	public static void main(String[] args){
		InvocationHandler handler = new MyInvocationHandler();
		MyObjectInterface proxy = (MyObjectInterface)Proxy.newProxyInstance(MyInvocationHandler.class.getClassLoader(), new Class[]{MyObjectInterface.class}, handler);
		String abc = proxy.trans("NIHaO");
		System.out.println(abc);
		System.out.println(proxy.life("ohohoh"));
	}
}
