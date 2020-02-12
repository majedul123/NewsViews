package com.example.newsviews.Api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class BaseApiService {


    @GET("api/v1/location/{driver_id}/distance")
    Call<ResponseBody> getDistance(@Header("Authorization") String token,
                                   @Path("driver_id") int driver_id) {
        return null;
    }

    @GET("api/v1/campaign/driver/{Id}")
    Call<ResponseBody> getCampaignDetails(@Header("Authorization") String token,
                                          @Path("Id") int driver_id) {
        return null;
    }

    @GET("api/v1/meta/campaign/{campaign_id}/driver/{driver_id}")
    Call<ResponseBody> getCampaignDistance(@Header("Authorization") String token,
                                           @Path("campaign_id") int campaign_id,
                                           @Path("driver_id") int driver_id) {
        return null;
    }
}
