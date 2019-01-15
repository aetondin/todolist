package ie.todolist.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {

		String uri = request.getRequestURI();
		if(uri.equals("/todolist/") || uri.endsWith("/enter")) {
			return true;
		}
		
		if(request.getSession().getAttribute("users")!=null) {
			return true;
		}else {
			response.sendRedirect("/todolist/");
			return false;
		}
	}
	
}
