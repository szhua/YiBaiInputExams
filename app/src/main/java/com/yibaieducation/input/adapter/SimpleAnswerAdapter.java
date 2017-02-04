package com.yibaieducation.input.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yibaieducation.bean.Ti_title;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.R;
import com.yibaieducation.input.ui.AddFenluActivity;
import com.yibaieducation.input.ui.fragment.SimpleAnswerFragment;

import java.util.List;

/**
 * YiBaiInputExams
 * Create   2017/1/21 9:49;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class SimpleAnswerAdapter extends BaseAdapter {

    private List<Ti_title> titleList ;

    private Context context ;

    private LayoutInflater layoutInflater ;
    private SimpleAnswerFragment simpleAnswerFragment ;

    public void setTitleList(List<Ti_title> titleList) {
        this.titleList = titleList;
        notifyDataSetChanged();
    }

    public SimpleAnswerAdapter(Context context , SimpleAnswerFragment simpleAnswerFragment){
        this.context =context ;
        this.simpleAnswerFragment =simpleAnswerFragment;
        layoutInflater =LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return titleList==null?0:titleList.size();
    }
    @Override
    public Object getItem(int position) {
        return titleList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Ti_title title =titleList.get(position) ;

        MyViewHolder  holder =null ;

        if(convertView==null){
            convertView =layoutInflater.inflate(R.layout.item_simple_answer_layout,parent,false) ;
            holder =new MyViewHolder() ;
            holder.item_a = (TextView) convertView.findViewById(R.id.item_a);
            holder.item_b = (TextView) convertView.findViewById(R.id.item_b);
            holder.item_c = (TextView) convertView.findViewById(R.id.item_c);
            holder.item_d = (TextView) convertView.findViewById(R.id.item_d);
            holder.right_answer = (TextView) convertView.findViewById(R.id.right_answer);
            holder.question_des= (TextView) convertView.findViewById(R.id.question_des);
            holder.selectContainer = (LinearLayout) convertView.findViewById(R.id.select_container);
            holder.item_score = (TextView) convertView.findViewById(R.id.item_score);
            holder.type = (ImageView) convertView.findViewById(R.id.type);
            holder.type_text = (TextView) convertView.findViewById(R.id.type_text);
            holder.edit = (ImageView) convertView.findViewById(R.id.eidt);
            holder.delete = (ImageView) convertView.findViewById(R.id.delete);
            convertView.setTag(holder);
        }else{
            holder = (MyViewHolder) convertView.getTag();
        }

        if(title.getType_code()!= Config.INDEFINITE_TYPE){
            holder.selectContainer.setVisibility(View.GONE);

            if(title.getType_code()==Config.FENLU_TYPE){
                holder.type.setImageResource(R.drawable.fenlu);
                holder.type_text.setText("分录题");
                holder.edit.setVisibility(View.GONE);
            }else {
                holder.type.setImageResource(R.drawable.jisuan);
                holder.type_text.setText("计算题");
                holder.edit.setVisibility(View.VISIBLE);
            }
        }else{
            holder.edit.setVisibility(View.VISIBLE);
            holder.type.setImageResource(R.drawable.multi_type);
            holder.type_text.setText("不定项选择题");
            holder.selectContainer.setVisibility(View.VISIBLE);
            holder.item_a.setText("A、"+title.getItemA());
            holder.item_b.setText("B、"+title.getItemB());
            holder.item_c.setText("C、"+title.getItemC());
            holder.item_d.setText("D、"+title.getItemD());
        }

        if(!TextUtils.isEmpty(title.getDes())) {
        holder.question_des.setText(title.getDes());
        }else {
        holder.question_des.setText("暂无描述");
        }

        if(!TextUtils.isEmpty(title.getAnswer())){

            if(title.getType_code()==Config.FENLU_TYPE){
                Gson gson =new Gson() ;
                List<AddFenluActivity.FenluBean> list = gson.fromJson(title.getAnswer(), new TypeToken<List<AddFenluActivity.FenluBean>>() {}.getType());
                if(list!=null){
                    String answer ="" ;
                    for (AddFenluActivity.FenluBean fenluBean : list) {
                        if("借".equals(fenluBean.getJiedai())) {
                            answer += fenluBean.getJiedai() + ":" + fenluBean.getKemu() + "       " + fenluBean.getJine() + "\n";
                        }else{
                            answer += "   "+fenluBean.getJiedai() + ":" + fenluBean.getKemu() + "       " + fenluBean.getJine() + "\n";
                        }
                    }
                    holder.right_answer.setText(answer);
                }else{
                    holder.right_answer.setText("暂无答案");
                }

            }else{
                holder.right_answer.setText(title.getAnswer());
            }


        }else{
            holder.right_answer.setText("暂无答案");
        }
      if(title.getScore()!=null){
          holder.item_score.setText(title.getScore()+"分");
      }else{
          holder.item_score.setText("暂无分数");
      }

      holder.delete.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
          simpleAnswerFragment.deleteTitle(title);
          }
      });


      holder.edit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              simpleAnswerFragment.editTitile(title);
          }
      });

    return convertView;
    }


    private final  class MyViewHolder {

        private TextView question_des ;
        private TextView item_a ;
        private TextView item_b ;
        private TextView item_c ;
        private TextView item_d ;
        private TextView right_answer;
        private LinearLayout selectContainer ;
        private TextView item_score ;
        private TextView type_text ;
        private ImageView type ;
        private ImageView edit ;
        private ImageView delete ;

    }


}
