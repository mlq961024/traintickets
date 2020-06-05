package com.dao.jdbcTemplate;


import com.bean.Rider;
import com.bean.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserTemplate extends TemplateFather{
    /**
     * user表查询
     */
    public User select(String id){
       // JdbcTemplate template = new JdbcTemplate(JdbcDruidUtils.getDs());
        try {
            String sql = "select * from user where id = ? ";
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),id);
        }catch (Exception e){
            return null;
        }
    }
    /**
     * user表添加
     */
    public void add(User user){

        String sql = "insert into user(id,password,balance,power,sex,birthday,psq,answer) values(?,?,?,?,?,?,?,?)";
        template.update(sql,user.getId(),user.getPassword(),user.getBalance(),user.getPower(),user.getSex(),user.getBirthday(),user.getPsq(),user.getAnswer());
    }

    /**
     *user表删除
     */
    public void delete(String id){
        String sql = "delete from user where id = ?";
        template.update(sql,id);
    }

    /**
     * user表修改
     */
    public void update(User user){
        String sql = "update user set password=?,balance=?,power=?,inform=? where id = ?";
        template.update(sql,user.getPassword(),user.getBalance(),user.getPower(),user.getInform(),user.getId());
    }

    /**
     * user添加消息
     */
    public void addinform(String id,String inform){
        User user = select(id);
        user.setInform(user.getInform()+"-"+inform);
        String sql = "update user set inform=? where id = ?";
        template.update(sql,user.getInform(),id);
    }

    /**
     * rider表查找
     */
    public List<Rider> selectRiders (String id){
        try {
            String sql = "select * from ridertable where mainid = ? ";
            List<Rider> query = template.query(sql, new BeanPropertyRowMapper<Rider>(Rider.class), id);
            return query;

        } catch (Exception e){
            return null;
        }
    }

    /**
     * rider表增加
     */
    public void addRider(Rider rider,String id){
        String sql = "insert into ridertable(mainid,riderid,ridername) values(?,?,?)";
        template.update(sql,id,rider.getRiderid(),rider.getRidername());
    }

    /**
     * rider表删除
     */
    public void deleteRider(String mainid,String riderid){
        String sql = "delete from ridertable where mainid = ? and riderid = ?";
        template.update(sql,mainid,riderid);
    }


    public Rider selectRider(String riderid, String mainid) {
        try {
            String sql = "select * from ridertable where mainid = ? and riderid = ?";
            return template.queryForObject(sql,new BeanPropertyRowMapper<Rider>(Rider.class),mainid,riderid);
        } catch (Exception e){
            return null;
        }
    }
    

    public int findTotalCount(String id) {
        //1.定义模板初始化sql
        String sql = "select count(*) from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
            //判断value是否有值
            if(id != null && !"".equals(id)){
                //有值
                sb.append(" and id like ? ");
                return template.queryForObject(sb.toString(),Integer.class,"%"+id+"%");
            }
        return template.queryForObject(sql,Integer.class);
    }

    public List<User> findByPage(int start, int rows, String id) {
        String sql = "select * from user  where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

            //判断value是否有值
        if(id != null && !"".equals(id)){
            //有值
            sb.append(" and id like ? ");
            sb.append(" limit ?,? ");
            return template.query(sb.toString(),new BeanPropertyRowMapper<User>(User.class),"%"+id+"%",start,rows);
        }
        sb.append(" limit ?,? ");
        return template.query(sb.toString(),new BeanPropertyRowMapper<User>(User.class),start,rows);
    }
}
