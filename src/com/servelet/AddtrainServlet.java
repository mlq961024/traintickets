package com.servelet;

import com.dao.fuction.AdminControl;
import com.dao.jdbcTemplate.UserTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddtrainServlet")
public class AddtrainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        UserTemplate userDao = new UserTemplate();
        List<String> pro = userDao.getAllPro();
        List<String> citys = new ArrayList<>();
        for (String s : pro) {
            List<String> allStation = userDao.getAllStation(s);
            for (String s1 : allStation) {
                citys.add(s1);
            }
        }
        AdminControl adminControl = new AdminControl();
        List<String> gettrains = adminControl.gettrains();
        request.setAttribute("trainids",gettrains);
        request.setAttribute("station",citys);
        request.getRequestDispatcher("/addtrain.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
