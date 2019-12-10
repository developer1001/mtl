package com.zgc.mtl.config.log;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @program: CTW
 * @description: 系统日志，采用aop模式
 * @author: laoyangtou
 * @create: 2018-08-13 13:50
 **/
@Entity
@Table(name = "t_syslog")
public class SysLog implements Serializable {
    private static final long serialVersionUID = -9125833859668088905L;
    private Integer id;
    private Integer user_id;
    private Timestamp time = new Timestamp(new Date().getTime());
    private String method;
    private String description;
    private String ip;
    private String broswer;
    private String os;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBroswer() {
        return broswer;
    }

    public void setBroswer(String broswer) {
        this.broswer = broswer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", time=" + time +
                ", method='" + method + '\'' +
                ", description='" + description + '\'' +
                ", ip='" + ip + '\'' +
                ", broswer='" + broswer + '\'' +
                ", os='" + os + '\'' +
                '}';
    }
}
