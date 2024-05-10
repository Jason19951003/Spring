package room.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/room/*", "/bookingroom/*"})
public class PassCodeFilter extends HttpFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 取得session物件
		HttpSession httpSession = req.getSession();
		System.out.println("PassCodeFilter for Room, sessionId = " + httpSession.getId());
		
		// 檢查request 是否有帶入?code=1234
		String code = req.getParameter("code") == null ? "" : req.getParameter("code");
		String passCode = httpSession.getAttribute("passCode") + "";
		
		if (code.equals(passCode)) {
			httpSession.setAttribute("code", code);
			chain.doFilter(req, res);
		} else if (httpSession.getAttribute("code") != null) {
			chain.doFilter(req, res);
		} else {
			//res.getWriter().print("PassCode Error!");
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/passcode.jsp");
			rd.forward(req, res);
		}
	}
}
