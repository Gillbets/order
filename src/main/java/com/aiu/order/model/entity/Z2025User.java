package com.aiu.order.model.entity;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Z2025User
 * @Description TODO
 * @Author DH.WANG
 * @Date 2021/5/10 15:36
 * @Version 1.0
 **/

@Data
@DS("copy")
@TableName("z2025_users")
public class Z2025User {

    @TableId(type = IdType.AUTO)
    /**
     * id
     */
    private Integer id;

    /**
     * id
     */
    private String aiuskinid;

    /**
     * 用户名
     */
    private String account;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 微信昵称
     */
    private String nicknameWechat;

    /**
     * 性别
     */
    private String sex;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 年龄范围
     */
    private String agerange;

    /**
     * 皮肤油性
     */
    private String skinoiliness;

    /**
     * 皮肤敏感度
     */
    private String skinsensitivity;

    /**
     * 每天喝水量
     */
    private String waterintake;

    /**
     * 仪器01-皮肤弹性
     */
    private Integer i1Elasticity;

    /**
     * 仪器01-皮肤油份%
     */
    private Integer i1Oil;

    /**
     * 仪器01-皮肤油份%
     */
    private Integer i1Water;

    /**
     * 省份
     */
    private String province;

    /**
     * 区站号
     */
    private String stationNo;

    /**
     * 站名
     */
    private String stationName;

    /**
     * 开始月份
     */
    private Integer startmonth;

    /**
     * 结束月份
     */
    private Integer stopmonth;

    /**
     * 平均空气湿度
     */
    private Integer averagehumidity;

    /**
     * 改善方向
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
     * no9
     */
    private Integer no9;

    /**
     * no10
     */
    private Integer no10;

    /**
     * no11
     */
    private Integer no11;

    /**
     * no12
     */
    private Integer no12;

    /**
     * no13
     */
    private Integer no13;

    /**
     * no14
     */
    private Integer no14;

    /**
     * no15
     */
    private Integer no15;
}
