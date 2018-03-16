/********************************************
 * Copyright (c) , shixun.online
 *
 * All rights reserved
 *
*********************************************/
package online.shixun.demo.robot.common;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	
	/**
     * <p>获取当前日期是星期几</p>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
   public static String getWeekOfDate(Date dt) {
       String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
       Calendar cal = Calendar.getInstance();
       cal.setTime(dt);

       int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
       if (w < 0)	// 回到星期日
           w = 0;

       return weekDays[w];
   }

}
