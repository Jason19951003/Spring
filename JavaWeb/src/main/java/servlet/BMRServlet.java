package servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet/bmr")
public class BMRServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Map<String, Object>> bmrList = new CopyOnWriteArrayList<Map<String, Object>>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deleteId = req.getParameter("deleteId");
		if (deleteId != null) {
			int id = Integer.parseInt(deleteId);
			bmrList.remove(id);
		}
		//處理資料
		req.setAttribute("bmrList", bmrList);
		//分派到jsp
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/BMRList.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 編碼
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");

		// 接收參數
		String username = req.getParameter("userName");
		int userAge = Integer.parseInt(req.getParameter("userAge"));
		int userSex = Integer.parseInt(req.getParameter("userSex"));
		double userHeight = Double.parseDouble(req.getParameter("userHeight"));
		double userWeight = Double.parseDouble(req.getParameter("userWeight"));
		double bmr;
		if (userSex == 1) {
			bmr = 66 + (13.7 * userWeight) + (5 * userHeight) - (6.8 * userAge);
		} else {
			bmr = 655 + (9.6 * userWeight) + (1.8 * userHeight) - (4.7 * userAge);
		}
		//建立Map集合
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("name", username);
		map.put("age", userAge);
		map.put("sex", userSex);
		map.put("height", userHeight);
		map.put("weight", userWeight);
		map.put("bmr", bmr);
		//將結果存回bmiList
		bmrList.add(map);
		// 回傳內容
		resp.getWriter().print(username + "的BMR Result = " + bmr);
	}
}
