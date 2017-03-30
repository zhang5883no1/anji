package com.xidu.framework.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.xidu.framework.Constants;


/**
 *
 */
public final class DateUtil {

    private DateUtil(){
        
    }
    public static final SimpleDateFormat dfDate = new SimpleDateFormat(Constants.DATE_FORMAT_YYMMDD);
    public static final SimpleDateFormat dfTime = new SimpleDateFormat(Constants.DATE_FORMAT_HHMMSS);
    public static final SimpleDateFormat dfDateAndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat dfTimeHm = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat dfDateAndTimeHm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    private static final String MIDDLE_NIGHT_FORMAT = "00:00:00";
    public static final String DEFAULT_TIME_HHMM = "00:00";
    /**
     * 
     * @Date        :      2011-11-14
     * @param date
     * @param time
     * @return
     */
    public static Date getMergedDateAndTime(Date date, Date time){
        if(null == date){
            return null;
        }
        
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);
        dateCal.set(Calendar.HOUR, 0);
        dateCal.set(Calendar.MINUTE, 0);
        dateCal.set(Calendar.SECOND, 0);
        
        if(null != time){
            Calendar timeCal = Calendar.getInstance();
            timeCal.setTime(time);
            
            dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
            dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
            dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));
        }
        return dateCal.getTime();
    }
    
    /**
     * 
     * @Date        :      2011-11-14
     * @param date
     * @return
     */
    public static String getDateText(Date date){
        if(null == date){
            return "";
        }
        return dfDate.format(date);
    }
    
    /**
     * 
     * @Date        :      2011-11-14
     * @param time
     * @return
     */
    public static String getTimeText(Date time){
        if(null == time){
            return "";
        }
        return dfTime.format(time);
    }
    
    /**
     * 
     * @Date        :      2011-11-14
     * @param dateText
     * @param timeText
     * @return
     * @throws ParseException
     */
    public static Date getMergedDateAndTime(String dateText, String timeText) throws ParseException{
        if(StringUtils.isBlank(dateText)){
            return null;
        }
        Date date = dfDate.parse(dateText);
        Date time = null;
        if(StringUtils.isNotBlank(timeText)){
            time = dfTime.parse(timeText);
        }
        
        return getMergedDateAndTime(date, time);
    }
    /**
     * 
     * @Date        :      2011-11-14
     * @param date
     * @param days
     * @return
     */
    public static Date getDateByDays(Date date, int days){
        //null check
        if(null == date){
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
    /**
     * 
     * @Date        :      Nov 30, 2011
     * @param date
     * @return
     */
    public static String getDateTextMiddleNight(Date date){
        if(null == date){
            return "";
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        if(calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) == 0 && calendar.get(Calendar.SECOND) == 0){
            return dfDate.format(date);
        }else{
            return dfDateAndTimeHm.format(date);
        }
        
    }
    /**
     * 
     * @Date        :      Nov 30, 2011
     * @param date
     * @return
     */
    public static String getTimeTextMiddleNight(Date date){
        if(null == date){
            return "";
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        if(calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) == 0 && calendar.get(Calendar.SECOND) == 0){
            return "";
        }else{
            return dfTime.format(date);
        }
        
    }
    
    /**
     * 
     * @Date        :      Dec 8, 2011
     * @param beginDate
     * @param endDate
     * @return
     */
    public static boolean compareOnlyDate(Date beginDate, Date endDate){
        if(null == beginDate || null == endDate ){
            return false;
        }
        
        Calendar beginCal = Calendar.getInstance();
        beginCal.setTime(beginDate);
        int beginYear = beginCal.get(Calendar.YEAR);
        int beginMonth = beginCal.get(Calendar.MONTH);
        int beginDay = beginCal.get(Calendar.DAY_OF_MONTH);
        
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        int endYear = endCal.get(Calendar.YEAR);
        int endMonth = endCal.get(Calendar.MONTH);
        int endDay = endCal.get(Calendar.DAY_OF_MONTH);
        
        if(beginYear > endYear){
           return false;
        }else if(beginYear < endYear ){
            return true;
        }else if(beginMonth > endMonth){
            return false;
        }else if(beginMonth < endMonth){
            return true;
        }else if(beginDay > endDay){
            return false;
        }else if(beginDay < endDay){
            return true;
        }
        return true;
    }
}
