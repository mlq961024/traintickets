package com.servelet;

import com.bean.User;
import com.dao.fuction.UserControl;
import com.dao.jdbcTemplate.UserTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/CheckAnwser")
public class CheckAnwser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String questionAnswer = (String) session.getAttribute("QuestionAnswer");
        session.removeAttribute("QuestionAnswer");
        String answer = request.getParameter("answer");
        if(questionAnswer==null||!questionAnswer.equals(answer)){
            request.setAttribute("lost_msg","密保答案错误请重试！");
            request.getRequestDispatcher("/lostpass.jsp").forward(request,response);
            return;
        }
        //修改密码
        response.sendRedirect(request.getContextPath()+"/password.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
