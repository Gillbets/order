package com.aiu.order.controller;

import com.aiu.order.model.entity.AutoSelfParameterEntity;
import com.aiu.order.model.entity.YxUserEntity;
import com.aiu.order.model.entity.Z2025User;
import com.aiu.order.model.vo.ExcelImport;
import com.aiu.order.service.autoself.AutoSelfParameterService;
import com.aiu.order.service.yxuser.YxUserService;
import com.aiu.order.service.z2025.Z2025UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Api("admin")
@RestController
@RequestMapping("/admin")
public class OrderController {

    @Resource
    AutoSelfParameterService autoSelfParameterService;
    @Resource
    Z2025UserService z2025UserService;
    @Resource
    YxUserService yxUserService;

    private static String getKey(HashMap<String, Integer> map, Integer value) {
        String key = "";
        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                key = entry.getKey();
            }
        }
        return key;
    }

    @ApiOperation("导入数据并计算")
    @PostMapping("/execute")
    public List<AutoSelfParameterEntity> importData(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);

            //读取第一个工作表(这里的下标与list一样的，从0开始取，之后的也是如此)
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            int maxRow = sheet.getLastRowNum();
            System.out.println("总行数为：" + maxRow);
            XSSFRow title = sheet.getRow(0);
            HashMap<String, Integer> map = new HashMap<>();
            for (int i = 0; i < title.getLastCellNum(); i++) {
                map.put(title.getCell(i).toString(), i);
            }
            List<ExcelImport> importList = new ArrayList<>();
            for (int row = 1; row <= maxRow; row++) {
                //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
                int maxRol = sheet.getRow(row).getLastCellNum();
                System.out.println("--------第" + row + "行的数据如下--------");
                ExcelImport entity = new ExcelImport();
                for (int rol = 0; rol < maxRol; rol++) {
                    XSSFCell cell = sheet.getRow(row).getCell(rol);
                    String key = getKey(map, rol);// map.get(rol);
                    if (key.equals("订单号")) {
                        entity.setOrderId(cell.getRawValue());
                    }
                    if (key.equals("客户编号")) {
                        entity.setUserAccount(cell.getRawValue());
                    }
                    if (key.equals("客户名称")) {
                        entity.setUserName(cell.getStringCellValue());
                    }
                    if (key.equals("会员ID")) {
                        entity.setUId(Integer.valueOf(cell.getRawValue()));
                    }
                    if (key.equals("收货人")) {
                        entity.setReceiveName(cell.getStringCellValue());
                    }
                    if (key.equals("联系电话")) {
                        entity.setReceiveMobile(cell.getRawValue());
                    }
                    if (key.equals("收货地址")) {
                        entity.setReceiveAddress(cell.getStringCellValue());
                    }


                }
                importList.add(entity);

            }
            List<AutoSelfParameterEntity> auto = autoSelfParameterService.list();
            List<String> userAccountList = importList.stream().map(ExcelImport::getUserAccount).collect(Collectors.toList());
            List<YxUserEntity> userEntityList = yxUserService.list(new QueryWrapper<YxUserEntity>().in("account", userAccountList));
            List<String> aiuIdList = userEntityList.stream().map(YxUserEntity::getAiuskinid).collect(Collectors.toList());
            List<Z2025User> z2025Users = z2025UserService.list(new QueryWrapper<Z2025User>().in("aiuskinid", aiuIdList));
            for (String s : userAccountList) {
                List<String> skinOiliness = new ArrayList<>();
                List<String> skinSensitivity = new ArrayList<>();
                List<String> agerange = new ArrayList<>();
                List<String> averageHumidity = new ArrayList<>();
                List<String> sex = new ArrayList<>();
                List<String> drink = new ArrayList<>();
                List<String> i1Water = new ArrayList<>();
                List<String> i1Oil = new ArrayList<>();
                List<String> i1Elasticity = new ArrayList<>();
                Optional<YxUserEntity> first = userEntityList.stream().filter(x -> x.getAccount().equals(s)).findFirst();
                first.ifPresent(x -> {
                    String aiuskinid = x.getAiuskinid();
                    Optional<Z2025User> z2025 = z2025Users.stream().filter(z -> z.getAiuskinid().equals(aiuskinid)).findFirst();
                    z2025.ifPresent(z -> {
                        /*
                          油量
                         */
                        Optional<AutoSelfParameterEntity> oil = auto.stream().filter(b -> b.getId().contains("50")
                                && z.getSkinoiliness().contains(b.getLow())).findFirst();
                        oil.ifPresent(a -> {
                            skinOiliness.add(a.getNo1());
                            skinOiliness.add(a.getNo2());
                            skinOiliness.add(a.getNo3());
                            skinOiliness.add(a.getNo4());
                            skinOiliness.add(a.getNo5());
                            skinOiliness.add(a.getNo6());
                            skinOiliness.add(a.getNo7());

                        });
                        /*
                          敏感
                         */
                        Optional<AutoSelfParameterEntity> sensitivity = auto.stream().filter(c -> c.getId().contains("60")
                                && z.getSkinsensitivity().contains(c.getLow())).findFirst();
                        sensitivity.ifPresent(a -> {
                            skinSensitivity.add(a.getNo1());
                            skinSensitivity.add(a.getNo2());
                            skinSensitivity.add(a.getNo3());
                            skinSensitivity.add(a.getNo4());
                            skinSensitivity.add(a.getNo5());
                            skinSensitivity.add(a.getNo6());
                            skinSensitivity.add(a.getNo7());

                        });
                        /*
                          年龄
                         */
                        try {
                            Optional<AutoSelfParameterEntity> agerangeObj = auto.stream().filter(d -> d.getId().contains("20")
                                    && Integer.parseInt(z.getAgerange()) > Integer.parseInt(d.getLow())
                                    && Integer.parseInt(z.getAgerange()) < Integer.parseInt(d.getHigh())).findFirst();

                            agerangeObj.ifPresent(a -> {
                                agerange.add(a.getNo1());
                                agerange.add(a.getNo2());
                                agerange.add(a.getNo3());
                                agerange.add(a.getNo4());
                                agerange.add(a.getNo5());
                                agerange.add(a.getNo6());
                                agerange.add(a.getNo7());

                            });
                        } catch (Exception ignore) {

                        }

                        /*
                          湿度
                         */
                        Optional<AutoSelfParameterEntity> averageHumidityObj = auto.stream().filter(e -> e.getId().contains("30")
                                && z.getAveragehumidity() > Integer.parseInt(e.getLow())
                                && z.getAveragehumidity() < Integer.parseInt(e.getHigh())).findFirst();

                        averageHumidityObj.ifPresent(a -> {
                            averageHumidity.add(a.getNo1());
                            averageHumidity.add(a.getNo2());
                            averageHumidity.add(a.getNo3());
                            averageHumidity.add(a.getNo4());
                            averageHumidity.add(a.getNo5());
                            averageHumidity.add(a.getNo6());
                            averageHumidity.add(a.getNo7());

                        });
                        /*
                          性别
                         */
                        Optional<AutoSelfParameterEntity> sexObj = auto.stream().filter(f -> f.getId().contains("40")
                                && z.getSex().equals(f.getLow())).findFirst();

                        sexObj.ifPresent(a -> {
                            sex.add(a.getNo1());
                            sex.add(a.getNo2());
                            sex.add(a.getNo3());
                            sex.add(a.getNo4());
                            sex.add(a.getNo5());
                            sex.add(a.getNo6());
                            sex.add(a.getNo7());

                        });
                        /*
                          饮水量
                         */
                        String drinkId = "701";
                        if (z.getWaterintake().contains("非常少")) {
                            drinkId = "701";
                        } else if (z.getWaterintake().contains("较少")) {
                            drinkId = "702";
                        } else if (z.getWaterintake().contains("适中")) {
                            drinkId = "703";
                        } else if (z.getWaterintake().contains("较多")) {
                            drinkId = "704";
                        }
                        String finalDrinkId = drinkId;
                        Optional<AutoSelfParameterEntity> drinkObj = auto.stream().filter(g -> g.getId().equals(finalDrinkId)).findFirst();
                        drinkObj.ifPresent(a -> {
                            drink.add(a.getNo1());
                            drink.add(a.getNo2());
                            drink.add(a.getNo3());
                            drink.add(a.getNo4());
                            drink.add(a.getNo5());
                            drink.add(a.getNo6());
                            drink.add(a.getNo7());

                        });
                        /*
                        水分测试
                         */
                        try {
                            Optional<AutoSelfParameterEntity> i1WaterObj = auto.stream().filter(h -> h.getId().contains("80")
                                    && z.getI1Water() > Integer.parseInt(h.getLow())
                                    && z.getI1Water() < Integer.parseInt(h.getHigh())).findFirst();
                            i1WaterObj.ifPresent(a -> {
                                i1Water.add(a.getNo1());
                                i1Water.add(a.getNo2());
                                i1Water.add(a.getNo3());
                                i1Water.add(a.getNo4());
                                i1Water.add(a.getNo5());
                                i1Water.add(a.getNo6());
                                i1Water.add(a.getNo7());

                            });
                        } catch (Exception ignore) {

                        }

                        /*
                        油分测试
                         */
                        try {
                            Optional<AutoSelfParameterEntity> i1OilObj = auto.stream().filter(i -> i.getId().contains("90")
                                    && z.getI1Oil() > Integer.parseInt(i.getLow())
                                    && z.getI1Oil() < Integer.parseInt(i.getHigh())).findFirst();
                            i1OilObj.ifPresent(a -> {
                                i1Oil.add(a.getNo1());
                                i1Oil.add(a.getNo2());
                                i1Oil.add(a.getNo3());
                                i1Oil.add(a.getNo4());
                                i1Oil.add(a.getNo5());
                                i1Oil.add(a.getNo6());
                                i1Oil.add(a.getNo7());

                            });
                        } catch (Exception ignore) {

                        }

                        /*
                        弹性测试
                         */
                        try {
                            Optional<AutoSelfParameterEntity> i1ElasticityObj = auto.stream().filter(j -> j.getId().contains("A0")
                                    && z.getI1Elasticity() > Integer.parseInt(j.getLow())
                                    && z.getI1Elasticity() < Integer.parseInt(j.getHigh())).findFirst();
                            i1ElasticityObj.ifPresent(a -> {
                                i1Elasticity.add(a.getNo1());
                                i1Elasticity.add(a.getNo2());
                                i1Elasticity.add(a.getNo3());
                                i1Elasticity.add(a.getNo4());
                                i1Elasticity.add(a.getNo5());
                                i1Elasticity.add(a.getNo6());
                                i1Elasticity.add(a.getNo7());

                            });
                        } catch (Exception ignore) {

                        }


                    });
                });
                List<String> no1 = new ArrayList<>();
                List<String> no2 = new ArrayList<>();
                List<String> no3 = new ArrayList<>();
                List<String> no4 = new ArrayList<>();
                List<String> no5 = new ArrayList<>();
                List<String> no6 = new ArrayList<>();
                List<String> no7 = new ArrayList<>();
                no1 = add(skinOiliness, skinSensitivity, agerange, averageHumidity, sex, drink, i1Water, i1Oil, i1Elasticity, 0, no1);
                no2 = add(skinOiliness, skinSensitivity, agerange, averageHumidity, sex, drink, i1Water, i1Oil, i1Elasticity, 1, no2);
                no3 = add(skinOiliness, skinSensitivity, agerange, averageHumidity, sex, drink, i1Water, i1Oil, i1Elasticity, 2, no3);
                no4 = add(skinOiliness, skinSensitivity, agerange, averageHumidity, sex, drink, i1Water, i1Oil, i1Elasticity, 3, no4);
                no5 = add(skinOiliness, skinSensitivity, agerange, averageHumidity, sex, drink, i1Water, i1Oil, i1Elasticity, 4, no5);
                no6 = add(skinOiliness, skinSensitivity, agerange, averageHumidity, sex, drink, i1Water, i1Oil, i1Elasticity, 5, no6);
                no7 = add(skinOiliness, skinSensitivity, agerange, averageHumidity, sex, drink, i1Water, i1Oil, i1Elasticity, 6, no7);
                Double n1 = execute(no1);
                Double n2 = execute(no2);
                Double n3 = execute(no3);
                Double n4 = execute(no4);
                Double n5 = execute(no5);
                Double n6 = execute(no6);
                Double n7 = execute(no7);



            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Double execute(List<String> list) {
        Double num = 0.0;
        Integer count = 0;
        for (String s : list) {
            if (StringUtils.hasText(s)) {
                num += Double.parseDouble(s);
                count++;
            }
        }
        return (num/count);

    }

    private List<String> add(List<String> skinOiliness, List<String> skinSensitivity, List<String> agerange, List<String> averageHumidity, List<String> sex, List<String> drink, List<String> i1Water, List<String> i1Oil, List<String> i1Elasticity, Integer index, List<String> no) {
        try {
            no.add(skinOiliness.get(index));

        } catch (Exception ignore) {

        }
        try {

            no.add(skinSensitivity.get(index));

        } catch (Exception ignore) {

        }
        try {

            no.add(agerange.get(index));

        } catch (Exception ignore) {

        }
        try {

            no.add(sex.get(index));

        } catch (Exception ignore) {

        }
        try {

            no.add(drink.get(index));

        } catch (Exception ignore) {

        }
        try {

            no.add(i1Water.get(index));

        } catch (Exception ignore) {

        }
        try {

            no.add(i1Oil.get(index));

        } catch (Exception ignore) {

        }
        try {


            no.add(i1Elasticity.get(index));
        } catch (Exception ignore) {

        }
        return no;

    }
}
