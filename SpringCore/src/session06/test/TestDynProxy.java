package session06.test;

import session06.dyn_proxy.Calc;
import session06.dyn_proxy.CalcImpl;
import session06.dyn_proxy.DynProxy;
import session06.proxy.Man;
import session06.proxy.Person;
import session06.proxy.Women;

public class TestDynProxy {

	public static void main(String[] args) {
		Calc calc = (Calc) new DynProxy(new CalcImpl()).getProxy();
		
		calc.add(20, 30);
		calc.div(50, 5);
		
		Person man = (Person)new DynProxy(new Man()).getProxy();
		man.work();
		
		Person women = (Person)new DynProxy(new Women()).getProxy();
		women.work();
	}

}
