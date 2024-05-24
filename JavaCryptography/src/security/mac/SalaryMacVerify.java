package security.mac;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.crypto.SecretKey;

import security.KeyUtil;

/*
 * 
 * 用於驗證薪資明細的 MAC
 * 
 * 證明成功實施了 MAC 驗證策略，並且可以正確地驗證您的薪資明細的完整性和來源。
 * 使用這樣的策略可以確保只有擁有正確 MAC 密鑰的人（在這個例子中是 HR 部門）
 * 才能生成有效的 MAC，而其他人則不能。
 * 
 * 這是一個非常重要的安全策略，特別是在涉及敏感資訊（如薪資明細）的場合。
 * 只要保護好您的密鑰，就可以確保消息的真實性和完整性。
*/

public class SalaryMacVerify {

	public static void main(String[] args) throws Exception {
		// 員工:取得薪資檔案位置
		String filePath = "src/security/mac/my_salary.txt";
		// 取得金鑰檔案位置
		String keyPath = "src/security/mac/macKey.key";
		// 得到HR部門所生成的macValue
		String macValueFromHR = "6608bed89a9dc4ffc8e87d6cf9d4d6d5674304f439ee7573f33bd3883de06eb2";
		
		// 將密鑰檔 macKey.key 轉密鑰物件
		SecretKey macKey = KeyUtil.getSecretKeyFromFile("HmacSHA256", keyPath);
		
		// 員工透過 macKey + filePath(my_salary.txt) 自行生成 macValueFromEmployee
		String macValueFromEmployee = KeyUtil.generateMac("HmacSHA256", macKey, filePath);
		
		// 驗證 macValueFromEmployee == macValueFromHR 來判斷資料是否來自HR部門
		if (macValueFromEmployee.equals(macValueFromHR)) {
			System.out.println("MAC驗證成功, 資料來源HR");
			// 讀取檔案內容
			String fileContent = Files.readString(Paths.get(filePath));
			System.out.println(fileContent);
		} else {
			System.out.println("MAC驗證失敗~");
		}
	}

}
