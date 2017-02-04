package com.yibaieducation.input.dao;

import android.support.annotation.NonNull;

import com.yibaieducation.bean.Ti_title;
import com.yibaieducation.dao.DaoSession;
import com.yibaieducation.dao.Ti_titleDao;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.dao.basedao.BaseGetPageDataDao;
import com.yibaieducation.input.dao.basedao.SqlResult;

import java.util.List;

/**
 * YiBaiInputExams
 * Create   2017/1/17 13:44;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class GetTitlesDao extends BaseGetPageDataDao<Ti_title,Ti_titleDao> {


   private List<Ti_title> titles ;

    public List<Ti_title> getTitles() {
        return titles;
    }

    public GetTitlesDao(@NonNull DaoSession daoSession, @NonNull SqlResult sqlResut) {
        super(daoSession, sqlResut);
        dao=daoSession.getTi_titleDao();
    }


    public void getSimpleTitle(String paperId ,String parentId){
        getDataByTag("where PAPER_ID = "+paperId+" and PARENT_ID = "+parentId,Config.REQUEST_CODE_4);
    }


    public void insertTitle(Ti_title title){
        requestInsert(Config.REQUEST_CODE_1,title);
    }
    public void  updateTitle(Ti_title title){
        requestUpdate(Config.REQUEST_CODE_2,title);
    }

    public void deleteTitle(Ti_title title){
        requestDelte(Config.REQUEST_CODE_3,title);
    }

    @Override
    public void requestResult(int requestCode, Object data) {
        super.requestResult(requestCode, data);
        if(requestCode==Config.REQUEST_CODE_4){
            this.titles = (List<Ti_title>) data;
        }
    }
}
