package com.yibaieducation.input.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.yibaieducation.bean.Ti_sku_subject;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.adapter.SubjectAdapter;
import com.yibaieducation.input.base.BaseLoadMoreFragment;
import com.yibaieducation.input.dao.GetSubjectDao;
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
public class HomeListFragment extends BaseLoadMoreFragment implements SqlResult {

    private GetSubjectDao subjectDao ;
    private List<Ti_sku_subject> datas ;

    private SubjectAdapter subjectAdapter ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datas =new ArrayList<>();
        subjectAdapter =new SubjectAdapter(getContext());
        subjectAdapter.setDatas(datas);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subjectDao =new GetSubjectDao(getDaoSession(),this);
        subjectDao.request();
        showSwipeProgress();
    }

    @Override
    protected RecyclerView.Adapter getAdaper() {
        return subjectAdapter;
    }

    @Override
    protected boolean hasMore() {
        return false;
    }

    @Override
    protected void reFresh() {
       subjectDao.request();
    }

    @Override
    protected void loadMore() {

    }

    @Override
    public void onItemClick(int postion) {

    }

    @Override
    public void success(int requestCode, int erroCode, String msg) {
        if(requestCode== Config.REQUEST_CODE_1){
           hideSwipeProgress();
            if(Config.ERRO_CODE_ERRO==erroCode){
                ToastUtil.showToast(getBaseApplication(),msg);
            }else{
                //ToastUtil.showToast(getBaseApplication(),subjectDao.getSubjects().toString());
                datas =subjectDao.getSubjects();
                subjectAdapter.setDatas(datas);
            }
        }
    }
}
