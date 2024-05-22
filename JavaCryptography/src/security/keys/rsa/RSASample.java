package security.keys.rsa;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import security.KeyUtil;

public class RSASample {

	public static void main(String[] args) throws Exception {
		// 1. 生成RSA 密鑰(公/私)
		System.out.println("1. 生成 RSA 密鑰對(公/私)");
		KeyPair keyPair = KeyUtil.generateRSAKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		
		System.out.println("公鑰: " + publicKey);
		System.out.println("私鑰: " + privateKey);
		
		// 2. 要加密的資訊(原始資料)
		System.out.println("\n2. 要加密的資料(原始資料)");
		String originalMessage = "味鄉便當店";
		System.out.println("要加密的資訊(原始資料): " + originalMessage);
		
		// 3. 利用公鑰進行資料加密
		System.out.println("\n3. 利用公鑰進行資料加密");
		// 將OriginalMessage 透過公鑰進行加密
		byte[] encryptedBytes = KeyUtil.encryptWithPublicKey(publicKey, originalMessage.getBytes());
		System.out.println("以Base64編碼來呈現加密後的資訊: " + Base64.getEncoder().encodeToString(encryptedBytes));
		
		// 4. 利用私鑰進行資料解密
		System.out.println("\n4. 利用私鑰進行資料解密");
		System.out.println("解密中....");
		Thread.sleep(3000);
		// 將encryptedBytes(已被公鑰加密的資訊) 透過私鑰進行解密
		byte[] decrypted = KeyUtil.decryptWithPrivateKey(privateKey, encryptedBytes);
		System.out.println("得到解密後的訊息:" + new String(decrypted));
	}
	
}
