package com.example.registeration;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DetailCourseListAdapter extends BaseAdapter {

    private Context context;
    private List<DetailCourse> detailCourseList;

    public DetailCourseListAdapter(Context context, List<DetailCourse> detailCourseList) {
        this.context = context;
        this.detailCourseList = detailCourseList;
    }

    @Override
    public int getCount() {
        return detailCourseList.size();
    }

    @Override
    public Object getItem(int position) {
        return detailCourseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.detail_course, null);
        TextView courseCode = (TextView) v.findViewById(R.id.DcourseCode);
        TextView courseTitle = (TextView) v.findViewById(R.id.DcourseTitle);
        TextView courseArea = (TextView) v.findViewById(R.id.DcourseArea);
        TextView courseCredit= (TextView) v.findViewById(R.id.DcourseCredit);

        courseCode.setText(detailCourseList.get(i).getCourseCode()+"");
        courseTitle.setText(detailCourseList.get(i).getCourseTitle()+"");
        courseArea.setText(detailCourseList.get(i).getCourseArea()+"");
        courseCredit.setText(detailCourseList.get(i).getCourseCredit()+"학점");

        v.setTag(detailCourseList.get(i).getCourseID());
        return v;
    }

}
