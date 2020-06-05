package com.servelet;

import com.bean.TrainShow;
import com.dao.fuction.AdminControl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addtrain")
public class Addtrain extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String[] starts = request.getParameterValues("start");
        String[] ends = request.getParameterValues("end");
        String[] startts = request.getParameterValues("startt");
        String[] endts = request.getParameterValues("endt");
        String[] nowts = request.getParameterValues("nowt");
        String[] timenums = request.getParameterValues("timenum");
        String[] agos = request.getParameterValues("ago");
        String[] prices = request.getParameterValues("price");
        String[] nums = request.getParameterValues("num");
        String id = request.getParameter("id");
        List<TrainShow> trainShows = new ArrayList<>();
        for (int i = 0; i < starts.length; i++) {
            TrainShow trainShow = new TrainShow();
            trainShow.setTimenum(Integer.parseInt(timenums[i]));
            trainShow.setAgo(agos[i]);
            trainShow.setStart(starts[i]);
            trainShow.setEnd(ends[i]);
            trainShow.setPrice(Double.parseDouble(prices[i]));
            trainShow.setNum(Integer.parseInt(nums[i]));
            trainShow.setId(id);
            trainShow.setEndt(endts[i]);
            trainShow.setNowt(Integer.parseInt(nowts[i]));
            trainShow.setStartt(startts[i]);
            trainShows.add(trainShow);
        }
        AdminControl adminControl =new AdminControl();
        adminControl.addTrainAndTime(trainShows);
        request.getRequestDispatcher("/addtrain.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
