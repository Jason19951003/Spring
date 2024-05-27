package security.jwt;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import com.nimbusds.jwt.JWTClaimsSet;

import net.glxn.qrgen.QRCode;
import security.KeyUtil;

// 建立一個高鐵票的JWT, 有時效性
public class SimpleLimitedJWT {

	public static void main(String[] args) throws Exception {
		// 1. 高鐵公司的簽名專用密鑰(JWK)
		String signingSecure = "abcdefghijklmnopqrstuvwxyz123456"; // 32 bytes
		
		Date time = new Date(new Date().getTime() + (8 * 1000));
		// 2. 建立 PayLoad (創建JWT的聲明 claims) 裡面就是票務資訊
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
				.subject("高鐵票")
				.issuer("台灣高鐵")
				.claim("authcode", "068E")
				.expirationTime(time) // 設定有效期限
				.build();
		
		// 3. 對JWT進行簽名並取得token
		String token = KeyUtil.signJWT(claimsSet, signingSecure);
		
		System.out.printf("高鐵票 token: %s%n", token);
		
		// 4. 產生QRCode
		File file = new File("src/security/jwt/ticket_qrcode.png");
		QRCode.from(token).withSize(300, 300).writeTo(new FileOutputStream(file));
		System.out.println("QRCode 產生完畢");
				
		//----------------------------------------------------------------------------
		// 5. 驗證JWT的簽名
		// 模擬 token 過期
		while(true) {
			if (KeyUtil.verifyJWTSignature(token, signingSecure)) {
				System.out.println("JWT 簽名驗證成功");
				System.out.println("驗票閘門開啟....");
			} else {
				System.out.println("JWT 簽名驗證失敗");
			}
			Thread.sleep(2000);
		}
	}
}
