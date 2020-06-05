package com.servelet;

import com.bean.Ticket;
import com.bean.User;
import com.dao.fuction.SellContol;
import com.dao.fuction.UserControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/brackTicketServlet")
public class BrackTicketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String currentPage = request.getParameter("currentPage");
        //String date= request.getParameter("date");
        UserControl userControl = new UserControl();
        Ticket ticket = userControl.findticketbyid(id);
        User user = (User) request.getSession().getAttribute("user");
        if(user.getPower()==1){
            SellContol sellContol = new SellContol();
            boolean flag;
            if(ticket.getUserid().equals("车站购票")){
                flag=sellContol.backTicket(ticket);
            }else {
                flag=sellContol.backTicket(ticket);
            }
            if(flag){
                request.setAttribute("brackticket_msg","退票成功");
            }else {
                request.setAttribute("brackticket_msg","退票失败");
            }
            request.getRequestDispatcher("/myTicketServlet?rows=5&currentPage="+currentPage).forward(request,response);

        }else {
            String s = "退票失败,票已过期";
            if (userControl.backTicket(ticket)){
                s="退票成功";
            }
            request.setAttribute("brackticket_msg",s);
            request.getRequestDispatcher("/myTicketServlet?rows=5&currentPage="+currentPage).forward(request,response);
        }
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
