package session04.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"session04.bean"})
public class FoodConfig {
	
//	@Bean
//	public FoodItem burger() {
//		return new Burger();
//	}
//	
//	@Bean
//	public FoodItem fries() {
//		return new Fries();
//	}
//	
//	@Bean
//	public FoodItem coke() {
//		return new Coke();
//	}
//	
//	@Bean
//	public ComboMeal comboMeal() {
//		return new ComboMeal(burger(), fries(), coke());
//	}
}
