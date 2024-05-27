package security.jwt;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

/**
依據（JWA、JWK）與（JWE、JWS）產生 Token（JWT） 

	+-----+   +-----+   +-----+
	| JWK | → | JWE |   | JWS |
	+-----+   +-----+   +-----+
	   ↑            ↓   ↓
	+-----+        +-----+
	| JWA |        | JWT |
	+-----+        +-----+
	
*/

public class JWTExample {
	public static void main(String[] args) throws Exception {
		// 1. JWA: 決定演算法: 使用HS256
		JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
		
		// 2. JWK: 產生一個簽名用的密鑰給JWS 使用
		//String signingSecret = KeyUtil.generateSecret(32); // 256位元(32 bytes)
		String signingSecret = "abcdefghijklmnopqrstuvwxyz123456"; // 256位元(32 bytes)
		System.out.printf("signingSecret: %s%n",signingSecret);
		
		// 3. 定義payload
		JWTClaimsSet payload = new JWTClaimsSet.Builder()
				.subject("人事公吿")
				.issuer("人事部")
				.claim("name", "Jason")
				.claim("title", "升副總經理")
				.claim("date", "2024-05-27")
				.claim("privilege", "上班可以不用打卡")
				.claim("bonus", "年終至少12個月")
				.claim("salary", 3000000)
				.build();
		
		// 4. JWT: 創建JWT (尚未簽名加密)
		SignedJWT signedJWT = new SignedJWT(header, payload);
		
		// 5. JWS: 將 JWT 簽名
		JWSSigner jwsSigner = new MACSigner(signingSecret);
		
		// 6. 進行簽名
		signedJWT.sign(jwsSigner);
		
		// 7. 透過序列化技術產生 token: 可以被安全的傳遞, 儲存
		String token = signedJWT.serialize();
		System.out.printf("JWT(token): %n%s%n", token);
		
		// --------------------------------------------------
		// 8. 驗證JWT 簽名
		System.out.println();
		System.out.println("驗證 JWT");
		System.out.printf("已知 signingSecret: %s%n", signingSecret);
		System.out.printf("已知 token: %s%n", token);
		
		// 9. 從token 取得簽名
		SignedJWT verifiedJWT = SignedJWT.parse(token);
		
		// 10. 透過 signingSecret 取得密鑰
		JWSVerifier verifier = new MACVerifier(signingSecret);
		
		// 11. 進行驗證
		if (verifiedJWT.verify(verifier)) {
			System.out.println("JWT 簽名驗證成功");
			// 顯示 payload 資料
			JWTClaimsSet claims = verifiedJWT.getJWTClaimsSet();
			System.out.printf("主題 subject: %s%n", claims.getSubject());
			System.out.printf("發行者 issuer: %s%n", claims.getIssuer());
			System.out.printf("name: %s%n", claims.getStringClaim("name"));
			System.out.printf("title: %s%n", claims.getStringClaim("title"));
			System.out.printf("date: %s%n", claims.getStringClaim("date"));
		} else {
			System.out.println("JWT 簽名驗證失敗");
		}
		
 	}
}
