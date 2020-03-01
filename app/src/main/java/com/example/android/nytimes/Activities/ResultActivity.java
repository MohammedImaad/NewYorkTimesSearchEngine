package com.example.android.nytimes.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.android.nytimes.R;
import com.example.android.nytimes.RecyclerView.NewsAdapter;
import com.example.android.nytimes.Retrofit.GetDataService;
import com.example.android.nytimes.Retrofit.RetrofitClientInstance;
import com.example.android.nytimes.RetrofitGetterAndSetter.Doc;
import com.example.android.nytimes.RetrofitGetterAndSetter.Example;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity implements NewsAdapter.ListItemClickListener {
    ArrayList<String> url=new ArrayList<>();

    public void makingACall(String year,String month)
    {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Example> call = service.getAllPhotos(year, month);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                List<Doc> list = response.body().getResponse().getDocs();
                ArrayList<String> headlinesArrayList=new ArrayList<>();
                for (Doc d : list) {
                    if(d.getSnippet()!=null) {
                        headlinesArrayList.add(d.getSnippet());
                        url.add(d.getWebUrl());
                    }
                }
                settingUpRecyclerView(headlinesArrayList,url);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("Error",t.getMessage());

            }
        });
    }

    public void settingUpRecyclerView(ArrayList<String> arrayList,ArrayList<String> arrayList1)
    {
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        NewsAdapter newsAdapter=new NewsAdapter(this);
        newsAdapter.settingUpHeadlines(arrayList);
        newsAdapter.settingUpURL(arrayList1);
        recyclerView.setAdapter(newsAdapter);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent=getIntent();
        String year=intent.getStringExtra("Year");
        String month=intent.getStringExtra("Month");
        Log.i("Year",year);
        Log.i("Month",month);
        makingACall(year,month);
    }

    @Override
    public void onListItemClicked(int clickedItemIndex) {
        String url1 = url.get(clickedItemIndex);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url1));
        startActivity(i);

    }
}
