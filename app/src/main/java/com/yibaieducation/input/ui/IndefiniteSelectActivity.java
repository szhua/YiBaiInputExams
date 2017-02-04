package com.yibaieducation.input.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
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

public class IndefiniteSelectActivity extends BaseActivity implements SqlResult {

    @InjectView(R.id.des)
    EditText des;
    @InjectView(R.id.item_a)
    EditText item_a;
    @InjectView(R.id.item_b)
    EditText item_b;
    @InjectView(R.id.item_c)
    EditText item_c;
    @InjectView(R.id.item_d)
    EditText item_d;
    @InjectView(R.id.commit)
    TextView commit;
    @InjectView(R.id.check_a)
    CheckBox checkA;
    @InjectView(R.id.check_b)
    CheckBox checkB;
    @InjectView(R.id.check_c)
    CheckBox checkC;
    @InjectView(R.id.check_d)
    CheckBox checkD;
    @InjectView(R.id.item_score)
    EditText itemScore;

    private GetTitlesDao getTitlesDao;


    private String answer;
    private String itemA;
    private String itemB;
    private String itemC;
    private String itemD;
    private String describe;
    private String score;


    private Ti_title tiTitle ;
    private  boolean isEdit ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indefinite_select);
        ButterKnife.inject(this);

        getTitlesDao = new GetTitlesDao(getDaoSession(), this);

        final long paperId = getIntent().getLongExtra("paperId", 0);
        final long parentId = getIntent().getLongExtra("parentId", 0);

         tiTitle = (Ti_title) getIntent().getSerializableExtra("title");
         isEdit =getIntent().getBooleanExtra("edit",false);

         if(isEdit&&tiTitle!=null){
          des.setText(tiTitle.getDes());
          itemScore.setText(""+tiTitle.getScore());
          item_a.setText(tiTitle.getItemA());
          item_b.setText(tiTitle.getItemB());
          item_c.setText(tiTitle.getItemC());
          item_d.setText(tiTitle.getItemD());
          answer =tiTitle.getAnswer() ;
          if(answer.contains("A")){
              checkA.setChecked(true);
          }
          if(answer.contains("B")){
              checkB.setChecked(true);
          }
          if(answer.contains("C")){
              checkC.setChecked(true);
          }
          if(answer.contains("D")){
              checkD.setChecked(true);
          }
         }

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()) {
                    Ti_title ti_title = new Ti_title();
                    ti_title.setAnswer(answer);
                    ti_title.setPaper_id(Integer.parseInt(String.valueOf(paperId)));
                    ti_title.setItemA(itemA);
                    ti_title.setType_code(Config.INDEFINITE_TYPE);
                    ti_title.setItemB(itemB);
                    ti_title.setItemC(itemC);
                    ti_title.setItemD(itemD);
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
                         ti_title.setId(IndefiniteSelectActivity.this.tiTitle.getId());
                         getTitlesDao.updateTitle(ti_title);
                         showProgress(true);
                     }
                }

            }
        });


    }

    private boolean checkInput() {

        describe = des.getText().toString();
        itemA = item_a.getText().toString();
        itemB = item_b.getText().toString();
        itemC = item_c.getText().toString();
        itemD = item_d.getText().toString();
        score =itemScore.getText().toString() ;

        if (TextUtils.isEmpty(describe)) {
            ToastUtil.showToast(this, "输入问题的描述");
            return false;
        }

        if(TextUtils.isEmpty(score)){
            ToastUtil.showToast(this,"请输入分数");
            return  false ;
        }


        if (TextUtils.isEmpty(itemA)) {
            ToastUtil.showToast(this, "输入A选项");
            return false;
        }
        if (TextUtils.isEmpty(itemB)) {
            ToastUtil.showToast(this, "请输入B选项");
            return false;
        }

        if (TextUtils.isEmpty(itemC)) {
            ToastUtil.showToast(this, "请输入C选项");
            return false;
        }
        if (TextUtils.isEmpty(itemD)) {
            ToastUtil.showToast(this, "请输入D选项");
            return false;
        }

        //都没有被选择的话
        if (!checkA.isChecked() && !checkB.isChecked() && !checkC.isChecked() && !checkD.isChecked()) {
            ToastUtil.showToast(this, "确定正确答案~~");
            return false;
        } else {
            answer = getMulitAnswer();
        }

        return true;
    }


    private String getMulitAnswer() {
        String answer = "";
        if (checkA.isChecked()) {
            answer += "A,";
        }
        if (checkB.isChecked()) {
            answer += "B,";
        }
        if (checkC.isChecked()) {
            answer += "C,";
        }
        if (checkD.isChecked()) {
            answer += "D";
        }
        return answer;
    }


    @Override
    public void success(int requestCode, int erroCode, String msg) {
        if (requestCode == Config.REQUEST_CODE_1) {
            setResult(RESULT_OK);
            finish();
        }else if(requestCode==Config.REQUEST_CODE_2){
            setResult(RESULT_OK);
            finish();
        }
    }
}
