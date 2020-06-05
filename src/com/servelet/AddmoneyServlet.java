package com.servelet;

import com.dao.fuction.UserControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addmoneyServlet")
public class AddmoneyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        double money = Double.parseDouble(request.getParameter("money"));
        String userid = request.getParameter("userid");
        UserControl userControl = new UserControl();
        String addmoney_msg = userControl.addMoney(userid, money);
        request.setAttribute("addmoney_msg",addmoney_msg);
        request.getRequestDispatcher("/addmoney.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
