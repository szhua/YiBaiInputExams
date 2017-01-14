package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Index;
import de.greenrobot.daogenerator.Schema;

public class Main {

    public static void main(String[] args) throws  Exception{



        Schema schema = new Schema(1, "com.yibaieducation.bean");
        schema.setDefaultJavaPackageDao("com.yibaieducation.dao");
        schema.setDefaultJavaPackageTest("com.yibaieducation.test");
        schema.enableActiveEntitiesByDefault();
        schema.enableKeepSectionsByDefault();


        /*试卷*/
        Entity ti_paper =schema.addEntity("Ti_paper");
        ti_paper.addIdProperty();
        ti_paper.addStringProperty("name");
        ti_paper.addIntProperty("type") ;
        ti_paper.addIntProperty("sku_code") ;
        ti_paper.addIntProperty("subject_code");
        ti_paper.addIntProperty("status");
        ti_paper.addStringProperty("update_time") ;
        ti_paper.addStringProperty("total_count");
        ti_paper.addStringProperty("answer_date") ;
        ti_paper.addStringProperty("total_score") ;
        ti_paper.addStringProperty("start_time");
        ti_paper.addStringProperty("answer_time");
        ti_paper.addIntProperty("paper_order");
        /*题目*/
        Entity ti_title = schema.addEntity("Ti_title");
        ti_title.addIdProperty();
        ti_title.addIntProperty("type_code");
        ti_title.addStringProperty("des") ;
        ti_title.addStringProperty("itemA") ;
        ti_title.addStringProperty("itemB") ;
        ti_title.addStringProperty("itemC");
        ti_title.addStringProperty("itemD") ;
        ti_title.addFloatProperty("score") ;
        ti_title.addStringProperty("answer");
        ti_title.addStringProperty("analyze_text") ;
        ti_title.addStringProperty("parent_id") ;
        ti_title.addIntProperty("paper_id");
        ti_title.addIntProperty("status") ;
        ti_title.addStringProperty("create_time") ;
        /*简答题目的描述*/
        Entity title_parent =schema.addEntity("Ti_title_parent") ;
        title_parent.addIdProperty();
        title_parent.addIntProperty("type_code");
        title_parent.addStringProperty("des") ;
        title_parent.addStringProperty("itemA") ;
        title_parent.addStringProperty("itemB") ;
        title_parent.addStringProperty("itemC");
        title_parent.addStringProperty("itemD") ;
        title_parent.addFloatProperty("score") ;
        title_parent.addStringProperty("answer");
        title_parent.addStringProperty("analyze_text") ;
        title_parent.addStringProperty("parent_id") ;
        title_parent.addIntProperty("paper_id");
        title_parent.addIntProperty("status") ;
        title_parent.addStringProperty("create_time") ;
        /*简答 item*/
        Entity ti_item =schema.addEntity("Ti_item");
        ti_item.addIdProperty();
        ti_item.addIntProperty("title_id") ;
        ti_item.addStringProperty("item_name");
        ti_item.addStringProperty("answer");
        ti_item.addStringProperty("price") ;
        ti_item.addIntProperty("score") ;
        ti_item.addIntProperty("sort_number") ;

         /*ti_item_config*/
        Entity ti_item_config=schema.addEntity("Ti_item_config");
        ti_item_config.addIdProperty() ;
        ti_item_config.addIntProperty("sku_id");
        ti_item_config.addStringProperty("item_name");
        ti_item_config.addStringProperty("item_code");
        ti_item_config.addStringProperty("parent_id") ;
        ti_item_config.addIntProperty("is_leaf");

        /*
        * ti_sku_subject:
id           INTEGER NOT NULL
sku        INTEGER
sku_name      TEXT
subject_code INTEGER
subject_name TEXT
PRIMARY KEY ( id )
*/
        Entity ti_sku_subject =schema.addEntity("Ti_sku_subject");
        ti_sku_subject.addIdProperty() ;
        ti_sku_subject.addIntProperty("sku");
        ti_sku_subject.addStringProperty("sku_name");
        ti_sku_subject.addIntProperty("subject_code");
        ti_sku_subject.addStringProperty("subject_name").unique();

        DaoGenerator daoGenerator = new DaoGenerator();
        daoGenerator.generateAll(schema, "app/src/main/javagen");
    }


}
