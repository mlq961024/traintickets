package com.servelet;

import com.bean.User;
import com.dao.fuction.UserControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/updateUser")
public class UpdateUser extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        String id = req.getParameter("userid");
        String sex = req.getParameter("sex");
        String password = req.getParameter("password");
        String psq = req.getParameter("psq");
        String answer = req.getParameter("answer");
        String brithday = req.getParameter("brithday");
        UserControl userControl = new UserControl();
        User user = userControl.select(id);
        user.setPassword(password);
        user.setSex(sex);
        user.setPsq(psq);
        user.setBirthday(brithday);
        user.setAnswer(answer);
        userControl.updateUser(user);
        req.setAttribute("updateUser_msg","修改成功！");
        req.getRequestDispatcher("/updateuser.jsp").forward(req,resp);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
