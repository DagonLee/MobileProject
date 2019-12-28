package com.example.registeration;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String userID=MainActivity.userID;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //졸업요건 학점
    private TextView Gtotal;//총학점
    private TextView GHRDS;//HRD 선택
    private TextView GHRDR;//HRD 필수
    private TextView GgeneralS;//교양 선택
    private TextView GgeneralR;//교양 필수
    private TextView GmscS;//MSC 선택
    private TextView GmscR;//MSC 필수
    private TextView GmajorR;//전공 필수
    private TextView GmajorS;//전공 선택
    private TextView Gfree;// 자유학점
    //사용자 학점들
    private TextView total;//총학점
    private TextView HRDS;//HRD 선택
    private TextView HRDR;// HRD 필수
    private TextView generalS;// 교양 선택
    private TextView generalR;//교양 필수
    private TextView mscS;//MSC 선택
    private TextView mscR;// MSC 필수
    private TextView majorR;//전공 필수
    private TextView majorS;// 전공 선택
    private TextView free;// 자유학점

    private int totalnum=0;//총학점
    private int HRDSnum=0;//HRD 선택
    private int  HRDRnum=0;// HRD 필수
    private int  generalSnum=0;// 교양 선택
    private int generalRnum=0;//교양 필수
    private int mscSnum=0;//MSC 선택
    private int  mscRnum=0;// MSC 필수
    private int  majorRnum=0;//전공 필수
    private int majorSnum=0;// 전공 선택
    private int freenum=0;// 자유학점
    private TextView userid;
    private TextView major;

    private CheckBox IPP;
    private CheckBox Project;
    private EditText Toeic;


    private OnFragmentInteractionListener mListener;

    public CourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
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

        IPP= (CheckBox)getView().findViewById(R.id.myIPP);
        Project= (CheckBox)getView().findViewById(R.id.my졸업작품);
        Toeic =(EditText)getView().findViewById(R.id.my토익) ;
        total =(TextView)getView().findViewById(R.id.my총학점);
        HRDS=(TextView)getView().findViewById(R.id.myHRD선택);
        HRDR=(TextView)getView().findViewById(R.id.myHRD필수);
        generalS=(TextView)getView().findViewById(R.id.my교양선택);
        generalR=(TextView)getView().findViewById(R.id.my교양필수);
        mscS=(TextView)getView().findViewById(R.id.myMSC선택);
        mscR=(TextView)getView().findViewById(R.id.myMSC필수);
        majorR=(TextView)getView().findViewById(R.id.my전공필수);
        majorS=(TextView)getView().findViewById(R.id.my전공선택);
        free=(TextView)getView().findViewById(R.id.my자유선택);
        userid =(TextView)getView().findViewById(R.id.userID);

        SharedPreferences prefs = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        if(prefs.getBoolean("IPP",true)){
            IPP.setChecked(true);
        }
        if(prefs.getBoolean("Project",true)){
            Project.setChecked(true);
        }

        Toeic.setText(prefs.getString("Toeic",""));

        new BackgroundTask().execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_course, container, false);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String target;
        protected void onPreExecute() {
            try {
                target = "http://tinwoon21.cafe24.com/DetailStatus.php?userID="+URLEncoder.encode(MainActivity.userID,"UTF-8");
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
//                 AlertDialog dia;
//                AlertDialog.Builder build = new AlertDialog.Builder(DetailedStatusFragment.this.getContext());
//                dia=build.setMessage(result).setPositiveButton("확인",null).create();
//                dia.show();

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");

                int count=0;
                int courseID;
                String courseCode;
                String courseArea;
                String courseTitle;
                int courseCredit;
                String Major="";

                while(count<jsonArray.length())
                {
                    JSONObject object = jsonArray.getJSONObject(count);

                    courseID =object.getInt("courseID");
                    courseCode =object.getString("courseCode");
                    courseArea =object.getString("courseArea");
                    courseTitle =object.getString("courseTitle");
                    courseCredit=object.getInt("courseCredit");
                    DetailCourse detailCourse =new DetailCourse( courseID, courseCode, courseArea, courseTitle, courseCredit);
                    Log.i("count","count++");
                    Toast.makeText(getContext(),courseArea,Toast.LENGTH_SHORT).show();
                    if(courseArea.equals("교양필수")){
                        generalRnum+=courseCredit;
                    }
                    else if(courseArea.equals("교양선택")){
                        generalSnum+=courseCredit;
                    }
                    else if (courseArea.equals("전공선택")){
                        majorSnum+=courseCredit;
                    }
                    else if (courseArea.equals("전공필수")){
                        majorRnum+=courseCredit;
                    }
                    else if (courseArea.equals("HRD필수")){
                        HRDRnum+=courseCredit;
                    }
                    else if (courseArea.equals("HRD선택")){
                        HRDSnum+=courseCredit;
                    }
                    else if (courseArea.equals("MSC선택")){
                        mscSnum+=courseCredit;
                    }
                    else if (courseArea.equals("MSC필수")){
                        mscRnum+=courseCredit;
                    }
                    else if( courseArea.equals("자유선택")){
                        freenum+=courseCredit;
                    }
                    count++;
                }
                userid.setText(MainActivity.userID);

                total.setText(Integer.toString(generalRnum+generalSnum+majorSnum+majorRnum+HRDSnum+HRDRnum+mscRnum+mscRnum+freenum));
                HRDS.setText(Integer.toString(HRDSnum));//HRD 선택
                HRDR.setText(Integer.toString(HRDRnum));
                generalS.setText(Integer.toString(generalSnum));
                generalR.setText(Integer.toString(generalRnum));
                mscS.setText(Integer.toString(mscSnum));
                mscR.setText(Integer.toString(mscRnum));
                majorR.setText(Integer.toString(majorRnum));
                majorS.setText(Integer.toString(majorSnum));
                free.setText(Integer.toString(freenum));

            } catch (Exception e) {
                e.printStackTrace();
            }
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

    public void onPause() {
        super.onPause();

        SharedPreferences prefs = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("IPP",IPP.isChecked());
        editor.putBoolean("Project",Project.isChecked());
        editor.putString("Toeic",Toeic.getText().toString());
        editor.apply();

    }

}
