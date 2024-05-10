package com.test.lambda;

public class Person {
	private int id;
	private String name;
	private String address;

	Person() {

	}

	Person(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public boolean func1() {
		return true;
	}

	protected static final void func2(int arg1, long arg2, String arg3) {
		System.out.println(arg3 + arg2 + arg1);
	}
	
	public void sayHello(String name) {
		System.out.println("Hello, My name is " + name);
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
}
