package com.yibaieducation.input.dao.basedao;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;
import com.yibaieducation.dao.DaoSession;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.util.YibaiSqlUtil;

import java.util.ArrayList;
import java.util.List;
import de.greenrobot.dao.AbstractDao;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * YiBaiInputExams
 * Create   2017/1/12 21:12;
 * https://github.com/szhua
 * @author sz.hua
 */
public abstract class BaseGetDataDao<T,Z extends AbstractDao> implements GetDataDaoImp<T,Z> {





    protected  DaoSession daoSession ;
    protected  Z dao ;
    protected SqlResult sqlResult ;


    private static final int PER_PAGE_DEFAULT_NUM = 15 ;

    protected int perPageNum ;




    public BaseGetDataDao(@NonNull  DaoSession daoSession , @NonNull SqlResult sqlResut){
        this.daoSession =daoSession;
        this.sqlResult =sqlResut;
        perPageNum =PER_PAGE_DEFAULT_NUM ;
        //please setup getDao !!!must!!
    }

    /*每一页的显示个数*/
    public void setPerPageNum(int perPageNum) {
        this.perPageNum = perPageNum;
    }

    @Override
    public DaoSession getDaoSession() {
        return daoSession;
    }
    @Override
    public void setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    @Override
    public Z getDao() {
        return dao;
    }

    @Override
    public void getDataFromLimit(int start, int end ,int requestCode ) {

        if(dao==null){
            Logger.e("You must set up  dao in custructor in first Step !!! ");
            return;
        }
      ;
    }

    @Override
    public void getDataByTag(String where ,final   int requestCode) {

        if(dao==null){
            Logger.e("You must set up  dao in custructor in first Step !!! ");
            return;
        }

      Observable.just(where)
              .subscribeOn(Schedulers.io())
              .map(new Func1<String, List<T>>() {
                  @Override
                  public List<T> call(String s) {
                      return  dao.queryRaw(s,new String[]{});
                  }
              })
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Subscriber<List<T>>() {
                  @Override
                  public void onCompleted() {

                  }

                  @Override
                  public void onError(Throwable e) {
                      Logger.d(e);
                      sqlResult.success(requestCode,Config.ERRO_CODE_ERRO,e.toString());
                  }

                  @Override
                  public void onNext(List<T> ts) {
                      requestResult(requestCode,ts);
                      sqlResult.success(requestCode,Config.ERRO_CODE_SUCCESS,"ok");
                  }
              });


    }

    @Override
    public void  getDataFromPage(int page , final int requestCode ,String where) {
        if(dao==null){
            Logger.e("You must set up  dao in custructor in first Step !!! ");
            return ;
        }
        final String sql = YibaiSqlUtil.getSql(dao.getPkColumns()[0],page,perPageNum ,where);
        Observable
                .just(null)
                .subscribeOn(Schedulers.io())
                .map(new Func1<Object, List<T>>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List<T> call(Object o) {
                        return dao.queryRaw(sql,new String[]{});
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<T>>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                     Logger.d(e);
                     sqlResult.success(requestCode,Config.ERRO_CODE_ERRO,e.toString());
                    }
                    /*request Success!!*/
                    @Override
                    public void onNext(List<T> ts) {
                     requestResult(requestCode,ts);
                     sqlResult.success(requestCode,Config.ERRO_CODE_SUCCESS,"ok");
                    }
                });
    }

    //for get All datas !!
    @Override
    public void getAllData(final int requestCode) {
        if(dao==null){
            Logger.e("You must set up  dao in custructor !!!");
            return;
        }
        Observable
                .just(null)
                .subscribeOn(Schedulers.io())

                .map(new Func1<Object,List<T>>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List<T> call(Object o) {
                        return  dao.loadAll();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<T>>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        sqlResult.success(requestCode,Config.ERRO_CODE_ERRO,e.toString());
                    }

                    // request Success!!
                    @Override
                    public void onNext(List<T> ts) {
                        requestResult(requestCode,ts);
                        sqlResult.success(requestCode,Config.ERRO_CODE_SUCCESS,"ok");
                    }
                }) ;
    }


    @Override
    public void requestDelte(final int requestCode, T t) {

        if(dao==null){
            Logger.e("You must set up  dao in custructor !!!");
            return;
        }
         Observable.just(t)
              .subscribeOn(Schedulers.io())
              .map(new Func1<T,Integer>() {
                  @Override
                  public Integer  call(T t) {
                     try{
                         dao.delete(t);
                     }catch (Exception e){
                         return  -1 ;
                     }
                      return 1;
                  }
              })
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Subscriber<Integer>() {
                  @Override
                  public void onCompleted() {

                  }
                  @Override
                  public void onError(Throwable e) {
                      requestResult(requestCode,new ArrayList<T>());
                      sqlResult.success(requestCode,Config.ERRO_CODE_ERRO,e.toString());
                  }
                  @Override
                  public void onNext(Integer integer) {
                      requestResult(requestCode,new ArrayList<T>());
                      sqlResult.success(requestCode,Config.ERRO_CODE_SUCCESS,"ok");
                  }
              });
    }

    @Override
    public void requestInsert(final int requestCode ,T t){
        if(dao==null){
            Logger.e("You must set up  dao in custructor !!!");
            return;
        }
        Observable.just(t)
                .subscribeOn(Schedulers.io())
                .map(new Func1<T,Long>() {
                    @Override
                    public Long  call(T t) {

                         long id =-1 ;
                        try{
                          id = dao.insertOrReplace(t);
                        }catch (Exception e){

                        }
                        return id;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        requestResult(requestCode,new ArrayList<T>());
                        sqlResult.success(requestCode,Config.ERRO_CODE_ERRO,e.toString());
                    }
                    @Override
                    public void onNext(Long integer) {
                        requestResult(requestCode,integer);
                        sqlResult.success(requestCode,Config.ERRO_CODE_SUCCESS,"ok");
                    }
                });
    }

    @Override
    public void requestInsertDatas( final  int requestCode, List<T> ts) {
        if(dao==null){
            Logger.e("You must set up  dao in custructor !!!");
            return;
        }
        Observable.just(ts)
                .subscribeOn(Schedulers.io())
                .map(new Func1<List<T>,Integer>() {
                    @Override
                    public Integer  call(List<T> ts) {
                        try{
                            dao.insertOrReplaceInTx(ts);
                        }catch (Exception e){
                            return  -1 ;
                        }
                        return 1;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        requestResult(requestCode,new ArrayList<T>());
                        sqlResult.success(requestCode,Config.ERRO_CODE_ERRO,e.toString());
                    }
                    @Override
                    public void onNext(Integer integer) {
                        requestResult(requestCode,new ArrayList<T>());
                        sqlResult.success(requestCode,Config.ERRO_CODE_SUCCESS,"ok");
                    }
                });



    }

    @Override
    public void requestUpdate(final int requestCode, T t) {
        if(dao==null){
            Logger.e("You must set up  dao in custructor !!!");
            return;
        }
        Observable.just(t)
                .subscribeOn(Schedulers.io())
                .map(new Func1<T,Integer>() {
                    @Override
                    public Integer  call(T t) {
                        try{
                            dao.update(t);
                        }catch (Exception e){
                            return  -1 ;
                        }
                        return 1;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        requestResult(requestCode,new ArrayList<T>());
                        sqlResult.success(requestCode,Config.ERRO_CODE_ERRO,e.toString());
                    }
                    @Override
                    public void onNext(Integer integer) {
                        requestResult(requestCode,new ArrayList<T>());
                        sqlResult.success(requestCode,Config.ERRO_CODE_SUCCESS,"ok");
                    }
                });
    }
}
