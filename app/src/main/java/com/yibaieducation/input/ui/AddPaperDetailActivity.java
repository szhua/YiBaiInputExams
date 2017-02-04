package com.yibaieducation.input.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.yibaieducation.bean.Ti_paper;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.R;
import com.yibaieducation.input.base.BaseActivity;
import com.yibaieducation.input.dao.GetPaperDao;
import com.yibaieducation.input.dao.basedao.SqlResult;
import com.yibaieducation.input.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AddPaperDetailActivity extends BaseActivity implements SqlResult {

    @InjectView(R.id.paper_name)
    EditText paperName;
    @InjectView(R.id.paper_time)
    EditText paperTime;
    @InjectView(R.id.paper_scores)
    EditText paperScores;
    @InjectView(R.id.commit)
    TextView commit;
    @InjectView(R.id.paper_total_count)
    EditText paperTotalCount;

    private String name;
    private String time;
    private String scores;
    private long subjectId;
    private String totalCount;


    private GetPaperDao getPaperDao;


    private static  final int EDIT_TYPE =1 ;
    private static  final int INPUT_TYPE =2 ;
    private int eidt_type = INPUT_TYPE;


    private Ti_paper ti_paper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paper_detail);
        ButterKnife.inject(this);


        setTitle("编辑试卷信息");

        ti_paper = (Ti_paper) getIntent().getSerializableExtra("ti_paper");

         if(ti_paper!=null){
             eidt_type =EDIT_TYPE ;
              paperName.setText(ti_paper.getName());
             paperTime.setText(ti_paper.getAnswer_time());
             paperScores.setText(ti_paper.getTotal_score());
             paperTotalCount.setText(ti_paper.getTotal_count());
         }

        subjectId = getIntent().getLongExtra("subjectId",0);

        getPaperDao = new GetPaperDao(getDaoSession(), this);

    }


    @OnClick(R.id.commit)
    public void onClick() {
        if (checkState()) {
           if(eidt_type==EDIT_TYPE){
                ti_paper.setName(name);
                ti_paper.setAnswer_time(time);
                ti_paper.setTotal_count(totalCount);
                ti_paper.setTotal_score(scores);
                getPaperDao.updateTiPaper(ti_paper);
                showProgress(true);

           }else{
               Ti_paper ti_paper = new Ti_paper();
               ti_paper.setName(name);
               /*sku 先暂定为subjectId*/
               ti_paper.setSku_code((int) subjectId);
               ti_paper.setAnswer_time(time);
               ti_paper.setTotal_score(scores);
               /*先暂定为1*/
               ti_paper.setType(1);
               /*未读*/
               ti_paper.setStatus(0);
               ti_paper.setTotal_count(totalCount);
               ti_paper.setSubject_code((int) subjectId);
               ti_paper.setAnswer_date("");
               ti_paper.setPaper_order(0);
               ti_paper.setUpdate_time("");
               ti_paper.setStart_time("");
               getPaperDao.insertPaper(ti_paper);
               showProgress(true);
           }
        }
    }

    public boolean checkState() {
        name = paperName.getText().toString();
        time = paperTime.getText().toString();
        scores = paperScores.getText().toString();
        totalCount =paperTotalCount.getText().toString();
        if (TextUtils.isEmpty(name)) {
            ToastUtil.showToast(this, "输入名称");
            return false;
        }
        if(TextUtils.isEmpty(totalCount)){
            ToastUtil.showToast(this,"输入题目个数");
            return  false ;
        }
        if (TextUtils.isEmpty(time)) {
            ToastUtil.showToast(this, "输入时间");
            return false;
        }
        if (TextUtils.isEmpty(scores)) {
            ToastUtil.showToast(this,"输入分数");
            return false;
        }
        return true;
    }

    @Override
    public void success(int requestCode, int erroCode, String msg) {
        if(requestCode== Config.REQUEST_CODE_2){
            showProgress(false);
            ToastUtil.showToast(this,"创建成功");
            setResult(RESULT_OK);
            finish();
        }else if(requestCode==Config.REQUEST_CODE_4){
            showProgress(false);
            setResult(RESULT_OK);
            ToastUtil.showToast(this,"编辑成功！");
            finish();
        }
    }
}
