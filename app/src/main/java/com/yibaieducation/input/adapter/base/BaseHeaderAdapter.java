package com.yibaieducation.input.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yibaieducation.input.R;

import java.util.List;

/**
 * YiBaiInputExams
 * Create   2017/1/14 10:22;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public abstract class BaseHeaderAdapter<T,Y> extends RecyclerView.Adapter {

    private static  final int HEADER_TYPE=1;

    private static final int NORMAL_TYPE=2;

    private List<T> datas ;

    private Y headerData;

    private int headerLayout ;
    private int normalLayout ;

    abstract void setHeaderData (Y headerData) ;

    abstract  void setDatas(List<T> datas);


    protected  Context context ;
    protected LayoutInflater layoutInflater ;

    public BaseHeaderAdapter(Context context){
        this.context =context ;
        layoutInflater =LayoutInflater.from(context);
    }

    protected  void setLayout(int headerLayout, int normalLayout){
        this.headerLayout =headerLayout ;
        this.normalLayout =normalLayout ;
    };



    @Override
    public BaseHeaderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(headerLayout==0||normalLayout==0){
            throw new IllegalArgumentException("Please set layout int your adapter before settting adapter");
        }
        BaseHeaderViewHolder myViewHolder ;
        if(viewType==HEADER_TYPE){
            myViewHolder =new BaseHeaderViewHolder(layoutInflater.inflate(headerLayout,parent,false),viewType);
        }else{
            myViewHolder =new BaseHeaderViewHolder(layoutInflater.inflate(normalLayout,parent,false),viewType);
        }
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {

        if(position==0){
            return  HEADER_TYPE ;
        }else{
            return  NORMAL_TYPE ;
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }




}
