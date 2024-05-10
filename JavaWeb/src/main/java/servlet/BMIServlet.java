package servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet/bmi")
public class BMIServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Map<String, Object>> bmiList = new CopyOnWriteArrayList<Map<String, Object>>();

	// 查詢使用
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 編碼
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		// 回傳內容
		//resp.getWriter().print(bmiList);
		resp.getWriter().print("<html>");
		resp.getWriter().print("<head>");
		resp.getWriter().print("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css\">");
		resp.getWriter().print("<link rel=\"stylesheet\" href=\"/JavaWeb/css/buttons.css\">");
		resp.getWriter().print("</head>");
		resp.getWriter().print("<body>");
		resp.getWriter().print("<table class=\"pure-table\">");
		resp.getWriter().print("<thead>");
		resp.getWriter().print("<tr>");
		resp.getWriter().print("<th>Name</th>");
		resp.getWriter().print("<th>Height</th>");
		resp.getWriter().print("<th>Weight</th>");
		resp.getWriter().print("<th>Bmi</th>");
		resp.getWriter().print("</tr>");
		resp.getWriter().print("</thead>");
		resp.getWriter().print("<tbody>");
		bmiList.forEach(map -> {
			try {
				resp.getWriter().print("<tr>");
				resp.getWriter().print("<td>"+ map.get("name") + "</td>");
				resp.getWriter().print("<td>"+ map.get("height") + "</td>");
				resp.getWriter().print("<td>"+ map.get("weight") + "</td>");
				resp.getWriter().print("<td>"+ map.get("bmi") + "</td>");
				resp.getWriter().print("</tr>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		resp.getWriter().print("</tbody>");
		resp.getWriter().print("</table>");
		resp.getWriter().print("<p>");
		resp.getWriter().print("<a href=\"/JavaWeb/BMI.html\" class=\"button-success pure-button\">回上一頁</a>");
		resp.getWriter().print("</body>");
		resp.getWriter().print("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 編碼
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");

		// 接收參數
		String username = req.getParameter("userName");
		double userHeight = Double.parseDouble(req.getParameter("userHeight"));
		double userWeight = Double.parseDouble(req.getParameter("userWeight"));

		double bmi = userWeight / Math.pow(userHeight / 100, 2);
		//建立Map集合
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("name", username);
		map.put("height", userHeight);
		map.put("weight", userWeight);
		map.put("bmi", bmi);
		//將結果存回bmiList
		bmiList.add(map);
		// 回傳內容
		resp.getWriter().print(username + "的BMI Result = " + bmi);
	}
}
