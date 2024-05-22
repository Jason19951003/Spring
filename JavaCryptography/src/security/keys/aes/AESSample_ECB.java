package security.keys.aes;

import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.spec.SecretKeySpec;

import security.KeyUtil;

// AES 對稱式加密
public class AESSample_ECB {

	public static void main(String[] args) throws Exception {
		// 建立一個AES的key(AES-128 bits, 16 bytes)
		final String KEY = "0123456789abcdef";
		
		System.out.println("AES 加密範例");
		
		String orginalText = "不能說的秘密";
		System.out.println("原始訊息(明文):" + orginalText);
		System.out.println("--------------------------------");
		
		// 建立AES密鑰規範
		SecretKeySpec aesKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
		// ECB 模式
		System.out.println("ECB 模式");
		byte[] enctryptedECB = KeyUtil.encryptWithAESKey(aesKeySpec, orginalText);
		System.out.println("加密後的訊息:" + Arrays.toString(enctryptedECB));
		// 將加密後的訊息轉 base64 便於儲存與傳輸
		String enctryptedECBBase64 = Base64.getEncoder().encodeToString(enctryptedECB);
		System.out.println("加密後的訊息(base64):" + enctryptedECBBase64);
		
		// ------------解密-------------
		Scanner scanner = new Scanner(System.in);
		System.out.print("請輸入密文:(base64):");
		String base64 = scanner.next();
		
		// 將base64 轉回byte[]
		byte[] enctryptedECB_2 = Base64.getDecoder().decode(base64);
		// 進行解密
		String decryptedECBString = KeyUtil.decryptWithAESKey(aesKeySpec, enctryptedECB_2);
		System.out.println("解密後的密文:" + decryptedECBString);
		
	}

}
