package com.example.newsviews.Api;
public class NewUtilsApi {

    public static final String BASE_URL_API = "http://numbersapi.com/";
    // public static final String BASE_URL_API = "http://api.dev.stickerdriver.com/v1/";
//public static final String BASE_URL_API = "http://api.dev.stickerdriver.com/";
    //public static final String BASE_URL_API = BuildConfig.BASE_URL;





    public static BaseApiService getAPIService(){

        return RetrofitClient.getNewClient(BASE_URL_API).create(BaseApiService.class);


    }

}

