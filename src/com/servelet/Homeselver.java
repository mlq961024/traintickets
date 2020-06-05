package com.servelet;

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

@WebServlet("/feimao.com")
public class Homeselver extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserTemplate userDao = new UserTemplate();
        List<String> pro = userDao.getAllPro();
        HashMap<String, List<String>> citys = new HashMap<>();
        for (String s : pro) {
            List<String> allStation = userDao.getAllStation(s);
            citys.put(s,allStation);
        }
        session.setAttribute("citys",citys);
        response.sendRedirect(request.getContextPath()+"/index.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
