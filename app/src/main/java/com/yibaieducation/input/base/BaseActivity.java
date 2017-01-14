package com.yibaieducation.input.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.yibaieducation.dao.DaoSession;
import com.yibaieducation.input.InitApplication;
import com.yibaieducation.input.R;
import com.yibaieducation.input.widget.ProgressDialog;

/**
 * YiBaiInputExams
 * Create   2017/1/10 11:32;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar =getSupportActionBar() ;

        if (actionBar!=null&&isBack()){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    protected  boolean isBack(){
       return  true ;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    protected InitApplication getBaseApplication(){
        return (InitApplication) getApplication();
    }

    protected DaoSession getDaoSession(){
        if(getBaseApplication()!=null){
            return  getBaseApplication().getDaoSession();
        }
        return  null ;
    }

    protected  void showProgress(boolean isShow ){
       showProgressWithText(isShow,getString(R.string.is_loading));
    }
    protected  void showProgressWithText(boolean isShow,String message){
        if(progressDialog==null){
            progressDialog =ProgressDialog.showProgress(this,false,message,null);
        }
        if(isShow){
            progressDialog.show();
        }else{
            progressDialog.cancel();
        }
    }

}
