package com.yibaieducation.input.dao;

import android.support.annotation.NonNull;

import com.yibaieducation.bean.Ti_paper;
import com.yibaieducation.dao.DaoSession;
import com.yibaieducation.dao.Ti_paperDao;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.dao.basedao.BaseGetDataDao;
import com.yibaieducation.input.dao.basedao.SqlResult;

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


  public void requestSubjectPapers(String id){
      String where ="where SUBJECT_CODE = "+id ;
      getDataByTag(where,Config.REQUEST_CODE_2);

  }

  public void deleteTipaper(Ti_paper ti_paper){
      requestDelte(Config.REQUEST_CODE_3,ti_paper);
  }

    public void updateTiPaper(Ti_paper ti_paper){
        requestUpdate(Config.REQUEST_CODE_4,ti_paper);
    }





    public void requestAllData(){
        getAllData(Config.REQUEST_CODE_1);
    }

    public void insertPaper(Ti_paper paper){
        requestInsert(Config.REQUEST_CODE_2,paper);
    }

    @Override
    public void requestResult(int requestCode, Object data) {
          if(requestCode==Config.REQUEST_CODE_1){
              ti_papers = (List<Ti_paper>) data;
          }else if(requestCode==Config.REQUEST_CODE_2){
              ti_papers = (List<Ti_paper>) data;
          }
    }
}
