package com.servelet;

import com.bean.PageBean;
import com.bean.TrainSelect;
import com.dao.fuction.UserControl;
import com.dao.jdbcTemplate.TrainTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/selectTicketServlet")
public class SelectTicketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        //1.获取参数
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示条数

        String page = request.getParameter("page");//页面来源
        if(currentPage == null || "".equals(currentPage)){

            currentPage = "1";
        }


        if(rows == null || "".equals(rows)){
            rows = "5";
        }

        //获取条件查询参数
        String date = request.getParameter("date");//当前日期
        String start = request.getParameter("start");//出发地
        String end = request.getParameter("end");//到达地
        //2.调用service查询
        UserControl service = new UserControl();
        PageBean<TrainSelect> pb = service.findTrainPage(date,start,end,currentPage,rows);
        // 3.将PageBean存入request
        request.setAttribute("pb",pb);
        request.setAttribute("start",start);
        request.setAttribute("end",end);
        request.setAttribute("date",date);
        //4.转发到jsp
        if("changeticket".equals(page)){
            request.getRequestDispatcher("/changeticket.jsp").forward(request,response);
        }else {
            request.getRequestDispatcher("/ticket.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
