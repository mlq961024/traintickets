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
import java.util.*;

@WebServlet("/addRiderServlet")
public class AddRiderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String riderid = request.getParameter("riderid");
        String ridername = request.getParameter("ridername");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String usreid = user.getId();
        if(user.getPower()==1){
            usreid="车站售票";
        }
        UserControl userControl = new UserControl();
        Rider rider =  new Rider();
        rider.setRiderid(riderid);
        rider.setRidername(ridername);
        System.out.println(rider);
        String addRider = userControl.addRider(rider, usreid);
        if(addRider.equals("添加成功")){
            List<Rider> riders = (List<Rider>) session.getAttribute("riders");
            if(riders==null){
              riders = new ArrayList<Rider>();
            }
            riders.add(rider);
            session.removeAttribute("riders");
            session.setAttribute("riders",riders);
        }
        request.setAttribute("addrider_msg",addRider);
        request.getRequestDispatcher("/addrider.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
