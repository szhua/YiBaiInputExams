package com.yibaieducation.input.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yibaieducation.bean.Ti_account_subs;
import com.yibaieducation.bean.Ti_account_types;
import com.yibaieducation.dao.Ti_account_typesDao;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.R;
import com.yibaieducation.input.adapter.AccountAdapter;
import com.yibaieducation.input.base.BaseActivity;
import com.yibaieducation.input.dao.GetAccountsDao;
import com.yibaieducation.input.dao.TiAccountTypesDao;
import com.yibaieducation.input.dao.basedao.SqlResult;
import com.yibaieducation.input.util.ToastUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SelectAccountActivity extends BaseActivity implements SqlResult{

    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;
    private AccountAdapter accountAdapter;
    private GetAccountsDao getAccountsDao;
    private List<Ti_account_subs> subsList ;


    private int tag ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_account);
        ButterKnife.inject(this);

        tag =getIntent().getIntExtra("tag",0) ;


        accountAdapter =new AccountAdapter(this);
        accountAdapter.setDatas(subsList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(accountAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getAccountsDao =new GetAccountsDao(getDaoSession(),this) ;
        getAccountsDao.getSubs();
        showProgress(true);
    }
    public void onItemClick(int postion){
        Intent intent =new Intent() ;
        intent.putExtra("name",subsList.get(postion).getSub_name());
        intent.putExtra("tag",tag) ;
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void success(int requestCode, int erroCode, String msg) {
        showProgress(false);
        if(requestCode==Config.REQUEST_CODE_4){
             subsList =getAccountsDao.getSubses();
              //进行分类
             if(subsList!=null){
                 int lastParentId =0;
                 for (Ti_account_subs ti_account_subs : subsList) {
                   int parentId= ti_account_subs.getParent_id();
                   if(parentId!=lastParentId){
                       ti_account_subs.setFirst(true);
                   }
                   lastParentId =parentId;
                 }
             }
            accountAdapter.setDatas(subsList);
          }
    }
}
