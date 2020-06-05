package com.dao.jdbcTemplate;

import com.bean.Time;
import com.bean.Train;
import com.utils.JdbcDruidUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TemplateFather {

    JdbcTemplate template = new JdbcTemplate(JdbcDruidUtils.getDs());

    /**
     *根据城市，车次，日期选择出 开始的timenum
     */
    public int startSe(String city,String id,String date){
        try{
            String sql="SELECT timenum from train where `start`=(SELECT id FROM station WHERE city = ?) and id =?and `date`=?";
            return template.queryForObject(sql, Integer.class, city, id, date);
        }catch (Exception e){
            return 0;
        }
    }

    /**
     *根据城市，车次，日期选择出 结束的timenum
     */
    public int endtSe(String city,String id,String date) {
        try{
            String sql="SELECT timenum from train where `end`=(SELECT id FROM station WHERE city = ?) and id =?and `date`=?";
            return template.queryForObject(sql, Integer.class, city, id, date);
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * 获取所有站点
     */
    public List<String> getAllStation(String pro){
        String sql = "select city from station where province =(select id from pro where province = ?)";
        return template.queryForList(sql, String.class,pro);
    }



/**
 * 获取所有省份
 * */

    public List<String> getAllPro(){
        String sql = "select province from pro ";
        return template.queryForList(sql, String.class);
    }

    /**
     * 获得最大站点
     */
    public int getMax(String id,String date){
        String sql = "select max(timenum) timenum from train where id=?and date=?";
        return template.queryForObject(sql,Integer.class,id,date);
    }

    /**
     * station表查询
     */
    //id推算城市名
    public String selectStation(int city){
        String sql = "select city from station where id=?";
        return template.queryForObject(sql,String.class,city);
    }
    //城市名推算id
    public int selectStation(String city){
        String sql = "select id from station where city=?";
        return template.queryForObject(sql,Integer.class,city);
    }

    /**
     *
     */

    /**
     *获取时间表
     */
    public List<Time> getTime(String trainid){
        String sql = "select * from `time` where trainid = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(Time.class),trainid);
    }

    /**
     * 删除时间表
     */
    public void deleteTime(String trainid){
        String sql = "delete from time where trainid=?";
        template.update(sql,trainid);
    }

    /**
     *更新时间表
     */
    public void updateTime(List<Time> times){
        this.deleteTime(times.get(0).getTrainid());
        String sql = "insert into time (trainid,id,startt,endt) values (?,?,?,?)";
        for (Time time : times) {
            template.update(sql,time.getTrainid(),time.getId(),time.getStartt(),time.getEndt());
        }

    }

    /**
     * 改变后list中单车次最大站点值
     */
    public int Maxupdate(List<Train> trains,String id){
        int max=0;
        for (Train train : trains) {
            if(train.getId().equals(id)){
                if(max<train.getTimenum()){
                    max=train.getTimenum();
                }
            }
        }
        return max;
    }

    /**
     * 获取所有日期
     */
    public List<String> selectdate() {
        String sql = "select `date` FROM train GROUP BY `date`";
        return template.queryForList(sql, String.class);
    }
    public List<String> getdatebytrainid(String id) {
        String sql = "select `date` FROM train where id=? GROUP BY `date`";
        return template.queryForList(sql, String.class,id);
    }
    protected List<String> getdates() {
        List<String> dates = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date time = new Date();
        Calendar cal = Calendar.getInstance();
        for (int i =0;i<31;i++){
            cal.setTime(time);
            cal.add(Calendar.DATE,i);
            String date1 = simpleDateFormat.format(cal.getTime());
        }
        return dates;
    }
}
