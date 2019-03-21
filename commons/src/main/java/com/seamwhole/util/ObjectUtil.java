package com.seamwhole.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/5 0005.
 */
public class ObjectUtil {

    public static void setObjectFieldsEmpty(Object obj, boolean bo, String... attribute) {
        // 对obj反射
        Class objClass = obj.getClass();
        Method[] objmethods = objClass.getDeclaredMethods();
        Map objMeMap = new HashMap();
        a:
        for (int i = 0; i < objmethods.length; i++) {
            Method method = objmethods[i];
            String name = method.getName();
            b:
            for (int j = 0; j < attribute.length; j++) {
                String substring = name.substring(3, name.length());
                if (bo) {
                    if (substring.equals(upperCase(attribute[j]))) {
                        continue a;
                    }
                    objMeMap.put(name, method);
                } else {
                    if (substring.equals(upperCase(attribute[j]))) {
                        objMeMap.put(name, method);
                    }
                }

            }
        }
        for (int i = 0; i < objmethods.length; i++) {
            {
                String methodName = objmethods[i].getName();
                if (methodName != null && methodName.startsWith("get")) {
                    try {
                        Object returnObj = objmethods[i].invoke(obj,
                                new Object[0]);
                        Method setmethod = (Method) objMeMap.get("set"
                                + methodName.split("get")[1]);
                        if (returnObj != null) {
                            returnObj = null;
                        }
                        if (setmethod != null) {
                            setmethod.invoke(obj, returnObj);
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static void setObjectFieldsEmpty(Object obj, String... attribute) {
        // 对obj反射
        Class objClass = obj.getClass();
        Method[] objmethods = objClass.getDeclaredMethods();
        Map objMeMap = new HashMap();
        a:
        for (int i = 0; i < objmethods.length; i++) {
            Method method = objmethods[i];
            String name = method.getName();
            b:
            for (int j = 0; j < attribute.length; j++) {
                String substring = name.substring(3, name.length());
                if (substring.equals(upperCase(attribute[j]))) {
                    continue a;
                }
            }
            objMeMap.put(name, method);
        }
        for (int i = 0; i < objmethods.length; i++) {
            {
                String methodName = objmethods[i].getName();
                if (methodName != null && methodName.startsWith("get")) {
                    try {
                        Object returnObj = objmethods[i].invoke(obj,
                                new Object[0]);
                        Method setmethod = (Method) objMeMap.get("set"
                                + methodName.split("get")[1]);
                        if (returnObj != null) {
                            returnObj = null;
                        }
                        if (setmethod != null) {
                            setmethod.invoke(obj, returnObj);
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    public static Map getGoodsMap(String id, Object goodsId, int type, int amount) {
        Map map = new HashMap();
        map.put("id", id);
        map.put("goodsId", goodsId);
        map.put("type", type);
        map.put("amount", amount);
        return map;
    }
}
