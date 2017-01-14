package com.yibaieducation.input.dao.basedao;

import android.support.annotation.NonNull;

import com.yibaieducation.bean.Ti_paper;
import com.yibaieducation.dao.DaoSession;
import com.yibaieducation.dao.Ti_paperDao;
import com.yibaieducation.input.Config;

import java.util.List;

/**
 * YiBaiInputExams
 * Create   2017/1/14 11:14;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class GetPaperDao extends BaseGetDataDao<Ti_paper,Ti_paperDao> {


    private List<Ti_paper> ti_papers ;


    public List<Ti_paper> getTi_papers() {
        return ti_papers;
    }

    public GetPaperDao(@NonNull DaoSession daoSession, @NonNull SqlResult sqlResut) {
        super(daoSession, sqlResut);
        dao =daoSession.getTi_paperDao();
    }


    public void requestAllData(){
        getAllData(Config.REQUEST_CODE_1);
    }

    public void insertPaper(Ti_paper paper){
        requestInsert(Config.REQUEST_CODE_2,paper);
    }

    @Override
    public void requestResult(int requestCode, List<Ti_paper> data) {
          if(requestCode==Config.REQUEST_CODE_1){
              ti_papers =data ;
          }
    }
}
