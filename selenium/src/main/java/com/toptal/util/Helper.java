package com.toptal.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author askeledzija
 */

public class Helper {

    private static final Logger logger = LogManager.getLogger(Helper.class);

     /** Return diff between dates in days
     * @param d1  element
     * @param d2  element
     *
     */
     public static long getDifferenceDays(Date d1, Date d2) {
         long diffInMillies = Math.abs(d1.getTime() - d2.getTime());
         long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
         return diff;
     }
}
