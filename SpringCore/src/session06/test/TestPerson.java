package session06.test;

import session06.proxy.Man;
import session06.proxy.Person;
import session06.proxy.PersonProxy;
import session06.proxy.Women;

public class TestPerson {

	public static void main(String[] args) {
		Person man =  new PersonProxy(new Man());
		man.work();
		
		Person women = new PersonProxy(new Women());
		women.work();
	}

}
