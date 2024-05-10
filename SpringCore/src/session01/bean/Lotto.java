package session01.bean;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Lotto {
	private Set<Integer> numbers;
	
	public Lotto() {
		System.out.println("Lotto 建構子");
		Random random = new Random();
		// 自動產生 1~39 不重複的5個號碼
		numbers = new LinkedHashSet<>();
		while (numbers.size() < 5) {
			int number = random.nextInt(39)+1;
			numbers.add(number); // 自動去除重複
		}
	}
	
	public Set<Integer> getNumbers() {
		return this.numbers;
	}
}
