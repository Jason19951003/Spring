package session06.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import session06.aop.Computer;
import session06.aop.ComputerImpl;

// --add-opens java.base/java.lang=ALL-UNNAMED
public class TestComputerAOP {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop-config.xml");
		
		Computer computer = applicationContext.getBean("computerImpl", ComputerImpl.class);
		
		//System.out.println(computer.add(10, 20));
		//System.out.println(computer.sub(10, 20));
		//System.out.println(computer.mul(10, 20));
		System.out.println(computer.div(30, 3));
	}

}
