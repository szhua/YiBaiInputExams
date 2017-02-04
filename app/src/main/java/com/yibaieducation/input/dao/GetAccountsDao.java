package com.yibaieducation.input.dao;

import android.support.annotation.NonNull;

import com.yibaieducation.bean.Ti_account_subs;
import com.yibaieducation.bean.Ti_account_types;
import com.yibaieducation.dao.DaoSession;
import com.yibaieducation.dao.Ti_account_subsDao;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.dao.basedao.BaseGetDataDao;
import com.yibaieducation.input.dao.basedao.SqlResult;

import java.util.List;

/**
 * YiBaiInputExams
 * Create   2017/1/19 14:00;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class GetAccountsDao extends BaseGetDataDao<Ti_account_subs,Ti_account_subsDao> {

    private List<Ti_account_subs> subses ;

    public List<Ti_account_subs> getSubses() {
        return subses;
    }

    public GetAccountsDao(@NonNull DaoSession daoSession, @NonNull SqlResult sqlResut) {
        super(daoSession, sqlResut);
        dao=daoSession.getTi_account_subsDao();
    }
    public void insertSubses (List<Ti_account_subs> subses){
        requestInsertDatas(Config.REQUEST_CODE_3,subses);
    }
    public void getSubs(){
        getAllData(Config.REQUEST_CODE_4);
    }
    @Override
    public void requestResult(int requestCode, Object data) {
         if(requestCode==Config.REQUEST_CODE_4){
           subses = (List<Ti_account_subs>) data;
         }
    }
}
