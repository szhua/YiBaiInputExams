package com.yibaieducation.input.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.yibaieducation.bean.Ti_item_config;
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
public class ItemConfigAdapter extends BaseAadpter<Ti_item_config> {

    public ItemConfigAdapter(Context context) {
        super(context);
        Logger.d("createwaaf");
    }

    @Override
    public BaseViewHolder onCreateHolder(ViewGroup parent) {
        return new SubjectViewHolder(layoutInflater.inflate(R.layout.item_home_list,parent,false));
    }

    @Override
     public void onBindDatas(BaseViewHolder holder, int position) {

        SubjectViewHolder ho = (SubjectViewHolder) holder;
        Ti_item_config sku_subject =datas.get(position);
        ho.text.setText(sku_subject.getItem_name());

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
                    Intent intent =new Intent(v.getContext(), AddPaperActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }




}
