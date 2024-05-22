package security.keys.aes;

import java.util.Base64;
import java.util.Scanner;

import javax.crypto.spec.SecretKeySpec;

import security.KeyUtil;

public class AESSample2_ECB {
	
	private static String userName = "admin";
	private static String userPassword = "BHZONrn9k80UgcBZ82yePHkNtVSeF36RHpDbsSo5w9A=";
	
	public static void main(String[] args) throws Exception {
		final String KEY = "0123456789abcdef";
		SecretKeySpec aesKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("請輸入帳號:");
		String inputUserName = sc.next();
		System.out.print("請輸入密碼:");
		String inputUserPassWord = sc.next();
		
		if (!inputUserName.equals(userName)) {
			System.out.println("帳號輸入錯誤");
			return;
		}
		
		// 將 inputUserPassWord 轉 AES 再轉 Base64 ==> 再進行userPassword 比較
		byte[] enctryptedECB = KeyUtil.encryptWithAESKey(aesKeySpec, inputUserPassWord);
		String passowordEncodeBase64 = Base64.getEncoder().encodeToString(enctryptedECB);
		
		if (!passowordEncodeBase64.equals(userPassword)) {
			System.out.println("密碼錯誤!");
			return;
		}
		System.out.println("登入成功!");
	}
}
