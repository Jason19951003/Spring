package security.keys.aes;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import security.KeyUtil;

public class AESSample_CBC {
	public static void main(String[] args) throws Exception {
		final String KEY = "0123456789abcdef";
		SecretKeySpec aesKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
		// CBC 模式
		// 建立IV
		// byte[] iv = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
		// 透過 SecureRandom 定義 IV 內容
		byte[] iv = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(iv);
		
		System.out.println("IV: " + Arrays.toString(iv));
		// 進行CBC資料加密
		byte[] encryptedCBC = KeyUtil.encryptWithAESKeyAndIV(aesKeySpec, "蔬菜蛋餅", iv);
		//System.out.println(new String(encryptedCBC, "UTF-8"));
		System.out.println("CBC加密後: " + Base64.getEncoder().encodeToString(encryptedCBC));
		
		// -----------------解密-------------
		String decryptedCBC = KeyUtil.decryptWithAESKeyAndIV(aesKeySpec, encryptedCBC, iv);
		System.out.println("CBC解密後: " + decryptedCBC);
	}
}
