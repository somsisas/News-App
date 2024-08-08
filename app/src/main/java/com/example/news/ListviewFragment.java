/*
 * @author: Soumay Agarwal
 * @description: This class is known as the ListviewFragment.
 *               It is the fragment which sets a title, an edit text,
 *               a search button and the list view.
 */
package com.example.news;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class ListviewFragment extends Fragment {
    public Activity containerActivity = null;
    private ArrayList<String> req_list = new ArrayList<String>();
    public ArrayList<String> content_list = new ArrayList<String>();
    public ArrayList<String> website_names_list = new ArrayList<String>();
    private NewAdapter adapter;
    public String website_name;
    public String desc;
    public ArrayList<String> url_web = new ArrayList<String>();
    public String[] next_list = new String[3];
    public String searchTerm;
    public String cur_date;

    /*
     * Empty constructor.
     */

    public ListviewFragment() {}

    /*
     * This method sets the container activity.
     */

    public void setContainerActivity(Activity containerActivity) {
        this.containerActivity = containerActivity;
    }

    /*
     * This method is the on create view method
     * which displays the content. Here we are setting
     * the onclick of the search button and the onclick
     * for listview for each web news article.
     *
     * @return v is a View object
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_listview, container, false);
        ListView languagesListView = v.findViewById(R.id.listview1);
        adapter = new NewAdapter(containerActivity, R.layout.news_row, req_list);
        languagesListView.setAdapter(adapter);
        EditText editText = (EditText) v.findViewById(R.id.et1);
        Button button = (Button) v.findViewById(R.id.but1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                String user_text = editText.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String currentDate = sdf.format(new Date());
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -7);
                Date todate1 = cal.getTime();
                String fromdate = sdf.format(todate1);
                cur_date = fromdate;
                searchTerm = user_text;
                JSONtask jsoNtask = new JSONtask();
                jsoNtask.execute();
            }
        });
        languagesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                PreviewFragment frag = new PreviewFragment();
                frag.setContainerActivity(this);
                Bundle bundle = new Bundle();
                next_list[0] = website_names_list.get(i);
                next_list[1] = content_list.get(i);
                next_list[2] = url_web.get(i);
                bundle.putStringArray("the_list",next_list);
                frag.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.ll1, frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return v;
    }

    /*
     * This method is the on stop
     * method.
     */

    @Override
    public void onStop() {
        super.onStop();
        getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }

    /*
     * This method is the on Destroy
     * method.
     */

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /*
     * This method is the on Resume
     * method.
     */

    @Override
    public void onResume() {
        super.onResume();
    }

    /*
     * This is an asynctask which is responsible for making API calls to newsapi.org
     * in the background and setting the results of various things in onpostexexute.
     */

    private class JSONtask extends AsyncTask<String, Integer, JSONObject> {
        /*
         * This method is responsible for doing tasks in background.
         *
         * @param strs is a string
         * @return JSONObject is returned from the provided URL string
         */
        protected JSONObject doInBackground(String... strs) {
            try {
                String json = "";
                String line;
                URL url = new URL("https://newsapi.org/v2/everything?sortBy=publishedAt&q="+searchTerm+"&from="+ cur_date +"&apiKey=9530666cd33d452a8b70b1e7489c782c");
                URLConnection urlc = url.openConnection();
                urlc.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36 OPR/71.0.3770.284");
                BufferedReader in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
                while ((line = in.readLine()) != null) {
                    json += line;
                }
                in.close();
                return new JSONObject(json);
            } catch (Exception e) {
                e.printStackTrace(); }
            return null;
        }
        protected void onPostExecute(JSONObject tiobe) {
            /*
             * This method is responsible for setting various
             * things after the background tasks are complete.
             *
             * @param JSONObject is returned from the provided URL string
             */
            try {

                JSONArray rankings = tiobe.getJSONArray("articles");
                req_list.clear();
                content_list.clear();
                website_names_list.clear();
                url_web.clear();
                for (int i = 0; i < rankings.length(); i++) {
                    JSONObject rank = rankings.getJSONObject(i);
                    JSONObject src = rank.getJSONObject("source");
                    String web_name = src.getString("name");
                    website_name = src.getString("name");
                    if(web_name == null){
                        web_name="null";
                        website_name="null";
                    }
                    String author = rank.getString("author");
                    if(author == null){
                        author= "null";
                    }
                    desc = rank.getString("content");
                    if(desc == null){
                        desc= "null";
                    }
                    String url_w = rank.getString("url");
                    if(url_w==null){
                        url_w = "null";
                    }
                    url_web.add(url_w);
                    website_names_list.add(web_name);
                    content_list.add(i,desc);
                    req_list.add(i, web_name + ":" +"("+ author + ")");
                }
                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

}