package com.ganyobicodes.listviewapp;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;


/**
 * A simple {@link Fragment} subclass.
 */
public class Detailfragment extends Fragment {


    public Detailfragment() {
        // Required empty public constructor
    }

    private TextView title;
    private TextView body;
    private TextView userId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View detailfarg =  inflater.inflate(R.layout.fragment_detailfragment, container, false);

        Intent  intent = getActivity().getIntent();
        if(intent.getExtras() != null){
            String id = intent.getStringExtra("id");
        }

        title = detailfarg.findViewById(R.id.detailTitle);
        body = detailfarg.findViewById(R.id.detailDescription);
        userId = detailfarg.findViewById(R.id.detailSubTitle);



        return detailfarg;
    }

    public class GetPostAsyncTask extends AsyncTask<String, Void, String> {

        private String url;

        @Override
        protected String doInBackground(String... strings) {
            String postId = strings[0];
            int res;
            InputStream inputStream;
            String resultcoming = null;
            try{
                URL url = new URL("https://jsonplaceholder.typicode.com/posts/"+postId);
                URLConnection urlConnection = url.openConnection();

                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) urlConnection;
                httpsURLConnection.setAllowUserInteraction(false);
                httpsURLConnection.setRequestMethod("GET");
                httpsURLConnection.connect();
                res = httpsURLConnection.getResponseCode();

                if(res == HttpsURLConnection.HTTP_OK){
                    inputStream = httpsURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
                    StringBuilder stringBuilder = new StringBuilder();
                    String newline;
                    while((newline = reader.readLine()) != null){
                        stringBuilder.append(newline).append("\n");
                    }
                    inputStream.close();
                    resultcoming = stringBuilder.toString();

                }else{

                }

            }catch (IOException e){
                e.printStackTrace();
            }
            return resultcoming;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s != null){
                try{
                    JSONObject jsonObject = new JSONObject(s);
                    String stitle = jsonObject.getString("title");
                    String sbody = jsonObject.getString("body");
                    String ssub = jsonObject.getString("userId");

                    title.setText(stitle);
                    body.setText(sbody);
                    userId.setText(ssub);



                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }


    }

}
