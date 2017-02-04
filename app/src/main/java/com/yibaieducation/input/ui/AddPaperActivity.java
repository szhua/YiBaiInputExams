package com.yibaieducation.input.ui;

import android.os.Bundle;
import com.yibaieducation.input.R;
import com.yibaieducation.input.base.BaseActivity;
import com.yibaieducation.input.ui.fragment.AddPaperFragment;
import com.yibaieducation.input.util.ToastUtil;

public class AddPaperActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paper);
        setTitle("添加试卷");
        long subjectID =getIntent().getLongExtra("subjectId",0);
        ToastUtil.showToast(this,"id"+getIntent().getLongExtra("subjectId",0));
        getSupportFragmentManager().beginTransaction().add(R.id.container,AddPaperFragment.newInstance(subjectID)).commit();
    }
}
