package com.yibaieducation.input.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.yibaieducation.bean.Ti_sku_subject;
import com.yibaieducation.dao.Ti_sku_subjectDao;
import com.yibaieducation.input.R;
import com.yibaieducation.input.ui.fragment.HomeListFragment;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initToolBar();

        getSupportFragmentManager().beginTransaction().add(R.id.container,new HomeListFragment()).commit();



    }
    /*设置标题栏*/
    private void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));
        toolbar.setSubtitleTextColor( ContextCompat.getColor(this,R.color.collect_color));
        toolbar.setSubtitle(getString(R.string.only_for_admin));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.setting) {
            Intent intent =new Intent(this,InitDataActivity.class) ;
            startActivity(intent);
            return true;
        }else if (id==R.id.setting1){
            Intent intent =new Intent(this,InputItemActivity.class) ;
            startActivity(intent);
        }else if(id==R.id.setting2){
            Intent intent =new Intent(this,SelectAccountActivity.class) ;
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
