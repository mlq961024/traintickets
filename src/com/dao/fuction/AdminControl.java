package com.dao.fuction;

import com.bean.*;

import java.util.ArrayList;
import java.util.List;

public class AdminControl extends ControlFather {

    /**
     * 1、显示日历
     * 2、显示所有车次信息
     * 3、查询指定日期的车次（1个月以内）
     * 4、修改指定日期，车次列表，添加车次，删除车次
     * 5、添加站点
     * 6、修改站点
     * 7、清理已过期车票（影响用户体验）
     * 8、清理已过期车次（循环使用，修改日期）
     * 9、一键清理所有
     * 10、修改权限
     */

    /**
     * 获取trainshow表
     */
    public List<TrainShow> getTrainShow(String date,String trainId){
        List<TrainShow> boxs = new ArrayList<>();//最终输出
            List<Train> trains = trainDao.select(trainId, date);
            List<Time> times = trainDao.getTime(trainId);
            for (int i = 0; i < trains.size(); i++) {
                TrainShow trainShow = new TrainShow();
                trainShow.setDate(trains.get(i).getDate());
                trainShow.setEnd(trainDao.selectStation(trains.get(i).getEnd()));
                trainShow.setEndt(times.get(i).getEndt());
                trainShow.setId(trainId);
                trainShow.setNum(trains.get(i).getNum());
                trainShow.setPrice(trains.get(i).getPrice());
                trainShow.setStart(trainDao.selectStation(trains.get(i).getStart()));
                trainShow.setStartt(times.get(i).getStartt());
                trainShow.setNowt(trains.get(i).getNowt());
                trainShow.setAgo(trains.get(i).getAgo());
                trainShow.setTimenum(trains.get(i).getTimenum());
                boxs.add(trainShow);
            }
        return boxs;
    }


    /**
     * 更新train和time表
     */
    public void updateTrainAndTime(List<TrainShow> trainShows){
        List<Train> trains = new ArrayList<>();
        List<Time> times = new ArrayList<>();
        String s="";
        for (TrainShow trainShow : trainShows) {
            Train train = new Train();
            Time time = new Time();
            s=trainShow.getId();
            train.setNowt(trainShow.getNowt());
            train.setAgo(trainShow.getAgo());
            train.setEnd(trainDao.selectStation(trainShow.getEnd()));
            train.setId(trainShow.getId());
            train.setNum(trainShow.getNum());
            train.setPrice(trainShow.getPrice());
            train.setStart(trainDao.selectStation(trainShow.getStart()));
            train.setTimenum(trainShow.getTimenum());
            time.setEndt(trainShow.getEndt());
            time.setId(trainShow.getTimenum());
            time.setStartt(trainShow.getStartt());
            time.setTrainid(trainShow.getId());
            times.add(time);
            trains.add(train);
        }
        trainDao.updateTAndT(s,trains,times);
    }
    public void addTrainAndTime(List<TrainShow> trainShows) {
        List<Train> trains = new ArrayList<>();
        List<Time> times = new ArrayList<>();
        String s="";
        for (TrainShow trainShow : trainShows) {
            Train train = new Train();
            Time time = new Time();
            s=trainShow.getId();
            train.setNowt(trainShow.getNowt());
            train.setAgo(trainShow.getAgo());
            train.setEnd(trainDao.selectStation(trainShow.getEnd()));
            train.setId(trainShow.getId());
            train.setNum(trainShow.getNum());
            train.setPrice(trainShow.getPrice());
            train.setStart(trainDao.selectStation(trainShow.getStart()));
            train.setTimenum(trainShow.getTimenum());
            time.setEndt(trainShow.getEndt());
            time.setId(trainShow.getTimenum());
            time.setStartt(trainShow.getStartt());
            time.setTrainid(trainShow.getId());
            times.add(time);
            trains.add(train);
        }
        this.addTAndT(s,trains,times);
    }
    /**
     * 删除站点
     */
    public void deleteStation(String city,String pro){
        stationDao.delete(new Station(city,pro));
    }

    //添加站点
    public String insterStation(String city,String pro){
        //判断是否有站点
        if(!this.judge(city,(String[]) stationDao.getAllStation(pro).toArray())){
            //添加
            Station station = new Station();
            station.setProvince(pro);
            station.setCity(city);
            stationDao.add(station);
            return "添加成功";
        }else {
            //该城市已存在
            return "该城市已存在";
        }

    }

    /**
     *修改权限
     */
    public void updatePower(String id ,int power){
        User user = this.select(id);
        user.setPower(power);
        userDao.update(user);

    }



    public PageBean<User> findUserByPage(String _currentPage, String _rows, String id) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if(currentPage <=0) {
            currentPage = 1;
        }
        //1.创建空的PageBean对象
        PageBean<User> pb = new PageBean<User>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数
        int totalCount = userDao.findTotalCount(id);
        pb.setTotalCount(totalCount);
        //4.调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<User> list = userDao.findByPage(start,rows,id);
        pb.setList(list);

        //5.计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);


