package com.yibaieducation.input.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.yibaieducation.bean.Ti_item_config;
import com.yibaieducation.bean.Ti_sku_subject;
import com.yibaieducation.dao.Ti_sku_subjectDao;
import com.yibaieducation.input.base.BaseActivity;
import com.yibaieducation.input.R;
import com.yibaieducation.input.bean.ItemConfigBean;
import com.yibaieducation.input.getService.GetSubjectService;
import com.yibaieducation.input.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/*一些固定数据的初始化*/
public class InitDataActivity extends BaseActivity implements View.OnClickListener {


    @InjectView(R.id.insert_sub_bt)
    Button insertSubBt;
    @InjectView(R.id.get_sub_bt)
    Button getSubBt;
    @InjectView(R.id.delete_sub_bt)
    Button deleteSubBt;
    @InjectView(R.id.getdata)
    Button getdata;
    @InjectView(R.id.datas)
    TextView datas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_data);
        ButterKnife.inject(this);
        setTitle(getString(R.string.activity_init_data_title));

        insertSubBt.setOnClickListener(this);
        getSubBt.setOnClickListener(this);
        deleteSubBt.setOnClickListener(this);
        getdata.setOnClickListener(this);

    }

    private void insertItemData() {
        Observable.just(null)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showProgress(true);
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<Object, Object>() {
                    @Override
                    public Object call(Object o) {

                        return null;
                    }
                }).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        });
    }


    private void insertSubData() {
        Observable
                .just(null)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        Logger.d("doOnSubscribe");
                        showProgress(true);
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<Object, Object>() {
                    @Override
                    public Object call(Object o) {
                        Logger.d("map");
                        //考试的科目； index == 1:会计基础  2：财经法规 3：会计电算化
                        String[] sujectNames = new String[]{getString(R.string.kuaijijichu), getString(R.string.caijingfagui), getString(R.string.kuaijidiansuanhua)};

                        Ti_sku_subjectDao subjectDao = getDaoSession().getTi_sku_subjectDao();
                        ArrayList<Ti_sku_subject> subjects = new ArrayList<>();

                        for (int i = 0; i < sujectNames.length; i++) {
                            String subName = sujectNames[i];
                            Ti_sku_subject sub = new Ti_sku_subject();
                            sub.setSku(1);
                            sub.setSku_name(getString(R.string.sku_congye));
                            sub.setSubject_code(i);
                            sub.setSubject_name(subName);
                            subjects.add(sub);

                        }
                         /*插入数据*/
                        subjectDao.insertOrReplaceInTx(subjects);


                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        Logger.d("finish");
                        showProgress(false);
                        ToastUtil.showToast(InitDataActivity.this, "插入成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d("onErro");
                        showProgress(false);
                    }

                    @Override
                    public void onNext(Object o) {
                        Logger.d("onNext");
                    }
                });
    }

    private void deleteSubs() {
        Observable.just(null)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showProgress(true);
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<Object, Object>() {
                    @Override
                    public Object call(Object o) {
                        getDaoSession().getTi_sku_subjectDao().deleteAll();
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        showProgress(false);
                        ToastUtil.showToast(InitDataActivity.this, "删除成功");
                    }
                    @Override
                    public void onError(Throwable e) {
                        showProgress(false);
                    }

                    @Override
                    public void onNext(Object o) {
                        showProgress(false);
                    }
                });
    }

    private void insertItems(List<ItemConfigBean> beans){
       Observable.just( beans)
               .subscribeOn(AndroidSchedulers.mainThread())
               .doOnSubscribe(new Action0() {
                   @Override
                   public void call() {
                   showProgressWithText(true,"正在插入数据库");
                   }
               })
               .observeOn(Schedulers.io())
               .map(new Func1<List<ItemConfigBean>, List<Ti_item_config>>() {
                   @Override
                   public List<Ti_item_config> call(List<ItemConfigBean> itemConfigBeens) {
                       Logger.d("正在数据转换");
                       ArrayList<Ti_item_config> subs =new ArrayList<>();
                       if(itemConfigBeens!=null){
                           for (ItemConfigBean itemConfigBean : itemConfigBeens) {
                               Ti_item_config item =new Ti_item_config() ;
                               if(!TextUtils.isEmpty(itemConfigBean.getSku_id())) {
                                   item.setSku_id(Integer.parseInt(itemConfigBean.getSku_id()));
                               }else{
                                   item.setSku_id(0);
                               }
                               if(!TextUtils.isEmpty(itemConfigBean.getItem_name())){
                                   item.setItem_name(itemConfigBean.getItem_name());
                               }
                               if(!TextUtils.isEmpty(itemConfigBean.getParent_id())){
                                   item.setParent_id(itemConfigBean.getParent_id());
                               }
                               if(!TextUtils.isEmpty(itemConfigBean.getId_leaf())){
                                   item.setIs_leaf(Integer.parseInt(itemConfigBean.getId_leaf()));
                               }
                               if(!TextUtils.isEmpty(itemConfigBean.getImte_code())){
                                   item.setItem_code(itemConfigBean.getImte_code());
                               }else{item.setItem_code("");}

                               subs.add(item);
                           }
                       }
                       return subs;
                   }
               })
               .map(new Func1<List<Ti_item_config>, Object>() {
                   @Override
                   public Object call(List<Ti_item_config> ti_sku_subjects) {
                       Logger.d("insertting");
                       getDaoSession().getTi_item_configDao().insertOrReplaceInTx(ti_sku_subjects);
                       return null;
                   }
               })
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Subscriber<Object>() {
                   @Override
                   public void onCompleted() {
                       showProgress(false);
                       ToastUtil.showToast(getApplicationContext(),"success");
                   }

                   @Override
                   public void onError(Throwable throwable) {
                         showProgress(false);
                       ToastUtil.showToast(getApplicationContext(),"erro");
                   }

                   @Override
                   public void onNext(Object o) {

                   }
               }) ;

    }

    private void getSubDatas() {


        Observable.just(null)
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showProgress(true);
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<Object, List<Ti_sku_subject>>() {
                    @Override
                    public List<Ti_sku_subject> call(Object o) {
                        return getDaoSession().getTi_sku_subjectDao().loadAll();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Ti_sku_subject>>() {
                    @Override
                    public void onCompleted() {
                        showProgress(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        showProgress(false);
                    }

                    @Override
                    public void onNext(List<Ti_sku_subject> subs) {

                    }
                });

    }


    //进行网络请求
    private void getSubs() {
        String baseUrl = "http://192.168.191.1/study/szhua/getJson.php/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetSubjectService movieService = retrofit.create(GetSubjectService.class);
        Call<Object> call = movieService.getTopMovie();
        showProgress(true);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Gson gson = new Gson();
                String json = gson.toJson(response.body());
                Logger.d(json);
                List<ItemConfigBean> stringList = gson.fromJson(json, new TypeToken<List<ItemConfigBean>>() {
                }.getType());
                ToastUtil.showToast(getApplicationContext(), stringList.toString());
                datas.setText(stringList.toString());
                showProgress(false);
                insertItems(stringList);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Logger.e(t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == insertSubBt) {
            insertSubData();
        } else if (v == getSubBt) {
            getSubDatas();
        } else if (v == deleteSubBt) {
            deleteSubs();
        } else if (v == getdata) {
            getSubs();
        }
    }
}
