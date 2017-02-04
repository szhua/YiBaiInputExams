package com.yibaieducation.input.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.yibaieducation.input.R;

/**
 * YiBaiInputExams
 * Create   2017/1/12 17:31;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public abstract class BaseLoadMoreFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected RecyclerView recyclerView ;
    private SwipeRefreshLayout swipeRefreshLayout ;

    private LinearLayoutManager layoutManager ;

    //最后一个可见的itemPosition！
    private int  lastVisibleItemPosition;

    protected  abstract  RecyclerView.Adapter getAdaper();

     protected  abstract  boolean hasMore();

     protected  abstract  void reFresh();

     protected  abstract  void loadMore();

     public  abstract  void  onItemClick(int postion);


     public void onItemClick(int postion ,int type){

     }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootLoadMoreView =inflater.inflate(R.layout.layout_base_loadmore,container,false) ;
        recyclerView = (RecyclerView) rootLoadMoreView.findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) rootLoadMoreView.findViewById(R.id.swiperefresh);

        return rootLoadMoreView;
    }




    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout.setOnRefreshListener(this);
        //设置进度动画的颜色
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light); //没转一圈换一个颜色
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()  //applyDimension 该处意思是获取24dip
                        .getDisplayMetrics()));


        recyclerView.setHasFixedSize(true);

        layoutManager =new LinearLayoutManager(getActivity()) ;

         recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(getAdaper());

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //不滚动的时候 并且滑动到底部的时候 ；
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&getAdaper()!=null&&lastVisibleItemPosition+1==getAdaper().getItemCount()){
                    if(hasMore()){
                        loadMore();
                    }
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                lastVisibleItemPosition =layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void onRefresh() {
        reFresh();
    }

    protected  void showSwipeProgress(){
        if(swipeRefreshLayout!=null){
            swipeRefreshLayout.setRefreshing(true);
        }
    }
    protected  void hideSwipeProgress(){
         if(swipeRefreshLayout!=null){
             swipeRefreshLayout.setRefreshing(false);
         }
    }

}
