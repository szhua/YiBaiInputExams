package com.yibaieducation.input.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.yibaieducation.bean.Ti_paper;
import com.yibaieducation.bean.Ti_title;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.R;
import com.yibaieducation.input.base.BaseLoadMoreFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * YiBaiInputExams
 * Create   2017/1/17 13:42;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class TopicAdapter extends RecyclerView.Adapter {

    private static final int HEADER_TYPE = 1;
    private static final int NORMAL_TYPE =2;
    private BaseLoadMoreFragment baseLoadMoreFragment ;
    private LayoutInflater inflater;

    private List<Ti_title> titles =new ArrayList<>();

    public void setTitles(List<Ti_title> titles) {
        if(titles==null){
            titles =new ArrayList<>() ;
        }
        titles.add(0,new Ti_title());
        this.titles =titles ;
        notifyDataSetChanged();
    }

    public TopicAdapter(BaseLoadMoreFragment loadMoreFragment){
        this.baseLoadMoreFragment =loadMoreFragment ;
        inflater = LayoutInflater.from(loadMoreFragment.getContext());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder ;
        if(viewType==HEADER_TYPE){
            holder =new HeaderHolder(inflater.inflate(R.layout.item_add_topic_header,parent,false));
        }else{
            holder =new MyHolder(inflater.inflate(R.layout.item_title_layout,parent,false));
        }
      return  holder ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
              Ti_title ti_title =titles.get(position);
              if(getItemViewType(position)==HEADER_TYPE){
                   HeaderHolder headerHolder = (HeaderHolder) holder;
              }else{
                  MyHolder myHolder = (MyHolder) holder;
                  if(ti_title.getType_code()==Config.JUDGE_TYPE){
                    ((MyHolder) holder).select_container.setVisibility(View.GONE);
                      myHolder.typeName.setText("判断题");
                      myHolder.typeIcon.setImageResource(R.drawable.judge_type);
                  }else{
                      ((MyHolder) holder).select_container.setVisibility(View.VISIBLE);
                      myHolder.a.setText("A."+ti_title.getItemA());
                      myHolder.b.setText("B."+ti_title.getItemB());
                      myHolder.c.setText("C."+ti_title.getItemC());
                      myHolder.d.setText("D"+ti_title.getItemD());

                      if(ti_title.getType_code()==Config.MULTI_TYPE){
                          myHolder.typeName.setText("多选题");
                          myHolder.typeIcon.setImageResource(R.drawable.multi_type);
                      }else if(ti_title.getType_code()==Config.SINGLE_TYPE){
                          myHolder.typeName.setText("单选题");
                          myHolder.typeIcon.setImageResource(R.drawable.single_type);
                      }
                  }
                  myHolder.des.setText("解析：    "+ti_title.getAnalyze_text());
                  myHolder.title_name.setText(ti_title.getDes());
                  myHolder.score.setText(ti_title.getScore()+"分");
                  myHolder.right_answer.setText("正确答案："+ti_title.getAnswer());
              }
    }

    @Override
    public int getItemCount() {
        return titles.size();
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
            private Button add_single_button ;
            private Button  add_multi_button ;
            private Button   add_judge_button ;
         public HeaderHolder(View itemView) {
            super(itemView);
             add_single_button = (Button) itemView.findViewById(R.id.add_single_button);
             add_multi_button = (Button) itemView.findViewById(R.id.add_multi_button);
             add_judge_button = (Button) itemView.findViewById(R.id.add_judge_button);
             add_single_button.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     baseLoadMoreFragment.onItemClick(-1);
                 }
             });
             add_multi_button.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     baseLoadMoreFragment.onItemClick(-2);
                 }
             });
             add_judge_button.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     baseLoadMoreFragment.onItemClick(-3);
                 }
             });

        }
    }

    private class MyHolder extends RecyclerView.ViewHolder{
        private TextView a;
        private  TextView b;
        private TextView c;
        private TextView d;
        private TextView title_name;
        private TextView des;
        private TextView score;
        private TextView right_answer ;
        private LinearLayout select_container ;

        private TextView typeName ;
        private ImageView typeIcon ;


        private TextView edit ;
        private TextView delete ;


        public MyHolder(View itemView) {
            super(itemView);
            a = (TextView) itemView.findViewById(R.id.a);
            b = (TextView) itemView.findViewById(R.id.b);
            c = (TextView) itemView.findViewById(R.id.c);
            d = (TextView) itemView.findViewById(R.id.d);
            score = (TextView) itemView.findViewById(R.id.score);
            title_name= (TextView) itemView.findViewById(R.id.title_name);
            des = (TextView) itemView.findViewById(R.id.des);
            score = (TextView) itemView.findViewById(R.id.score);
            right_answer = (TextView) itemView.findViewById(R.id.right_answer);
            select_container = (LinearLayout) itemView.findViewById(R.id.select_container);
            typeName = (TextView) itemView.findViewById(R.id.type_name);
            typeIcon = (ImageView) itemView.findViewById(R.id.type_icon);
            edit = (TextView) itemView.findViewById(R.id.eidt);
            delete = (TextView) itemView.findViewById(R.id.delete);

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseLoadMoreFragment.onItemClick(getAdapterPosition(),Config.EDIT_TYPE);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     baseLoadMoreFragment.onItemClick(getAdapterPosition(),Config.DELETE_TYPE);
                }
            });






        }
    }

}
