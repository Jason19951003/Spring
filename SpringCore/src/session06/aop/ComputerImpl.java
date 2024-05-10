package session06.aop;

import org.springframework.stereotype.Component;

@Component
public class ComputerImpl implements Computer {

	@Override
	public Integer add(Integer x, Integer y) {
		System.out.println("加法!");
		return x+y;
	}

	@Override
	public Integer sub(Integer x, Integer y) {
		System.out.println("減法!");
		return x-y;
	}

	@Override
	public Integer mul(Integer x, Integer y) {
		System.out.println("乘法!");
		return x*y;
	}

	@Override
	public Integer div(Integer x, Integer y) {
		System.out.println("除法!");
		return x/y;
	}

}
