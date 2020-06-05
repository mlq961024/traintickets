package com.servelet;

import com.bean.TrainShow;
import com.dao.fuction.AdminControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddAlltrain")
public class AddAlltrain extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String trainid = request.getParameter("trainid");
        String date = request.getParameter("date");
        AdminControl adminControl = new AdminControl();
        List<String> getdates = adminControl.getdates(trainid);
        String date1=getdates.get(0);
        List<TrainShow> trainShow = adminControl.getTrainShow(date1,trainid);
      String s = adminControl.addtrain(trainShow,date);
      request.setAttribute("addtrain_msg",s);
      request.getRequestDispatcher("/AddtrainServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
