package com.yibaieducation.input.dao.basedao;

import android.support.annotation.NonNull;
import com.yibaieducation.dao.DaoSession;
import com.yibaieducation.input.Config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import de.greenrobot.dao.AbstractDao;

/**
 * YiBaiInputExams
 * Create   2017/1/13 16:57;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class BaseGetPageDataDao<T,Z extends AbstractDao> extends BaseGetDataDao<T,Z> {


    /*从数据库获得的数据*/
    private List<T> datas ;

    /*当前页数*/
    private int currentPage ;

    /*是否有更多*/
    private boolean hasMore =true ;

    private String where  ;

    public List<T> getDatas() {
        return datas;
    }

    public BaseGetPageDataDao(@NonNull DaoSession daoSession, @NonNull SqlResult sqlResut) {
        super(daoSession, sqlResut);
    }

    public void setWhere(String where) {
        this.where = where;
    }

    /*分页获取数据*/
    public void requestPage(){
        if(hasMore) {
            getDataFromPage(currentPage, Config.REQUEST_CODE_PAGE_DATA,where);
            currentPage++;
        }
    }
    /*刷新*/
    public void refresh(){

        currentPage = 0 ;
        hasMore =true ;

        if(datas!=null)
           datas.clear();

        requestPage();
    }


    /*获得数据的处理*/// TODO: 2017/1/13
    @Override
    public void requestResult(int requestCode, Object data) {

    if(requestCode==Config.REQUEST_CODE_PAGE_DATA){


            if(this.datas!=null){
                this.datas.addAll((Collection<? extends T>) data);
            }else{
                this.datas =new ArrayList<>();
                this.datas.addAll((Collection<? extends T>) data);
            }

            if(data!=null&&((Collection<? extends T>) data).size()==perPageNum){
                hasMore =true;
            }else{
                hasMore =false;
            }
        }
    }

    /*是否有更多的数据*/
    public boolean isHasMore(){
        return  hasMore ;
    }
}
