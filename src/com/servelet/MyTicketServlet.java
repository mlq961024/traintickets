package com.servelet;

import com.bean.PageBean;
import com.bean.Ticket;
import com.bean.User;
import com.dao.fuction.UserControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/myTicketServlet")
public class MyTicketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示条数

        if(currentPage == null || "".equals(currentPage)){

            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "5";
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserControl userControl = new UserControl();
        if(user.getPower()==1){
            String riderid = request.getParameter("riid");//
            String ridername = request.getParameter("riname");//
            PageBean<Ticket> pb =  userControl.seeriderTicket(currentPage, rows,riderid);
            request.setAttribute("riid",riderid );
            request.setAttribute("riname",ridername);
            request.getRequestDispatcher("/sellticket.jsp").forward(request,response);
        }else {
            PageBean<Ticket> pb = userControl.seeMyTicket(currentPage, rows, user.getId());
            request.setAttribute("pb",pb);
            request.getRequestDispatcher("/myticket.jsp").forward(request,response);
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
