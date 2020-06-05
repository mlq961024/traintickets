package com.servelet;

import com.bean.Ticket;
import com.bean.TrainSelect;
import com.bean.User;
import com.dao.fuction.SellContol;
import com.dao.fuction.UserControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/changeTicket")
public class ChangeTicket extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String date = request.getParameter("date");//当前日期
        String start = request.getParameter("start");//出发地
        String end = request.getParameter("end");//到达地
        String startt = request.getParameter("startt");//出发
        String endt = request.getParameter("endt");//到达
        String trainid = request.getParameter("trainid");//到达
        //2.调用service查询
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserControl service = new UserControl();
        TrainSelect train = service.selectoneTrain(start, end, date, startt, endt, trainid);
        Ticket oldticket = (Ticket) session.getAttribute("oldticket");
        if(user.getPower()==1){
            SellContol sellContol = new SellContol();
            String flag="";
            if(oldticket.getUserid().equals("车站购票")){
                flag = sellContol.changeTicket(oldticket, train);
            }else {
                flag=sellContol.changeTicket(oldticket,train);
            }
            request.setAttribute("changeticket_msg",flag);
            request.getRequestDispatcher("/changeticket.jsp").forward(request,response);
        }else {
            String changeticket_msg = service.changeTicket(oldticket, train);
            request.setAttribute("changeticket_msg",changeticket_msg);
            request.getRequestDispatcher("/changeticket.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
