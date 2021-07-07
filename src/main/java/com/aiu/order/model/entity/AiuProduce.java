package com.aiu.order.model.entity;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Aiu私定生产
 * @Description TODO
 * @Author DH.WANG
 * @Date 2021/7/6 15:12
 * @Version 1.0
 **/
@Data
@DS("local")
@TableName("aiu_produce")
public class AiuProduce implements Serializable {



    @TableId(type = IdType.AUTO)
    /**
     * id
     */
    private Integer id;

    /**
     * productno
     */
    private Integer productno;

    /**
     * shopid
     */
    private Integer shopid;

    /**
     * aiuskinid
     */
    private String aiuskinid;

    /**
     * real_name
     */
    private String realName;

    /**
     * account
     */
    private String account;

    /**
     * postname
     */
    private String postname;

    /**
     * postphone
     */
    private String postphone;

    /**
     * postaddress
     */
    private String postaddress;

    /**
     * price
     */
    private Integer price;

    /**
     * paytime
     */
    private Date paytime;

    /**
     * hope
     */
    private String hope;

    /**
     * no1
     */
    private Integer no1;

    /**
     * no2
     */
    private Integer no2;

    /**
     * no3
     */
    private Integer no3;

    /**
     * no4
     */
    private Integer no4;

    /**
     * no5
     */
    private Integer no5;

    /**
     * no6
     */
    private Integer no6;

    /**
     * no7
     */
    private Integer no7;

    /**
     * no8
     */
    private Integer no8;

    /**
     * 生产状态:0.未生产1.已生产5.正在生产
     */
    private Integer productionstatus;


}
