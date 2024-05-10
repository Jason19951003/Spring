package guestbook.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


// 通關密碼: code=1234
// 使用者輸入通關密碼才可以到訪客留言版(GuestBook)
@WebFilter("/guestbook")
public class PassCodeFilter extends HttpFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("PassCodeFilter 過濾" + req.getMethod());
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		
		if (req.getMethod().equals("GET")) {
			// 檢查code 是否等於1234
			if (req.getParameter("deleteId") == null || req.getParameter("updateId") == null) {
				if(req.getParameter("code") == null || !req.getParameter("code").equals("1234")) {
					res.getWriter().print("PassCode Error !");
					return;
				}
			}
		}
		// 放行
		chain.doFilter(req, res);
	}
}
