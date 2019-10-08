import javax.servlet.*;

import javax.servlet.annotation.WebFilter;

import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebFilter("/MyFilter")
public class MyFilter implements Filter {

   public static Singleton singleton;

     private static void init(){
        singleton.GetInstance();
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

         HttpServletRequest request = (HttpServletRequest) req;

         Cookie[] cookies = request.getCookies();
         if (cookies == null) {
             chain.doFilter(req, res);
         }
         Cookie sessionId = Arrays.stream(cookies)
                                  .filter(c -> c.getName().equals("sessionId"))
                                  .findFirst()
                                  .orElse(null);
         Boolean sessionIsValid = sessionId != null && singleton.containsSessionId(sessionId.getValue());

         String validUrl = sessionIsValid ? SecondServlet.URL : MainServlet.URL;

         doFilterOrRedirect(req, res, chain, validUrl);
    }

    private void doFilterOrRedirect(ServletRequest req, ServletResponse res, FilterChain chain, String url) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String fullUrl = request.getRequestURI();
        String contextPath = request.getContextPath();

        if (fullUrl.equals(contextPath + "/" + url)) {
            chain.doFilter(req, res);
        } else {
            response.sendRedirect(url);
        }
    }
}
