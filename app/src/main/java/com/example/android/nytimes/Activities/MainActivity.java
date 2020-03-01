package com.example.android.nytimes.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.nytimes.R;
import com.example.android.nytimes.Retrofit.GetDataService;
import com.example.android.nytimes.Retrofit.RetrofitClientInstance;
import com.example.android.nytimes.RetrofitGetterAndSetter.Doc;
import com.example.android.nytimes.RetrofitGetterAndSetter.Example;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText month;
    EditText year;
    public void buttonClicked(View view)
    {
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra("Year", year.getText().toString());
        intent.putExtra("Month", month.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        month=findViewById(R.id.month);
        year=findViewById(R.id.year);

    }
}
