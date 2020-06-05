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

@WebServlet("/register")
public class RegistServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        String randomString = (String) session.getAttribute("randomString");
        session.removeAttribute("randomString");
        String checkcode = req.getParameter("checkcode");
        if(!randomString.equalsIgnoreCase(checkcode)){
            req.setAttribute("regist_msg","验证码错误请重试！");
            req.getRequestDispatcher("/regist.jsp").forward(req,resp);
            return;
        }
        String useid = req.getParameter("useid");
        String sex = req.getParameter("sex");
        String password = req.getParameter("password");
        String psq = req.getParameter("psq");
        String answer = req.getParameter("answer");
        String brithday = req.getParameter("brithday");
        UserTemplate ust = new UserTemplate();
        if(ust.select(useid)!=null){
            req.setAttribute("regist_msg","用户已存在！");
            req.getRequestDispatcher("/regist.jsp").forward(req,resp);
            return;
        }
        User user = new User();
        user.setId(useid);
        user.setPassword(password);
        user.setSex(sex);
        user.setPsq(psq);
        user.setBirthday(brithday);
        user.setAnswer(answer);
        ust.add(user);
        req.getRequestDispatcher("/login.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doGet(req,resp);
    }
}
