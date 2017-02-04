package com.yibaieducation.input.dao.basedao;

import com.yibaieducation.dao.DaoSession;

import java.util.List;

import de.greenrobot.dao.AbstractDao;

/**
 * YiBaiInputExams
 * Create   2017/1/12 21:13;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public interface GetDataDaoImp<T,X extends AbstractDao> {


    X getDao();


    /*通过单个的tag获得单个的数据*/
    void  getDataByTag(String where ,int requestCode);



    /*获得所有的数据*/
    void getAllData(int requestCode);


    /*获得每页的数据*/
    /*@retrun isHasMore*/
    void getDataFromPage(int page , int requestCode ,String where);




    /*通过limit获得数据*/
    void getDataFromLimit(int start ,int end ,int requestCode);



    /*Daosession 操作数据对象*/
    DaoSession getDaoSession();


    /*set daoSession outside todo*/
    void setDaoSession(DaoSession daoSession);


    /*请求成功后的处理*/
    void requestResult(int requestCode ,Object data );


    /*删除某条记录*/
    void requestDelte(int requestCode ,T t) ;


  /*插入List数据*/
   void requestInsertDatas(int requestCode,List<T> ts);


    /*更新*/
    void requestUpdate(int requestCode ,T t);


    /*插入单条数据*/
    void requestInsert(final int requestCode ,T t) ;
}
