package com.seamwhole.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2018/10/11 0011.
 */
public class CommonUtil {

    public static <T> Object getObjectElement(T t, String element) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> aClass = t.getClass();
        element = "get" + element.substring(0, 1).toUpperCase() + element.substring(1);
        Method setReadOnly = t.getClass().getMethod(element);
        Object invoke = setReadOnly.invoke(t);
        return invoke;
    }

    public static <T, H> void voluationBean(T t, String method, H value) {
        try {
            Method setReadOnly = null;
            method = "set" + method.substring(0, 1).toUpperCase() + method.substring(1);
            if (value == null) {
                setReadOnly = t.getClass().getMethod(method, null);
            } else {
             setReadOnly = t.getClass().getMethod(method, value.getClass());
            }
            setReadOnly.invoke(t, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T luckyDraw(Map<List<Integer>, T> jackpotMap, Integer weight) {
        int randomNumber = new Random().nextInt(weight);
        Iterator it = jackpotMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, T> entry = (Map.Entry<String, T>) it.next();

            if (entry.getKey().contains(randomNumber + "")) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static String getProbabilityList(Integer start, Integer finish) {
        List<Integer> retList = new LinkedList<>();
        for (int i = start; i < finish; i++) {
            retList.add(i);
        }
        return retList.toString().replace("[", "").replace("]", "") + ",";
    }


    public static <T> T getItemCount(Map<String, T> jackpotMap, Integer weight) {
        int randomNumber = new Random().nextInt(weight);
        Iterator it = jackpotMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, T> entry = (Map.Entry<String, T>) it.next();

            if (entry.getKey().contains(randomNumber + ",")) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static Boolean getChance(int num){
        Random random = new Random();
        int i = random.nextInt(99);
        if(i>=0&&i<num)
            return true;
        else
            return false;
    }

    /**
     * 生成订单的编号order_sn
     */
    public static String generateOrderNumber() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String timeStr = DateUtils.format(cal.getTime(), DateUtils.DATE_TIME_PATTERN_YYYY_MM_DD_HH_MM_SS_SSS);
        return timeStr + CharUtil.getRandomNum(6);
    }
}
