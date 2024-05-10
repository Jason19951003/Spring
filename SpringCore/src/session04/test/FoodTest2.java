package session04.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import session04.bean.ComboMeal;
import session04.config.FoodConfig;

public class FoodTest2 {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(FoodConfig.class);
		ComboMeal comboMeal = applicationContext.getBean("comboMeal", ComboMeal.class);
		System.out.println(comboMeal.serveMeal());
	}

}
