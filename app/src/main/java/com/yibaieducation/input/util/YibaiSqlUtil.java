package com.yibaieducation.input.util;

import android.content.Context;

import com.yibaieducation.input.R;

/**
 * YiBaiInputExams
 * Create   2017/1/13 12:18;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class YibaiSqlUtil {

    public static String getSql ( String orderBy , int page , int perpageCount){
        return  String.format(" order by %1$s limit %2$s offset %2$s*%3$s",orderBy,perpageCount,page);
    }
}
