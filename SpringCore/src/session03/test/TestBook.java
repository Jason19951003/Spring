package session03.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import session03.bean.ComicBook;
import session03.bean.LibraryManager;
import session03.config.BookConfig;

//執行時請加入 VM 參數: --add-opens java.base/java.lang=ALL-UNNAMED
//整體來說，這行命令的意思是："在運行時，允許所有未命名的模組訪問 java.base 模組中的 java.lang 包"。
//這是為了解決一些框架，如 Spring，在需要訪問這些封裝的API時遇到的反射問題。
public class TestBook {

	public static void main(String[] args) {
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("book-config.xml");
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BookConfig.class);
		LibraryManager libraryManager = applicationContext.getBean("libraryManager", LibraryManager.class);
//		ComicBook comicBook = applicationContext.getBean("comic", ComicBook.class);
//		comicBook.display();
		libraryManager.displayComic();
		libraryManager.displayComputer();
	}

}
