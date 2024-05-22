package security.keys.aes;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import security.KeyUtil;

public class AESSample_CTR {

	public static void main(String[] args) throws Exception {
		// 建立一個AES的key(AES-128 bits, 16 bytes)
		final String KEY = "0123456789abcdef";
		// 建立AES密鑰規範
		SecretKeySpec aesKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
		// 透過 SecureRandom 定義 IV 內容
		byte[] iv = new byte[16];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(iv);
		// ---------------加密---------------
		byte[] encryptedCTR = KeyUtil.encryptWithAESKeyAndIVInCTRMode(aesKeySpec, "蔬菜蛋餅", iv);
		System.out.println("CTR加密後: " + Base64.getEncoder().encodeToString(encryptedCTR));
		// --------------解密----------------
		String decryptedCTR = KeyUtil.decryptWithAESKeyAndIVInCTRMode(aesKeySpec, encryptedCTR, iv);
		System.out.println("CTR解密後: " + decryptedCTR);
	}

}
