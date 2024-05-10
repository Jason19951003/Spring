package session05.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import session05.service.GuestBookService;

public class GuestBookTest {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("guestbook-config.xml");
		
		GuestBookService guestBookService = applicationContext.getBean("guestBookServiceImpl", GuestBookService.class);
		guestBookService.queryAll().forEach(gb -> System.out.printf("%d %s %s %n", gb.getId(), gb.getUserName(), gb.getContent()));
	
		guestBookService.add("Amy", "HelloAmy");
		
		System.out.println("=================================");
		
		guestBookService.queryAll().forEach(gb -> System.out.printf("%d %s %s %n", gb.getId(), gb.getUserName(), gb.getContent()));
	}

}
