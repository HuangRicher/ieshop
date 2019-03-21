package com.seamwhole.util;

import java.util.Random;

public class RandomUtil {

    /**
     * 取指定随机次数8~12的随机数和
     * @param count 随机次数
     * @return
     */
    public static int RandomMult8To12SumValue(int count){
        Random rand = new Random();
        int sum=0;
        if(count>0){
            for(int i=0; i<count; i++) {
                sum+=rand.nextInt(5) + 8;
            }
        }
        return sum;
    }

    /**
     * 取指定随机次数8~12的随机数和
     * @param count 随机次数
     * @return
     */
    public static int RandomMultIntervalSumValue(int start ,int end,int count){
        Random rand = new Random();
        int sum=0;
        if(count>0){
            for(int i=0; i<count; i++) {
                sum+=rand.nextInt(end-start) + start;
            }
        }
        return sum;
    }

    /**
     * 取指定随机次数3~5的随机数和
     * @param count 随机次数
     * @return
     */
    public static int RandomMult3To5SumValue(int count){
        Random rand = new Random();
        int sum=0;
        if(count>0){
            for(int i=0; i<count; i++) {
                sum+=rand.nextInt(3) + 3;
            }
        }
        return sum;
    }


    /**
     * 取指定随机次数10000~11000的随机身价
     * @return
     */
    public static int RandomSellingPrice(){
        Random rand = new Random();
        int sum=rand.nextInt(1001) + 10000;
        return sum;
    }

    /**
     * 取某个范围的随机数
     * @param min
     * @param max
     * @return
     */
    public static int random(int min,int max){
        Random rand = new Random();
        int sum=rand.nextInt(max-min+1) + min;
        return sum;
    }

    /**
     * 取某个概率事件是否发生
     * @param data    data>1 && data<100
     * @return true 发生  false 不发生
     */
    public static boolean  probabilityEvent(float data){
        int count=(int)(data*10);
        Random r = new Random();
        int n5 = r.nextInt(1000);
        if(n5 < count){ //count个数字的区间，data%的几率
            return true;
        }else{
            return  false;
        }
    }
}
