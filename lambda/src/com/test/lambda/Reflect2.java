package com.test.lambda;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Reflect2 {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("Before loading Person class");
		Class clazz = Person.class;
		Object object = clazz.getInterfaces();
		System.out.println(clazz.getName());// Person

		for (Method m : clazz.getDeclaredMethods()) {
			System.out.println("Method name: " + m.getName());
			System.out.println("Method modifier: " + Modifier.toString(m.getModifiers()));
			if (m.getReturnType() == void.class) {
				System.out.println("Method's return type is void");
			}
			System.out.println("------------------------------------------------");
		}
		
		for (Annotation anno : clazz.getAnnotations()) {
			System.out.println(anno);
		}
		
		System.out.println("------------------------------------------------");
//		for (Constructor c : clazz.getDeclaredConstructors()) {
//			System.out.println("Constructor: " + c.getName() + ", # of parameters: " + c.getParameterTypes().length);
//		}

		Constructor<Person> constructor = clazz.getDeclaredConstructor(int.class, String.class, String.class);
		Person person = constructor.newInstance(123, "jyt0532", "mountain view");
		
		System.out.println("Result of calling func1: " + person.func1());
		Method m1 = clazz.getDeclaredMethod("func1");
		Object ret1 = m1.invoke(person);
		System.out.println("Result of invoking func1: " + ((Boolean) ret1).booleanValue());

		Method m2 = clazz.getDeclaredMethod("func2", int.class, long.class, String.class);
		Object ret2 = m2.invoke(person, 1, 2L, "abc");
		// void Method 回傳null
		System.out.println(ret2);

		Person jason = constructor.newInstance(89, "Jason", "Taoyuan");
		
		Method m3 = clazz.getDeclaredMethod("sayHello", String.class);
		m3.setAccessible(true); // 方法為private 需要設定
		m3.invoke(jason, "Jason");
		
		Method m4 = clazz.getDeclaredMethod("toString");
		System.out.println(m4.invoke(jason));
		
		Field[] fields = clazz.getDeclaredFields();
		
		for (Field f : fields) {
			System.out.println(f.getType());
		}
	}

}
