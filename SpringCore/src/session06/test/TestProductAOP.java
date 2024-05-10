package session06.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import session06.aop.Product;

// --add-opens java.base/java.lang=ALL-UNNAMED
public class TestProductAOP {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop-config.xml");
		Product product = applicationContext.getBean("product", Product.class);
		product.setName("IPhone20");
		product.setPrice(800.0);
		
		System.out.println(product.getName());
		System.out.println(product.getPrice());
	}

}
