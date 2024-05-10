package session04.bean;

import org.springframework.stereotype.Component;

@Component("fries")
public class Fries implements FoodItem {

	@Override
	public String serve() {
		return "麥當勞薯條";
	}

}
