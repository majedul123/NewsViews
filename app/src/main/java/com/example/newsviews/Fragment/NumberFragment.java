package com.example.newsviews.Fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.newsviews.Adapter.NewsAdapter;
import com.example.newsviews.Api.BaseApiService;
import com.example.newsviews.Api.NewUtilsApi;
import com.example.newsviews.Api.UtilsApi;
import com.example.newsviews.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NumberFragment extends Fragment {

    BaseApiService mapiservice;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_number, container, false);
        mapiservice = NewUtilsApi.getAPIService();

        Call<ResponseBody> call1 = mapiservice.getNumber("#42");
        call1.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Toast.makeText(getApplicationContext(), " campaign id " + campaign_id + "\n driver id :" + Id, Toast.LENGTH_SHORT).show();
                JSONArray jo2;
                if (response.isSuccessful()) {

                     Toast.makeText(getContext(), " success " , Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {


            }
        });


        return view;
    }


}
