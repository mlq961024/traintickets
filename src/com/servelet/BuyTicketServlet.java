package com.servelet;

import com.bean.Rider;
import com.bean.Train;
import com.bean.TrainSelect;
import com.dao.fuction.UserControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/BuyTicketServlet")
public class BuyTicketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String date = request.getParameter("date");//当前日期
        String start = request.getParameter("start");//出发地
        String end = request.getParameter("end");//到达地
        String trainid = request.getParameter("trainid");//到达
        //2.调用service查询
        TrainSelect train = new TrainSelect();
        train.setDate(date);
        train.setStart(start);
        train.setEnd(end);
        train.setId(trainid);
        UserControl service = new UserControl();
//        request.getSession().setAttribute("willbuytrain",service.selectoneTrain(start,end,date,startt,endt,trainid));
        request.getSession().setAttribute("willbuytrain",service.selectoneTrain(train));
        System.out.println("sadadb");
        request.getRequestDispatcher("/buyticket.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
