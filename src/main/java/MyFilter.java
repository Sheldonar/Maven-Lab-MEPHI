import javax.servlet.*;

import javax.servlet.annotation.WebFilter;

import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebFilter("/MyFilter")
public class MyFilter implements Filter {

   public static Singleton singleton;

    private static void init(){
        singleton.GetInstance();
    }
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        Cookie[] cookies = request.getCookies();
        if (!(cookies == null)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("sessionId")) {
                    if (singleton.containsSessionId(cookie.getValue())) {
                        request.getRequestDispatcher("hello_inside.jsp").forward(request, response);
                    }
                }
            }
        }
        chain.doFilter(request, response);

    }
}
