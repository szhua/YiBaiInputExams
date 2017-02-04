package com.yibaieducation.input.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yibaieducation.bean.Ti_account_subs;
import com.yibaieducation.input.R;
import com.yibaieducation.input.ui.SelectAccountActivity;

import java.util.List;

/**
 * YiBaiInputExams
 * Create   2017/1/19 15:06;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class AccountAdapter extends RecyclerView.Adapter{

    private List<Ti_account_subs>  datas ;

    private LayoutInflater layoutInflater ;
    private SelectAccountActivity selectAccountActivity ;
    public AccountAdapter(SelectAccountActivity context){
        this.selectAccountActivity =context ;
        layoutInflater =LayoutInflater.from(context) ;
    }

    public void setDatas(List<Ti_account_subs> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =layoutInflater.inflate(R.layout.item_account_layout,parent,false) ;
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Ti_account_subs subs =datas.get(position);
        if(subs.isFirst()){
            myViewHolder.parent_name.setVisibility(View.VISIBLE);
            myViewHolder.parent_name.setText(subs.getParent_name());
        }else{
            myViewHolder.parent_name.setVisibility(View.GONE);
        }
        myViewHolder.name.setText(subs.getSub_name());
    }
    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView parent_name ;
        private TextView name ;

        public MyViewHolder(View itemView) {
            super(itemView);
            parent_name = (TextView) itemView.findViewById(R.id.parent_name);
            name = (TextView) itemView.findViewById(R.id.name);

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectAccountActivity.onItemClick(getAdapterPosition());
                }
            });
        }
    }

}
