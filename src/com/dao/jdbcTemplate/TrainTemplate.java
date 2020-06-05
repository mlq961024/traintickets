package com.dao.jdbcTemplate;
import com.bean.Time;
import com.bean.Train;
import com.bean.TrainSelect;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import java.util.List;
import java.util.Map;

public class TrainTemplate extends TemplateFather {


    /**
     * 查所有车次名
     */
    public List<String> selectId() {
        String sql = "select id FROM train GROUP BY id";
        return template.queryForList(sql, String.class);
    }



    /**
     * 获取区间的ago
     */
    public List<String> getAgo(String id, int st, int et, String date) {
        //List<Map<String,Object>> s=null;
        try {
            String sql = "SELECT ago from train where id=? and `date`=? and" +
                    " timenum in (select n from (SELECT timenum n FROM train where" +
                    " timenum>=?and timenum<=?)tem)";
            return template.queryForList(sql, String.class, id, date, st, et);

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 查单次价格及数量
     */
    public Map<String, Object> selectPriceAndNum(String start, String end, String id, String date) {
        if (this.startSe(start, id, date) != 0 && this.endtSe(end, id, date) != 0) {
            try {
                String sql = "SELECT id,MIN(num) num,SUM(price) price,max(nowt) nowt from train where timenum >= ? AND timenum <=? and id=?";
                Map<String, Object> stringObjectMap = template.queryForMap(sql, this.startSe(start, id, date), this.endtSe(end, id, date), id);
               if(stringObjectMap.get("id")==null){
                   stringObjectMap=null;
               }
                return stringObjectMap;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 查开始时间
     */
    public String selectStartTime(String start, String id, String date) {
        try {
            String sql = "SELECT startt from time where id=? and trainid=?";
            return template.queryForObject(sql, String.class, this.startSe(start, id, date), id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 查到达时间
     */
    public String selectEndTime(String end, String id, String date) {
        try {
            String sql = "SELECT endt from time where id=? and trainid=?";
            return template.queryForObject(sql, String.class, this.endtSe(end, id, date), id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * train表查询区间各个num及ago
     */
    public Map<String, Object> select(String id, String date, int timenum) {
        try{
            String sql = " SELECT nowt,ago from train where id=? and `date`=? and timenum=?";
            return template.queryForMap(sql, id, date, timenum);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 查询某日所有车次表
     */
    public List<Train> select(String id) {
        try {
            String sql = " SELECT * from train where id=? group by date ";
            List<Train> query = template.query(sql, new BeanPropertyRowMapper<>(Train.class), id);
            return query;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Train> select(String id,String date) {
        try {
            String sql = " SELECT * from train where id=? and date = ?";
            List<Train> query = template.query(sql, new BeanPropertyRowMapper<>(Train.class), id,date);
            return query;
        } catch (Exception e) {
            return null;
        }
    }
        /**
         * 整体单个单天车次更新
         */
//        public void updateAllTrain (List <Train> trains) {
//            String sql = "update train set start=?,end=?,num=?,price=?,nowt=?,ago=? where id=?,timenum=?";
//            String sql1 = "insert into train (start,end,num,price,nowt,ago,id,timenum) values (?,?,?,?,?,?,?,?)";
//            String sql2 = "delete from train where id =? and timenum=?";
//            List<String> trainids=new ArrayList<>(); ///去重
//            for (Train train : trains) {
//                trainids.add(train.getId());
//            }
//            HashSet<String> setids = new HashSet<>();
//            setids.addAll(trainids);
//            for (String id : setids) {
//                int max = this.getMax(id, trains.get(0).getDate());//存储区车次站点最大值
//                int upmax= this.Maxupdate(trains,id);//修改后车次最大值
//                for (int i = 0; i < trains.size(); i++) {
//                    if (upmax < max) {//修改区单次车次是否比存储区最大值
//                        for (int j = 0; j < (max-upmax); j++) {//删除存储区多的东西
//                            template.update(sql2, id, max - i);
//                        }
//                    }else {
//                        if (trains.get(i).getTimenum() > max) {
//                            sql = sql1;
//                        }
//                        template.update(sql, trains.get(i).getStart(),
//                                trains.get(i).getEnd(),
//                                trains.get(i).getNum(), trains.get(i).getPrice(),
//                                trains.get(i).getNowt(), trains.get(i).getAgo(),
//                                trains.get(i).getId(), trains.get(i).getTimenum());
//                    }
//                }
//            }
//        }

    /**
     *train表单个修改
     */
    public void updateAgo(String ago,String id,String date,int timenum){
        String sql = " update train set ago=? where id=? and `date`=? and timenum=?";
        template.update(sql, ago,id, date, timenum);
    }

    /**
     * 票减一
     */
    public Boolean sub(String id,String start,String end,int i,String date){
        String sql = "update train set num = num+? where id=? and `date`=? and" +
                " timenum in (select n from (SELECT timenum n FROM train where" +
                " timenum>=? and timenum<=?)tem)";
        template.update(sql,i,id,date,this.startSe(start,id,date),this.endtSe(end,id,date));
        if((int)(this.selectPriceAndNum(start,end,id,date).get("num"))>=0){
            return true;
        }else{
            this.sub(start,end,id,1,date);
            return false;
        }
    }

    /**
     * 更新当前车票顺序位置
     */
    public void nowT(String id,String start,String end,int i,String date){
        String sql = "update train set nowt=? where id=? and `date`=? and" +
                " timenum in (select n from (SELECT timenum n FROM train where" +
                " timenum>=? and timenum<=?)tem)";
        template.update(sql,i,id,date,this.startSe(start,id,date),this.endtSe(end,id,date));
    }

    /**
     * 更新单日train和time表
     */
    public void updateTAndT(String s,List<Train> trains, List<Time> times){
        this.deleteTime(s);
        this.addtime(times);
        this.addtrain(trains);
    }
    /**
     * 添加train和time表
     */
    public void addtime(List<Time> times) {
        String sql = "insert into `time`(trainid,id,startt,endt) values(?,?,?,?)";
        for (Time time : times) {
            template.update(sql,time.getTrainid(),time.getId(),time.getStartt(),time.getEndt());
        }

    }

//添加一个
public void addtrain1(List<Train> trains) {
    String sql = "insert into `train`(id,start,end,date,timenum,price,num,nowt,ago) values(?,?,?,?,?,?,?,?,?)";
    for (Train train : trains) {
        template.update(sql,train.getId(),train.getStart(),train.getEnd(),train.getDate(),train.getTimenum(),
                train.getPrice(),train.getNum(),train.getNowt(),train.getAgo());
    }
}
    //初次添加
public void addtrain(List<Train> trains) {
    String sql = "insert into `train`(id,start,end,date,timenum,price,num,nowt,ago) values(?,?,?,?,?,?,?,?,?)";
    List<String> getdates = this.getdates();
    for (String getdate : getdates) {
        for (Train train : trains) {
            template.update(sql,train.getId(),train.getStart(),train.getEnd(),getdate,train.getTimenum(),
                    train.getPrice(),train.getNum(),train.getNowt(),train.getAgo());
        }
    }

}
    //更新添加
    public void adduptrain(List<Train> trains) {
        String sql = "insert into `train`(id,start,end,date,timenum,price,num,nowt,ago) values(?,?,?,?,?,?,?,?,?)";
        List<String> selectdate = this.getdates();
        for (String date : selectdate) {
            for (Train train : trains) {
                template.update(sql,train.getId(),train.getStart(),train.getEnd(),date,train.getTimenum(),
                        train.getPrice(),train.getNum(),train.getNowt(),train.getAgo());
            }
        }

    }


    public void addtrainid(String s) {
        String sql = "insert into trainid(id,trainline) values(?,?)";
        template.update(sql,s,null);
    }

    public void deleteTrainbyid(String id, String date) {
        String sql = "delete from train where id=? and date=?";
        template.update(sql,id,date);
    }

    public void updateAgos(TrainSelect train) {
        String sql = "update train set ago=? where id=? and `date`=? and" +
                " timenum =?";
        for (int i = train.getSt();i<=train.getEt();i++){
            template.update(sql,train.getAgo().get(i-train.getSt()),train.getId(),train.getDate(),i);
        }
    }
}


