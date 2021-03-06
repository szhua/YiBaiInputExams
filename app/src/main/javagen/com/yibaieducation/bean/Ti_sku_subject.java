package com.yibaieducation.bean;

import com.yibaieducation.dao.DaoSession;
import de.greenrobot.dao.DaoException;

import com.yibaieducation.dao.Ti_sku_subjectDao;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table TI_SKU_SUBJECT.
 */
public class Ti_sku_subject {

    private Long id;
    private Integer sku;
    private String sku_name;
    private Integer subject_code;
    private String subject_name;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient Ti_sku_subjectDao myDao;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Ti_sku_subject() {
    }

    public Ti_sku_subject(Long id) {
        this.id = id;
    }

    public Ti_sku_subject(Long id, Integer sku, String sku_name, Integer subject_code, String subject_name) {
        this.id = id;
        this.sku = sku;
        this.sku_name = sku_name;
        this.subject_code = subject_code;
        this.subject_name = subject_name;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTi_sku_subjectDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public String getSku_name() {
        return sku_name;
    }

    public void setSku_name(String sku_name) {
        this.sku_name = sku_name;
    }

    public Integer getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(Integer subject_code) {
        this.subject_code = subject_code;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
