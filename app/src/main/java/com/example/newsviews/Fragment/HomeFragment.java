package com.example.newsviews.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newsviews.Adapter.NewsAdapter;
import com.example.newsviews.Api.BaseApiService;
import com.example.newsviews.Api.UtilsApi;
import com.example.newsviews.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;


    BaseApiService mApiserive;
    String[] author;
    String[] title;
    String[] description;
    String[] url;
    String[] url_to_image;
    String[] published_date;
    String[] content;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);


        savedata();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                savedata();

            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void savedata() {

        mApiserive = UtilsApi.getAPIService();


        // recyclerView = view.findViewById(R.id.recyclerview);
        //    webView = view.findViewById(R.id.webview);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String currentdate = df.format(c);

        Toast.makeText(getActivity(), "date :" + currentdate, Toast.LENGTH_SHORT).show();
        String api_key = "019e040aff25434a88d54890bfdc278f";
        Call<ResponseBody> call = mApiserive.getNews(currentdate);
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

                        author = new String[length];
                        title = new String[length];
                        description = new String[length];
                        url = new String[length];
                        url_to_image = new String[length];
                        published_date = new String[length];
                        content = new String[length];
                        for (int i = 0; i < jo2.length(); i++) {
                            JSONObject rec = jo2.getJSONObject(i);


                            author[i] = rec.getString("author");
                            title[i] = rec.getString("title");
                            description[i] = rec.getString("description");
                            url[i] = rec.getString("url");
                            url_to_image[i] = rec.getString("urlToImage");
                            published_date[i] = rec.getString("publishedAt");
                            content[i] = rec.getString("content");


                        }


                        NewsAdapter newsAdapter = new NewsAdapter(getActivity(), author, title, description, url, url_to_image, published_date, content);


                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(newsAdapter);


                        newsAdapter.setItemClickListener(new NewsAdapter.ClickListener() {
                            @Override
                            public void onClick(View view, final int position) {


                                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                                LayoutInflater inflater = getActivity().getLayoutInflater();
                                final View dialogView = inflater.inflate(R.layout.selectview, null);

                                Button yes, no;
                                yes = dialogView.findViewById(R.id.yes);
                                no = dialogView.findViewById(R.id.no);
                                dialogBuilder.setView(dialogView);
                                final Dialog markerPopUpDialog = dialogBuilder.create();
                                markerPopUpDialog.show();

                                yes.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        markerPopUpDialog.dismiss();

                                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                                        LayoutInflater inflater = getActivity().getLayoutInflater();
                                        final View dialogViewnew = inflater.inflate(R.layout.webshow, null);
                                        WebView wv = (WebView) dialogViewnew.findViewById(R.id.web);
                                        wv.loadUrl(url[position]);
                                        wv.setWebViewClient(new WebViewClient() {
                                            @Override
                                            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                                view.loadUrl(url);

                                                return true;
                                            }
                                        });
                                        ImageButton imageButton = dialogViewnew.findViewById(R.id.imagebutton);
                                        dialogBuilder.setView(dialogViewnew);
                                        final Dialog markerPopUpDialognew = dialogBuilder.create();
                                        markerPopUpDialognew.show();
                                        imageButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                markerPopUpDialognew.dismiss();
                                            }
                                        });
                                    }
                                });

                                no.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        markerPopUpDialog.dismiss();
                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url[position]));
                                        startActivity(browserIntent);

                                    }
                                });

                             /*  AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                                LayoutInflater inflater = getActivity().getLayoutInflater();
                                final View dialogView = inflater.inflate(R.layout.webshow, null);
                                WebView wv = (WebView) dialogView.findViewById(R.id.web);
                                wv.loadUrl(url[position]);
                                wv.setWebViewClient(new WebViewClient() {
                                    @Override
                                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                        view.loadUrl(url);

                                        return true;
                                    }
                                });
                                ImageButton imageButton=dialogView.findViewById(R.id.imagebutton);
                                dialogBuilder.setView(dialogView);
                                final Dialog markerPopUpDialog = dialogBuilder.create();
                                markerPopUpDialog.show();
                                imageButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        markerPopUpDialog.dismiss();
                                    }
                                });
                                  */
                                Toast.makeText(getContext(), "posotion \n" + position, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongClick(View view, int position) {

                            }
                        });


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
        swipeRefreshLayout.setRefreshing(false);
    }


}