package security.mac;

import java.util.Arrays;

import javax.crypto.SecretKey;

import security.KeyUtil;

public class MACSample {

	public static void main(String[] args) throws Exception {
		// 1. 定義我們要加上的 MAC 的訊息
		String message = "本月加發 10000 元";
		System.out.printf("原始訊息: %s%n", message);
		
		// 2. 產生一把專用於MAC的金鑰
		// MAC  : Message Authentication Code
		// HMAC : Hash-base Message Authentication Code
		SecretKey macKey = KeyUtil.generateKeyForHmac();

		// 3. 利用此密鑰(macKey)和訊息(message)生成MAC值
		byte[] macValue = KeyUtil.generateMac("HmacSHA256", macKey, message.getBytes());
		
		// 4. 將MAC值以(Hex)印出
		System.out.printf("產生的 HAC(Hex): %s%n", KeyUtil.bytesToHex(macValue));
		
		// 5. 在實際應用中, 接收方會收到 message 與 macValue(macValue的hex)
		//    此時接收方本身就要有 macKey(雙方統一都有的)
		//    接收方根據 message + macKey 所產生的值與 macValue進行比對
		byte[] computedMacValue = KeyUtil.generateMac("HmacSHA256", macKey, message.getBytes());
		
		// 6. 比較 macValue 與 computedMacValue 是否相等?
		if (Arrays.equals(macValue, computedMacValue)) {
			System.out.println("MAC 驗證成功, 來源正確");
		} else {
			System.out.println("MAC 驗證失敗, 來源不正確");
		}
	}

}
