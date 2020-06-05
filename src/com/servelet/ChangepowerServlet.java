package com.servelet;

import com.dao.fuction.AdminControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changepowerServlet")
public class ChangepowerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("ids");
        int power = Integer.parseInt(request.getParameter("power"));
        AdminControl adminControl = new AdminControl();
        adminControl.updatePower(id,power);
//        request.getRequestDispatcher("/findUserByPageServlet?page=power").forward(request,response);
        response.sendRedirect(request.getContextPath()+"/findUserByPageServlet?page=power");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
