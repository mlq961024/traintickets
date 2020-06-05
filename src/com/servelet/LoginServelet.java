package com.servelet;

import com.bean.Rider;
import com.bean.User;
import com.dao.fuction.UserControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServelet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        String id = req.getParameter("login-username");
        String page = req.getParameter("page");
        String password = req.getParameter("login-password");
        UserControl userControl = new UserControl();
        User user = userControl.login(id, password);
        if(user==null){
            //弹出对话框或显示 用户名或密码错误
            req.setAttribute("login_msg","用户名或密码错误！");
            //跳转登录页面
            if("adminlogin".equals(page)){
                req.getRequestDispatcher("/adminlogin.jsp").forward(req,resp);
            }else {
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }else {
            //传回user
            //登录成功
            //将用户存入session

            //跳转页面
            session.setAttribute("user",user);
            if(user.getPower()==2){
                resp.sendRedirect(req.getContextPath()+"/admin.jsp");
            }else {
                //获取乘车人
                List<Rider> rider = userControl.getRiders(user.getId());
                session.setAttribute("riders",rider);
                resp.sendRedirect(req.getContextPath()+"/index.jsp");
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
