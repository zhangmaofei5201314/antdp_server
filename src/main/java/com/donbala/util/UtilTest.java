package com.donbala.util;

import com.donbala.constants.TestEnum;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * {\_/}
 * ( ^.^ )
 * / > @ zmf
 * 工具类测试
 * @date 2019/11/4
 */
public class UtilTest {

    public static void main1(String[] args) {
        String a= TestEnum.Head0.getCode();
        System.out.println(a);

        List<Map<String, Object>> list = TestEnum.toList();
        for (Map<String, Object> map : list) {
            System.out.println(map.get("code")+":"+map.get("desc"));
        }

        System.out.println(UUID.randomUUID().toString().replace("-",""));



    }
}
