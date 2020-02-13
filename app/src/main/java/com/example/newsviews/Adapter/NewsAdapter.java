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

import androidx.recyclerview.widget.RecyclerView;

import com.example.newsviews.R;
import com.squareup.picasso.Picasso;


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

        TextView tv_title,tv_author,tv_description,tv_url,tv_published_date;

        ImageView news_image;

        public MyViewHolder(View view) {
            super(view);

         tv_title=view.findViewById(R.id.title);
         tv_author=view.findViewById(R.id.author);
         tv_description=view.findViewById(R.id.description);
         tv_url=view.findViewById(R.id.url);
         tv_published_date=view.findViewById(R.id.published_time);
         news_image=view.findViewById(R.id.image);




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


        holder.tv_title.setText(title[position]);
        holder.tv_author.setText(author[position]);
        //holder.tv_description.setText(description[position]);
        holder.tv_published_date.setText(published_date[position]);
        holder.tv_url.setText(url[position]);
        Picasso.with(context).load(url_to_image[position]).into(holder.news_image);

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


