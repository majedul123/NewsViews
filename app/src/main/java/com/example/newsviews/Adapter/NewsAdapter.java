package com.example.newsviews.Adapter;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsviews.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{

    Context context;
    String [] author;
    String [] title;
    String [] description;
    String [] url;
    String [] url_to_image;
    String [] published_date;
    String [] content;



    public NewsAdapter(Context context, String[] author, String[] title, String[] description, String[] url, String[] url_to_image, String[] published_date, String[] content) {
         this.context=context;
         this.author=author;
         this.title=title;
         this.description=description;
         this.url=url;
         this.url_to_image=url_to_image;
         this.published_date=published_date;
         this.content=content;


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_driver_name, tv_distence, tv_driver_impresion;
        LinearLayout list_of_driver, botombackground;
        ImageView map_image;

        public MyViewHolder(View view) {
            super(view);




        }
    }


    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list, parent, false);

        return new NewsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NewsAdapter.MyViewHolder holder, final int position) {


//        if (position % 3 == 0)
//            holder.botombackground.setBackgroundResource(R.color.active_color);
//        else if (position % 3 == 1) {
//            holder.botombackground.setBackgroundResource(R.color.Blue);
//        } else if (position % 3 == 2) {
//            holder.botombackground.setBackgroundResource(R.color.colorBadgeText);
//        }
//
//
//        BaseApiService mApiService;
//        mApiService = UtilsApi.getAPIService();
//
//        Call<ResponseBody> call = mApiService.getName(access_token, driver_id[position]);
//        call.enqueue(new Callback<ResponseBody>() {
//
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                JSONArray jo2;
//                if (response.isSuccessful()) {
//                    try {
//                        JSONObject jsonobject = new JSONObject(response.body().string());
//                        String sname = jsonobject.getString("name");
//                        holder.tv_driver_name.setText(sname);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//
//            }
//        });






    }

    @Override
    public int getItemCount() {

        return content.length;
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private NewsAdapter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final NewsAdapter.ClickListener clickListener) {
            this.clickListener = clickListener;
//            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
//                @Override
//                public boolean onSingleTapUp(MotionEvent e) {
//                    return true;
//                }
//
//                @Override
//                public void onLongPress(MotionEvent e) {
//                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
//                    if (child != null && clickListener != null) {
//                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
//                    }
//                }
//            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

//            View child = rv.findChildViewUnder(e.getX(), e.getY());
//            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
//                clickListener.onClick(child, rv.getChildPosition(child));
//            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}


