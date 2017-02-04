package com.yibaieducation.input.dao;

import android.support.annotation.NonNull;

import com.yibaieducation.bean.Ti_account_types;
import com.yibaieducation.dao.DaoSession;
import com.yibaieducation.dao.Ti_account_typesDao;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.dao.basedao.BaseGetDataDao;
import com.yibaieducation.input.dao.basedao.SqlResult;

import java.util.List;

/**
 * YiBaiInputExams
 * Create   2017/1/19 11:43;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class TiAccountTypesDao extends BaseGetDataDao<Ti_account_types,Ti_account_typesDao> {

    private List<Ti_account_types> types ;

    public List<Ti_account_types> getTypes() {
        return types;
    }
    public TiAccountTypesDao(@NonNull DaoSession daoSession, @NonNull SqlResult sqlResut) {
        super(daoSession, sqlResut);
        dao=daoSession.getTi_account_typesDao() ;
    }
    public void insertTypes (List<Ti_account_types> types){
        requestInsertDatas(Config.REQUEST_CODE_1,types);
    }
    public void getItems(){
        getAllData(Config.REQUEST_CODE_2);
    }

    @Override
    public void requestResult(int requestCode, Object data) {
         if(requestCode==Config.REQUEST_CODE_2){
             this.types = (List<Ti_account_types>) data;
         }
    }
}
