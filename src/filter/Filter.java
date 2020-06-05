package filter;

import com.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
       HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        if(uri.contains("login")||uri.contains("regist")||
                uri.contains("lostpass.jsp")||uri.contains("password.jsp")||
                uri.contains("index")||uri.contains("ticket.jsp")||
                uri.contains("CheckAnwser")||uri.contains("Checkcode")||
                uri.contains("selectTicketServlet")||uri.contains("updateUserServlet")||uri.contains("css")
                ||uri.contains("js")||uri.contains("fonts")||uri.contains("images")||uri.equals("/SellTrainTicket/")||uri.contains("Checkquestion")
                ||uri.contains("adminlogin")

        ){
            chain.doFilter(req, resp);
        }else {
            User user = (User) request.getSession().getAttribute("user");
            if (user!=null){
               int power = user.getPower();
               if(power==0){//普通用户拦截
                   if(uri.contains("myTicketServlet")||uri.contains("myrider.jsp")||
                           uri.contains("changeinform.jsp")||uri.contains("addmoney")||
                           uri.contains("outLoginServlet")||uri.contains("myticket.jsp")||
                           uri.contains("delRidersServlet")||uri.contains("addrider.jsp")||
                           uri.contains("brackTicketServlet")||uri.contains("changeTicketServlet")||
                           uri.contains("addRiderServlet")||uri.contains("uy")||uri.contains("PayTicket")){
                       chain.doFilter(req, resp);
                       System.out.println("565464");
                   }
               }else if(power==1){
                   if(uri.contains("myTicketServlet")||
                           uri.contains("changeinform.jsp")||uri.contains("addmoney")||
                           uri.contains("outLoginServlet")||uri.contains("myticket.jsp")||
                           uri.contains("brackTicketServlet")||uri.contains("changeTicketServlet")||
                           uri.contains("sellticket.jsp")||uri.contains("buyTicket")||
                           uri.contains("buyticket.jsp")||uri.contains("updateUserServlet")
                           ||uri.contains("PayTicket")){
                       chain.doFilter(req, resp);
                   }

               }else if(power==2){
                   if(uri.contains("admin")||uri.contains("delatetrainServlet")||
                           uri.contains("find")||uri.contains("AddtrainServlet")||
                           uri.contains("outLoginServlet")||uri.contains("changeinform.jsp")||
                           uri.contains("ticketServlet")||uri.contains("updateUserServlet")||
                           uri.contains("changepower")||uri.contains("cate.jsp")||
                           uri.contains("cits.jsp")||uri.contains("trainupdate.jsp")||
                           uri.contains("addtrain")||
                           uri.contains("delatetrain")||
                           uri.contains("ticketadmin.jsp")||
                           uri.contains("AddAlltrain")||
                           uri.contains("main")||
                           uri.contains("delSelectedServlet")||
                           uri.contains("delUserServlet")||
                           uri.contains("changeUserServlet")||
                           uri.contains("delTicketServlet")||
                           uri.contains("trainchangeServlet")||
                           uri.contains("updateUser")){
                       chain.doFilter(req, resp);
                   }

               }
            }else {
                request.setAttribute("login_msg","您尚未登陆，请登陆后再操作！");
                request.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
