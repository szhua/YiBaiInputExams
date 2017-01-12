package com.yibaieducation.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.yibaieducation.bean.Ti_title;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table TI_TITLE.
*/
public class Ti_titleDao extends AbstractDao<Ti_title, Long> {

    public static final String TABLENAME = "TI_TITLE";

    /**
     * Properties of entity Ti_title.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Type_code = new Property(1, Integer.class, "type_code", false, "TYPE_CODE");
        public final static Property Des = new Property(2, String.class, "des", false, "DES");
        public final static Property ItemA = new Property(3, String.class, "itemA", false, "ITEM_A");
        public final static Property ItemB = new Property(4, String.class, "itemB", false, "ITEM_B");
        public final static Property ItemC = new Property(5, String.class, "itemC", false, "ITEM_C");
        public final static Property ItemD = new Property(6, String.class, "itemD", false, "ITEM_D");
        public final static Property Score = new Property(7, Float.class, "score", false, "SCORE");
        public final static Property Answer = new Property(8, String.class, "answer", false, "ANSWER");
        public final static Property Analyze_text = new Property(9, String.class, "analyze_text", false, "ANALYZE_TEXT");
        public final static Property Parent_id = new Property(10, String.class, "parent_id", false, "PARENT_ID");
        public final static Property Paper_id = new Property(11, Integer.class, "paper_id", false, "PAPER_ID");
        public final static Property Status = new Property(12, Integer.class, "status", false, "STATUS");
        public final static Property Create_time = new Property(13, String.class, "create_time", false, "CREATE_TIME");
    };

    private DaoSession daoSession;


    public Ti_titleDao(DaoConfig config) {
        super(config);
    }
    
    public Ti_titleDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'TI_TITLE' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'TYPE_CODE' INTEGER," + // 1: type_code
                "'DES' TEXT," + // 2: des
                "'ITEM_A' TEXT," + // 3: itemA
                "'ITEM_B' TEXT," + // 4: itemB
                "'ITEM_C' TEXT," + // 5: itemC
                "'ITEM_D' TEXT," + // 6: itemD
                "'SCORE' REAL," + // 7: score
                "'ANSWER' TEXT," + // 8: answer
                "'ANALYZE_TEXT' TEXT," + // 9: analyze_text
                "'PARENT_ID' TEXT," + // 10: parent_id
                "'PAPER_ID' INTEGER," + // 11: paper_id
                "'STATUS' INTEGER," + // 12: status
                "'CREATE_TIME' TEXT);"); // 13: create_time
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'TI_TITLE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Ti_title entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer type_code = entity.getType_code();
        if (type_code != null) {
            stmt.bindLong(2, type_code);
        }
 
        String des = entity.getDes();
        if (des != null) {
            stmt.bindString(3, des);
        }
 
        String itemA = entity.getItemA();
        if (itemA != null) {
            stmt.bindString(4, itemA);
        }
 
        String itemB = entity.getItemB();
        if (itemB != null) {
            stmt.bindString(5, itemB);
        }
 
        String itemC = entity.getItemC();
        if (itemC != null) {
            stmt.bindString(6, itemC);
        }
 
        String itemD = entity.getItemD();
        if (itemD != null) {
            stmt.bindString(7, itemD);
        }
 
        Float score = entity.getScore();
        if (score != null) {
            stmt.bindDouble(8, score);
        }
 
        String answer = entity.getAnswer();
        if (answer != null) {
            stmt.bindString(9, answer);
        }
 
        String analyze_text = entity.getAnalyze_text();
        if (analyze_text != null) {
            stmt.bindString(10, analyze_text);
        }
 
        String parent_id = entity.getParent_id();
        if (parent_id != null) {
            stmt.bindString(11, parent_id);
        }
 
        Integer paper_id = entity.getPaper_id();
        if (paper_id != null) {
            stmt.bindLong(12, paper_id);
        }
 
        Integer status = entity.getStatus();
        if (status != null) {
            stmt.bindLong(13, status);
        }
 
        String create_time = entity.getCreate_time();
        if (create_time != null) {
            stmt.bindString(14, create_time);
        }
    }

    @Override
    protected void attachEntity(Ti_title entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Ti_title readEntity(Cursor cursor, int offset) {
        Ti_title entity = new Ti_title( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // type_code
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // des
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // itemA
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // itemB
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // itemC
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // itemD
            cursor.isNull(offset + 7) ? null : cursor.getFloat(offset + 7), // score
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // answer
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // analyze_text
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // parent_id
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // paper_id
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // status
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13) // create_time
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Ti_title entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setType_code(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setDes(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setItemA(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setItemB(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setItemC(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setItemD(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setScore(cursor.isNull(offset + 7) ? null : cursor.getFloat(offset + 7));
        entity.setAnswer(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setAnalyze_text(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setParent_id(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setPaper_id(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setStatus(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setCreate_time(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Ti_title entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Ti_title entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}