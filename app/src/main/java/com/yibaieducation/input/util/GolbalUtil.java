package com.yibaieducation.input.util;


import android.text.format.DateFormat;

import java.util.Date;

/**
 * YiBaiInputExams
 * Create   2017/1/20 16:50;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class GolbalUtil {

    public  static  String formatDate(){
       return (String) DateFormat.format("yyyy-MM-dd hh:mm:ss",new Date());
    }
}
