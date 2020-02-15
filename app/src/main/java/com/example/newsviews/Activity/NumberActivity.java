package com.example.newsviews.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsviews.Api.BaseApiService;
import com.example.newsviews.Api.NewUtilsApi;
import com.example.newsviews.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NumberActivity extends AppCompatActivity {

    BaseApiService mapiservice;
    @BindView(R.id.result)
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        ButterKnife.bind(this);

        String query = getIntent().getStringExtra("query");
        Toast.makeText(getApplicationContext(), " " + query, Toast.LENGTH_SHORT).show();
        mapiservice = NewUtilsApi.getAPIService();
        Call<ResponseBody> call1 = mapiservice.getNumber(query);
        call1.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Toast.makeText(getApplicationContext(), " campaign id " + campaign_id + "\n driver id :" + Id, Toast.LENGTH_SHORT).show();
                JSONArray jo2;
                if (response.isSuccessful()) {

                    try {
                        JSONObject jsonobject = new JSONObject(response.body().string());
                        int len = jsonobject.length();

                        String abc = jsonobject.getString("text");
                        result.setText(" " + abc);
                        Toast.makeText(getApplicationContext(), "len :" + len, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "result :" + abc, Toast.LENGTH_SHORT).show();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(getApplicationContext(), " success ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {


            }
        });


    }
}
