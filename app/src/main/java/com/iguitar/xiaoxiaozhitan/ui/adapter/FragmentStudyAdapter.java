package com.iguitar.xiaoxiaozhitan.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iguitar.xiaoxiaozhitan.R;
import com.iguitar.xiaoxiaozhitan.model.StudyJavaBean;

import java.util.ArrayList;

/**
 * Created by Jiang on 2017/4/16.
 */

public class FragmentStudyAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<StudyJavaBean> studyJavaBeanArrayList;

    public FragmentStudyAdapter(Context mContext, ArrayList<StudyJavaBean> studyJavaBeanArrayList) {
        this.mContext = mContext;
        this.studyJavaBeanArrayList = studyJavaBeanArrayList;
    }

    @Override
    public int getCount() {
        return studyJavaBeanArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return studyJavaBeanArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        StudyJavaBean studyJavaBean = studyJavaBeanArrayList.get(i);
        if(convertView == null){
            convertView = View.inflate(mContext, R.layout.item_fragment_study_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_item_title = (TextView) convertView.findViewById(R.id.tv_item_title);
            viewHolder.tv_description = (TextView) convertView.findViewById(R.id.tv_url);
            viewHolder.iv_arrow = (ImageView) convertView.findViewById(R.id.iv_arrow);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_item_title.setText(studyJavaBean.getTitle());
        viewHolder.tv_description.setText(studyJavaBean.getDescription());
        if(studyJavaBean.getIsMore() == 0){
            viewHolder.iv_arrow.setVisibility(View.GONE);
        }else{
            viewHolder.iv_arrow.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    private static class ViewHolder{
        TextView tv_item_title;
        TextView tv_description;
        ImageView iv_arrow;
    }

}
