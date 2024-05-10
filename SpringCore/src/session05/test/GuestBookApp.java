package session05.test;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import session05.service.GuestBookService;

@Component
public class GuestBookApp {

	@Autowired
	private GuestBookService guestBookService;
	
	private String userName;
	
	public void setUsername(String userName) {
		this.userName = userName;
	}
	
	public void menu() {
		System.out.println("----------------------");
		System.out.println("1: 新增留言");
		System.out.println("2: 修改留言");
		System.out.println("3: 刪除留言");
		System.out.println("4: 查詢留言");
		System.out.println("9: 離開系統");
		System.out.println("----------------------");
		System.out.print("請選擇=> ");

		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();

		switch (choice) {
		case 1:
			addContent();
			break;
		case 2:
			updateContent();
			break;
		case 3:
			deleteContent();
			break;
		case 4:
			findContent();
			break;
		case 9:
			System.exit(1);
		default:
			System.out.println("輸入錯誤請重新輸入");
			menu();
		}
	}

	public void addContent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("請輸入留言內容: ");
		String content = sc.nextLine();
		guestBookService.add(userName, content);
	}

	public void updateContent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("請輸入要修改的留言ID: ");
		int id = sc.nextInt();
		System.out.println("請輸入要修改的留言內容: ");
		String content = sc.nextLine();
		guestBookService.updateContent(id, content);
	}

	public void deleteContent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("請輸入要刪除的留言ID: ");
		int id = sc.nextInt();
		guestBookService.removeById(id);
	}

	public void findContent() {
		guestBookService.queryAll().forEach(gb -> {
			System.out.printf("%d %s %s %s %n", gb.getId(), gb.getUserName(), gb.getContent(), gb.getUpdateDate());
		});
	}
	
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("guestbook-config.xml");
		GuestBookApp guestBookApp = applicationContext.getBean(GuestBookApp.class);
		
		Scanner sc = new Scanner(System.in);
		System.out.print("請輸入暱稱: ");
		String useName = sc.next();
		guestBookApp.setUsername(useName);
		
		while(true) {
			guestBookApp.menu();
		}
	}

}
