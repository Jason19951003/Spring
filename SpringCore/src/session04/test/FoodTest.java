package session04.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import session04.bean.ComboMeal;

public class FoodTest {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("food-config.xml");
		
		ComboMeal comboMeal = applicationContext.getBean("comboMeal", ComboMeal.class);
		System.out.println(comboMeal.serveMeal());
	}

}
