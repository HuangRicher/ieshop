package com.seamwhole.util;



import java.time.*;
import java.util.Date;

/**
 * 时间计算工具类
 */
public class DateComputeUtil {

    /**
     * 检查是否按工时完成工作
     *  工种收益按分钟计算
     * @return
     */
    public static boolean checkWorkIsOver(Date startDate){
        Instant instant = startDate.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime startDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDateTime overDateTime=startDateTime.plusMinutes(1);
        LocalDateTime currentDateTime=LocalDateTime.now();
        return currentDateTime.isAfter(overDateTime);
    }

    /**
     * 在线状态  离线时显示上次登录时间，不超过一天的显示具体小时数（不足一小时显示“刚刚”），超过1天的显示具体天数，超过七天的显示七天前
     * @param loginDate
     * @return
     */
    public static String compareOfflineTime(Date loginDate){
        String result="";
        Instant instant = loginDate.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime loginDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDateTime currentDateTime=LocalDateTime.now();
        Duration duration=Duration.between(loginDateTime,currentDateTime);
        if(duration.toMinutes()<60){
            result="刚刚";
            return result;
        }
        if(duration.toMinutes()>=60 && duration.toHours()<24){
            long hours=duration.toHours();
            result="最近上线："+hours+"小时前";
            return result;
        }

        if(duration.toHours()>=24 && duration.toDays()<7){
            long days=duration.toDays();
            result="最近上线："+days+"天前";
            return result;
        }

        if(duration.toDays()>=7){
            result="最近上线：7天前";
            return result;
        }

        return result;
    }

    /**
     * 计算工作时间（小时）
     * @param date
     * @return
     */
    public static String compareWorkTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime loginDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDateTime currentDateTime=LocalDateTime.now();
        Duration duration=Duration.between(loginDateTime,currentDateTime);
        if(duration.toHours()<=0){
            return "工作不超过1小时";
        }else{
            return "已工作"+duration.toHours()+"小时";
        }
    }

    /**
     * LocalDate转Date
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * LocalDateTime转Date
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * Date转LocalDate
     * @param date
     * @return
     */
    public static LocalDate DateToLocalDate(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * Date转LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime DateToLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 获得剩余保护时间 HH:mm
     * @param protectStartEnd
     * @return
     */
    public static String getRemainderTimeOfProtect(Date protectStartEnd) {
        Instant instant = protectStartEnd.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime loginDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDateTime currentDateTime=LocalDateTime.now();
        Duration duration=Duration.between(currentDateTime,loginDateTime);
        if(duration.toMinutes()<0){
            return null;
        }else{
            String hh="";
            String mm="";
            long m=duration.toMinutes()%60;
            long h=duration.toHours();
            if(h<10) hh="0"+h;else hh=""+h;
            if(m<10) mm="0"+m;else mm=""+m;
            return hh+":"+mm;
        }

    }

    /**
     * 计算两个时间点的分钟差
     * @param start
     * @param end
     * @return
     */
    public static int countMinutesBetweenTwoDate(Date start,Date end){
        Instant instantStart = start.toInstant();
        ZoneId zoneStart = ZoneId.systemDefault();
        LocalDateTime loginDateTimeStart = LocalDateTime.ofInstant(instantStart, zoneStart);

        Instant instantEnd = end.toInstant();
        ZoneId zoneEnd = ZoneId.systemDefault();
        LocalDateTime loginDateTimeEnd = LocalDateTime.ofInstant(instantEnd, zoneEnd);

        Duration duration=Duration.between(loginDateTimeStart,loginDateTimeEnd);

        if(duration.toMinutes()>0)
            return (int)duration.toMinutes();
        else
            return (int)(0-duration.toMinutes());
    }

    /**
     * 计算两个时间点的秒级差
     * @param start
     * @param end
     * @return
     */
    public static int countSecondBetweenTwoDate(Date start,Date end){
        Instant instantStart = start.toInstant();
        ZoneId zoneStart = ZoneId.systemDefault();
        LocalDateTime loginDateTimeStart = LocalDateTime.ofInstant(instantStart, zoneStart);

        Instant instantEnd = end.toInstant();
        ZoneId zoneEnd = ZoneId.systemDefault();
        LocalDateTime loginDateTimeEnd = LocalDateTime.ofInstant(instantEnd, zoneEnd);

        Duration duration=Duration.between(loginDateTimeStart,loginDateTimeEnd);

        if(duration.toMillis()>0)
            return (int)duration.toMillis()/1000;
        else
            return (int)(0-duration.toMillis()/1000);
    }

    /**
     * 计算现在到第二天零点的间隔时间（单位/秒）
     * @return
     */
    public static long getSecondsNextEarlyMorning(){
        int sec=RandomUtil.random(0,10);
        LocalDateTime current=LocalDateTime.now();
        LocalDateTime next=current.plusDays(1).withHour(0).withMinute(0).withSecond(sec);
        Duration duration = Duration.between(current,next);
        return duration.getSeconds();
    }

    /**
     * 检查是否有收益
     * 工种收益按分钟计算
     * @return
     */
    public static boolean checkHaveIncome(Date startDate){
        Instant instant = startDate.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime startDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDateTime overDateTime=startDateTime.plusMinutes(1);
        LocalDateTime currentDateTime=LocalDateTime.now();
        return currentDateTime.isAfter(overDateTime);
    }

    public static int getSeason(LocalDate localDate){
        if(localDate.isBefore(localDate.with(Month.APRIL).withDayOfMonth(1))) {
            return 1;
        } else if(localDate.isBefore(localDate.with(Month.JULY).withDayOfMonth(1))) {
            return 2;
        } else if(localDate.isBefore(localDate.with(Month.NOVEMBER).withDayOfMonth(1))) {
            return 3;
        } else {
            return 4;
        }
    }
}
