import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.UUID;


@WebServlet("")
public class MainServlet extends HttpServlet {
    private Map<Integer, Set<String>> base;
    public Singleton single;

    @Override
     public void init(){
         base = new HashMap<Integer, Set<String>>();
         single = new Singleton();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Random random = new Random();
        int a = random.nextInt(472) - 125;
        int b = random.nextInt(472) - 125;
        int sum = a + b;
        String hash = getHash(a, b);
        if (base.containsKey(sum))
        {
            String s = hash;
            base.get(sum).add(s);
        }
        else
        {
            String s = hash;
            Set<String> set = new HashSet<String>();
            set.add(s);
            base.put(sum, set);
        }
        request.setAttribute("a", a);
        request.setAttribute("b", b);
        request.setAttribute("hash", hash);
        request.getRequestDispatcher("count_to_get_in.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            int answer = Integer.parseInt(request.getParameter("answer"));
        String HashedSum = request.getParameter("hash");
        if (base.containsKey(answer) && base.get(answer).contains(HashedSum)) {
            request.getRequestDispatcher("hello_inside.jsp").forward(request, response);
            String newId = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("sessionId", newId);
            response.addCookie(cookie);
            single.addId(newId);
            request.getRequestDispatcher("hello_inside.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("http://localhost:8080/LAB2_war_exploded/");
        }
        }catch (Exception e){
            request.getRequestDispatcher("count_to_get_in.jsp").forward(request, response);
        }
    }

    private String getHash(int a, int b) {
        return  Integer.toString(322 * a + 1337 * b + 1488 * (int)System.currentTimeMillis());
    }
}
