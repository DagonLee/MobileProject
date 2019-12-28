package com.example.registeration;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayAdapter yearAdapter;
    private Spinner yearSpinner;
    private ArrayAdapter termAdapter;
    private Spinner termSpinner;
    private ArrayAdapter  areaAdapter;
    private Spinner areaSpinner;
    private ArrayAdapter majorAdapter;
    private Spinner majorSpinner;

    private String courseYear="";
    private String courseTerm="";
    private String courseMajor="";
    private String courseArea="";
    private OnFragmentInteractionListener mListener;

    private ListView courseListView;
    private CourseListAdapter adapter;
    private List<Course> courseList;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    public void onActivityCreated(Bundle b)
    {
        super.onActivityCreated(b);
        yearSpinner=(Spinner)getView().findViewById(R.id.yearSpinner);
        termSpinner=(Spinner)getView().findViewById(R.id.termSpinner);
        majorSpinner=(Spinner)getView().findViewById(R.id.areaSpinner);
        areaSpinner=(Spinner)getView().findViewById(R.id.majorSpinner);
        yearAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.courseYear, android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);
        termAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.courseTerm, android.R.layout.simple_spinner_dropdown_item);
        termSpinner.setAdapter(termAdapter);
        majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.coursemajor, android.R.layout.simple_spinner_dropdown_item);
        majorSpinner.setAdapter(majorAdapter);
        areaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.courseArea, android.R.layout.simple_spinner_dropdown_item);
        areaSpinner.setAdapter(areaAdapter);

        courseListView = (ListView)getView().findViewById(R.id.courseListView);
        courseList = new ArrayList<Course>();
        adapter = new CourseListAdapter(getContext().getApplicationContext(), courseList,this);
        courseListView.setAdapter(adapter);

        Button searchButton =(Button)getView().findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(getActivity(),yearSpinner.getSelectedItem().toString()+termSpinner.getSelectedItem().toString()+areaSpinner.getSelectedItem().toString()+majorSpinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                new BackgroundTask().execute();
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add, container, false);

//        yearSpinner=(Spinner)view.findViewById(R.id.yearSpinner);
//        termSpinner=(Spinner)view.findViewById(R.id.termSpinner);
//        majorSpinner=(Spinner)view.findViewById(R.id.areaSpinner);
//        areaSpinner=(Spinner)view.findViewById(R.id.majorSpinner);
//        yearAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.courseYear, android.R.layout.simple_spinner_dropdown_item);
//        yearSpinner.setAdapter(yearAdapter);
//        termAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.courseTerm, android.R.layout.simple_spinner_dropdown_item);
//        termSpinner.setAdapter(termAdapter);
//        majorAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.coursemajor, android.R.layout.simple_spinner_dropdown_item);
//        majorSpinner.setAdapter(majorAdapter);
//        areaAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.courseArea, android.R.layout.simple_spinner_dropdown_item);
//        areaSpinner.setAdapter(areaAdapter);


        return view;



        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_add, container, false);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;
        protected void onPreExecute() {
            try {
                target = "http://tinwoon21.cafe24.com/CourseList.php?courseYear=" + URLEncoder.encode(yearSpinner.getSelectedItem().toString().substring(0,4), "UTF-8") +
                        "&courseTerm=" + URLEncoder.encode(termSpinner.getSelectedItem().toString(), "UTF-8") +
                        "&courseMajor=" + URLEncoder.encode(majorSpinner.getSelectedItem().toString(), "UTF-8")+ "&courseArea=" + URLEncoder.encode(areaSpinner.getSelectedItem().toString(), "UTF-8") ;
                Toast.makeText(getContext(),target,Toast.LENGTH_SHORT).show();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        public void onPostExecute(String result) {
            try {
                // AlertDialog dia;
                //AlertDialog.Builder build = new AlertDialog.Builder(AddFragment.this.getContext());
                //dia=build.setMessage(result).setPositiveButton("확인",null).create();
                //dia.show();


                courseList.clear();
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                int courseID;//강의 고유 번호
                String courseCode;//
                int courseYear;//해당 연도
                String courseTerm;
                String courseTitle;
                int courseCredit;
                String courseMajor;
                String courseArea;
                while(count<jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    courseTitle = object.getString("courseTitle");
                    courseCode = object.getString("courseCode");
                    courseArea = object.getString("courseArea");
                    courseCredit=object.getInt("courseCredit");
                    courseMajor=object.getString("courseMajor");
                    courseID=object.getInt("courseID");
                    courseTerm=object.getString("courseTerm");
                    courseYear=object.getInt("courseYear");
                    Course course = new Course(courseID, courseCode,courseYear, courseTerm, courseTitle, courseCredit, courseMajor, courseArea);
                    courseList.add(course);
                    count++;
                    if(count==0){
                        AlertDialog dialog;
                        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(AddFragment.this.getActivity());
                        dialog = builder.setMessage("조회된 강의가 없습니다..")
                                .setPositiveButton("확인", null)
                                .create();
                        dialog.show();
                    }
                    adapter.notifyDataSetChanged();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
