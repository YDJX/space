package org.mythink.clazzloader.learn;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 所有被加载到Java应用中的类都以类的全名（包名 + 类名）作为一个唯一标识来让ClassLoader实例来加载。这意味
 * 着，类MyObject被类加载器A加载，如果类加载器B又加载了MyObject类，那么两个加载器加载出来的类是不同的
 * 
 * 如果myClassReloadingFactory工厂对象使用不同的类加载器重载MyObject类，你不能把重载的MyObject类的实例转换（cast）到类型为MyObject的对象变量。一旦MyObject类分别被两个类加载器加载，那么它就会被认为是两个不同的类，尽管它们的类的全名是完全一样的。你如果尝试把这两个类的实例进行转换就会报ClassCastException。
MyObject object = (MyObject)
    myClassReloadingFactory.newInstance("com.jenkov.MyObject");
你可以解决这个限制，不过你需要从以下两个方面修改你的代码：
1、标记这个变量类型为一个接口，然后只重载这个接口的实现类。
2、标记这个变量类型为一个超类，然后只重载这个超类的子类
 * @author ydjx
 *
 */
public class MyClassLoader extends ClassLoader {

	public MyClassLoader(ClassLoader parent) {
		super(parent);
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if(!"org.mythink.clazzloader.learn.MyObject".equals(name)){
			return super.loadClass(name);
		}
		try {
			String url = "file:/home/ydjx/workspace/NewJavaSpec/bin/org/mythink/clazzloader/learn/MyObject.class";
			URL myUrl = new URL(url);
			URLConnection conn = myUrl.openConnection();
			InputStream in = conn.getInputStream();
			ByteArrayOutputStream buffer  = new ByteArrayOutputStream();
			int data = in.read();
			while(data>=0){
				buffer.write(data);
				data = in.read();
			}
			in.close();
			byte[] classData = buffer.toByteArray();
			return defineClass("org.mythink.clazzloader.learn.MyObject", classData, 0, classData.length);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
		MyClassLoader classLoader = new MyClassLoader(parentClassLoader);
		Class<?> myobjectClass = classLoader.loadClass("org.mythink.clazzloader.learn.MyObject");
		MyObjectInterface object1 = (MyObjectInterface) myobjectClass.newInstance();
		MyObjectSuperclass object2 = (MyObjectSuperclass) myobjectClass.newInstance();
		System.out.println(object1.trans("123"));
		System.out.println(object2.callme());
		try {
			Thread.sleep(15000);// go to modify class 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//create new class loader so classes can be reloaded;
		classLoader = new MyClassLoader(parentClassLoader);
		myobjectClass = classLoader.loadClass("org.mythink.clazzloader.learn.MyObject");
		object1 = (MyObjectInterface)myobjectClass.newInstance();
		object2 = (MyObjectSuperclass)myobjectClass.newInstance();
		System.out.println(object1.trans("fai"));
		System.out.println(object2.callme());
	}
}

