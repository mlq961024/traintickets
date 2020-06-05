package com.dao.fuction;

import com.bean.Rider;
import com.bean.Ticket;
import com.bean.TrainSelect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class SellContol extends UserControl {
    /**
     * 1、未使用票退票
     * 2、未使用票改签
     * 3、普通购票*/
    /**
     * 退票
     */
//    public Boolean backTicket(Ticket tickets) {
//        java.util.Date date1 = new java.util.Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String date = simpleDateFormat.format(date1);
//        if (tickets.getUsering() == 0) {//未使用
//            if ((Integer.parseInt(tickets.getDate().split("-")[1]) <= Integer.parseInt(date.split("-")[1])) && ((Integer.parseInt(tickets.getDate().split("-")[2]) < Integer.parseInt(date.split("-")[2])))) {
//                for(int i = tickets.getSt();i<=tickets.getEt();i++){//从开设到结束的所有站
//                    Map<String, Object> select = trainDao.select(tickets.getId(), tickets.getDate(), i);//获取所有nowt、ago
//                    String str = (String)select.get("ago");
//                    StringBuffer s = new StringBuffer();
//                    if(str!=null){
//                        s.append(str);
//                    }
//                    s.append(tickets.getSatenum());
//                    //去重；
//                    //存入数据库
//                    this.clearsave(s.toString(),tickets.getId(),tickets.getDate(),i);
//                }
//                ticketDao.delete(tickets.getId());
//                trainDao.sub(tickets.getTrainid(), tickets.getStart(), tickets.getEnd(), 1, tickets.getDate());
//            }
//            return true;
//        }
//        return false;
//    }
//    public Boolean backTicket(Ticket tickets,String userid) {
//        java.util.Date date1 = new java.util.Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String date = simpleDateFormat.format(date1);
//        if (tickets.getUsering() == 0) {//未使用
//            if ((Integer.parseInt(tickets.getDate().split("-")[1]) <= Integer.parseInt(date.split("-")[1])) && ((Integer.parseInt(tickets.getDate().split("-")[2]) < Integer.parseInt(date.split("-")[2])))) {
//                this.addMoney(userid, tickets.getPrice());
//                trainDao.sub(tickets.getTrainid(), tickets.getStart(), tickets.getEnd(), 1, tickets.getDate());
//            }else {
//                this.addMoney(userid, tickets.getPrice()*0.8);
//                trainDao.sub(tickets.getTrainid(), tickets.getStart(), tickets.getEnd(), 1, tickets.getDate());
//            }
//            for(int i = tickets.getSt();i<=tickets.getEt();i++){//从开设到结束的所有站
//                Map<String, Object> select = trainDao.select(tickets.getId(), tickets.getDate(), i);//获取所有nowt、ago
//                String str = (String)select.get("ago");
//                StringBuffer s = new StringBuffer();
//                if(str!=null){
//                    s.append(str);
//                }
//                s.append(tickets.getSatenum());
//                //去重；
//                //存入数据库
//                this.clearsave(s.toString(),tickets.getId(),tickets.getDate(),i);
//            }
//            ticketDao.delete(tickets.getId());
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     *改签
//     */
//
//    public String changeTicket(Ticket oldTicket,TrainSelect train,String userid ) {//1:改签成功、2：余额不足、3：余票不足、4：已有车票验证冲突\5：票过期不能改，到售票处改、6、票已使用
//            if (this.backTicket(oldTicket,userid)) {
//                Rider rider = new Rider();
//                rider.setRiderid(oldTicket.getRiderid());
//                rider.setRidername(oldTicket.getName());
//                return this.buyTicket(train, rider);
//            }else {
//                return "票已使用！";
//            }
//    }
//
//    public String changeTicket(Ticket oldTicket,TrainSelect train) {//1:改签成功、2：余额不足、3：余票不足、4：已有车票验证冲突\5：票过期不能改，到售票处改、6、票已使用
//        if (this.backTicket(oldTicket)) {
//            Rider rider = new Rider();
//            rider.setRiderid(oldTicket.getRiderid());
//            rider.setRidername(oldTicket.getName());
//            return this.buyTicket(train, rider);
//        }else {
//            return "票已使用！";
//        }
//    }
//
//    /**
// * 购票
// */
//    public String buyTicket(TrainSelect train, Rider rider){//1:购票成功、2：余额不足、3：余票不足、4：已有车票验证冲突
//        //1：确定是否符合条件{是否已买过次车的票(同一天只能买同一车次一张票)}
//        if(this.buyed(train,rider.getRiderid())){//车票是否冲突
//            if(train.getNum()>0){//车票数量足够
//                    if(trainDao.sub(train.getId(),train.getStart(),train.getEnd(),-1,train.getDate())/*票减1*/){
//                        Ticket tickets = new Ticket();
//                        tickets.setId(this.produceTicket(train.getId()));
//                        tickets.setEnd(train.getEnd());
//                        tickets.setStart(train.getStart());
//                        tickets.setTrainid(train.getId());
//                        tickets.setRiderid(rider.getRiderid());
//                        tickets.setPrice(train.getPrice());
//                        tickets.setName(rider.getRidername());
//                        tickets.setSt(train.getSt());
//                        tickets.setEt(train.getEt());
//                        tickets.setDate(train.getDate());
//                        tickets.setUserid("车站售票");
//                        tickets.setUsering(0);
//                        tickets.setBuyed(1);
//                        tickets.setStartt(train.getStartt());
//                        tickets.setEndt(train.getEndt());
//                        if(train.getNowt()<100) {//是否正常买票（正常买票nowt+1）
//                            for(int i = train.getSt();i<=train.getEt();i++){//从开设到结束的所有站
//                                Map<String, Object> select = trainDao.select(train.getId(), train.getDate(), i);//获取所有nowt、ago
//                                int m =(Integer)select.get("nowt");//获取100-单站点对余票=当前票号(nowt是座位号)
//                                String str = (String)select.get("ago");
//                                if(m<train.getNowt()){//此站点对的上次位置在这次车票的最大位置之前
//                                    System.out.println(m+train.getNowt());
//                                    StringBuffer s = new StringBuffer();
//                                    if(str!=null){
//                                        s.append(str);
//                                    }
//                                    for(int j =m+1;j<train.getNowt()+1;j++){
//                                        s.append(j);
//                                        if (j!=train.getNowt()){
//                                            s.append("-");
//                                        }
//                                    }
//                                    //去重；
//                                    //存入数据库
//                                    this.clearsave(s.toString(),train.getId(),train.getDate(),i);
//                                }
//                            }
//                            trainDao.nowT(train.getId(), train.getStart(), train.getEnd(), train.getNowt() + 1, train.getDate());//座位+1
//                            tickets.setSate(this.producSate(train.getNowt()+1,train.getId()));
//                            tickets.setSatenum(train.getNowt()+1);
//                        }else{
//                            //检测ago中余票
//                            String ticketnum =  this.useago(train);
//                            if(ticketnum==null||"".equals(ticketnum)){//没有符合
//                                trainDao.sub(train.getId(),train.getStart(),train.getEnd(),1,train.getDate());
//                                return "余票不足!";
//                            }else {
//                                tickets.setSatenum(Integer.parseInt(ticketnum));
//                                tickets.setSate(this.producSate(Integer.parseInt(this.useago(train)),train.getId()));
//                            }
//                        }
//                        ticketDao.add(tickets);
//                        return "成功!";
//                    }else {
//                        return "余票不足!";
//                    }
//            }
//            else {
//                return "余票不足!";
//            }
//        }
//        else {
//            return"已有车票验证冲突!";
//        }
//    }
//
    public Boolean backTicket(Ticket tickets) {
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(date1);
        if ((Integer.parseInt(tickets.getDate().split("-")[1]) <= Integer.parseInt(date.split("-")[1])) || tickets.getUsering() == 0) {
            if (tickets.getBuyed() == 1) {
                String userid = tickets.getUserid();
                if (!"车站售票".equals(userid)) {
                    this.addMoney(userid, tickets.getPrice());
                }
            }
            //ago增加
            for (int i = tickets.getSt(); i <= tickets.getEt(); i++) {//从开设到结束的所有站
                Map<String, Object> select = trainDao.select(tickets.getId(), tickets.getDate(), i);//获取所有nowt、ago
                StringBuffer s = new StringBuffer();
                if (select != null) {
                    String str = (String) select.get("ago");
                    if (str != null) {
                        s.append(str);
                    }
                }
                s.append(tickets.getSatenum());
                //去重；
                //存入数据库
                this.clearsave(s.toString(), tickets.getId(), tickets.getDate(), i);
            }
            ticketDao.delete(tickets.getId());
            trainDao.sub(tickets.getTrainid(), tickets.getStart(), tickets.getEnd(), 1, tickets.getDate());

            return true;
        }
        return false;
    }

    public String changeTicket(Ticket oldTicket, TrainSelect train) {//1:改签成功、2：余额不足、3：余票不足、4：已有车票验证冲突\5：票过期不能改，到售票处改、6、票已使用
        Rider rider = new Rider();
        rider.setRiderid(oldTicket.getRiderid());
        rider.setRidername(oldTicket.getName());
        String buyTicket = this.buyTicket(train, rider);
        String ticketid = buyTicket.split(":")[1];
        if (buyTicket.contains("购票成功")) {
            if (!this.backTicket(oldTicket)) {
                this.backTicket(this.findticketbyid(ticketid));//退掉新买的票
                return "票已失效";
            }
            return "改签成功";
        } else {
            return "改签失败";
        }
    }

    public String buyTicket(TrainSelect train, Rider rider) {//1:购票成功、2：余额不足、3：余票不足、4：已有车票验证冲突

        //1：确定是否符合条件{是否已买过次车的票(同一天只能买同一车次一张票)
        if (this.buyed(train, rider.getRiderid())) {//车票是否冲突
            if (train.getNum() > 0) {//车票数量足够
                Ticket tickets = new Ticket();
                tickets.setId(this.produceTicket(train.getId()));
                tickets.setEnd(train.getEnd());
                tickets.setStart(train.getStart());
                tickets.setTrainid(train.getId());
                tickets.setUserid("车站售票");
                tickets.setPrice(train.getPrice());
                tickets.setName(rider.getRidername());
                tickets.setRiderid(rider.getRiderid());
                tickets.setSt(train.getSt());
                tickets.setEt(train.getEt());
                tickets.setDate(train.getDate());
                tickets.setStartt(train.getStartt());
                tickets.setEndt(train.getEndt());
                tickets.setUsering(0);
                tickets.setBuyed(1);
                if (train.getNowt() < 100) {//是否正常买票（正常买票nowt+1）
                    for (int i = train.getSt(); i <= train.getEt(); i++) {//从开设到结束的所有站
                        Map<String, Object> select = trainDao.select(train.getId(), train.getDate(), i);//获取所有nowt、ago
                        int m = (Integer) select.get("nowt");//获取100-单站点对余票=当前票号(nowt是座位号)
                        String str = (String) select.get("ago");
                        if (m < train.getNowt()) {//此站点对的上次位置在这次车票的最大位置之前
                            StringBuffer s = new StringBuffer();
                            if (str != null) {
                                s.append(str);
                            }
                            for (int j = m + 1; j < train.getNowt() + 1; j++) {
                                s.append(j);
                                if (j != train.getNowt()) {
                                    s.append("-");
                                }
                            }
                            //去重；
                            //存入数据库
                            this.clearsave(s.toString(), train.getId(), train.getDate(), i);
                        }
                    }
                    trainDao.nowT(train.getId(), train.getStart(), train.getEnd(), train.getNowt() + 1, train.getDate());//座位+1
                    tickets.setSate(this.producSate(train.getNowt() + 1, train.getId()));
                    tickets.setSatenum(train.getNowt() + 1);
                } else {
                    //检测ago中余票
                    String ticketnum = this.useago(train);
                    if (ticketnum == null || "".equals(ticketnum)) {//没有符合
                        return "余票不足!";
                    } else {
                        tickets.setSatenum(Integer.parseInt(ticketnum));
                        tickets.setSate(this.producSate(Integer.parseInt(this.useago(train)), train.getId()));
                    }
                }
                if (tickets.getSate() != null && !"".equals(tickets.getSate())) {//是否锁定购票
                    return "购票成功！";
                }else {
                    return "余票不足!";
                }
            } else {
                return "余票不足！";
            }
        } else {
            return "已有车票验证冲突!";
        }
    }

}
