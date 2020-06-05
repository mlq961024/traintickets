package com.dao.jdbcTemplate;

import com.bean.Station;
import com.utils.JdbcDruidUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class StationTemplate extends TemplateFather{
    /**
     *station表添加
     */
    public void add(Station station){
        String sql = "insert into station (city,province)values(?,?)";
        template.update(sql,station.getCity(),this.getPro(station.getProvince()));
    }
    /**
     * 删除
     */
    public void delete(Station station){
        String sql = "delete from station where city = ? and province = ?";
        template.update(sql,station.getCity(),this.getPro(station.getProvince()));
    }
    /**
     *获取城市
     */
    public int getPro(String pro){
        String sql = "select id from pro where province=?";
        return template.queryForObject(sql,Integer.class,pro);
    }
}
