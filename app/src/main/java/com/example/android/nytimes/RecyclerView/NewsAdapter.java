package com.example.android.nytimes.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.nytimes.R;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    ArrayList<String> newsHeadline=new ArrayList<>();
    ArrayList<String> url=new ArrayList<>();
    final private ListItemClickListener itemClickListener;
    public void settingUpHeadlines(ArrayList<String> arrayList)
    {
        this.newsHeadline.clear();
        this.newsHeadline.addAll(arrayList);
    }
    public void settingUpURL(ArrayList<String> arrayList)
    {
        this.url.clear();
        this.url.addAll(arrayList);
    }
    public interface ListItemClickListener
    {
        void onListItemClicked(int clickedItemIndex);
    }
    public NewsAdapter(ListItemClickListener listItemClickListener)
    {
        itemClickListener=listItemClickListener;
    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        int layoutIdForListItem=R.layout.recycle_layout;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(layoutIdForListItem,parent,false);
        NewsViewHolder newsViewHolder=new NewsViewHolder(view);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return newsHeadline.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        Button recycleButton;
        public NewsViewHolder(View itemView)
        {
            super(itemView);
            recycleButton=itemView.findViewById(R.id.recycleButton);
            recycleButton.setOnClickListener(this);
        }
        public void bind(int i)
        {
            recycleButton.setText(newsHeadline.get(i));
        }


        @Override
        public void onClick(View view) {
            int adapterposition=getAdapterPosition();
            itemClickListener.onListItemClicked(adapterposition);
        }
    }
}
