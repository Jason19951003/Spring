package session02.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import session02.bean.BeverageMachine;

//執行時請加入 VM 參數: --add-opens java.base/java.lang=ALL-UNNAMED
//整體來說，這行命令的意思是："在運行時，允許所有未命名的模組訪問 java.base 模組中的 java.lang 包"。
//這是為了解決一些框架，如 Spring，在需要訪問這些封裝的API時遇到的反射問題。
public class TestBeverage2 {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-config2.xml");
		BeverageMachine machine1 = applicationContext.getBean("coffeeBeverageMachine", BeverageMachine.class);
		System.out.println(machine1.serveBeverage());
	}

}
