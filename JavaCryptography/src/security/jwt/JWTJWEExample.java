package security.jwt;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import security.KeyUtil;

/**
依據（JWA、JWK）與（JWS）產生 Token（JWT） 

	+-----+   +-----+
	| JWK | → | JWS |
	+-----+   +-----+
	   ↑         ↓
	+-----+   +-----+
	| JWA |   | JWT |
	+-----+   +-----+
		
 */

public class JWTJWEExample {
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
		
		// 7. JWE: 對已簽名的JWT 進行資料加密
		JWEHeader jweHeader = new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A192GCM)
							.contentType("JWT")
							.build();
		
		JWEObject jweObject = new JWEObject(jweHeader, new Payload(signedJWT));
		
		// 8. 加密
		String encryptionSecure = KeyUtil.generateSecret(16);
		jweObject.encrypt(new DirectEncrypter(encryptionSecure.getBytes()));
		
		// 9. 得到加密後的token
		String token = jweObject.serialize();
		System.out.printf("JWT(Token 有加密): %n%s%n", token);
		
		// ----------------------------------------------
		// 10. 解密
		JWEObject decryptedJweObject = JWEObject.parse(token);
		decryptedJweObject.decrypt(new DirectDecrypter(encryptionSecure.getBytes()));
		
		// 11. 驗證 JWT簽名
		SignedJWT verifiedJWT = decryptedJweObject.getPayload().toSignedJWT();
		
		// 12. 取得密鑰
		JWSVerifier verifier = new MACVerifier(signingSecret);
		
		// 13. 進行驗證
		if (verifiedJWT.verify(verifier)) {
			System.out.printf("JWT 簽名驗證成功");
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
