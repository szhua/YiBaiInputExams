package com.yibaieducation.input.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yibaieducation.bean.Ti_item_config;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.adapter.ItemConfigAdapter;
import com.yibaieducation.input.base.BaseLoadMoreFragment;
import com.yibaieducation.input.dao.GetItemConfigDao;
import com.yibaieducation.input.dao.basedao.SqlResult;
import com.yibaieducation.input.util.ToastUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * YiBaiInputExams
 * Create   2017/1/12 23:16;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class ItemConfigFragment extends BaseLoadMoreFragment implements SqlResult {

    private GetItemConfigDao configDao ;
    private List<Ti_item_config> datas ;
    private ItemConfigAdapter subjectAdapter ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datas =new ArrayList<>();
        subjectAdapter =new ItemConfigAdapter(getContext());
        subjectAdapter.setDatas(datas);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configDao =new GetItemConfigDao(getDaoSession(),this) ;
        configDao.requestPage();
        showSwipeProgress();
    }

    @Override
    protected RecyclerView.Adapter getAdaper() {
        return subjectAdapter;
    }

    @Override
    protected boolean hasMore() {
        return configDao.isHasMore();
    }

    @Override
    protected void reFresh() {
        configDao.refresh();
    }

    @Override
    protected void loadMore() {
        if(hasMore()){
            configDao.requestPage();
        }
    }
    @Override
    public void onItemClick(int postion) {
    }
    @Override
    public void success(int requestCode, int erroCode, String msg) {
        if(requestCode== Config.REQUEST_CODE_2){
           hideSwipeProgress();
            if(Config.ERRO_CODE_ERRO==erroCode){
                ToastUtil.showToast(getBaseApplication(),msg);
            }else{
                ToastUtil.showToast(getBaseApplication(),msg);
                datas =configDao.getDatas();
                subjectAdapter.setDatas(datas);
            }
        }
    }
}
