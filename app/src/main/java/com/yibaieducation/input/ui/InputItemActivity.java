package com.yibaieducation.input.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yibaieducation.bean.Ti_account_subs;
import com.yibaieducation.bean.Ti_account_types;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.R;
import com.yibaieducation.input.base.BaseActivity;
import com.yibaieducation.input.dao.TiAccountTypesDao;
import com.yibaieducation.input.dao.GetAccountsDao;
import com.yibaieducation.input.dao.basedao.SqlResult;
import com.yibaieducation.input.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class InputItemActivity extends BaseActivity implements SqlResult ,View.OnClickListener{


    private static String[] types = new String[]{"资产类", "负债类", "所有者权益类", "成本类", "损益类"};

    private static String[] zichans = new String[]{"库存现金", "银行存款", "其他货币资金", "交易性金融资产", "应收票据", "应收账款"
            , "预付账款", "其他应收款", "坏账准备", "材料采购", "在途物资", "原材料", "材料成本差异", "库存商品", "包装物及低值易耗品",
            "存货跌价准备", "待摊费用", "长期股权投资", "固定资产", "累计折旧", "固定资产减值准备"
            , "在建工程", "工程物资", "固定资产清理", "无形资产", "累计摊销", "长期待摊费用", "待处理财产损益"
    };
    private static String[] fuzhais = new String[]{
            "短期借款", "交易性金融负债", "应付票据", "应付账款", "预收账款", "应付职工薪酬", "应交税费-应交增值税(进项税额)", "应交税费-应交增值税(进项税额转出)"
            , "应交税费-应交增值税(销项税额)", "应交税费-应交增值税(已交税金)", "应交税费-应交营业税", "应交税费-应交城市维护建设税", "应缴税费-应交教育附加税"
            , "应交税费-应交所得税", "应付利息", "应付股利(或应付利润)", "预提费用", "长期借款", "预计负债", "其他应付款"
    };

    private static String[] suoyous = new String[]{
            "实收资本(或股本)", "资本公积", "盈余公积", "本年利润", "利润分配"
    };
    private static String[] chengbens = new String[]{
            "生产成本", "制造费用", "劳务成本"
    };
    private String[] sunyis = new String[]{
            "主营业务收入", "其他业务收入", "投资收益", "营业外收入", "主营业务成本", "其他业务成本", "营业税金及附加", "销售费用", "管理费用",
            "财务费用", "资产减值损失", "营业外支出", "所得税费用", "以前年度损益调整"
    };
    @InjectView(R.id.types)
    TextView type;
    @InjectView(R.id.account)
    TextView account;
    @InjectView(R.id.insert)
    TextView insert;
    @InjectView(R.id.qury)
    TextView qury;


    private GetAccountsDao getAccountsDao;
    private TiAccountTypesDao tiAccountTypesDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_item);
        ButterKnife.inject(this);
        getAccountsDao = new GetAccountsDao(getDaoSession(), this);
        tiAccountTypesDao = new TiAccountTypesDao(getDaoSession(), this);

        insert.setOnClickListener(this);
        qury.setOnClickListener(this);

    }

    @Override
    public void success(int requestCode, int erroCode, String msg) {
        showProgress(false);
      if(requestCode== Config.REQUEST_CODE_1){
          ToastUtil.showToast(this,"插入分类成功！");
      }else if(requestCode==Config.REQUEST_CODE_2){
           String str ="" ;
          for (Ti_account_types ti_account_types : tiAccountTypesDao.getTypes()) {
              str+=ti_account_types.getType_name()+"\n";
          }
          type.setText(str);
      }else if(requestCode==Config.REQUEST_CODE_3){
          ToastUtil.showToast(this,"插入科目成功！");
      }else if(requestCode==Config.REQUEST_CODE_4){
          String str ="" ;
          for (Ti_account_subs ti_account_subs : getAccountsDao.getSubses()) {
              str +=ti_account_subs.getSub_name()+"\n" ;
              account.setText(str);
          }
      }
    }

    @Override
    public void onClick(View v) {
     if(v==insert){
         showProgress(true);
         List<Ti_account_types> typeList =new ArrayList<>() ;
         for (String s : types) {
             Ti_account_types ti =new Ti_account_types() ;
             ti.setType_name(s);
             typeList.add(ti);
         }
         tiAccountTypesDao.insertTypes(typeList);

         showProgress(true);
         List<Ti_account_subs >subList =new ArrayList<>() ;
         for (String zichan : zichans) {
             Ti_account_subs sub =new Ti_account_subs() ;
             sub.setParent_id(1);
             sub.setSub_name(zichan);
             sub.setParent_name("资产类");
             subList.add(sub);
         }
         for (String zichan : fuzhais) {
             Ti_account_subs sub =new Ti_account_subs() ;
             sub.setParent_id(2);
             sub.setSub_name(zichan);
             sub.setParent_name("负债类");
             subList.add(sub);
         }
         for (String zichan : suoyous) {
             Ti_account_subs sub =new Ti_account_subs() ;
             sub.setParent_id(3);
             sub.setSub_name(zichan);
             sub.setParent_name("所有者权益类");
             subList.add(sub);
         }

         for (String zichan : chengbens) {
             Ti_account_subs sub =new Ti_account_subs() ;
             sub.setParent_id(4);
             sub.setSub_name(zichan);
             sub.setParent_name("成本类");
             subList.add(sub);
         }

         for (String zichan : sunyis) {
             Ti_account_subs sub =new Ti_account_subs() ;
             sub.setParent_id(5);
             sub.setSub_name(zichan);
             sub.setParent_name("损益类");
             subList.add(sub);
         }
         getAccountsDao.insertSubses(subList);

     }else if(v==qury){
         tiAccountTypesDao.getItems();
         getAccountsDao.getSubs();
     }
    }
}
