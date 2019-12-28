package com.example.registeration;

import android.content.Context;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
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
public class DetailedStatusFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ScrollView scroll;

    private ListView grListView;
    private DetailCourseListAdapter gradapter;
    private List<DetailCourse> grList;

    private ListView gsListVIew;
    private DetailCourseListAdapter gsAdapter;
    private List<DetailCourse> gsList;

    private ListView msListVIew;
    private DetailCourseListAdapter msAdapter;
    private List<DetailCourse> msList;

    private ListView mrListVIew;
    private DetailCourseListAdapter mrAdapter;
    private List<DetailCourse> mrList;

    private ListView hrdrListVIew;
    private DetailCourseListAdapter hrdrAdapter;
    private List<DetailCourse> hrdrList;

    private ListView hrdsListVIew;
    private DetailCourseListAdapter hrdsAdapter;
    private List<DetailCourse> hrdsList;

    private ListView mscsListVIew;
    private DetailCourseListAdapter mscsAdapter;
    private List<DetailCourse> mscsList;

    private ListView mscrListVIew;
    private DetailCourseListAdapter mscrAdapter;
    private List<DetailCourse> mscrList;

    private ListView fListVIew;
    private DetailCourseListAdapter fAdapter;
    private List<DetailCourse> fList;

    static int generalR_credit_sum=0;
    static int generalS_credit_sum=0;
    static int majorR_credit_sum=0;
    static int majorS_credit_sum=0;
    static int HRDR_credit_sum=0;
    static int HRDS_credit_sum=0;
    static int MSCR_credit_sum=0;
    static int MSCS_credit_sum=0;
    static int free_credit_sum=0;

    public DetailedStatusFragment() {
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
    public static DetailedStatusFragment newInstance(String param1, String param2) {
        DetailedStatusFragment fragment = new DetailedStatusFragment();
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

        scroll = (ScrollView)getView().findViewById(R.id.scroll);

        grListView =(ListView)getView().findViewById(R.id.generalRList);
        grList= new ArrayList<DetailCourse>();
        gradapter =new DetailCourseListAdapter(getContext().getApplicationContext(),grList);
        grListView.setAdapter(gradapter);
        grListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scroll.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        gsListVIew = (ListView)getView().findViewById(R.id.generalSList);
        gsList =  new ArrayList<DetailCourse>();
        gsAdapter= new DetailCourseListAdapter(getContext().getApplicationContext(),gsList);
        gsListVIew.setAdapter(gsAdapter);
        gsListVIew.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scroll.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        msListVIew = (ListView)getView().findViewById(R.id.majorSList);
        msList =  new ArrayList<DetailCourse>();
        msAdapter= new DetailCourseListAdapter(getContext().getApplicationContext(),msList);
        msListVIew.setAdapter(msAdapter);
        msListVIew.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scroll.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        mrListVIew = (ListView)getView().findViewById(R.id.majorRList);;
        mrList =  new ArrayList<DetailCourse>();
        mrAdapter = new DetailCourseListAdapter(getContext().getApplicationContext(),mrList);
        mrListVIew.setAdapter(mrAdapter);
        mrListVIew.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scroll.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        hrdrListVIew = (ListView)getView().findViewById(R.id.HRDRList);
        hrdrList =  new ArrayList<DetailCourse>();
        hrdrAdapter = new DetailCourseListAdapter(getContext().getApplicationContext(),hrdrList);
        hrdrListVIew.setAdapter(hrdrAdapter);
        hrdrListVIew.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scroll.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        hrdsListVIew = (ListView)getView().findViewById(R.id.HRDSList);
        hrdsList =  new ArrayList<DetailCourse>();
        hrdsAdapter= new DetailCourseListAdapter(getContext().getApplicationContext(),hrdsList);
        hrdsListVIew.setAdapter(hrdsAdapter);
        hrdsListVIew.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scroll.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        mscsListVIew = (ListView)getView().findViewById(R.id.MSCSList);
        mscsList =  new ArrayList<DetailCourse>();
        mscsAdapter= new DetailCourseListAdapter(getContext().getApplicationContext(),mscsList);
        mscsListVIew.setAdapter(mscsAdapter);
        mscsListVIew.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scroll.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        mscrListVIew = (ListView)getView().findViewById(R.id.MSCRList);
        mscrList =  new ArrayList<DetailCourse>();
        mscrAdapter= new DetailCourseListAdapter(getContext().getApplicationContext(),mscrList);
        mscrListVIew.setAdapter(mscrAdapter);
        mscrListVIew.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scroll.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        fListVIew = (ListView)getView().findViewById(R.id.freeList);
        fList =  new ArrayList<DetailCourse>();
        fAdapter= new DetailCourseListAdapter(getContext().getApplicationContext(),fList);
        fListVIew.setAdapter(fAdapter);
        fListVIew.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scroll.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        new BackgroundTask().execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_detailed_status, container, false);

        return view;

        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_add, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onDetach() {
        super.onDetach();

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

                grList.clear();
                gsList.clear();
                msList.clear();
                msList.clear();
                hrdrList.clear();
                hrdsList.clear();
                mscsList.clear();
                mscrList.clear();
                fList.clear();

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");

                int count=0;
                int courseID;
                String courseCode;
                String courseArea;
                String courseTitle;
                int courseCredit;

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
                        grList.add(detailCourse);
                        TextView generalR_credit=getView().findViewById(R.id.generalR_credit);
                        generalR_credit_sum=generalR_credit_sum+courseCredit;
                        generalR_credit.setText(String.valueOf(generalR_credit_sum));

                    }
                    else if(courseArea.equals("교양선택")){
                        gsList.add(detailCourse);
                        TextView generalS_credit=getView().findViewById(R.id.generalS_credit);
                        generalS_credit_sum=generalS_credit_sum+courseCredit;
                        generalS_credit.setText(String.valueOf(generalS_credit_sum));
                    }
                    else if (courseArea.equals("전공선택")){
                        msList.add(detailCourse);
                        TextView majorS_credit=getView().findViewById(R.id.majorS_credit);
                        majorS_credit_sum=majorS_credit_sum+courseCredit;
                        majorS_credit.setText(String.valueOf(majorS_credit_sum));
                    }
                    else if (courseArea.equals("전공필수")){
                        mrList.add(detailCourse);
                        TextView majorR_credit=getView().findViewById(R.id.majorR_credit);
                        majorR_credit_sum=majorR_credit_sum+courseCredit;
                        majorR_credit.setText(String.valueOf(majorR_credit_sum));
                    }
                    else if (courseArea.equals("HRD필수")){
                        hrdrList.add(detailCourse);
                        TextView HRDR_credit=getView().findViewById(R.id.HRDR_credit);
                        HRDR_credit_sum=HRDR_credit_sum+courseCredit;
                        HRDR_credit.setText(String.valueOf(HRDR_credit_sum));
                    }
                    else if (courseArea.equals("HRD선택")){
                        hrdsList.add(detailCourse);
                        TextView HRDS_credit=getView().findViewById(R.id.HRDS_credit);
                        HRDS_credit_sum=HRDS_credit_sum+courseCredit;
                        HRDS_credit.setText(String.valueOf(HRDS_credit_sum));
                    }
                    else if (courseArea.equals("MSC선택")){
                        mscsList.add(detailCourse);
                        TextView MSCS_credit=getView().findViewById(R.id.MSCS_credit);
                        MSCS_credit_sum=MSCS_credit_sum+courseCredit;
                        MSCS_credit.setText(String.valueOf(MSCS_credit_sum));
                    }
                    else if (courseArea.equals("MSC필수")){
                        mscrList.add(detailCourse);
                        TextView MSCR_credit=getView().findViewById(R.id.MSCR_credit);
                        MSCR_credit_sum=MSCR_credit_sum+courseCredit;
                        MSCR_credit.setText(String.valueOf(MSCR_credit_sum));
                    }
                    else if( courseArea.equals("자유선택")){
                        fList.add(detailCourse);
                        TextView free_credit=getView().findViewById(R.id.free_credit);
                        free_credit_sum=free_credit_sum+courseCredit;
                        free_credit.setText(String.valueOf(free_credit_sum));
                    }

                    count++;
                    mrAdapter.notifyDataSetChanged();
                    msAdapter.notifyDataSetChanged();
                    gsAdapter.notifyDataSetChanged();
                    gradapter.notifyDataSetChanged();
                    hrdrAdapter.notifyDataSetChanged();
                    hrdsAdapter.notifyDataSetChanged();
                    mscsAdapter.notifyDataSetChanged();
                    mscrAdapter.notifyDataSetChanged();
                    fAdapter.notifyDataSetChanged();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
