package com.yibaieducation.input.base;

import android.support.v4.app.Fragment;

import com.yibaieducation.dao.DaoSession;
import com.yibaieducation.input.InitApplication;
import com.yibaieducation.input.R;
import com.yibaieducation.input.widget.ProgressDialog;

/**
 * YiBaiInputExams
 * Create   2017/1/12 17:29;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class BaseFragment extends Fragment {


    private ProgressDialog progressDialog ;

    protected InitApplication getBaseApplication(){
        return (InitApplication) getActivity().getApplication();
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
            progressDialog = ProgressDialog.showProgress(getContext(),false,message,null);
        }
        if(isShow){
            progressDialog.show();
        }else{
            progressDialog.cancel();
        }
    }



}
