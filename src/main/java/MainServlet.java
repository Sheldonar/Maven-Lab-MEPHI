import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.UUID;

public class MainServlet extends HttpServlet {

    final static String URL = "count_to_get_in.html";
    final static String JSP = "count_to_get_in.jsp";

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
        base.merge(sum, Collections.singleton(hash), (s1, s2) -> { // слияние 2х мап
            s1.addAll(s2);
            return s1;
        });
//        if (base.containsKey(sum)) {
//            String s = hash;
//            base.get(sum).add(s);
//        } else {
//            String s = hash;
//            Set<String> set = new HashSet<String>();
//            set.add(s);
//            base.put(sum, set);
//        }
        request.setAttribute("a", a);
        request.setAttribute("b", b);
        request.setAttribute("hash", hash);
        request.getRequestDispatcher(JSP).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String answerStr = (request.getParameter("answer"));
        if (answerStr == null || answerStr.isEmpty()) {
            response.sendRedirect(URL);
            return;
            //throw new IllegalArgumentException("Введите ответ");
        }

        Integer answer = Integer.parseInt(answerStr);
        String hashedSum = request.getParameter("hash");

        if (!base.containsKey(answer) || !base.get(answer).contains(hashedSum)) {
            response.sendRedirect(URL);
            return;
            //throw new IllegalArgumentException("Сумма посчитана неверно");
        }

        String newId = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("sessionId", newId);
        response.addCookie(cookie);
        single.addId(newId);

        response.sendRedirect(SecondServlet.URL);
    }

    private String getHash(int a, int b) {
        return  String.valueOf(322 * a + 1337 * b + 1488 * System.currentTimeMillis());
    }
}
