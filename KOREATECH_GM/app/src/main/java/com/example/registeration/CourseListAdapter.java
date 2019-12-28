package com.example.registeration;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

public class CourseListAdapter extends BaseAdapter {

    private Context context;
    private List<Course> courseList;
    private Fragment parent;
    public CourseListAdapter(Context context, List<Course>  courseList,Fragment parent) {
        this.context = context;
        this.courseList =  courseList;
        this.parent =parent;
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int position) {
        return  courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.course, null);
        TextView courseCode = (TextView) v.findViewById(R.id.courseCode);
        final TextView courseTitle= (TextView) v.findViewById(R.id.courseTitle);
        TextView courseMajor = (TextView) v.findViewById(R.id.courseMajor);
        TextView courseArea= (TextView) v.findViewById(R.id.courseArea);
        TextView courseCredit = (TextView) v.findViewById(R.id.courseCredit);

        courseCode.setText(courseList.get(i).getCourseCode()+"");
        courseTitle.setText(courseList.get(i).getCourseTitle()+"");
        courseMajor.setText(courseList.get(i).getCourseMajor()+"");
        courseArea.setText(courseList.get(i).getCourseArea()+"");
        courseCredit.setText(courseList.get(i).getCourseCredit()+"학점");

        v.setTag(courseList.get(i).getCourseID());

        Button addButton =(Button)v.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String userID =MainActivity.userID;
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            System.out.println("success는 무엇일까요:"+success);
                            if(success) {
                                System.out.println("success가 들어갔을까요?:"+success);

                                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());

                                AlertDialog dialog = builder.setMessage("강의가 추가되었습니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getActivity());
                                AlertDialog dialog = builder.setMessage("강의추가에 실패했습니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                            }
                        } catch (Exception e) {

                            e.printStackTrace();
                        }

                    }
                };
                AddRequest addRequest  = new AddRequest(userID, courseList.get(i).getCourseID()+"",responseListener);
                RequestQueue queue = Volley.newRequestQueue(parent.getActivity());
                queue.add(addRequest);

            }
        });
        return v;
    }

}
