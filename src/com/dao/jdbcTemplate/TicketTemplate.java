package com.dao.jdbcTemplate;

import com.bean.Ticket;
import com.utils.JdbcDruidUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class TicketTemplate extends TemplateFather {

    /**
     * tickets表查询
     */
    public  Ticket selectbyId(String id){
        String sql = "select * from tickets where id=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(Ticket.class),id);
    }
    //指定用户车票信息
    public List<Ticket> select(String userid){
        String sql = "select * from tickets where userid=?";
        return template.query(sql, new BeanPropertyRowMapper<Ticket>(Ticket.class),userid);
    }
    //指定用户车票信息
    public List<Ticket> selectrider(String riderid){
        String sql = "select * from tickets where riderid=?";
        return template.query(sql, new BeanPropertyRowMapper<Ticket>(Ticket.class),riderid);
    }
    //分页查询用户车票信息
    public List<Ticket> select(int start, int rows, String id) {
        String sql = "select * from tickets where userid = ? limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<Ticket>(Ticket.class),id,start,rows);
    }
    public List<Ticket> selectr(int start, int rows, String id) {
        String sql = "select * from tickets where riderid = ? limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<Ticket>(Ticket.class),id,start,rows);
    }
    //所有车票信息
    public List<Ticket> select(int start, int rows,Integer usering) {
        String sql = "select * from tickets where usering = ? limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<Ticket>(Ticket.class),usering,start,rows);

    }

    /**
     *tickets表添加
     */
    public void add(Ticket ticket){
        String sql = "INSERT INTO tickets(`id`, `trainid`, `userid`, `start`, `end`, `price`, `st`, `et`, `name`, `startt`, `endt`, `sate`, `date`, `usering`,`riderid`,`satenum`,`buyed`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        template.update(sql,ticket.getId(),
                ticket.getTrainid(),
                ticket.getUserid(),
                ticket.getStart(),
                ticket.getEnd(),
                ticket.getPrice(),
                ticket.getSt(),
                ticket.getEt(),
                ticket.getName(),
                ticket.getStartt(),
                ticket.getEndt(),
                ticket.getSate(),
                ticket.getDate(),
                ticket.getUsering(),
                ticket.getRiderid(),
                ticket.getSatenum(),ticket.getBuyed());


    }

    /**
     *tickets表删除
     */
    public void delete(String id){
        String sql = "delete from tickets where id=?";
        template.update(sql,id);
    }

    /**
     *tickets表修改
     */
    public void update(Ticket tickets,String id){
        String sql = "update tickets set id=?,trainid=?,sate = ?,start=?,end=?,price=?,st=?,et=? where id=?";
        template.update(sql,tickets.getId(),tickets.getTrainid(),tickets.getSate(),tickets.getStart(),tickets.getEnd(),tickets.getPrice(),tickets.getSt(),tickets.getEt(),id);
    }
//获取总记录数
    public int findtcketCount(String id) {
        String sql = "select count(*) from tickets where userid = ?";
        return template.queryForObject(sql,Integer.class,id);
    }
    public int findtcketCount(Integer usering) {
        String sql = "select count(*) from tickets where usering = ? ";
        return template.queryForObject(sql,Integer.class,usering);
    }
    public int findtcketCountr(String id) {
        String sql = "select count(*) from tickets where riderid = ?";
        return template.queryForObject(sql,Integer.class,id);
    }


    public void updateticket(String id) {
        String sql = "update ticket set buyed=? where id = ?";
        template.update(sql,1,id);
    }
}
