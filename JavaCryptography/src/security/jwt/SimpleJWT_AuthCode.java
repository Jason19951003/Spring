package security.jwt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;

import net.glxn.qrgen.QRCode;
import security.KeyUtil;

// 建立一個高鐵票的JWT + AuthCode(授權碼)
public class SimpleJWT_AuthCode {
	// 票務資料表
	static Map<String, List<String>> tickets = new HashMap<>();	
	
	static {
		// key = 授權碼
		tickets.put("068E", List.of("台北", "桃園", "商務艙", "6", "8E", "651", "2024-05-27" , "Tom"));
		tickets.put("012A", List.of("台北", "高雄", "經濟艙", "1", "2A", "656", "2024-05-28" , "Sam"));
		tickets.put("026B", List.of("雲林", "彰化", "自由座", "2", "6B", "658", "2024-05-29" , "John"));
	}
	
	public static void main(String[] args) throws JOSEException, FileNotFoundException, ParseException {
		// 1. 高鐵公司的簽名專用密鑰(JWK)
		String signingSecure = "abcdefghijklmnopqrstuvwxyz123456"; // 32 bytes
		
		// 2. 建立 PayLoad (創建JWT的聲明 claims) 裡面就是票務資訊
		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
				.subject("高鐵票")
				.issuer("台灣高鐵")
				.claim("authcode", "012A")
				.build();
		
		// 3. 對JWT進行簽名並取得token
		String token = KeyUtil.signJWT(claimsSet, signingSecure);
		
		System.out.printf("高鐵票 token: %n%s%n", token);
		
		// 4. 產生QRCode
		File file = new File("src/security/jwt/ticket_qrcode.png");
		QRCode.from(token).withSize(300, 300).writeTo(new FileOutputStream(file));
		System.out.println("QRCode 產生完畢");
		
		//----------------------------------------------------------------------------
		// 5. 驗證JWT的簽名
		if (KeyUtil.verifyJWTSignature(token, signingSecure)) {
			System.out.println("JWT 簽名驗證成功");
			// 判斷授權碼是否有效
			JWTClaimsSet claims = KeyUtil.getClaimsFromToken(token);
			String autoCode = claims.getStringClaim("authcode");
			System.out.printf("授權碼: %s%n", autoCode);
			List<String> data = tickets.get(autoCode);
			
			if (data == null) {
				System.out.println("授權碼錯誤");
				return;
			}
			System.out.println("授權碼正確: ");
			System.out.printf("票務資訊: %s%n", data);
			
		} else {
			System.out.println("JWT 簽名驗證失敗");
		}
	}

}
