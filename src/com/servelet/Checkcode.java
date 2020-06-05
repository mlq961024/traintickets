package com.servelet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/Checkcode")
public class Checkcode extends HttpServlet {
    public static  String code = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
    public static Random random = new Random();

    public static String getRandomString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(code.charAt((random.nextInt(code.length()))));
        return buffer.toString();
    }

    public static Color getRandomColor() {
        return new Color(random.nextInt(255), random.nextInt(255), random
                .nextInt(255));
    }

    public static Color getReverseColor(Color c) {
        return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c
                .getBlue());
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        response.setContentType("image/jpeg");
        StringBuffer randomString = new StringBuffer();
        int width = 100;
        int height = 40;
        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 19));
        g.setColor(getRandomColor());
        g.fillRect(0, 0, width, height);

        //写字
        for (int i = 1; i < 5; i++) {
            String randoms = getRandomString();
            g.setColor(getRandomColor());
            g.drawString(randoms,width/5*i , 22);
            randomString.append(randoms);
        }
        //画点
        for (int i = 0, n = random.nextInt(100); i < n; i++) {
            Color reverse = getRandomColor();
            g.setColor(reverse);
            g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
        }
        //画线

        String s = randomString.toString();
        //request.getSession().removeAttribute("randomString");
         request.getSession().setAttribute("randomString", s);
        ImageIO.write(bi,"jpg",response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
