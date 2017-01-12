package com.yibaieducation.input;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;
import com.yibaieducation.dao.DaoMaster;
import com.yibaieducation.dao.DaoSession;

import io.github.skyhacker2.sqliteonweb.SQLiteOnWeb;

/**
 * YiBaiInputExams
 * Create   2017/1/10 13:51;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class InitApplication extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.t(Config.TAG);

        setupDatabase();
        SQLiteOnWeb.init(this).start();
    }


    @NonNull
    public  DaoSession getDaoSession(){
       return  daoSession ;
    }

    private void setupDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,getString(R.string.db_name), null);
        SQLiteDatabase db = helper.getWritableDatabase();
        //注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster daoMaster =  new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }
}
