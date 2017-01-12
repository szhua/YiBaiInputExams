package com.yibaieducation.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.yibaieducation.bean.Ti_item_config;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table TI_ITEM_CONFIG.
*/
public class Ti_item_configDao extends AbstractDao<Ti_item_config, Long> {

    public static final String TABLENAME = "TI_ITEM_CONFIG";

    /**
     * Properties of entity Ti_item_config.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Sku_id = new Property(1, Integer.class, "sku_id", false, "SKU_ID");
        public final static Property Item_name = new Property(2, String.class, "item_name", false, "ITEM_NAME");
        public final static Property Item_code = new Property(3, String.class, "item_code", false, "ITEM_CODE");
        public final static Property Parent_id = new Property(4, String.class, "parent_id", false, "PARENT_ID");
        public final static Property Is_leaf = new Property(5, Integer.class, "is_leaf", false, "IS_LEAF");
    };

    private DaoSession daoSession;


    public Ti_item_configDao(DaoConfig config) {
        super(config);
    }
    
    public Ti_item_configDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'TI_ITEM_CONFIG' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'SKU_ID' INTEGER," + // 1: sku_id
                "'ITEM_NAME' TEXT UNIQUE ," + // 2: item_name
                "'ITEM_CODE' TEXT," + // 3: item_code
                "'PARENT_ID' TEXT," + // 4: parent_id
                "'IS_LEAF' INTEGER);"); // 5: is_leaf
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'TI_ITEM_CONFIG'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Ti_item_config entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer sku_id = entity.getSku_id();
        if (sku_id != null) {
            stmt.bindLong(2, sku_id);
        }
 
        String item_name = entity.getItem_name();
        if (item_name != null) {
            stmt.bindString(3, item_name);
        }
 
        String item_code = entity.getItem_code();
        if (item_code != null) {
            stmt.bindString(4, item_code);
        }
 
        String parent_id = entity.getParent_id();
        if (parent_id != null) {
            stmt.bindString(5, parent_id);
        }
 
        Integer is_leaf = entity.getIs_leaf();
        if (is_leaf != null) {
            stmt.bindLong(6, is_leaf);
        }
    }

    @Override
    protected void attachEntity(Ti_item_config entity) {
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
    public Ti_item_config readEntity(Cursor cursor, int offset) {
        Ti_item_config entity = new Ti_item_config( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // sku_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // item_name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // item_code
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // parent_id
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5) // is_leaf
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Ti_item_config entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSku_id(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setItem_name(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setItem_code(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setParent_id(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setIs_leaf(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Ti_item_config entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Ti_item_config entity) {
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