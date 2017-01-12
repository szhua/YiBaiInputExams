package com.yibaieducation.input.bean;

/**
 * YiBaiInputExams
 * Create   2017/1/11 10:45;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class ItemConfigBean {


    String id ;
    String sku_id ;
    String item_name ;
    String item_code ;
    String parent_id ;
    String id_leaf ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public void setImte_code(String imte_code) {
        this.item_code = imte_code;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public void setId_leaf(String id_leaf) {
        this.id_leaf = id_leaf;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getImte_code() {
        return item_code;
    }

    public String getParent_id() {
        return parent_id;
    }

    public String getId_leaf() {
        return id_leaf;
    }

    @Override
    public String toString() {
        return "ItemConfigBean{" +
                "id=" + id +
                ", sku_id=" + sku_id +
                ", item_name='" + item_name + '\'' +
                ", item_code=" + item_code +
                ", parent_id=" + parent_id +
                ", id_leaf=" + id_leaf +
                '}';
    }
}
