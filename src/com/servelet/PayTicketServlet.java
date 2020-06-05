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

@WebServlet("/PayTicketServlet")
public class PayTicketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String currentPage = request.getParameter("currentPage");
        String sel = request.getParameter("page");
        float money = Float.parseFloat(request.getParameter("money"));
        User user = (User) request.getSession().getAttribute("user");
        if(user.getPower()==1){
            SellContol sellContol = new SellContol();
            sellContol.payticket(id);
            request.setAttribute("brackticket_msg","票号："+id+"支付成功");
        }else {
            if(user.getBalance()>=money){
                //调用花钱
                UserControl userControl = new UserControl();
                userControl.consume(user.getId(),money);
                userControl.payticket(id);
                request.setAttribute("brackticket_msg","票号："+id+"支付成功");
            }else {
                request.setAttribute("brackticket_msg","余额不足！请充值。");
            }
        }
        if("sel".equals(sel)){
            request.getRequestDispatcher("/myTicketServlet?rows=5&currentPage="+currentPage).forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
