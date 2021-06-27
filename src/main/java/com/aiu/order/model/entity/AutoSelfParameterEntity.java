package com.aiu.order.model.entity;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName AutoSelfParameterEntity
 * @Description TODO
 * @Author DH.WANG
 * @Date 2021/5/10 15:36
 * @Version 1.0
 **/

@Data
@DS("z2025")
@TableName("auto_selfparameter")
public class AutoSelfParameterEntity {
    private String id;

    /**
     * name
     */
    private String name;

    /**
     * type
     */
    private String type;

    /**
     * low
     */
    private String low;

    /**
     * high
     */
    private String high;

    /**
     * no1
     */
    private String no1;

    /**
     * no2
     */
    private String no2;

    /**
     * no3
     */
    private String no3;

    /**
     * no4
     */
    private String no4;

    /**
     * no5
     */
    private String no5;

    /**
     * no6
     */
    private String no6;

    /**
     * no7
     */
    private String no7;

    /**
     * no8
     */
    private String no8;
}
