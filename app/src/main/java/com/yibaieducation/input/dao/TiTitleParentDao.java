package com.yibaieducation.input.dao;

import android.support.annotation.NonNull;

import com.yibaieducation.bean.Ti_title_parent;
import com.yibaieducation.dao.DaoSession;
import com.yibaieducation.dao.Ti_title_parentDao;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.dao.basedao.BaseGetDataDao;
import com.yibaieducation.input.dao.basedao.SqlResult;

import java.util.List;

/**
 * YiBaiInputExams
 * Create   2017/1/20 15:46;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class TiTitleParentDao extends BaseGetDataDao<Ti_title_parent,Ti_title_parentDao> {

    private long parent_id ;

     private List<Ti_title_parent> parentList ;

    public List<Ti_title_parent> getParentList() {
        return parentList;
    }

    public long getParent_id(){
        return parent_id;
    }

    public TiTitleParentDao(@NonNull DaoSession daoSession, @NonNull SqlResult sqlResut) {
        super(daoSession, sqlResut);
        dao =daoSession.getTi_title_parentDao();
    }

    public void insertTiParent(Ti_title_parent parent){
        requestInsert(Config.REQUEST_CODE_1,parent);
    }

    public void getParentDataByPaperId(String paperId){
        getDataByTag("where PAPER_ID ="+paperId,Config.REQUEST_CODE_2);
    }

    public void getParentDataById(String parentId){
        getDataByTag("where _id = "+parentId,Config.REQUEST_CODE_3);
    }


    @Override
    public void requestResult(int requestCode, Object data) {
      if(requestCode==Config.REQUEST_CODE_1){
          this.parent_id = (long) data;
      }else if(requestCode==Config.REQUEST_CODE_2){
          this.parentList = (List<Ti_title_parent>) data;
      }else  if(requestCode==Config.REQUEST_CODE_3){
          this.parentList = (List<Ti_title_parent>) data;
      }
    }
}
