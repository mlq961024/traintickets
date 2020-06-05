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

@WebServlet("/findtrainServlet")
public class FindtrainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdminControl adminControl = new AdminControl();
        List<String> gettrains = adminControl.gettrains();
        List<String> getdates = adminControl.getdates();
        request.setAttribute("trainids",gettrains);

        String trainid = request.getParameter("trainid");
        String date = getdates.get(0);
        if(trainid==null||"0".equals(trainid)){
            trainid="D1001";
        }
        List<TrainShow> trainShow = adminControl.getTrainShow(date,trainid);
        request.setAttribute("trainShows",trainShow);
        request.setAttribute("size",trainShow.size());
        request.setAttribute("trainid",trainid);
        request.getRequestDispatcher("/trainupdate.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
