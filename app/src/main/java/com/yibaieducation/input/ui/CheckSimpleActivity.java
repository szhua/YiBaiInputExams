package com.yibaieducation.input.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yibaieducation.bean.Ti_title_parent;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.R;
import com.yibaieducation.input.base.BaseActivity;
import com.yibaieducation.input.dao.TiTitleParentDao;
import com.yibaieducation.input.dao.basedao.SqlResult;
import com.yibaieducation.input.ui.fragment.SimpleAnswerFragment;
import com.yibaieducation.input.util.ToastUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CheckSimpleActivity extends BaseActivity implements SqlResult {
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.add_button)
    ImageView addButton;
    @InjectView(R.id.container)
    LinearLayout container;
    private TiTitleParentDao parentDao;
    private List<Ti_title_parent> parentList;
    private long paperId;
    private SimpleAdapter  simpleAdapter;


    private boolean isAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_simple);
        ButterKnife.inject(this);

        paperId = getIntent().getLongExtra("paperId", 0);

        parentDao = new TiTitleParentDao(getDaoSession(), this);
        parentDao.getParentDataByPaperId(String.valueOf(paperId));
        showProgress(true);

        simpleAdapter = new SimpleAdapter(getSupportFragmentManager());
        simpleAdapter.setParentList(parentList);

        viewpager.setAdapter(simpleAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSimpleQuestion();
            }
        });
    }

    public void addSimpleQuestion() {
        Intent intent =new Intent(this,SimpleAnswerActivity.class);
        intent.putExtra("paperId",paperId);
        intent.putExtra("isAdd",true) ;
        startActivityForResult(intent,101);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simpl_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_action:
                addSimpleQuestion();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void success(int requestCode, int erroCode, String msg) {
        showProgress(false);
        if (requestCode == Config.REQUEST_CODE_2) {

            parentList = parentDao.getParentList();

               if(parentList==null||parentList.isEmpty()){
                   container.setVisibility(View.VISIBLE);
               }else{
                   container.setVisibility(View.GONE);
               }

                simpleAdapter.setParentList(parentList);

              //显示到添加的位置；
                if(isAdd){
                    if(viewpager.getAdapter().getCount()>simpleAdapter.getCount()-1)
                    viewpager.setCurrentItem(simpleAdapter.getCount()-1);
                }

        }

    }


    private final class SimpleAdapter extends FragmentPagerAdapter {

        private List<Ti_title_parent> parentList;

        public void setParentList(List<Ti_title_parent> parentList) {
            this.parentList = parentList;
            notifyDataSetChanged();
        }

        public SimpleAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return SimpleAnswerFragment.getInstance(paperId, parentList.get(position).getId());
        }

        @Override
        public int getCount() {
            return parentList == null ? 0 : parentList.size();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==101&&resultCode==RESULT_OK){
            parentDao.getParentDataByPaperId(String.valueOf(paperId));
            isAdd =data.getBooleanExtra("isAdd",false);
            showProgress(true);
        }

    }
}
