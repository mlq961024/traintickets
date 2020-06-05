package com.dao.fuction;

import com.bean.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserControl extends ControlFather {
    /**
     * 1、登陆
     * 2、注册
     * 3、销户
     * 4、修改密码
     * 5、查看个人车票
     * 6、查看余额
     * 7、查看消息
     * 8、删除消息
     * 9、忘记密码，找回
     * 10、添加乘车人
     * 11、充值
     * 12、购票
     * 13、退票
     * 14、改签
     */

    /**
     * 登陆
     */
    public User login(String id, String password){
        User user = userDao.select(id);
        if(user!=null&&password.equals(user.getPassword())){//登陆成功
            return user;
        }else{
            return null;//登陆失败

        }
    }

    /**
     * 注册
     */
//    public Boolean register(User user){
//        if(userDao.select(user.getId())==null){
//            userDao.add(user);
//            return true;
//        }else{
//            return false;
//        }
//
//    }

    /**
     * 销户
     */
    public void deleteUser(String id){
        userDao.delete(id);
    }

    /**
     * 修改密码
     */
//    public void updatePassword(String id, String password){
//        User user = this.select(id);
//        user.setPassword(password);
//        userDao.update(user);
//    }
    public void updateUser(User user){
        userDao.update(user);
    }
    /**
     * 查看个人车票
     */
    public List<Ticket> seeMyTicket(String id){
        List<Ticket> list = ticketDao.select(id);
        return list;
    }
    public List<Ticket> seeriderTicket(String id){
        List<Ticket> list = ticketDao.selectrider(id);
        return list;
    }
    public PageBean<Ticket> seeMyTicket(String _currentPage, String _rows, String id){
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);


        //1.创建空的PageBean对象
        PageBean<Ticket> pb = new PageBean<Ticket>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数
        int totalCount = ticketDao.findtcketCount(id);
        pb.setTotalCount(totalCount);
        //4.调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<Ticket> list =ticketDao.select(start,rows,id);
        pb.setList(list);
        System.out.println(list);
        //5.计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);
        return pb;

    }

    public PageBean<Ticket> seeriderTicket(String _currentPage, String _rows, String id){
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);


        //1.创建空的PageBean对象
        PageBean<Ticket> pb = new PageBean<Ticket>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数
        int totalCount = ticketDao.findtcketCountr(id);
        pb.setTotalCount(totalCount);
        //4.调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<Ticket> list =ticketDao.selectr(start,rows,id);
        pb.setList(list);
        System.out.println(list);
        //5.计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);
        return pb;

    }
    //查看我的余额(登陆后全部再user类中)

    /**
     * 获取通知
     */
    public List<String> getInform(String informs){
        List<String> strings=null;
        String[] split = informs.split("-");
        for (String s : split) {
            strings.add(s);
        }
        return strings;
    }

    /**
     * 删除通知
     */
    public void deleteInform(User user,int i){
        List<String> informs=this.getInform(user.getInform());
        informs.remove(informs.get(i));
        user.setInform(this.contuer(informs));
        userDao.update(user);
    }

    /**
     * 忘记密码，找回
     */
