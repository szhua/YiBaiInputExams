package com.yibaieducation.input.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yibaieducation.bean.Ti_title;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.R;
import com.yibaieducation.input.base.BaseActivity;
import com.yibaieducation.input.dao.GetTitlesDao;
import com.yibaieducation.input.dao.basedao.SqlResult;
import com.yibaieducation.input.util.GolbalUtil;
import com.yibaieducation.input.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AddJiSuanActivity extends BaseActivity implements SqlResult {


    @InjectView(R.id.des)
    EditText des;
    @InjectView(R.id.right_answer)
    EditText rightAnswer;
    @InjectView(R.id.commit)
    TextView commit;
    @InjectView(R.id.item_score)
    EditText itemScore;
    private GetTitlesDao getTitlesDao;

    private String describe;
    private String answer;
    private String score ;

    private Ti_title tiTitle ;
    private boolean isEdit ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ji_suan);
        ButterKnife.inject(this);
        setTitle("添加计算题");

        final long paperId = getIntent().getLongExtra("paperId", 0);
        final long parentId = getIntent().getLongExtra("parentId", 0);

         tiTitle = (Ti_title) getIntent().getSerializableExtra("title");
         isEdit =getIntent().getBooleanExtra("edit",false);

        /*编辑的情况下*/
        if(isEdit&&tiTitle!=null){
            des.setText(tiTitle.getDes());
            itemScore.setText(""+tiTitle.getScore());
            rightAnswer.setText(""+tiTitle.getAnswer());
        }

        getTitlesDao = new GetTitlesDao(getDaoSession(), this);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()) {
                    Ti_title ti_title = new Ti_title();
                    ti_title.setAnswer(answer);
                    ti_title.setPaper_id(Integer.parseInt(String.valueOf(paperId)));
                    ti_title.setItemA("");
                    ti_title.setType_code(Config.CALCULATE_TYPE);
                    ti_title.setItemB("");
                    ti_title.setItemC("");
                    ti_title.setItemD("");
                    ti_title.setStatus(0);
                    ti_title.setAnalyze_text("");
                    ti_title.setParent_id(String.valueOf(parentId));
                    ti_title.setCreate_time(GolbalUtil.formatDate());
                    ti_title.setDes(describe);
                    ti_title.setScore(Float.parseFloat(score));
                   if(!isEdit){
                       getTitlesDao.insertTitle(ti_title);
                       showProgress(true);
                   }else{
                       answer =AddJiSuanActivity.this.tiTitle.getAnswer();
                       ti_title.setId(AddJiSuanActivity.this.tiTitle.getId());
                       getTitlesDao.updateTitle(ti_title);
                       showProgress(true);
                   }
                }

            }
        });


    }

    private boolean checkInput() {
        describe = des.getText().toString();
        answer = rightAnswer.getText().toString();
        score =itemScore.getText().toString() ;

        if (TextUtils.isEmpty(describe)) {
            ToastUtil.showToast(this, "请输入问题描述");
            return false;
        }

        if(TextUtils.isEmpty(score)){
            ToastUtil.showToast(this,"请输入分数");
            return  false ;
        }

        if (TextUtils.isEmpty(answer)) {
            ToastUtil.showToast(this, "请输入答案");
            return false;
        }
        return true;
    }

    @Override
    public void success(int requestCode, int erroCode, String msg) {
        if (requestCode == Config.REQUEST_CODE_1){
            setResult(RESULT_OK);
            finish();
        }else if(requestCode==Config.REQUEST_CODE_2){
            setResult(RESULT_OK);
            finish();
        }
    }
}
