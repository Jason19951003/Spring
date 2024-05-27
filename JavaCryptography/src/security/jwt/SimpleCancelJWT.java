package security.jwt;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.nimbusds.jwt.JWTClaimsSet;

import security.KeyUtil;

// 建立一個高鐵票的JWT, 撤銷令牌(退票)
public class SimpleCancelJWT {

	// 儲存已撤銷的JWT(黑名單)
	static Set<String> cancelToken = new HashSet<>();
	
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
		
		// 4. 模擬撤銷
		cancelToken.add(token); // 將要撤銷的token令牌放入指定的集合中
		System.out.printf("撤銷 Token : %s%n", token);
		//----------------------------------------------------------------------------
		// 5. 驗證JWT的簽名
		if (KeyUtil.verifyJWTSignature(token, signingSecure)) {
			System.out.println("JWT 簽名驗證成功");
			// 確認此token 是否已被撤銷
			if (cancelToken.contains(token)) {
				System.out.println("此Token已被撤銷 / 高鐵票已經取消");
				return;
			}
			System.out.println("驗票閘門開啟....");
		} else {
			System.out.println("JWT 簽名驗證失敗");
		}
	}
}
