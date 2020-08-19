package test;

import java.util.Arrays;

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
        Integer[] arr = {4,2,6,3,1,5};
        select(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static Integer[] select(Integer[] arr){
//      每次找到最小的，与指定位置交换
        for (int i = 0; i <arr.length ; i++) {

            int mix = arr[i];
            int mixIndex=i;

            for (int j = arr.length-1; j >i ; j--) {
                if(arr[j]<mix){
                    mix = arr[j];
                    mixIndex=j;
                }
            }


            arr[mixIndex] = arr[i];
            arr[i]=mix;
        }

        return arr;
    }


    public static void bubble(Integer[] arr){
        int a = 0;
        for (int i = 0; i <arr.length ; i++) {
            for (int j = 0; j <arr.length-1-i ; j++) {
                a++;
                if(arr[j].compareTo(arr[j+1])>0){
                    int emp;
                    emp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=emp;
                }
            }
        }
        System.out.println("内层循环次数："+a);
    }




}
