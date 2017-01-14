package com.yibaieducation.input.dao;

import android.support.annotation.NonNull;
import com.yibaieducation.bean.Ti_item_config;
import com.yibaieducation.dao.DaoSession;
import com.yibaieducation.dao.Ti_item_configDao;
import com.yibaieducation.input.dao.basedao.BaseGetPageDataDao;
import com.yibaieducation.input.dao.basedao.SqlResult;


/**
 * YiBaiInputExams
 * Create   2017/1/13 1:01;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class GetItemConfigDao extends BaseGetPageDataDao<Ti_item_config,Ti_item_configDao> {


    public GetItemConfigDao(@NonNull DaoSession daoSession, @NonNull SqlResult sqlResut) {
        super(daoSession, sqlResut);
        dao =daoSession.getTi_item_configDao();
    }

//    /*获得全部数据*/
//    public void request(){
//        getAllData(Config.REQUEST_CODE_1);
//    }



}
