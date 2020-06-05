package com.dao.fuction;

import com.bean.Ticket;
import com.bean.Train;
import com.bean.TrainSelect;
import com.bean.User;
import com.dao.jdbcTemplate.StationTemplate;
import com.dao.jdbcTemplate.TicketTemplate;
import com.dao.jdbcTemplate.TrainTemplate;
import com.dao.jdbcTemplate.UserTemplate;

import java.util.*;

public class ControlFather {
    TrainTemplate trainDao = new TrainTemplate();
    UserTemplate userDao = new UserTemplate();
    StationTemplate stationDao = new StationTemplate();
    TicketTemplate ticketDao = new TicketTemplate();


    /**
     * 获取用户信息
     */
    public User select(String id){
        return userDao.select(id);
    }

    /**
     *重组信息字符串
     */
    public String contuer (List<String> informs){
        StringBuffer s =null;
        for (int i = 0; i < informs.size(); i++) {
            s.append(informs.get(i));
            if(i<informs.size()-1){
                s.append("-");
            }
        }
        return s.toString();
    }

    /**
     * 票号随机生成方法
     */
    public String produceTicket(String s) {
        StringBuilder str = new StringBuilder(s);
        str.append((int)(Math.random()*1000));
        System.out.println(str);
        long l = System.currentTimeMillis()/10000000;
        str.append(l);//添加时间戳
        return str.toString();//生成票号
    }

    /**
     * 座位号生成方法(一车20人一共5车厢)
     */
    public String producSate(int num,String id){

        int a = ((num-1)/20)+1;//几车
        int b = (((num-1%20))/5)+1;//几排
        int c = ((num-1%20)%5)+1;//几座
        String s="" ;
        switch (c){
            case 1:
                s="A";
                break;
            case 2:
                s="B";
                break;
            case 3:
                s="C";
                break;
            case 4:
                s="D";
                break;
            case 5:
                s="E";
                break;
        }
        StringBuilder str = new StringBuilder(id+"次列车 ");
        str.append(a);
        str.append("车");
        str.append(b);
        str.append(s);
        str.append("座");
        return str.toString();
    }


    /**
     * 花钱
     */
    public Boolean consume(String id,double money){
        User user = this.select(id);
        if(money<=user.getBalance()){
            user.setBalance(user.getBalance()-money);
            userDao.update(user);
            return true;
        }else {
            return false;
        }
    }

    /**
     * 去重
     */
    public void clearsave(String ago,String id,String date,int timenum){
        String[] split = ago.split("-");
        List<String> strings=new ArrayList<>();
        for (String s : split) {
            strings.add(s);
        }
        HashSet<String> set = new HashSet<>();
        set.addAll(strings);
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<String> it = set.iterator();
        while (it.hasNext()){
            stringBuffer.append(it.next());
            stringBuffer.append("-");
        }
        trainDao.updateAgo(stringBuffer.toString(),id,date,timenum);


    }
    /**
     * 解析火车ago
     */
    public String useago(TrainSelect train){
        String sate=null;
        List<String> ago = train.getAgo();
        String[]s1=ago.get(0).split("-");
        List<String> boxs  = Arrays.asList(s1);
//找到
        for (String box : boxs) {
            a:for (int i = 0; i < ago.size(); i++) {
                if (!ago.get(i).contains(box)){
                    break a;
                }
                if (i == ago.size()-1){
                    sate=box;
                }
            }
        }
//删除
        List<String> newago = new ArrayList<>();
        if(sate!=null){
            for (String s:ago) {
                s = s.replace("-" + sate, "");
                newago.add(s);
            }
            train.setAgo(newago);
        }
        trainDao.updateAgos(train);

//        for (int i = 0; i < ago.size(); i++) {
//            String[]s1=ago.get(i).split("-");
//            boxs.add(Arrays.asList(s1));
//        }
//        //找出sate
//        List<String> box = boxs.get(0);
//            for (String s : box) {
//                a:for (List<String> box1 : boxs) {
//                    if(!this.judge(s,box1)){
//                        break a;
//                    }
//                    sate=s;
//                }
//            }
//        StringBuffer buffer = new StringBuffer();
//        List<String> buffers= new ArrayList<>();
////            删除sate
//        for (int i = 0; i < boxs.size(); i++) {
//                if(this.judge(sate,boxs.get(i))){
//                    boxs.get(i).remove(sate);
//                }
//            for (int j = 0; j <  boxs.get(i).size(); j++) {
//                buffer.append(boxs.get(i))
//            }
//        }

        //写入数据库

        return sate;
    }
    /**
     * 判断一个字符是否在数组或列表里
     */
    public Boolean judge(String s,String[]ss){
        for (int i = 0; i < ss.length; i++) {
            if(s.equals(ss[i])){
                return true;
            }
        }
        return false;
    }
    public Boolean judge(String s,List<String> ss){
        for (int i = 0; i < ss.size(); i++) {
            if(s.equals(ss.get(i))){
                return true;
            }
        }
        return false;
    }

