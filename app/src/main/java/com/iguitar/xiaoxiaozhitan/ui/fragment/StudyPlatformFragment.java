package com.iguitar.xiaoxiaozhitan.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.iguitar.xiaoxiaozhitan.MyWebViewActivity;
import com.iguitar.xiaoxiaozhitan.R;
import com.iguitar.xiaoxiaozhitan.databinding.FragmentStudyBinding;
import com.iguitar.xiaoxiaozhitan.model.StudyJavaBean;
import com.iguitar.xiaoxiaozhitan.ui.adapter.FragmentStudyAdapter;
import com.iguitar.xiaoxiaozhitan.ui.base.BaseFragment;

import java.util.ArrayList;

/**
 * 学习平台Fragment
 * Created by Jiang on 2017/4/13.
 */

public class StudyPlatformFragment extends BaseFragment {
    private FragmentStudyBinding binding;
    private ArrayList<StudyJavaBean> studyJavaBeanArrayList;
    private ArrayList<String> title;
    private ArrayList<String> urls;
    private ArrayList<String> description;
    private ArrayList<Integer> isMore;
    private FragmentStudyAdapter adapter;

    private TextView tv_tittle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = View.inflate(getActivity(), R.layout.fragment_study, null);
//        return view;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_study, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lazyLoad();
    }

    /**
     * 初始化页面
     */
    private void initView() {
        tv_tittle = (TextView) mActivity.findViewById(R.id.tv_title);
        tv_tittle.setText("学习平台");

        //相关下拉刷新的设置
        binding.mRefreshView.setPullLoadEnable(false);
        binding.mRefreshView.setAutoLoadMore(false);
//        mRefreshView.setAutoRefresh(true);

        binding.mRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.mRefreshView.stopRefresh();
                    }
                }, 1000);
            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {

            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        studyJavaBeanArrayList = new ArrayList<>();
        title = new ArrayList<>();
        urls = new ArrayList<>();
        description = new ArrayList<>();
        isMore = new ArrayList<>();

        title.add("优酷频道");
        title.add("腾讯课堂");
        title.add("百度传课");
        title.add("张弛小小指弹");
        title.add("指弹南通");

        urls.add("http://i.youku.com/xxzhitan");
        urls.add("http://xxzt.ke.qq.com");
        urls.add("http://www.chuanke.com/s4522102.html");
        urls.add("");
        urls.add("http://tieba.baidu.com/f?ie=utf-8&kw=指弹南通&fr=search&red_tag=x0723945520");

        description.add("http://i.youku.com/xxzhitan");
        description.add("http://xxzt.ke.qq.com");
        description.add("http://www.chuanke.com/s4522102.html");
        description.add("指弹微博");
        description.add("指弹南通");

        isMore.add(1);
        isMore.add(1);
        isMore.add(1);
        isMore.add(0);
        isMore.add(0);

        for (int i = 0; i < 5; i++) {
            StudyJavaBean temp = new StudyJavaBean();
            temp.setTitle(title.get(i));
            temp.setDescription(description.get(i));
            temp.setIsMore(isMore.get(i));
            studyJavaBeanArrayList.add(temp);
        }

        adapter = new FragmentStudyAdapter(mActivity, studyJavaBeanArrayList);
        binding.fragStudyLv.setAdapter(adapter);

        binding.fragStudyLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!"".equals(urls.get(i))) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", urls.get(i));
                    bundle.putString("title", title.get(i));
                    startMyActivity(MyWebViewActivity.class, bundle);
                }
            }
        });
    }

    @Override
    protected void lazyLoad() {
        initDatas();
        initView();
    }
}
