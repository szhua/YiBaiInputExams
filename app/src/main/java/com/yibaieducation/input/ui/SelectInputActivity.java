package com.yibaieducation.input.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yibaieducation.bean.Ti_title;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.R;
import com.yibaieducation.input.base.BaseActivity;
import com.yibaieducation.input.dao.GetTitlesDao;
import com.yibaieducation.input.dao.basedao.SqlResult;
import com.yibaieducation.input.util.ToastUtil;

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SelectInputActivity extends BaseActivity implements SqlResult {

    @InjectView(R.id.title_des)
    EditText titleDes;
    @InjectView(R.id.title_analysis)
    EditText titleAnalysis;
    @InjectView(R.id.item_a)
    EditText itemA;
    @InjectView(R.id.item_b)
    EditText itemB;
    @InjectView(R.id.item_c)
    EditText itemC;
    @InjectView(R.id.item_d)
    EditText itemD;
    @InjectView(R.id.item_score)
    EditText itemScore;
    @InjectView(R.id.radios)
    RadioGroup radios;
    @InjectView(R.id.commit)
    TextView commit;
    @InjectView(R.id.select_container)
    LinearLayout selectContainer;
    @InjectView(R.id.check_a)
    CheckBox checkA;
    @InjectView(R.id.check_b)
    CheckBox checkB;
    @InjectView(R.id.check_c)
    CheckBox checkC;
    @InjectView(R.id.check_d)
    CheckBox checkD;
    @InjectView(R.id.radio_judge)
    RadioGroup radioJudge;
    @InjectView(R.id.checks_container)
    LinearLayout checksContainer;
    @InjectView(R.id.radio_a)
    RadioButton radioA;
    @InjectView(R.id.radio_b)
    RadioButton radioB;
    @InjectView(R.id.radio_c)
    RadioButton radioC;
    @InjectView(R.id.radio_d)
    RadioButton radioD;
    @InjectView(R.id.radio_right)
    RadioButton radioRight;
    @InjectView(R.id.radio_wrong)
    RadioButton radioWrong;


    private Integer type_code;
    private String des;
    private String A;
    private String B;
    private String C;
    private String D;
    private Float score;
    private String answer = "";
    private String analyze_text;
    private Integer paper_id;
    private String create_time;


    private GetTitlesDao getTitlesDao;


    private boolean edit;


    private Ti_title tiTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_input);
        ButterKnife.inject(this);
        setTitle("编辑单选题");
        getTitlesDao = new GetTitlesDao(getDaoSession(), this);
        long paperId = getIntent().getLongExtra("paperId", 0);
        paper_id = Integer.parseInt(String.valueOf(paperId));
        type_code = getIntent().getIntExtra("type", 0);
        edit = getIntent().getBooleanExtra("edit", false);


        if (edit) {
            tiTitle = (Ti_title) getIntent().getSerializableExtra("title");
        }


        switch (type_code) {
            case Config.SINGLE_TYPE:
                selectContainer.setVisibility(View.VISIBLE);
                radios.setVisibility(View.VISIBLE);

                /*编辑的情况下*/
                if (edit) {
                    answer = tiTitle.getAnswer();
                    switch (answer) {
                        case "A":
                            radioA.setChecked(true);
                            break;
                        case "B":
                            radioB.setChecked(true);
                            break;
                        case "C":
                            radioC.setChecked(true);
                            break;
                        case "D":
                            radioD.setChecked(true);
                            break;
                    }
                    titleDes.setText(tiTitle.getDes());
                    titleAnalysis.setText(tiTitle.getAnalyze_text());
                    itemA.setText(tiTitle.getItemA());
                    itemB.setText(tiTitle.getItemB());
                    itemC.setText(tiTitle.getItemC());
                    itemD.setText(tiTitle.getItemD());
                    itemScore.setText(""+tiTitle.getScore());

                }

                break;
            case Config.MULTI_TYPE:
                selectContainer.setVisibility(View.VISIBLE);
                checksContainer.setVisibility(View.VISIBLE);

                if (edit) {
                    answer = tiTitle.getAnswer();
                    if (answer.contains("A")) {
                        checkA.setChecked(true);
                    }
                    if (answer.contains("B")) {
                        checkB.setChecked(true);
                    }
                    if (answer.contains("C")) {
                        checkC.setChecked(true);
                    }
                    if (answer.contains("D")) {
                        checkD.setChecked(true);
                    }

                    titleDes.setText(tiTitle.getDes());
                    titleAnalysis.setText(tiTitle.getAnalyze_text());
                    itemA.setText(tiTitle.getItemA());
                    itemB.setText(tiTitle.getItemB());
                    itemC.setText(tiTitle.getItemC());
                    itemD.setText(tiTitle.getItemD());
                    itemScore.setText(""+tiTitle.getScore());

                }

                break;
            case Config.JUDGE_TYPE:
                radioJudge.setVisibility(View.VISIBLE);
                answer = "正确";
                if (edit) {
                    answer = tiTitle.getAnswer();
                    if ("正确".equals(answer)) {
                      radioRight.setChecked(true);
                    }else{
                      radioWrong.setChecked(false);
                    }
                    itemScore.setText(""+tiTitle.getScore());
                    titleDes.setText(tiTitle.getDes());
                    titleAnalysis.setText(tiTitle.getAnalyze_text());
                }


                break;
        }
        radios.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_a:
                        answer = "A";
                        ToastUtil.showToast(getApplicationContext(), "A");
                        break;
                    case R.id.radio_b:
                        answer = "B";
                        ToastUtil.showToast(getApplicationContext(), "B");
                        break;
                    case R.id.radio_c:
                        answer = "C";
                        ToastUtil.showToast(getApplicationContext(), "C");
                        break;
                    case R.id.radio_d:
                        answer = "D";
                        ToastUtil.showToast(getApplicationContext(), "D");
                        break;
                }

            }
        });


        radioJudge.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_wrong:
                        answer = "错误";
                        break;
                    case R.id.radio_right:
                        answer = "正确";
                        break;
                }

            }
        });
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

    private boolean checkState() {
        des = titleDes.getText().toString();
        analyze_text = titleAnalysis.getText().toString();

        if (type_code == Config.MULTI_TYPE) {
            answer = getMulitAnswer();
        }

        if (type_code == Config.SINGLE_TYPE || type_code == Config.MULTI_TYPE) {
            A = itemA.getText().toString();
            B = itemB.getText().toString();
            C = itemC.getText().toString();
            D = itemD.getText().toString();
        }

        create_time = (String) DateFormat.format("yy-MM-dd HH:mm:ss", new Date());

        if (TextUtils.isEmpty(des)) {
            ToastUtil.showToast(this, "请输入题目描述");
            return false;
        }

        if (TextUtils.isEmpty(analyze_text)) {
            ToastUtil.showToast(this, "请输入题目解析");
            return false;
        }

        if ((type_code == Config.SINGLE_TYPE) || (type_code == Config.MULTI_TYPE)) {
            if (TextUtils.isEmpty(A)) {
                ToastUtil.showToast(this, "请输入选项A");
                return false;
            }
            if (TextUtils.isEmpty(B)) {
                ToastUtil.showToast(this, "请输入选项B");
                return false;
            }
            if (TextUtils.isEmpty(C)) {
                ToastUtil.showToast(this, "请输入选项C");
                return false;
            }
            if (TextUtils.isEmpty(D)) {
                ToastUtil.showToast(this, "请输入选项D");
                return false;
            }
        }


        if (TextUtils.isEmpty(answer)) {
            ToastUtil.showToast(this, "请输入正确答案");
            return false;
        }

        if (!TextUtils.isEmpty(itemScore.getText().toString())) {
            try {
                score = Float.parseFloat(itemScore.getText().toString());
            } catch (Exception e) {
                ToastUtil.showToast(this, "分数解析出错");
                return false;
            }
        } else {
            ToastUtil.showToast(this, "请输入分数！");
            return false;
        }
        return true;
    }

    @OnClick(R.id.commit)
    public void onClick() {
        if (checkState()) {



            Ti_title ti_title = new Ti_title();
            ti_title.setAnalyze_text(analyze_text);
            ti_title.setAnswer(answer);
            ti_title.setCreate_time(create_time);

            if (type_code == Config.SINGLE_TYPE || type_code == Config.MULTI_TYPE) {
                ti_title.setItemA(A);
                ti_title.setItemB(B);
                ti_title.setItemC(C);
                ti_title.setItemD(D);
            } else {
                ti_title.setItemA(A);
                ti_title.setItemB(B);
                ti_title.setItemC("");
                ti_title.setItemD("");
            }

            ti_title.setDes(des);
            ti_title.setPaper_id(paper_id);
            ti_title.setParent_id("");
            ti_title.setScore(score);
            ti_title.setStatus(0);
            ti_title.setType_code(type_code);

            if(!edit){
                getTitlesDao.insertTitle(ti_title);
                showProgress(true);
            }else{
                ti_title.setId(this.tiTitle.getId());
                getTitlesDao.updateTitle(ti_title);
                showProgress(true);
            }
        }
    }

    @Override
    public void success(int requestCode, int erroCode, String msg) {
        showProgress(false);
        if (requestCode == Config.REQUEST_CODE_1) {
            ToastUtil.showToast(this, msg);
            setResult(RESULT_OK);
            finish();
        }else if(requestCode==Config.REQUEST_CODE_2){
            ToastUtil.showToast(this,msg);
            setResult(RESULT_OK);
            finish();
        }
    }
}
