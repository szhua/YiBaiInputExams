package com.yibaieducation.input.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.yibaieducation.input.R;

/**
 * YiBaiInputExams
 * Create   2017/1/10 15:33;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class ToastUtil {

    private static  Toast toast ;


    public static void showToast(Context context ,@NonNull String message){
        if(toast!=null){
            toast.cancel();
        }
        toast =null ;
        toast=new Toast(context) ;
        TextView textView =new TextView(context) ;
        textView.setText(message);
        Drawable leftDrawable =ContextCompat.getDrawable(context,R.drawable.toast_ic) ;
        leftDrawable.setBounds(0,0,leftDrawable.getMinimumWidth(),leftDrawable.getIntrinsicHeight());
      //  textView.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(context,R.drawable.toast_ic),null,null,null);
        textView.setCompoundDrawables(leftDrawable,null,null,null);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(ContextCompat.getColor(context,R.color.text_color_normal));
        toast.setView(textView);
        toast.show();
    }



}
