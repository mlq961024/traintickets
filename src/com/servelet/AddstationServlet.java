package com.servelet;

import com.dao.fuction.AdminControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addstationServlet")
public class AddstationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String pro = request.getParameter("pro");
        String city = request.getParameter("city");
        AdminControl adminControl = new AdminControl();
        String s = adminControl.insterStation(city, pro);
        request.setAttribute("addstation_msg",s);
        request.getRequestDispatcher("/cits.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
