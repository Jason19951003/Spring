package com.test.lambda;

public class Reflect {

	public static void main(String[] args) throws ClassNotFoundException {
		Class clazz1 = Person.class;
		System.out.println("class name : " + clazz1.getName());
		int[] a = {1,2,3};
		double[] b = {0.1,0.2,0.3};
		
		Class clazz2 = a.getClass();
		System.out.println(clazz2.getName());
		
		Class clazz3 = b.getClass();
		System.out.println(clazz3.getName());
		
		Class clazz4 = Class.forName("java.lang.String");
		System.out.println(clazz4.getName());
		
		String[] aaa = {""};
		Class claszz5 = aaa.getClass();
		System.out.println(claszz5.getName());
		
		
//		Class c = Class.forName("com.test.lambda.Person");
//		
//		Object obj = c.getInterfaces();
//		// 完整路徑
//		System.out.println("class name : " + c.getCanonicalName());
//		// 類別名稱
//		System.out.println("class name : " + c.getSimpleName());
//		
//		Method[] methods = c.getDeclaredMethods();
//		
//		for (int i = 0; i < methods.length; i++) {
//			System.out.println("method" + i + " : " + methods[i].getName());
//		}
//		
//		Field[] field = c.getDeclaredFields();
//		
//		for (int i = 0; i < field.length; i++) {
//			System.out.println("field" + i + " : " + field[i].getName());
//		}
		
	}

}
