package com.yibaieducation.input.adapter.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * YiBaiInputExams
 * Create   2017/1/14 10:46;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class BaseHeaderViewHolder extends RecyclerView.ViewHolder {
    private int type ;

    public int getType() {
        return type;
    }

    public BaseHeaderViewHolder(View itemView , int type){
        super(itemView);
        this.type =type ;
    }
}
