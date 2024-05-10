package session06.dyn_proxy;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

// Aspect 切面程式
public class MyLoggerAspect {
	// before: method before
	public static void before(Object[] args) {
		System.out.println("寫入 log 程序...");
		String path = "src/session06/dyn_proxy/log.txt";
		String content = Arrays.toString(args);
		
		try {
			// java.nio.file.Files
			Files.write(
					Paths.get(path), 
					content.getBytes(StandardCharsets.UTF_8),
					StandardOpenOption.CREATE, // 若 log.txt 不存在 就建立檔案
					StandardOpenOption.APPEND);// 在檔案後增加文字
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void after() {
		System.out.println("完成~log程序");
	}
}
