package com.example.newsviews.Api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {


    @Headers("Content-Type: application/json")
    @GET("everything?q=bitcoin&from=?&sortBy=publishedAt&apiKey=019e040aff25434a88d54890bfdc278f")
    Call<ResponseBody> getNews(@Query("from") String from);
    @Headers("Content-Type: application/json")
    @GET("{number}")
    Call<ResponseBody> getNumber(@Path("number") String number);

}