//    public String getBackPassword(User user,String answer){
//        if(answer.equals(user.getAnswer())){
//            return user.getPassword();
//        }else {
//            return null;
//        }
//    }

    /**
     *获取乘车人
     * @return
     */
    public List<Rider> getRiders(String id){
        List<Rider> riders = userDao.selectRiders(id);
        if(riders.isEmpty()){
            riders=null;
        }
        return riders;
    }

    /**
     *添加乘车人
     */
    public String addRider(Rider rider,String id){
        if(this.getRider(rider.getRiderid(),id)==null){
            userDao.addRider(rider,id);
            return "添加成功";
        }else {
            return "乘车人已存在请勿再次添加";
        }

    }

    /**
     * 删除乘车人
     */
    public void deleteRider(Rider rider,String id){
        userDao.deleteRider(id,rider.getRiderid());
    }

    /**
     * 充值
     */
    //充值
    public String addMoney(String id,double money){
        User user = this.select(id);
        user.setBalance(user.getBalance()+money);
        userDao.update(user);
        return "充值成功";
    }

    /**
     * 判断方法同一天同一车，是否有冲突的票
     */
    public Boolean buyed(TrainSelect train, String id){
        //获取已买的票
        List<Ticket> tickets = this.seeriderTicket(id);
        //获取将买票的时间
        int st = train.getSt();
        int et = train.getEt();
        String trainid = train.getId();
        if(!tickets.isEmpty()){
            for (Ticket ticket : tickets) {
                if(trainid.equals(ticket.getTrainid())&&train.getDate().equals(ticket.getDate())&&
                        (!((train.getSt()>ticket.getEt())||(train.getEt()<ticket.getSt())))){
                    return false;
                }
            }
        }else{
            return true;
        }
        return true;
    }

    /**
     * 购票
     */
    public String buyTicket( TrainSelect train,Rider rider,String userId){//1:购票成功、2：余额不足、3：余票不足、4：已有车票验证冲突


//        //1：确定是否符合条件{是否已买过次车的票(同一天只能买同一车次一张票)}
//        if(this.buyed(train,rider.getRiderid())){//车票是否冲突
//            if(train.getNum()>0){//车票数量足够
//                Ticket tickets = new Ticket();
//                tickets.setId(this.produceTicket(train.getId()));
//                tickets.setEnd(train.getEnd());
//                tickets.setStart(train.getStart());
//                tickets.setTrainid(train.getId());
//                tickets.setUserid(userId);
//                tickets.setPrice(train.getPrice());
//                tickets.setName(rider.getRidername());
//                tickets.setRiderid(rider.getRiderid());
//                tickets.setSt(train.getSt());
//                tickets.setEt(train.getEt());
//                tickets.setDate(train.getDate());
//                tickets.setStartt(train.getStartt());
//                tickets.setEndt(train.getEndt());
//                tickets.setUsering(0);
//                tickets.setBuyed(0);
//                if(train.getNowt()<100) {//是否正常买票（正常买票nowt+1）
//                    for(int i = train.getSt();i<=train.getEt();i++){//从开设到结束的所有站
//                        Map<String, Object> select = trainDao.select(train.getId(), train.getDate(), i);//获取所有nowt、ago
//                        int m =(Integer)select.get("nowt");//获取100-单站点对余票=当前票号(nowt是座位号)
//                        String str = (String)select.get("ago");
//                        if(m<train.getNowt()){//此站点对的上次位置在这次车票的最大位置之前
//                            StringBuffer s = new StringBuffer();
//                            if(str!=null){
//                                s.append(str);
//                            }
//                            for(int j =m+1;j<train.getNowt()+1;j++){
//                                s.append(j);
//                                if (j!=train.getNowt()){
//                                    s.append("-");
//                                }
//                            }
//                            //去重；
//                            //存入数据库
//                            this.clearsave(s.toString(),train.getId(),train.getDate(),i);
//                        }
//                    }
//                    trainDao.nowT(train.getId(), train.getStart(), train.getEnd(), train.getNowt() + 1, train.getDate());//座位+1
//                    tickets.setSate(this.producSate(train.getNowt()+1,train.getId()));
//                    tickets.setSatenum(train.getNowt()+1);
//                }
//                else{
//                    //检测ago中余票
//                    String ticketnum =  this.useago(train);
//                    if(ticketnum==null||"".equals(ticketnum)){//没有符合
//                        trainDao.sub(train.getId(),train.getStart(),train.getEnd(),1,train.getDate());
//                        this.addMoney(userId,train.getPrice());
//                        return "余票不足!";
//                    }else {
//                        tickets.setSatenum(Integer.parseInt(ticketnum));
//                        tickets.setSate(this.producSate(Integer.parseInt(this.useago(train)),train.getId()));
//                    }
//                }
//                if (this.select(userId).getBalance()-train.getPrice()>=0){//余额是否够买
//                    if(trainDao.sub(train.getId(),train.getStart(),train.getEnd(),-1,train.getDate())/*票减1*/){//票是否够
//                        this.consume(userId,train.getPrice());//花钱
//                        tickets.setBuyed(1);
//                        ticketDao.add(tickets);
//                        return "成功!";
//                    }
//                    else {
//                        return "余票不足!";
//                    }
//                } else {
//                    ticketDao.add(tickets);
//                    return "余额不足!已添加进车票，请稍后去我的车票付款";
//                }
//            }else {
//                return " 余票不足!";
//            }
//        }
//        else {
//            return"已有车票验证冲突!";
//        }
        //1：确定是否符合条件{是否已买过次车的票(同一天只能买同一车次一张票)}
        if(this.buyed(train,rider.getRiderid())) {//车票是否冲突
            if (train.getNum() > 0) {//车票数量足够
                Ticket tickets = new Ticket();
                tickets.setId(this.produceTicket(train.getId()));
                tickets.setEnd(train.getEnd());
                tickets.setStart(train.getStart());
                tickets.setTrainid(train.getId());
                tickets.setUserid(userId);
                tickets.setPrice(train.getPrice());
                tickets.setName(rider.getRidername());
                tickets.setRiderid(rider.getRiderid());
                tickets.setSt(train.getSt());
                tickets.setEt(train.getEt());
                tickets.setDate(train.getDate());
                tickets.setStartt(train.getStartt());
                tickets.setEndt(train.getEndt());
                tickets.setUsering(0);
                tickets.setBuyed(0);
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
                    trainDao.sub(train.getId(), train.getStart(), train.getEnd(), -1, train.getDate());/*票减1*/
                    if (this.selectTrainNum(train) >= 0) {//有票
                        if (this.select(userId).getBalance() - train.getPrice() >= 0) {//余额是否够买
                            this.consume(userId, train.getPrice());//花钱
                            tickets.setBuyed(1);
                            ticketDao.add(tickets);
                            return "购票成功!票号:"+tickets.getId();
                        } else {
                            return "购票成功，余额不足!已添加进车票，请稍后去我的车票付款。票号:"+tickets.getId();
                        }
                    } else {
                        trainDao.sub(train.getId(), train.getStart(), train.getEnd(), 1, train.getDate());
                        return " 余票不足!";
                    }
                } else {
                    return " 余票不足!";
                }
            } else {
                return "余票不足！";
            }
        }else {
            return"已有车票验证冲突!";
        }
    }


    /**
     *退票
     */
    public Boolean backTicket(Ticket tickets){
//        java.util.Date date1 = new java.util.Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String date = simpleDateFormat.format(date1);
//        if((Integer.parseInt(tickets.getDate().split("-")[1]) <= Integer.parseInt(date.split("-")[1]))&&((Integer.parseInt(tickets.getDate().split("-")[2]) < Integer.parseInt(date.split("-")[2])))){
//            this.addMoney(tickets.getUserid(),tickets.getPrice());
//            //ago增加
//            for(int i = tickets.getSt();i<=tickets.getEt();i++){//从开设到结束的所有站
//                Map<String, Object> select = trainDao.select(tickets.getId(), tickets.getDate(), i);//获取所有nowt、ago
//                StringBuffer s = new StringBuffer();
//                if(select !=null){
//                    String str = (String)select.get("ago");
//                    if(str!=null){
//                        s.append(str);
//                    }
//                }
//
//                    s.append(tickets.getSatenum());
//                    //去重；
//                    //存入数据库
//                    this.clearsave(s.toString(),tickets.getId(),tickets.getDate(),i);
//            }
//            ticketDao.delete(tickets.getId());
//            trainDao.sub(tickets.getTrainid(),tickets.getStart(),tickets.getEnd(),1,tickets.getDate());
//            return true;
//        }
//        return
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(date1);
        if((Integer.parseInt(tickets.getDate().split("-")[1]) <= Integer.parseInt(date.split("-")[1]))&&((Integer.parseInt(tickets.getDate().split("-")[2]) < Integer.parseInt(date.split("-")[2])))||tickets.getUsering()==0){
            if(tickets.getBuyed()==1) {
                this.addMoney(tickets.getUserid(),tickets.getPrice());
            }
            //ago增加
            for(int i = tickets.getSt();i<=tickets.getEt();i++){//从开设到结束的所有站
                Map<String, Object> select = trainDao.select(tickets.getId(), tickets.getDate(), i);//获取所有nowt、ago
                StringBuffer s = new StringBuffer();
                if(select !=null){
                    String str = (String)select.get("ago");
                    if(str!=null){
                        s.append(str);
                    }
                }
                s.append(tickets.getSatenum());
                //去重；
                //存入数据库
                this.clearsave(s.toString(),tickets.getId(),tickets.getDate(),i);
            }
            ticketDao.delete(tickets.getId());
            trainDao.sub(tickets.getTrainid(),tickets.getStart(),tickets.getEnd(),1,tickets.getDate());

            return true;
        }
        return false;
    }

    /**
     *改签
     */
    public String changeTicket(Ticket oldTicket,TrainSelect train ) {//1:改签成功、2：余额不足、3：余票不足、4：已有车票验证冲突\5：票过期不能改，到售票处改
//        if (this.select(oldTicket.getUserid()).getBalance() + oldTicket.getPrice() >= train.getPrice()) {//钱够
//            if (this.backTicket(oldTicket)) {
//                Rider rider = new Rider();
//                rider.setRiderid(oldTicket.getRiderid());
//                rider.setRidername(oldTicket.getName());
//                return this.buyTicket(train, rider,oldTicket.getUserid());
//            }else {
//                return "票过期不能改，到售票处改!";
//            }
//        } else {
//            return "余额不足！无法改签";
//        }
        if (this.select(oldTicket.getUserid()).getBalance() + oldTicket.getPrice() >= train.getPrice()) {//钱够
            Rider rider = new Rider();
            rider.setRiderid(oldTicket.getRiderid());
            rider.setRidername(oldTicket.getName());
            String buyTicket = this.buyTicket(train, rider, oldTicket.getUserid());
            String ticketid = buyTicket.split(":")[1];
            if (buyTicket.contains("购票成功")) {
                if(!this.backTicket(oldTicket)) {
                    this.backTicket(this.findticketbyid(ticketid));//退掉新买的票
                    return "票过期不能改，到售票处处理!";
                }
                if(this.findticketbyid(ticketid).getBuyed()==0) {
                    this.consume(oldTicket.getUserid(),train.getPrice());
                    this.payticket(ticketid);
                }
                return "改签成功";
            } else {
                return "改签失败";
            }
        } else {
            return "余额不足！无法改签";
        }
    }

    /*
     * 枫叶查询
     * */
    public PageBean<TrainSelect> findTrainPage(String date, String start, String end, String _currentPage, String _rows) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        if(currentPage <=0) {
            currentPage = 1;
        }
        PageBean<TrainSelect> pb=new PageBean<>();
        List<TrainSelect> train = new ArrayList<>();
        List<TrainSelect> trainSelects = this.selectTrain(start, end, date);
        int totalCount = trainSelects.size();
        int m = currentPage*rows;
        if(m>totalCount){
             m = totalCount;
        }

        for (int i = (currentPage-1)*rows; i <m; i++) {
             train.add(trainSelects.get(i));
        }
        pb.setList(train);
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        pb.setTotalCount(m);
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        if(totalPage==0){
            totalPage=1;
        }
        pb.setTotalPage(totalPage);

        return pb;
    }


    public Rider getRider(String riderid, String mainid) {
        return userDao.selectRider(riderid,mainid);
    }

    public void delSelectedUser(String[] ids) {
        if(ids != null && ids.length > 0){
            //1.遍历数组
            for (String id : ids) {
                //2.调用dao删除
                userDao.delete(id);
            }
        }

    }

    public void payticket(String id) {
        ticketDao.updateticket(id);
    }
}
