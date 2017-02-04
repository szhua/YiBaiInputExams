package com.yibaieducation.input.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yibaieducation.bean.Ti_title;
import com.yibaieducation.bean.Ti_title_parent;
import com.yibaieducation.input.Config;
import com.yibaieducation.input.R;
import com.yibaieducation.input.adapter.SimpleAnswerAdapter;
import com.yibaieducation.input.base.BaseFragment;
import com.yibaieducation.input.dao.GetTitlesDao;
import com.yibaieducation.input.dao.TiTitleParentDao;
import com.yibaieducation.input.dao.basedao.SqlResult;
import com.yibaieducation.input.ui.AddFenluActivity;
import com.yibaieducation.input.ui.AddJiSuanActivity;
import com.yibaieducation.input.ui.IndefiniteSelectActivity;
import com.yibaieducation.input.ui.SimpleAnswerActivity;
import com.yibaieducation.input.util.ToastUtil;
import com.yibaieducation.input.widget.NoScrollListView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * YiBaiInputExams
 * Create   2017/1/20 17:38;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class SimpleAnswerFragment extends BaseFragment implements SqlResult, View.OnClickListener {

    @InjectView(R.id.des)
    TextView des;
    @InjectView(R.id.listview)
    NoScrollListView listview;
    @InjectView(R.id.addfenlu)
    TextView addfenlu;
    @InjectView(R.id.add_select)
    TextView addSelect;
    @InjectView(R.id.add_jisuan)
    TextView addJisuan;
    @InjectView(R.id.eidt_matrial)
    ImageView eidtMatrial;

    private long paperId;
    private long parentId;

    private GetTitlesDao getTitlesDao;
    private TiTitleParentDao tiTitleParentDao;


    private Ti_title_parent parent;
    private List<Ti_title> titles;

    private SimpleAnswerAdapter adapter;


    public static SimpleAnswerFragment getInstance(long paperId, long parentId) {
        SimpleAnswerFragment simpleAnswerFragment = new SimpleAnswerFragment();
        simpleAnswerFragment.paperId = paperId;
        simpleAnswerFragment.parentId = parentId;
        return simpleAnswerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTitlesDao = new GetTitlesDao(getDaoSession(), this);
        tiTitleParentDao = new TiTitleParentDao(getDaoSession(), this);
        adapter = new SimpleAnswerAdapter(getContext(),this);
        adapter.setTitleList(titles);
    }

    public void deleteTitle(Ti_title title){
        getTitlesDao.requestDelte(Config.REQUEST_CODE_1,title);
        showProgress(true);
    }
    public  void  editTitile(Ti_title title){
        Intent intent =null ;
       switch (title.getType_code()){
           case Config.FENLU_TYPE:
               intent =new Intent(getContext(),AddFenluActivity.class) ;
               break;
           case  Config.INDEFINITE_TYPE:
               intent =new Intent(getContext(),IndefiniteSelectActivity.class);
               break;
           case  Config.CALCULATE_TYPE:
               intent =new Intent(getContext(),AddJiSuanActivity.class) ;
               break;
       }
        intent.putExtra("paperId",paperId);
        intent.putExtra("parentId",parentId);
        intent.putExtra("title",title);
        intent.putExtra("edit",true);
        startActivity(intent);
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.simple_answer_fragment_layout, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addfenlu.setOnClickListener(this);
        addSelect.setOnClickListener(this);
        addJisuan.setOnClickListener(this);
        eidtMatrial.setOnClickListener(this);
        listview.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getTitlesDao.getSimpleTitle(String.valueOf(paperId), String.valueOf(parentId));
        tiTitleParentDao.getParentDataById(String.valueOf(parentId));
        showProgress(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void success(int requestCode, int erroCode, String msg) {
        showProgress(false);
        if (requestCode == Config.REQUEST_CODE_4) {
            ToastUtil.showToast(getContext(), msg);
            titles = getTitlesDao.getTitles();
            adapter.setTitleList(titles);
        } else if (requestCode == Config.REQUEST_CODE_3) {
            if (tiTitleParentDao.getParentList() != null && !tiTitleParentDao.getParentList().isEmpty())
                parent = tiTitleParentDao.getParentList().get(0);
            if (null != parent) {
                des.setText(parent.getDes());
            }
        }else if (requestCode==Config.REQUEST_CODE_1){
            getTitlesDao.getSimpleTitle(String.valueOf(paperId), String.valueOf(parentId));
        }
    }

    @Override
    public void onClick(View view) {
        if (view == addfenlu) {
            Intent intent = new Intent(getContext(), AddFenluActivity.class);
            intent.putExtra("paperId", paperId);
            intent.putExtra("parentId", parentId);
            startActivityForResult(intent, 101);
        } else if (view == addSelect) {
            Intent intent = new Intent(getContext(), IndefiniteSelectActivity.class);
            intent.putExtra("paperId", paperId);
            intent.putExtra("parentId", parentId);
            startActivityForResult(intent, 101);
        } else if (view == addJisuan) {
            Intent intent = new Intent(getContext(), AddJiSuanActivity.class);
            intent.putExtra("paperId", paperId);
            intent.putExtra("parentId", parentId);
            startActivityForResult(intent, 101);
        }else if(view==eidtMatrial){
            Intent intent =new Intent(getContext(), SimpleAnswerActivity.class);
            intent.putExtra("isAdd",false);
            intent.putExtra("paperId",paperId);
            intent.putExtra("parent",parent) ;
            intent.putExtra("edit",true) ;
            startActivityForResult(intent,101);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        ToastUtil.showToast(getContext(), "okokokokokokoko");

        super.onActivityResult(requestCode, resultCode, data);
    }
}
