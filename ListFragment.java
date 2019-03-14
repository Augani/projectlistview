package com.ganyobicodes.listviewapp;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    public ListFragment() {
        // Required empty public constructor
    }

    private ListView listView;
    ArrayList<Post> posts;
    private CustomeAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View listviewfrag =  inflater.inflate(R.layout.fragment_list, container, false);

        listView = listviewfrag.findViewById(R.id.listv);
        posts = new ArrayList<>();

        new GetPostAsyncTask().execute();



        return listviewfrag;
    }


    public class GetPostAsyncTask extends AsyncTask<Void, Void, String> {

        private String url;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s != null){
            try{
                JSONArray jsonArray = new JSONArray(s);
                for(int i = 0; i < jsonArray.length(); i++ ){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    String id = jsonObject.getString("id");
                    int idint = Integer.parseInt(id);
                    posts.add(new Post(title,idint));

                }

                adapter = new CustomeAdapter(getActivity(), posts);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Post post = posts.get(position);

                    }
                });

            }catch (JSONException e){
                e.printStackTrace();
            }
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            int res;
            InputStream inputStream;
            String resultcoming = null;
            try{
                URL url = new URL("https://jsonplaceholder.typicode.com/posts");
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
    }


}
