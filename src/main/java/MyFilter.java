import javax.servlet.*;

import javax.servlet.annotation.WebFilter;

import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebFilter("/MyFilter")
public class MyFilter implements Filter {

   public static Singleton singleton;
   boolean sessionValid;

    private static void init(){
        singleton.GetInstance();
    }
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        Cookie[] cookies = request.getCookies();
        //sessionValid = false;
        if (cookies != null) { // TODO переделать это в лямбды
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("sessionId")) {
                    sessionValid = false;
                    if (singleton.containsSessionId(cookie.getValue())) {
                        sessionValid = true;
                        request.getRequestDispatcher("hello_inside.jsp").forward(req, res);
                    }
                }
            }
        }
        /*if (request.getRequestURI().equals(request.getContextPath() + "/hello_inside")){
            if (sessionValid == true)
                request.getRequestDispatcher("hello_inside.jsp").forward(req, res);
            else
                request.getRequestDispatcher("count_to_get_in.jsp").forward(req, res);
        }
        else if (request.getRequestURI().equals(request.getContextPath() + "/count_to_get_in")){
            if (sessionValid == true)
                request.getRequestDispatcher("hello_inside.jsp").forward(req, res);
            else
                request.getRequestDispatcher("count_to_get_in.jsp").forward(req, res);
        }*/
        chain.doFilter(request, response);

    }
}
