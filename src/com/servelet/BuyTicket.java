package com.servelet;

import com.bean.Rider;
import com.bean.TrainSelect;
import com.bean.User;
import com.dao.fuction.SellContol;
import com.dao.fuction.UserControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/buyTicket")
public class BuyTicket extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String riderid = request.getParameter("riderid");
        String ridername = request.getParameter("ridername");
        User user = (User) session.getAttribute("user");
        UserControl userControl = new UserControl();
        String mainid =user.getId();
        Rider rider = new Rider();
        if(user.getPower()!=0){
            mainid="车站售票";
            rider.setRiderid(riderid);
            rider.setRidername(ridername);
            userControl.addRider(rider,"车站售票");
        }else {
             rider = userControl.getRider(riderid, mainid);
        }

        TrainSelect train = (TrainSelect) session.getAttribute("willbuytrain");

        String buyticket_msg="";
        if (user.getPower()==1){//售票员
            SellContol sellContol = new SellContol();
            buyticket_msg = sellContol.buyTicket(train,rider);
        }else{
            buyticket_msg = userControl.buyTicket(train, rider, mainid);
        }
        request.setAttribute("buyticket_msg",buyticket_msg);
        request.getRequestDispatcher("/buyticket.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
