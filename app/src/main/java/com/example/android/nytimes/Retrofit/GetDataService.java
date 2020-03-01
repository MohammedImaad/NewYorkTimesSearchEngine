package com.example.android.nytimes.Retrofit;

import com.example.android.nytimes.RetrofitGetterAndSetter.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {
    @GET("{year}/{month}.json?api-key=rZ1UR6rQGBFCkLlg9WyhclAfmt6s0Dbf")
    Call<Example> getAllPhotos(@Path("year")String year,@Path("month")String month);

}
