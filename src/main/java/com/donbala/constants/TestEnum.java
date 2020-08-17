package com.donbala.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum TestEnum {
    Head0("姓名","name",0),
    Head1("性别","sex",1);

    private String desc;
    private String code;
    private int index;

    TestEnum(String desc, String code, int index) {
        this.desc = desc;
        this.code = code;
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static List<Map<String,Object>> toList(){
        List<Map<String, Object>> list = new ArrayList<>();

        for (TestEnum te:TestEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", te.getCode());
            map.put("desc", te.getDesc());
            list.add(map);

        }
        return list;
    }
}
