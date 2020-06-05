package com.servelet;

import com.bean.PageBean;
import com.bean.User;
import com.dao.fuction.AdminControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.获取参数
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示条数
        String page = request.getParameter("page");

        if(currentPage == null || "".equals(currentPage)){

            currentPage = "1";
        }

        if(rows == null || "".equals(rows)){
            rows = "5";
        }
        
        //获取条件查询参数
        String usrename = request.getParameter("id");


        //2.调用service查询
        AdminControl adminControl = new AdminControl();

        PageBean<User> pb = adminControl.findUserByPage(currentPage,rows,usrename);

        //3.将PageBean存入request
        request.setAttribute("pb",pb);
        request.setAttribute("usrename",usrename);//将查询条件存入request
        if(page!=null&&page.equals("power")){
            request.getRequestDispatcher("/changepower.jsp").forward(request,response);
        }else {
            request.getRequestDispatcher("/cate.jsp").forward(request,response);
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
