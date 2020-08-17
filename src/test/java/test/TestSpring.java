package test;

import java.util.*;

/**
 * {\_/}
 * ( ^.^ )
 * / > @ zmf
 *
 * @date 2020/5/29
 */
public class TestSpring {


    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("");

//        TestJunitExp testJunitExp = context.getBean(TestJunitExp.class);

//        testJunitExp.test1();

        List<Map<String, Object>> returnList = new ArrayList<>();
        Map<String, Object> mapP = new HashMap<>();
        mapP.put("path","/dashboard");
        mapP.put("name","dashboard");
        mapP.put("icon","");
        List<Map<String, Object>> childrenList = new ArrayList<>();

        Map<String, Object> mapC1 = new HashMap<>();
        mapC1.put("path", "/dashboard/analysis");
        mapC1.put("name", "analysis");

        Map<String, Object> mapC2 = new HashMap<>();
        mapC2.put("path", "/dashboard/monitor");
        mapC2.put("name", "monitor");

        childrenList.add(mapC1);

        childrenList.add(mapC2);

        mapP.put("children",childrenList);
        returnList.add(mapP);

        System.out.println(returnList.toString());
    }


}
