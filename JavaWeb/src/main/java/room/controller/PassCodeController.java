package room.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/passcode")
public class PassCodeController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 產生四個數字的圖檔(BufferedImage)
	private BufferedImage getPassCodeImage(String passCode) {
		int w = 80;// 圖寬
		int h = 30;// 圖高
		
		// 建立圖像暫存區
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		// 建立畫布
		Graphics g = img.getGraphics();
		// 設定顏色
		g.setColor(Color.GRAY);
		// 塗滿背景
		g.fillRect(0, 0, w, h);
		// 繪製文字
		g.setColor(Color.BLUE);
		g.setFont(new Font("新細明體", Font.BOLD, 30));
		g.drawString(passCode, 10, 25);
		// 加入干擾線
		g.setColor(Color.YELLOW);
		Random random = new Random();
		
		for (int i = 0; i < 10; i++) {
			int x1 = random.nextInt(w);
			int y1 = random.nextInt(h);
			int x2 = random.nextInt(w);
			int y2 = random.nextInt(h);
			g.drawLine(x1, y1, x2, y2);
		}
		
		return img;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 取得驗證碼
		String passCode = String.format("%04d", new Random().nextInt(10000));
		System.out.println("驗證碼:" + passCode);
		// 將驗證法存放到session屬性中, 給PassCodeFilter.java使用
		HttpSession session = req.getSession();
		session.setAttribute("passCode", passCode);
		
		try {
			// 取得圖片資訊
			BufferedImage img = getPassCodeImage(passCode);
			// 發送圖片
			ImageIO.write(img, "JPEG", resp.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
