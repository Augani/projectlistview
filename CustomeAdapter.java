package com.ganyobicodes.listviewapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomeAdapter extends ArrayAdapter<Post> implements View.OnClickListener {
    private Context context;
    private ArrayList<Post> posts;
    private static LayoutInflater inflater = null;

    private static class ViewHolder {
        TextView title;
        TextView id;
        LinearLayout item;
    }

    public CustomeAdapter(Context context, ArrayList<Post> posts) {
        super(context, R.layout.list_item, posts);
        this.context = context;
        this.posts = posts;
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
       Post post = getItem(position);

       ViewHolder viewHolder;

       final View res;

       if(convertView == null){
           viewHolder = new ViewHolder();
           inflater = LayoutInflater.from(getContext());
           convertView = inflater.inflate(R.layout.list_item, parent, false);
           viewHolder.title = convertView.findViewById(R.id.postTitle);
           viewHolder.id = convertView.findViewById(R.id.postsub);
           viewHolder.item = convertView.findViewById(R.id.mainitem);

           res = convertView;

           convertView.setTag(viewHolder);
       }else{
           viewHolder= (ViewHolder)convertView.getTag();
           res = convertView;
       }

       viewHolder.title.setText(post.getTitle());
       viewHolder.id.setText(String.valueOf(post.getId()));
       viewHolder.item.setOnClickListener(this);



        return convertView;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer)v.getTag();
        Object object = getItem(position);
        Post post = (Post)object;



    }


}
