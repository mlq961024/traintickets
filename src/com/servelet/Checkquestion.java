package com.servelet;

import com.bean.User;
import com.dao.jdbcTemplate.UserTemplate;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet("/Checkquestion")
public class Checkquestion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");
        response.setContentType("image/jpeg");
        request.setCharacterEncoding("utf-8");
        int width = 315;
        int height = 40;
        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics g = bi.getGraphics();
        //设置画笔颜色为灰色
        g.setColor(new Color(0xF0F8FF));
        //填充图片
        g.fillRect(0,0, width,height);
        //获取问题人
        String question = request.getParameter("question");
        UserTemplate usedao = new UserTemplate();
        User userq = usedao.select(question);
        System.out.println(userq);
        String psq;
        if(userq==null) {
            psq = "用户不存在！";
        }else{
            psq = "密保问题：" + userq.getPsq();
            String answer = userq.getAnswer();
            request.getSession().setAttribute("QuestionAnswer",answer);
            request.getSession().setAttribute("lostpassword",userq.getPassword());
        }
        g.setColor(new Color(0x757575));
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        g.drawString(psq,10,24);
        ImageIO.write(bi,"jpg",response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
