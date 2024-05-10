package session01.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import session01.bean.Hello;

public class TestHello {

	public static void main(String[] args) {
//		Hello hello = new Hello();
//		System.out.println(hello.getToday());
		
		// Spring: 利用IoC技術來建立物件
		// 配置文件: conf/beans-config.xml
		// Spring 會根據配置文件的設定來自動建立 bean實體
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-config.xml");
		
//		Hello hello1 = applicationContext.getBean(Hello.class);
//		System.out.println(hello1.getToday());
//		
//		Hello hello2 = applicationContext.getBean(Hello.class);
//		System.out.println(hello2.getToday());
//		
//		System.out.println(hello1 == hello2);
		
		Hello hello1 = applicationContext.getBean("hello1", Hello.class);
		Hello hello2 = applicationContext.getBean("hello2", Hello.class);
		
		System.out.println(hello1.getToday());
		System.out.println(hello2.getToday());
		System.out.println(hello1 == hello2);
		
	}

}
