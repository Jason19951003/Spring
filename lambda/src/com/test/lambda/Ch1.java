package com.test.lambda;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ch1 {

	public static void main(String[] args) {
//		File file = new File("./src/com/test/lambda");
//		String[] names = file.list((dir, name) -> name.endsWith(".java"));
//		System.out.println(Arrays.asList(names));
//		Runnable r = () -> System.out.println("Runnable~");
//		new Thread(r).run();
		Consumer<Integer> print = System.out::println;
//		Stream.of(3, 1, 4, 5, 9).forEach(print);

//		Stream.of("this", "is", "a", "stream").map(x->x.length()).forEach(print);

//		List<String> names = Arrays.asList("Grace Hopper", "Barbara Liskov", "Ada Lovelace", "Karen Spärck Jones");

//		List<Person> persons = names.stream().map(Person::new).collect(Collectors.toList());
//		System.out.println(persons);
//		
//		Person before = new Person("Grace Hopper");
//		List<Person> people = Stream.of(before).collect(Collectors.toList());
//		Person after = people.get(0);
//		System.out.println(before == after);
//		before.setName("Grace Murray Hopper");
//		System.out.println("Grace Murray Hopper".equals(after.getName()));
//		System.out.println(before.getName());

//		List<Person> persons = names.stream().map(name->name.split(" ")).map(Person::new).collect(Collectors.toList());
//		System.out.println("Varargs ctor, names=" + persons);

//		List<Integer> nums = new ArrayList<>();
//		nums.add(-3);
//		nums.add(1);
//		nums.add(4);
//		nums.add(-1);
//		nums.add(5);
//		nums.add(9);
//		boolean removed = nums.removeIf(n -> n <= 0);
//		System.out.println("Elements were " + (removed ? "" : "NOT") + " removed");
//		nums.forEach(System.out::println);
//
//		Supplier s = Math::random;
//		System.out.println(s.get());

		List<String> names = Arrays.asList("Mal", "Wash", "Kaylee", "Inara", "Zoë", "Jayne", "Simon", "River",
				"Shepherd Book");
		Optional<String> first = names.stream().filter(name -> name.startsWith("C")).findFirst();
		System.out.println(first);
		System.out.println(first.orElse("None"));

		System.out.println(
				first.orElse(String.format("No result found in %s", names.stream().collect(Collectors.joining(", ")))));
		System.out.println(first.orElseGet(
				() -> String.format("No result found in %s", names.stream().collect(Collectors.joining(", ")))));

	}

}
