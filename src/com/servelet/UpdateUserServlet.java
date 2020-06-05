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
import java.util.Enumeration;

@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        String randomString = (String) session.getAttribute("randomString");
        session.removeAttribute("randomString");
        String checkcode = req.getParameter("checkcode");
        if(!randomString.equalsIgnoreCase(checkcode)){
            req.setAttribute("updateUser_msg","验证码错误请重试！");
            req.getRequestDispatcher("/changeinfor.jsp").forward(req,resp);
            return;
        }
        String sex = req.getParameter("sex");
        String password = req.getParameter("password");
        String psq = req.getParameter("psq");
        String answer = req.getParameter("answer");
        String brithday = req.getParameter("brithday");
        User user = (User) session.getAttribute("user");
        user.setPassword(password);
        user.setSex(sex);
        user.setPsq(psq);
        user.setBirthday(brithday);
        user.setAnswer(answer);
        UserControl userControl = new UserControl();
        userControl.updateUser(user);
        req.setAttribute("updateUser_msg","修改成功！");
        req.getRequestDispatcher("/changeinform.jsp").forward(req,resp);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
