package com.yibaieducation.input.dao;

import android.support.annotation.NonNull;

import com.yibaieducation.bean.Ti_sku_subject;
import com.yibaieducation.dao.DaoSession;
import com.yibaieducation.dao.Ti_sku_subjectDao;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.dao.basedao.BaseGetDataDao;
import com.yibaieducation.input.dao.basedao.SqlResult;

import java.util.List;


/**
 * YiBaiInputExams
 * Create   2017/1/12 21:31;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class GetSubjectDao extends BaseGetDataDao<Ti_sku_subject,Ti_sku_subjectDao> {


    private List<Ti_sku_subject> subjects ;


    public List<Ti_sku_subject> getSubjects() {
        return subjects;
    }

    public GetSubjectDao(@NonNull DaoSession daoSession , @NonNull SqlResult sqlResult) {
        super(daoSession,sqlResult);
        dao =this.daoSession.getTi_sku_subjectDao();
    }

    public void request(){
        getAllData(Config.REQUEST_CODE_1);
    }

    @Override
    public void requestResult(int requestCode, List<Ti_sku_subject> data) {
        if(requestCode==Config.REQUEST_CODE_1){
            subjects = data;
        }
    }
}