    /**
     * 查询指定车次
     */
    public List<TrainSelect> selectTrain(String start, String end, String date){
        List<TrainSelect> list=new ArrayList<TrainSelect>();
        List<String> boxs = trainDao.selectId();//获取所有车次
        for (String box : boxs) {
            TrainSelect train = new TrainSelect();
            Map<String, Object> box1 = trainDao.selectPriceAndNum(start,end,box,date);//获取单个车次符合条件的值
            String startt = trainDao.selectStartTime(start,box,date);
            String endt = trainDao.selectEndTime(end,box,date);
            if (box1!=null&&startt!=null&&endt!=null){//查询结果不为空
                train.setId((String) box1.get("id"));
                train.setStart(start);
                train.setEnd(end);
                train.setStartt(startt);
                train.setNowt((Integer)box1.get("nowt"));
                train.setEndt(endt);
                train.setDate(date);
                train.setSt(trainDao.startSe(start,(String) box1.get("id"),date));
                train.setEt(trainDao.endtSe(end,(String) box1.get("id"),date));
                train.setAgo(trainDao.getAgo((String)box1.get("id"),train.getSt(),train.getEt(),date));
                train.setPrice((Double) box1.get("price"));
                train.setNum((int) box1.get("num"));
                list.add(train);
            }
        }
        return list;
    }
//获取单个车次
public TrainSelect selectoneTrain(TrainSelect train) {//String start, String end, String date, String startt, String endt, String trainid
    Map<String, Object> box1 = trainDao.selectPriceAndNum(train.getStart(),train.getEnd(),train.getId(), train.getDate());//获取单个车次符合条件的值
    String startt = trainDao.selectStartTime(train.getStart(),train.getId(), train.getDate());
    String endt = trainDao.selectEndTime(train.getEnd(), train.getId(),train.getDate());
    train.setStartt(startt);
    train.setNowt((Integer)box1.get("nowt"));
    train.setEndt(endt);
    train.setSt(trainDao.startSe(train.getStart(),train.getId(), train.getDate()));
    train.setEt(trainDao.endtSe(train.getEnd(),train.getId(), train.getDate()));
    train.setAgo(trainDao.getAgo(train.getId(),train.getSt(),train.getEt(),train.getDate()));
    train.setPrice((Double) box1.get("price"));
    train.setNum((int) box1.get("num"));
    return train;
}

    public TrainSelect selectoneTrain(String start, String end, String date, String startt, String endt,String trainid) {
        List<TrainSelect> trainSelects = this.selectTrain(start, end, date);
        TrainSelect trainSelect = new TrainSelect();
        for (TrainSelect select : trainSelects) {
            if (trainid.equals(select.getId())&&startt.equals(select.getStartt())&&endt.equals(select.getEndt())){
                trainSelect = select;
            }
        }
        return trainSelect;
    }
  //根据票号获取车票
  public Ticket findticketbyid(String id){
        return ticketDao.selectbyId(id);
  }


    protected Integer selectTrainNum(TrainSelect train) {
        Map<String, Object> stringObjectMap = trainDao.selectPriceAndNum(train.getStart(), train.getEnd(), train.getId(), train.getDate());
        return (Integer) stringObjectMap.get("num");
    }
}
