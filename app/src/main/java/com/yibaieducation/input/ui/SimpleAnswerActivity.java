package com.yibaieducation.input.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yibaieducation.bean.Ti_title_parent;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.R;
import com.yibaieducation.input.base.BaseActivity;
import com.yibaieducation.input.dao.TiTitleParentDao;
import com.yibaieducation.input.dao.basedao.SqlResult;
import com.yibaieducation.input.util.ToastUtil;

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SimpleAnswerActivity extends BaseActivity implements View.OnClickListener ,SqlResult{

    @InjectView(R.id.des)
    EditText des;
    @InjectView(R.id.commit)
    TextView commit;

    private TiTitleParentDao tiTitleParentDao  ;
    private long  paperId;

    private boolean isAdd  ;

    private Ti_title_parent parent ;
    private boolean isEdit  ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_answer);
        ButterKnife.inject(this);

        commit.setOnClickListener(this);
        paperId =getIntent().getLongExtra("paperId",0);
        isAdd =getIntent().getBooleanExtra("isAdd",false);

        parent = (Ti_title_parent) getIntent().getSerializableExtra("parent");
        isEdit =getIntent().getBooleanExtra("edit",false) ;

        if(isEdit&&parent!=null){
            des.setText(parent.getDes());
        }
        tiTitleParentDao =new TiTitleParentDao(getDaoSession(),this) ;
    }

    @Override
    public void onClick(View view) {

     if(view==commit){
            String describe =des.getText().toString() ;
             if(TextUtils.isEmpty(describe)){
                 ToastUtil.showToast(this,"没有案例描述哦~~");
             }else{
                 Ti_title_parent parent =new Ti_title_parent() ;
                 parent.setPaper_id(Integer.parseInt(String.valueOf(paperId)));
                 parent.setAnswer("");
                 parent.setType_code(Config.SIMPLE_ANSWER);
                 parent.setDes(describe);
                 parent.setCreate_time((String) DateFormat.format("yyyy-MM-dd hh:mm:ss",new Date()));
                 parent.setAnalyze_text("");
                 parent.setItemA("");
                 parent.setItemB("");
                 parent.setItemC("");
                 parent.setItemD("");
                 parent.setScore(4f);
                 parent.setStatus(0);
                 parent.setParent_id("");
               if(!isEdit){
                   tiTitleParentDao.insertTiParent(parent);
                   showProgress(true);
               }else{
                   parent.setId(this.parent.getId());
                   tiTitleParentDao.requestUpdate(Config.REQUEST_CODE_2,parent);
               }
             }
        }
    }

    @Override
    public void success(int requestCode, int erroCode, String msg) {
        if(requestCode== Config.REQUEST_CODE_1){
          showProgress(false);
          commit.setEnabled(false);
          commit.setClickable(false);
          des.setEnabled(false);

          Intent intent =new Intent() ;
          intent.putExtra("isAdd",isAdd);
          setResult(RESULT_OK,intent);
          finish();

        }else if(requestCode==Config.REQUEST_CODE_2){
            showProgress(false);
            commit.setEnabled(false);
            commit.setClickable(false);
            des.setEnabled(false);

            Intent intent =new Intent() ;
            intent.putExtra("isAdd",isAdd);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
