package session01.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import session01.bean.Lotto;

public class TestLotto {

	public static void main(String[] args) {
		// 利用 Spring 得到 Lotto號碼
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-config.xml");
		
		Lotto lotto1 = applicationContext.getBean("lotto", Lotto.class);		
		System.out.println(lotto1.getNumbers());
		Lotto lotto2 = applicationContext.getBean("lotto", Lotto.class);
		System.out.println(lotto2.getNumbers());
	}

}
