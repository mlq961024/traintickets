package com.servelet;

import com.bean.PageBean;
import com.bean.Ticket;
import com.dao.fuction.AdminControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ticketServlet")
public class TicketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示条数

        String usering = request.getParameter("usering");

        if(usering==null||"".equals(usering)){
            usering="0";
        }
        if(currentPage == null || "".equals(currentPage)){

            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "5";
        }

        AdminControl adminControl = new AdminControl();
        PageBean<Ticket> pb =adminControl.selectAllticket(currentPage, rows,Integer.parseInt(usering));
        request.setAttribute("pb",pb);
        request.setAttribute("usering",usering);
        request.getRequestDispatcher("/ticketadmin.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
