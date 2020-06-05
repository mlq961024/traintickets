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

@WebServlet("/delRidersServlet")
public class DelRidersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] ids = request.getParameterValues("uid");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String userId = user.getId();
        session.removeAttribute("riders");
        //2.调用service删除
        UserControl userControl = new UserControl();
        for (String id : ids) {
            Rider rider = userControl.getRider(id, userId);
            userControl.deleteRider(rider,userId);
        }
        List<Rider> riders = userControl.getRiders(userId);
        session.setAttribute("riders",riders);
        request.getRequestDispatcher("/myrider.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
