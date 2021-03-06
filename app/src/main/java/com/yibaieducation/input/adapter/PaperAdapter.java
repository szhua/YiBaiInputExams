package com.yibaieducation.input.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.yibaieducation.bean.Ti_paper;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.R;
import com.yibaieducation.input.base.BaseLoadMoreFragment;
import com.yibaieducation.input.ui.AddPaperDetailActivity;
import com.yibaieducation.input.ui.fragment.AddPaperFragment;
import com.yibaieducation.input.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * YiBaiInputExams
 * Create   2017/1/14 10:55;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class PaperAdapter extends RecyclerView.Adapter {

    private static final int HEADER_TYPE = 1;
    private static final int NORMAL_TYPE =2;
    private BaseLoadMoreFragment baseLoadMoreFragment ;
    private LayoutInflater inflater;

    private List<Ti_paper> papers =new ArrayList<>();

    public void setPapers(List<Ti_paper> papers) {
        if(papers==null){
            papers =new ArrayList<>() ;
        }
        papers.add(0,new Ti_paper());
        this.papers =papers ;
        notifyDataSetChanged();
    }

    public PaperAdapter(BaseLoadMoreFragment loadMoreFragment){
        this.baseLoadMoreFragment =loadMoreFragment ;
        inflater = LayoutInflater.from(loadMoreFragment.getContext());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder ;
        if(viewType==HEADER_TYPE){
            holder =new HeaderHolder(inflater.inflate(R.layout.item_add_header,parent,false));
        }else{
            holder =new MyHolder(inflater.inflate(R.layout.item_paper_layout,parent,false));
        }
      return  holder ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
              Ti_paper paper =papers.get(position);
              if(getItemViewType(position)==HEADER_TYPE){
                   HeaderHolder headerHolder = (HeaderHolder) holder;
              }else{
                  MyHolder myHolder = (MyHolder) holder;
                  myHolder.paperName.setText(paper.getName());
                  myHolder.nums.setText("题目个数："+paper.getTotal_count());
                  myHolder.time.setText("考试时间:"+paper.getAnswer_time());
              }
    }

    @Override
    public int getItemCount() {
        return papers.size();
    }
    @Override
    public int getItemViewType(int position) {

        if(position==0){
            return  HEADER_TYPE;
        }else{
            return  NORMAL_TYPE;
        }
    }


    private class HeaderHolder extends  RecyclerView.ViewHolder{
         private Button addButton ;
        public HeaderHolder(View itemView) {
            super(itemView);
           addButton = (Button) itemView.findViewById(R.id.add_button);
           addButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   baseLoadMoreFragment.onItemClick(-1);
               }
           });
        }
    }

    private class MyHolder extends RecyclerView.ViewHolder{
        private TextView paperName ;
        private TextView edit ;
        private TextView delete ;
        private TextView time ;
        private TextView  nums ;

        public MyHolder(View itemView) {
            super(itemView);

            edit = (TextView) itemView.findViewById(R.id.eidt);
            delete = (TextView) itemView.findViewById(R.id.delete);
            time = (TextView) itemView.findViewById(R.id.time);
            nums = (TextView) itemView.findViewById(R.id.nums);

            paperName = (TextView) itemView.findViewById(R.id.paper_name);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseLoadMoreFragment.onItemClick(getAdapterPosition(), Config.EDIT_TYPE);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseLoadMoreFragment.onItemClick(getAdapterPosition(),Config.DELETE_TYPE);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseLoadMoreFragment.onItemClick(getAdapterPosition());
                }
            });
        }
    }

}
