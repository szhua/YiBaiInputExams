package com.yibaieducation.input.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.yibaieducation.bean.Ti_paper;
import com.yibaieducation.bean.Ti_title;
import com.yibaieducation.input.R;
import com.yibaieducation.input.base.BaseActivity;
import com.yibaieducation.input.ui.fragment.AddTopicFragment;
import com.yibaieducation.input.util.ToastUtil;

/*添加题目界面*/
public class AddTopicsActivity extends BaseActivity {
    private long  paperId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_topics);

         setTitle("添加题目");

         paperId =getIntent().getLongExtra("paperId",0);
         ToastUtil.showToast(this,"paperID:"+paperId);
         getSupportFragmentManager().beginTransaction().add(R.id.container,AddTopicFragment.inStance(paperId)).commit() ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.check_simple_answer_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.showsimple:
                Intent intent =new Intent(this,CheckSimpleActivity.class);
                intent.putExtra("paperId",paperId);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
