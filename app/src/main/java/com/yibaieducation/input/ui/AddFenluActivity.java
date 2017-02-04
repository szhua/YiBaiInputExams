package com.yibaieducation.input.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yibaieducation.bean.Ti_title;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.R;
import com.yibaieducation.input.base.BaseActivity;
import com.yibaieducation.input.dao.GetTitlesDao;
import com.yibaieducation.input.dao.basedao.SqlResult;
import com.yibaieducation.input.util.GolbalUtil;
import com.yibaieducation.input.util.ToastUtil;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AddFenluActivity extends BaseActivity implements View.OnClickListener, SqlResult {

    @InjectView(R.id.container)
    LinearLayout container;
    @InjectView(R.id.desss)
    EditText desss;
    @InjectView(R.id.score)
    EditText scoreet;


    private LayoutInflater layoutInflater;

    private ArrayMap<Integer, View> views;
    private int itemCount;
    private ArrayList<FenluBean> fenlus;

    private long paperId;
    private long parentId;

    private String des;

    private GetTitlesDao getTitlesDao;

    private String score ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fenlu);
        ButterKnife.inject(this);
        setTitle("添加分录");

        paperId = getIntent().getLongExtra("paperId", 0);
        parentId = getIntent().getLongExtra("parentId", 0);

        getTitlesDao = new GetTitlesDao(getDaoSession(), this);
        layoutInflater = LayoutInflater.from(this);

        addView();

    }

    public boolean checkAllData() {

        des = desss.getText().toString();
        score =scoreet.getText().toString() ;


        if (TextUtils.isEmpty(des)) {
            ToastUtil.showToast(this, "请输入题目描述！");
            return  false ;
        }

        if(TextUtils.isEmpty(score)){
            ToastUtil.showToast(this,"请输入题目的分数");
            return  false ;
        }


        fenlus = new ArrayList<>();
        for (int i = 0; i < views.size(); i++) {
            int key = views.keyAt(i);
            View view = views.get(key);
            TextView selct_account_bt = (TextView) view.findViewById(R.id.selct_account_bt);
            EditText et = (EditText) view.findViewById(R.id.inputnumber);
            RadioButton jie = (RadioButton) view.findViewById(R.id.jie);
            String jine = et.getText().toString();
            String kemu = selct_account_bt.getText().toString();

            if ("选择科目".equals(kemu)) {
                ToastUtil.showToast(this, "请输入会计科目");
                return false;
            }
            if (jine.equals("")) {
                ToastUtil.showToast(this, "请输入金额");
                return false;
            }

            FenluBean fenbean = new FenluBean();
            fenbean.setJiedai(jie.isChecked() == true ? "借" : "贷");
            fenbean.setJine(Float.parseFloat(jine));
            fenbean.setKemu(kemu);

            fenlus.add(fenbean);
        }

        return true;

    }


    private void addView() {

        itemCount++;

        final View view = layoutInflater.inflate(R.layout.add_fenlu_item, null);
        view.setTag(itemCount);

        container.addView(view);

        if (views == null)
            views = new ArrayMap<>();

        views.put((Integer) view.getTag(), view);

        TextView selct_account_bt = (TextView) view.findViewById(R.id.selct_account_bt);
        selct_account_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFenluActivity.this, SelectAccountActivity.class);
                intent.putExtra("tag", (Integer) view.getTag());
                startActivityForResult(intent, 101);
            }
        });
        RadioButton jie = (RadioButton) view.findViewById(R.id.jie);
        jie.setChecked(true);
    }

    private void removeView() {

        if (views.isEmpty() || views.size() == 1) {
            return;
        }

        if (views.containsKey(itemCount)) {
            container.removeView(views.get(itemCount));
            views.remove(itemCount);

            itemCount--;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.add_action:
                addView();
                break;

            case R.id.delete_action:
                removeView();
                break;

            case R.id.commit_action:
                if (checkAllData()) {
                    Ti_title ti_title = new Ti_title();
                    Gson gson =new Gson() ;
                    ti_title.setAnswer( gson.toJson(fenlus));
                    ti_title.setPaper_id(Integer.parseInt(String.valueOf(paperId)));
                    ti_title.setItemA("");
                    ti_title.setType_code(Config.FENLU_TYPE);
                    ti_title.setItemB("");
                    ti_title.setItemC("");
                    ti_title.setItemD("");
                    ti_title.setStatus(0);
                    ti_title.setAnalyze_text("");
                    ti_title.setParent_id(String.valueOf(parentId));
                    ti_title.setCreate_time(GolbalUtil.formatDate());
                    ti_title.setDes(des);
                    ti_title.setScore(Float.parseFloat(score));
                    getTitlesDao.insertTitle(ti_title);
                    showProgress(true);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            int tag = data.getIntExtra("tag", 0);
            String name = data.getStringExtra("name");
            if (views.containsKey(tag)) {
                TextView bt = (TextView) views.get(tag).findViewById(R.id.selct_account_bt);
                bt.setText(name);
            }
        }
    }

    @Override
    public void success(int requestCode, int erroCode, String msg) {
        showProgress(false);
        if (requestCode == Config.REQUEST_CODE_1) {
            setResult(RESULT_OK);
            finish();
        }
    }

    public final class FenluBean {
        String jiedai;
        String kemu;
        float jine;

        public String getJiedai() {
            return jiedai;
        }

        public void setJiedai(String jiedai) {
            this.jiedai = jiedai;
        }

        public String getKemu() {
            return kemu;
        }

        public void setKemu(String kemu) {
            this.kemu = kemu;
        }

        public float getJine() {
            return jine;
        }

        public void setJine(float jine) {
            this.jine = jine;
        }

        @Override
        public String toString() {
            return "{" + jiedai + "," + kemu + "," + jine + "}";
        }
    }


}
