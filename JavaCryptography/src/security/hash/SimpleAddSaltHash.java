package security.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

import security.KeyUtil;

public class SimpleAddSaltHash {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// 1.設定一個密碼
		String password = "1234";
		
		// 2.隨機生成一個鹽(salt)
		byte[] salt = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(salt); // 填充隨機值
		System.out.printf("鹽的資料: %s%n" , Arrays.toString(salt));
		System.out.printf("鹽(Hex): %s%n", KeyUtil.bytesToHex(salt));
		
		// 3. 獲取SHA-256 消息摘要物件來幫助我們生成密碼的Hash
		MessageDigest msDigest = MessageDigest.getInstance("SHA-256");
		
		// 4. 加鹽
		msDigest.update(salt);
		
		// 5. 將密碼轉會為byte[] 然後生成Hash
		byte[] hashedBytes = msDigest.digest(password.getBytes());
		
		// 6. 將byte[] 轉 Hex
		String hashedHexString = KeyUtil.bytesToHex(hashedBytes);
		System.out.printf("原始密碼: %s%n", password);
		System.out.printf("加鹽後的Hash密碼: %s%n", hashedHexString);
		
		// 7. 模擬使用者輸入密碼進行驗證
		Scanner sc = new Scanner(System.in);
		System.out.print("請輸入密碼: ");
		String inputPassword = sc.nextLine();
		
		// 8. 生成使用者輸入密碼的Hash
		msDigest.reset(); // 重置
		msDigest.update(salt); // 加鹽
		byte[] inputHashedByte = msDigest.digest(inputPassword.getBytes());
		String inputHashedHexString = KeyUtil.bytesToHex(inputHashedByte);
		
		// 9. 驗證密碼
		if (inputHashedHexString.equals(hashedHexString)) {
			System.out.println("密碼正確!");
		} else {
			System.out.println("密碼錯誤!");
		}
 	}

}