        return pb;
    }

    public PageBean<Station> findstaionPage(String pro, String _currentPage, String _rows) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        if(currentPage <=0) {
            currentPage = 1;
        }
        List<Station> stations = new ArrayList<>();
        List<String> proo = userDao.getAllPro();
        for (String s : proo) {
            List<String> allStation = userDao.getAllStation(s);
            for (String s1 : allStation) {//若没有城市不执行该循环

                Station station= new Station();
                station.setProvince(s);
                station.setCity(s1);
                stations.add(station);
            }
        }
        List<Station> citys = new ArrayList<>();
        if(pro!=null&&!"".equals(pro)){
            for (Station station : stations) {
                if(station.getProvince().equals(pro)){
                    citys.add(station);

                }
            }
        }else {
            citys=stations;
        }
        PageBean<Station> pb=new PageBean<>();
        int totalCount = citys.size();
        int m = currentPage*rows;
        if(m>totalCount){
            m = totalCount;
        }


        List<Station> arrayList = new ArrayList<>();
       if (!citys.isEmpty()){
           for (int i = (currentPage-1)*rows; i < m; i++) {
               arrayList.add(citys.get(i));
           }
       }

        pb.setList(arrayList);
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        pb.setTotalCount(totalCount);
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        if(totalPage==0){
            totalPage=1;
        }
        pb.setTotalPage(totalPage);

        return pb;
    }


    public List<String> gettrains() {
        List<String> strings = trainDao.selectId();
        return strings;
    }
    public List<String> getdates() {
        List<String> strings = trainDao.selectdate();
        return strings;
    }
    public List<String> getdates(String trainid) {
        List<String> strings = trainDao.getdatebytrainid(trainid);
        return strings;
    }

    //获取展示信息
    public List<Trainsimpol> getTrainsimpols() {
        List<Trainsimpol> tpl = new ArrayList<>();
        //获取所有车次id
        List<String> gettrains = this.gettrains();
        //获取所有日期

        for (String gettrain : gettrains) {
            List<String> getdates = this.getdates(gettrain);
            for (String getdate : getdates) {
                Trainsimpol tl = new Trainsimpol();
                List<Train> trains = trainDao.select(gettrain,getdate);
                StringBuilder sb = new StringBuilder();
                for (Train train : trains) {
                    sb.append(stationDao.selectStation(train.getStart()));
                    sb.append("-");
                    if (train.getTimenum()==trains.size()){
                        sb.append(stationDao.selectStation(train.getEnd()));
                    }
                }
                tl.setStation(sb.toString());
                tl.setDate(getdate);
                tl.setTrainid(gettrain);
                tpl.add(tl);
            }
        }
        return tpl;
    }

    public PageBean<Trainsimpol> finddelateinform(String date, String _currentPage, String _rows) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        if(currentPage <=0) {
            currentPage = 1;
        }
        PageBean<Trainsimpol> pb=new PageBean<>();
        List<Trainsimpol> train = new ArrayList<>();
        List<Trainsimpol> trainsimpols = this.getTrainsimpols();//全部信息

        if("".equals(date)||date==null){
            train=trainsimpols;
        }else {
            for (Trainsimpol trainsimpol : trainsimpols) {
                if(trainsimpol.getDate().equals(date)){
                    train.add(trainsimpol);//添加符合信息
                }
            }
        }

        int totalCount = train.size();
        int m = currentPage*rows;
        if(m>totalCount){
            m = totalCount;
        }
        List<Trainsimpol> train0 = new ArrayList<>();
        for (int i = (currentPage-1)*rows; i < m; i++) {
            train0.add(train.get(i));
        }
        pb.setList(train0);
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        pb.setTotalCount(totalCount);
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        if(totalPage==0){
            totalPage=1;
        }
        pb.setTotalPage(totalPage);

        return pb;
    }

    public void deltimebyid(String id) {
        stationDao.deleteTime(id);
    }


//初次添加
    private void addTAndT(String s,List<Train> trains, List<Time> times) {
        trainDao.addtrainid(s);
        trainDao.addtime(times);
        trainDao.addtrain(trains);
    }

    public PageBean<Ticket> selectAllticket(String _currentPage, String _rows,Integer usering) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);


        //1.创建空的PageBean对象
        PageBean<Ticket> pb = new PageBean<Ticket>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数
        int totalCount = ticketDao.findtcketCount(usering);
        pb.setTotalCount(totalCount);
        //4.调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<Ticket> list =ticketDao.select(start,rows,usering);
        pb.setList(list);
        System.out.println(list);
        //5.计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);
        return pb;

    }

    public void delticket(String id) {
        ticketDao.delete(id);
    }

    public String addtrain(List<TrainShow> trainShows, String date) {
        List<Train> trains = new ArrayList<>();
        List<Time> times = new ArrayList<>();

        String s=trainShows.get(0).getId();
        List<String> dates = trainDao.getdatebytrainid(s);
        if (this.judge(date,dates)){
            return "当天车次已存在";
        }
        for (TrainShow trainShow : trainShows) {
            Train train = new Train();
            Time time = new Time();

            train.setNowt(trainShow.getNowt());
            train.setDate(date);
            train.setAgo(trainShow.getAgo());
            train.setEnd(trainDao.selectStation(trainShow.getEnd()));
            train.setId(trainShow.getId());
            train.setNum(trainShow.getNum());
            train.setPrice(trainShow.getPrice());
            train.setStart(trainDao.selectStation(trainShow.getStart()));
            train.setTimenum(trainShow.getTimenum());
            trains.add(train);
        }
        trainDao.addtrain1(trains);
        return "添加成功";
    }

    public void deltrainbyid(String id,String date) {
        trainDao.deleteTrainbyid(id,date);
    }
}
