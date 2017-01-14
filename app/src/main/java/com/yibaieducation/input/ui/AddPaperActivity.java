package com.yibaieducation.input.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yibaieducation.input.R;
import com.yibaieducation.input.base.BaseActivity;
import com.yibaieducation.input.ui.fragment.AddPaperFragment;

public class AddPaperActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paper);


        String subjectID =getIntent().getStringExtra("subjectId");

        getSupportFragmentManager().beginTransaction().add(R.id.container,AddPaperFragment.newInstance(subjectID)).commit();





    }
}
