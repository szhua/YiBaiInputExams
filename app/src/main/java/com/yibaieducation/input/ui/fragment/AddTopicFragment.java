package com.yibaieducation.input.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.View;

import com.yibaieducation.bean.Ti_title;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.R;
import com.yibaieducation.input.adapter.TopicAdapter;
import com.yibaieducation.input.base.BaseLoadMoreFragment;
import com.yibaieducation.input.dao.GetTitlesDao;
import com.yibaieducation.input.dao.basedao.SqlResult;
import com.yibaieducation.input.ui.AddTopicsSelectActivity;
import com.yibaieducation.input.ui.SelectInputActivity;
import com.yibaieducation.input.util.ToastUtil;

import java.util.List;

/**
 * YiBaiInputExams
 * Create   2017/1/17 13:39;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class AddTopicFragment extends BaseLoadMoreFragment implements SqlResult {

    private GetTitlesDao getTitlesDao ;
    private TopicAdapter topicAdapter ;
    private List<Ti_title> titles ;
    private long paperID;

     public static AddTopicFragment inStance(long paperID){
         AddTopicFragment addTopicFragment =new AddTopicFragment() ;
         addTopicFragment.paperID =paperID ;
         return  addTopicFragment ;
      }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topicAdapter =new TopicAdapter(this) ;
        topicAdapter.setTitles(titles);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerForContextMenu(recyclerView);

        getTitlesDao =new GetTitlesDao(getDaoSession(),this) ;
        getTitlesDao.setWhere("where PAPER_ID = "+paperID +" and PARENT_ID is '' ");
        getTitlesDao.requestPage();
        showSwipeProgress();
    }

    @Override
    protected RecyclerView.Adapter getAdaper() {
        return topicAdapter;
    }
    @Override
    protected boolean hasMore() {
        return false;
    }
    @Override
    protected void reFresh() {
        getTitlesDao.refresh();
    }
    @Override
    protected void loadMore() {
        getTitlesDao.requestPage();
    }
    @Override
    public void onItemClick(int postion) {
        Intent intent =new Intent(getContext(), SelectInputActivity.class);
        intent.putExtra("paperId",paperID);
         switch (postion){
             case -1 :
                 intent.putExtra("type",Config.SINGLE_TYPE);
                 break;
             case -2 :
                 intent.putExtra("type",Config.MULTI_TYPE) ;
                 break;
             case -3:
                 intent.putExtra("type",Config.JUDGE_TYPE);
                 break;
         }
        if(intent.getIntExtra("type",0)!=0) {
            startActivityForResult(intent, 101);
        }
    }
    //特定点击事件：
    @Override
    public void onItemClick(int postion, int type) {
        if(type==Config.DELETE_TYPE){
         getTitlesDao.deleteTitle(titles.get(postion));
        }else if(type==Config.EDIT_TYPE){
            Ti_title ti_title =titles.get(postion) ;
            Intent intent =new Intent(getContext(), SelectInputActivity.class);
            intent.putExtra("title",ti_title);
            intent.putExtra("edit",true);
            intent.putExtra("type",ti_title.getType_code());
            intent.putExtra("paperId",paperID);
            startActivityForResult(intent, 101);
        }
    }

    @Override
    public void success(int requestCode, int erroCode, String msg) {
        hideSwipeProgress();
         if(requestCode== Config.REQUEST_CODE_PAGE_DATA){
             titles =getTitlesDao.getDatas();
             topicAdapter.setTitles(titles);
         }else if(requestCode==Config.REQUEST_CODE_3){
             ToastUtil.showToast(getContext(),"删除成功");
             reFresh();
         }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101&&resultCode==getActivity().RESULT_OK){
            reFresh();
            showSwipeProgress();
        }
    }
}
