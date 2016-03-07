package org.mythink.myenum.learn;

enum Search{ HITHER,YON}

/**
 * 即使是枚举的enum类型 没有valus()还是可以通过getClass() .getEnumConstants()来获取
 * @author ydjx
 *
 */
public class UpcastEnum {

	public static void main(String[] args) {
		Search[] vals = Search.values();
		Enum e = Search.HITHER;
		for(Enum en : e.getClass().getEnumConstants()){
			System.out.println(en);
		}
	}
}

/**
 * getEnumContants()是Class的方法，可以在没enum的情况调用。下面或获取到null 报空指针异常
 */
class NoEnum{
	public static void main(String[] args) {
		Class<Integer> intClass =  Integer.class;
		System.out.println(intClass);
		try {
			for(Integer it: intClass.getEnumConstants()){
				System.out.println(it);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
