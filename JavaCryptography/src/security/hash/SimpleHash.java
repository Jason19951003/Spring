package security.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import security.KeyUtil;

public class SimpleHash {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// 一段密碼
		String password = "1234";
		
		// 1. 獲取SHA-256 消息摘要物件, 幫助我們生成Hash
		MessageDigest msDigest = MessageDigest.getInstance("SHA-256");
		
		// 2. 生成Hash
		byte[] hashedBytes = msDigest.digest(password.getBytes());
		
		System.out.println("原始密碼: " + password);
		
		// 將Hash轉Hex(16進位)
		String hashed = KeyUtil.bytesToHex(hashedBytes);
		System.out.println("Hash密碼: " + hashed);
	}

}
