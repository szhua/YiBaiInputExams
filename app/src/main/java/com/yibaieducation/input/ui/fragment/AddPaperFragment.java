package com.yibaieducation.input.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.yibaieducation.bean.Ti_paper;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.adapter.PaperAdapter;
import com.yibaieducation.input.base.BaseLoadMoreFragment;
import com.yibaieducation.input.dao.GetPaperDao;
import com.yibaieducation.input.dao.basedao.SqlResult;
import com.yibaieducation.input.ui.AddPaperDetailActivity;
import com.yibaieducation.input.ui.AddTopicsActivity;
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
    private long subjectId ;

    public static  AddPaperFragment newInstance(long subjectId){
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
        getPaperDao.requestSubjectPapers(""+subjectId);
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
       getPaperDao.requestSubjectPapers(""+subjectId);
    }
    @Override
    protected void loadMore() {
    }

    @Override
    public void onItemClick(int postion, int type) {
        switch (type){
            case Config.EDIT_TYPE:
                ToastUtil.showToast(getContext(),"编辑："+papers.get(postion).getName());
                Intent intent =new Intent(getContext(), AddPaperDetailActivity.class) ;
                intent.putExtra("subjectId",subjectId);
                intent.putExtra("ti_paper",papers.get(postion));
                startActivityForResult(intent,101);
                break;
            case  Config.DELETE_TYPE:
                getPaperDao.deleteTipaper(papers.get(postion));
                showProgress(true);
                break;
        }
    }

    @Override
    public void onItemClick(int postion) {
     if(postion<0){
         Intent intent =new Intent(getContext(), AddPaperDetailActivity.class) ;
         intent.putExtra("subjectId",subjectId);
         startActivityForResult(intent,101);
     }else{
        Intent intent =new Intent(getContext(), AddTopicsActivity.class) ;
         intent.putExtra("paperId",papers.get(postion).getId()) ;
         startActivity(intent);
     }
    }
    @Override
    public void success(int requestCode, int erroCode, String msg) {
        hideSwipeProgress();
        showProgress(false);
        if(requestCode== Config.REQUEST_CODE_2){
          papers =getPaperDao.getTi_papers();
          paperAdapter.setPapers(papers);
        }else  if(requestCode==Config.REQUEST_CODE_3){
            reFresh();
            showProgress(true);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101){
            showSwipeProgress();
            reFresh();
        }
    }
}
