package com.aiu.order.model.vo;

import lombok.Data;

/**
 * @ClassName ExcelImport
 * @Description TODO
 * @Author DH.WANG
 * @Date 2021/6/19 15:22
 * @Version 1.0
 **/
@Data
public class ExcelImport {
    private String orderId;
    private String userAccount;
    private String userName;
    private Integer uId;
    private String receiveName;
    private String receiveMobile;
    private String receiveAddress;
}
