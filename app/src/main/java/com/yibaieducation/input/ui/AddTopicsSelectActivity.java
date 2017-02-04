package com.yibaieducation.input.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yibaieducation.input.Config;
import com.yibaieducation.input.R;
import com.yibaieducation.input.base.BaseActivity;
import com.yibaieducation.input.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AddTopicsSelectActivity extends BaseActivity {

    @InjectView(R.id.single_bt)
    TextView singleBt;
    @InjectView(R.id.mutil_bt)
    TextView mutilBt;
    @InjectView(R.id.judge_bt)
    TextView judgeBt;
    private long  paperId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_topics_select_layout);
        ButterKnife.inject(this);
        setTitle("编辑题目");

         paperId = getIntent().getLongExtra("paperId",0);
         ToastUtil.showToast(this, "paperId:" + paperId);

    }

    @OnClick({R.id.single_bt, R.id.mutil_bt, R.id.judge_bt})
    public void onClick(View view) {
        Intent intent =null;

        switch (view.getId()) {
            case R.id.single_bt:
                intent =new Intent(this,SelectInputActivity.class) ;
                intent.putExtra("type", Config.SINGLE_TYPE) ;
                break;
            case R.id.mutil_bt:
                intent =new Intent(this,SelectInputActivity.class) ;
                intent.putExtra("type",Config.MULTI_TYPE);
                break;
            case R.id.judge_bt:
                intent =new Intent(this,SelectInputActivity.class);
                intent.putExtra("type",Config.JUDGE_TYPE);
                break;
        }
        if(intent!=null) {
            intent.putExtra("paperId", paperId);
            startActivityForResult(intent, 101);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101&&resultCode==RESULT_OK){
            setResult(RESULT_OK);
            finish();
        }
    }
}
