package com.example.newsviews.Api;


public class UtilsApi {

    public static final String BASE_URL_API =  " ";

    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}