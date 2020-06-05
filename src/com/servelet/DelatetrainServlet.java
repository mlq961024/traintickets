package com.servelet;

import com.bean.PageBean;
import com.bean.TrainSelect;
import com.bean.TrainShow;
import com.bean.Trainsimpol;
import com.dao.fuction.AdminControl;
import com.dao.fuction.UserControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/delatetrainServlet")
public class DelatetrainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        AdminControl adminControl = new AdminControl();
        List<String> gettrains = adminControl.gettrains();
        List<String> getdates = adminControl.getdates();
        request.setAttribute("trainids",gettrains);
        request.setAttribute("dates",getdates);
        //1.获取参数
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示条数

//        String page = request.getParameter("page");//页面来源
        if(currentPage == null || "".equals(currentPage)){

            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "8";
        }
        //获取条件查询参数
        String date = request.getParameter("date");//当前日期
        //2.调用service查询
        PageBean<Trainsimpol> pb = adminControl.finddelateinform(date,currentPage,rows);
        // 3.将PageBean存入request
        request.setAttribute("pb",pb);
        request.setAttribute("date",date);
        //4.转发到jsp
        request.getRequestDispatcher("/delatetrain.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
