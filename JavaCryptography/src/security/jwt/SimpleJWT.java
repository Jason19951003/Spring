package security.jwt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;

import net.glxn.qrgen.QRCode;
import security.KeyUtil;

// 建立一個高鐵票的JWT
public class SimpleJWT {

	public static void main(String[] args) throws JOSEException, FileNotFoundException {
		// 1. 高鐵公司的簽名專用密鑰(JWK)
		String signingSecure = "abcdefghijklmnopqrstuvwxyz123456"; // 32 bytes
		
		// 2. 建立 PayLoad (創建JWT的聲明 claims) 裡面就是票務資訊
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
				.jwtID(UUID.randomUUID().toString())
				.subject("高鐵票")
				.issuer("台灣高鐵")
				.claim("起點", "台北")
				.claim("終點", "桃園")
				.claim("艙等", "商務艙")
				.claim("車廂", "6")
				.claim("座位", "8E")
				.claim("車次", "651")
				.claim("日期", "2024-05-27")
				.build();
		
		System.out.printf("JWT ID: %s%n", claimsSet.getJWTID());
		// 3. 對JWT進行簽名並取得token
		String token = KeyUtil.signJWT(claimsSet, signingSecure);
		
		System.out.printf("高鐵票 token: %s%n", token);
		
		// 4. 產生QRCode
		File file = new File("src/security/jwt/ticket_qrcode.png");
		QRCode.from(token).withSize(300, 300).writeTo(new FileOutputStream(file));
		System.out.println("QRCode 產生完畢");
		
		//----------------------------------------------------------------------------
		// 5. 驗證JWT的簽名
		if (KeyUtil.verifyJWTSignature(token, signingSecure)) {
			System.out.println("JWT 簽名驗證成功");
			System.out.println("驗票閘門開啟....");
		} else {
			System.out.println("JWT 簽名驗證失敗");
		}
	}

}
