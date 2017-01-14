package com.yibaieducation.input.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yibaieducation.bean.Ti_paper;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.adapter.PaperAdapter;
import com.yibaieducation.input.base.BaseLoadMoreFragment;
import com.yibaieducation.input.dao.basedao.GetPaperDao;
import com.yibaieducation.input.dao.basedao.SqlResult;
import com.yibaieducation.input.ui.AddPaperDetailActivity;
import com.yibaieducation.input.util.ToastUtil;

import java.util.List;

/**
 * YiBaiInputExams
 * Create   2017/1/14 11:13;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class AddPaperFragment extends BaseLoadMoreFragment implements SqlResult{


    private List<Ti_paper> papers ;
    private PaperAdapter paperAdapter ;

    private GetPaperDao getPaperDao ;

    private String subjectId ;


    public static  AddPaperFragment newInstance(String subjectId){
        AddPaperFragment addPaperFragment =new AddPaperFragment() ;
        addPaperFragment.subjectId =subjectId ;
        return  addPaperFragment ;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

          paperAdapter =new PaperAdapter(this);
          paperAdapter.setPapers(papers);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPaperDao =new GetPaperDao(getDaoSession(),this);
        getPaperDao.requestAllData();
        showSwipeProgress();
    }

    @Override
    protected RecyclerView.Adapter getAdaper() {
        return paperAdapter;
    }
    @Override
    protected boolean hasMore() {
        return false;
    }
    @Override
    protected void reFresh() {

    }
    @Override
    protected void loadMore() {
    }
    @Override
    public void onItemClick(int postion) {
     if(postion<0){
         Intent intent =new Intent(getContext(), AddPaperDetailActivity.class) ;
         intent.putExtra("subjectId",subjectId);
         startActivityForResult(intent,101);
     }else{
         ToastUtil.showToast(getContext(),"Normal"+postion);
     }
    }
    @Override
    public void success(int requestCode, int erroCode, String msg) {
        if(requestCode== Config.REQUEST_CODE_1){
            hideSwipeProgress();
            ToastUtil.showToast(getContext(),msg);
        }
    }
}
