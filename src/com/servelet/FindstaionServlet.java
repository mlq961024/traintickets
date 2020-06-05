package com.servelet;

import com.bean.PageBean;
import com.bean.Station;
import com.bean.TrainSelect;
import com.dao.fuction.AdminControl;
import com.dao.fuction.UserControl;
import com.dao.jdbcTemplate.StationTemplate;
import com.dao.jdbcTemplate.UserTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/findstaionServlet")
public class FindstaionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
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
        String pro = request.getParameter("proo");//当前省份

        //2.调用service查询
        StationTemplate stationTemplate = new StationTemplate();
        List<String> allPro = stationTemplate.getAllPro();
        request.setAttribute("allpro",allPro);
        AdminControl adminControl = new AdminControl();
        PageBean<Station> pb = adminControl.findstaionPage(pro,currentPage,rows);
        request.setAttribute("pb",pb);
        request.getRequestDispatcher("/cits.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
