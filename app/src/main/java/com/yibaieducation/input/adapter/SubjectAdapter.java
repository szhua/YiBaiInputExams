package com.yibaieducation.input.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.yibaieducation.bean.Ti_sku_subject;
import com.yibaieducation.input.R;
import com.yibaieducation.input.adapter.base.BaseAadpter;
import com.yibaieducation.input.ui.AddPaperActivity;
import com.yibaieducation.input.util.ToastUtil;

/**
 * YiBaiInputExams
 * Create   2017/1/12 17:59;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class SubjectAdapter extends BaseAadpter<Ti_sku_subject> {

    public SubjectAdapter(Context context) {
        super(context);

    }

    @Override
     public BaseViewHolder onCreateHolder(ViewGroup parent) {
        return new SubjectViewHolder(layoutInflater.inflate(R.layout.item_home_list,parent,false));
      }

    @Override
      public void onBindDatas(BaseViewHolder holder, int position) {

        SubjectViewHolder ho = (SubjectViewHolder) holder;
        Ti_sku_subject sku_subject =datas.get(position);
        ho.text.setText(sku_subject.getSubject_name());

      }


    private final  class  SubjectViewHolder extends BaseViewHolder{
        private TextView text ;
        private CardView card ;
        public SubjectViewHolder(View itemView) {
            super(itemView);
            text= (TextView) itemView.findViewById(R.id.title);
            card = (CardView) itemView.findViewById(R.id.card);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  //  ToastUtil.showToast(v.getContext(),"ID:"+datas.get(getAdapterPosition()).getId());
                    Intent intent =new Intent(v.getContext(), AddPaperActivity.class);
                    intent.putExtra("subjectId",datas.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }




}
