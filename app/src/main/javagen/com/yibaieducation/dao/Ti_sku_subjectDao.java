package com.yibaieducation.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.yibaieducation.bean.Ti_sku_subject;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table TI_SKU_SUBJECT.
*/
public class Ti_sku_subjectDao extends AbstractDao<Ti_sku_subject, Long> {

    public static final String TABLENAME = "TI_SKU_SUBJECT";

    /**
     * Properties of entity Ti_sku_subject.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Sku = new Property(1, Integer.class, "sku", false, "SKU");
        public final static Property Sku_name = new Property(2, String.class, "sku_name", false, "SKU_NAME");
        public final static Property Subject_code = new Property(3, Integer.class, "subject_code", false, "SUBJECT_CODE");
        public final static Property Subject_name = new Property(4, String.class, "subject_name", false, "SUBJECT_NAME");
    };

    private DaoSession daoSession;


    public Ti_sku_subjectDao(DaoConfig config) {
        super(config);
    }
    
    public Ti_sku_subjectDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'TI_SKU_SUBJECT' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'SKU' INTEGER," + // 1: sku
                "'SKU_NAME' TEXT," + // 2: sku_name
                "'SUBJECT_CODE' INTEGER," + // 3: subject_code
                "'SUBJECT_NAME' TEXT UNIQUE );"); // 4: subject_name
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'TI_SKU_SUBJECT'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Ti_sku_subject entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer sku = entity.getSku();
        if (sku != null) {
            stmt.bindLong(2, sku);
        }
 
        String sku_name = entity.getSku_name();
        if (sku_name != null) {
            stmt.bindString(3, sku_name);
        }
 
        Integer subject_code = entity.getSubject_code();
        if (subject_code != null) {
            stmt.bindLong(4, subject_code);
        }
 
        String subject_name = entity.getSubject_name();
        if (subject_name != null) {
            stmt.bindString(5, subject_name);
        }
    }

    @Override
    protected void attachEntity(Ti_sku_subject entity) {
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
    public Ti_sku_subject readEntity(Cursor cursor, int offset) {
        Ti_sku_subject entity = new Ti_sku_subject( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // sku
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // sku_name
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // subject_code
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // subject_name
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Ti_sku_subject entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSku(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setSku_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSubject_code(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setSubject_name(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Ti_sku_subject entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Ti_sku_subject entity) {
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
