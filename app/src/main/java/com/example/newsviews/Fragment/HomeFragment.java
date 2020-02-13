package com.example.newsviews.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsviews.Adapter.NewsAdapter;
import com.example.newsviews.Api.BaseApiService;
import com.example.newsviews.Api.UtilsApi;
import com.example.newsviews.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    BaseApiService mApiserive;
    String[] author;
    String[] title;
    String[] description;
    String[] url;
    String[] url_to_image;
    String[] published_date;
    String[] content;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        mApiserive = UtilsApi.getAPIService();


        recyclerView = view.findViewById(R.id.recyclerview);
        String api_key = "019e040aff25434a88d54890bfdc278f";
        Call<ResponseBody> call = mApiserive.getNews();
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                JSONArray jo2;
                if (response.isSuccessful()) {

                    try {
                        JSONObject jsonobject = new JSONObject(response.body().string());


                        jo2 = jsonobject.getJSONArray("articles");
                        int length = jo2.length();
                        Toast.makeText(getActivity(), "Length =" + length, Toast.LENGTH_SHORT).show();

                        author=new String[length];
                        title=new String[length];
                        description=new String[length];
                        url=new String[length];
                        url_to_image=new String[length];
                        published_date=new String[length];
                        content=new String[length];
                        for (int i = 0; i < jo2.length(); i++) {
                            JSONObject rec = jo2.getJSONObject(i);




                             author[i] = rec.getString("author");
                             title[i] = rec.getString("title");
                             description[i] = rec.getString("description");
                             url[i] = rec.getString("url");
                             url_to_image[i] = rec.getString("urlToImage");
                             published_date[i] = rec.getString("publishedAt");
                             content[i] = rec.getString("content");

                            String ar = rec.getString("content");
                            Toast.makeText(getActivity(), " author :" + ar, Toast.LENGTH_SHORT).show();

                        }


                        NewsAdapter newsAdapter = new NewsAdapter(getActivity(), author, title, description, url, url_to_image, published_date, content);
                        //NewsAdapter newsAdapter = new NewsAdapter(getActivity(), content);


                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(newsAdapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {


            }
        });


        return view;
    }
}