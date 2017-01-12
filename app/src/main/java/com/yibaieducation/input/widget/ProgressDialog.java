package com.yibaieducation.input.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.yibaieducation.input.R;

/**
 * YiBaiInputExams
 * Create   2017/1/10 14:37;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class ProgressDialog extends Dialog {

    protected ProgressDialog(Context context) {
        super(context);
    }

    protected ProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected ProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        ImageView imageView = (ImageView) findViewById(R.id.spinnerImageView);
        if(imageView!=null){
            AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
            animationDrawable.start();
        }
    }

    public static ProgressDialog showProgress (Context context,boolean cancleAble,CharSequence message ,OnCancelListener cancelListener){
        Dialog dialog =new ProgressDialog(context, R.style.ProgressHUD);
        dialog.setCancelable(cancleAble);
        dialog.setTitle("");
        dialog.setContentView(R.layout.progress_hud);

        if(TextUtils.isEmpty(message)){
         dialog.findViewById(R.id.message).setVisibility(View.GONE);
        }else{
            ((TextView)dialog.findViewById(R.id.message)).setText(message);
        }


        dialog.setOnCancelListener(cancelListener);
         /*gravity*/
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER ;
        //透明度的设置；
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount=0.2f;
        dialog.getWindow().setAttributes(lp);


        return (ProgressDialog) dialog;
    }



}
