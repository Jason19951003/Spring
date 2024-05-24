package security.random;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LineNotifyDemo {

    public static void main(String[] args) throws Exception {
    	// 1. 發送訊息
        String message = "測試貼圖";
        String stickerPackageId = "11539";
        String stickerId = "52114110";
        // 2. 存取權杖(也稱為:授權 Token)
        
        String token = "你的token";
        // 3. Line Notify 的發送位置
        String lineNotifyUrl = "https://notify-api.line.me/api/notify";

        // 4. 發送前設定
        String postData = "message=" + message + "&stickerPackageId=" + stickerPackageId + "&stickerId=" + stickerId;
        URL url = new URL(lineNotifyUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + token);

        // 5. 訊息發送
        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData.getBytes("UTF-8"));
        }

        // 6. 回應資料
        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);
    }
}