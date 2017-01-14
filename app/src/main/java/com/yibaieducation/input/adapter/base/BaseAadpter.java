package com.yibaieducation.input.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yibaieducation.input.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * YiBaiInputExams
 * Create   2017/1/12 17:50;
 * https://github.com/szhua
 *
 * @author sz.hua
 *
 * todo 定向更新;
 */
public abstract   class BaseAadpter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected LayoutInflater layoutInflater ;

    protected Context context ;

    protected List<T> datas ;

    public void setDatas(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public  BaseAadpter (Context context){
        this.context =context ;
        layoutInflater =LayoutInflater.from(context) ;
    }

      public abstract BaseViewHolder onCreateHolder(ViewGroup parent);
      public  abstract void onBindDatas(BaseViewHolder holder ,int position);

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateHolder(parent);
    }
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        onBindDatas(holder,position);
    }
    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }
}
