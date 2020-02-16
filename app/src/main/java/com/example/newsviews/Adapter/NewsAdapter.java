package com.example.newsviews.Adapter;

import android.content.Context;
import android.net.ParseException;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.newsviews.R;
import java.sql.Date;
import java.text.SimpleDateFormat;

import butterknife.BindView;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {


    private static ClickListener clickListener;

    Context context;
    String[] author;
    String[] title;
    String[] description;
    String[] url;
    String[] url_to_image;
    String[] published_date;
    String[] content;






    public NewsAdapter(Context context, String[] author, String[] title, String[] description, String[] url, String[] url_to_image, String[] published_date, String[] content) {
        this.context = context;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.url_to_image = url_to_image;
        this.published_date = published_date;
        this.content = content;


    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnTouchListener {
        TextView tv_title, tv_author, tv_description, tv_url, tv_published_date;

        ImageView news_image;

        public MyViewHolder(View view) {
            super(view);

            tv_title = view.findViewById(R.id.title);
            tv_author = view.findViewById(R.id.author);
            tv_description = view.findViewById(R.id.description);
            tv_url = view.findViewById(R.id.url);
            tv_published_date = view.findViewById(R.id.published_time);
            news_image = view.findViewById(R.id.image);
            view.setOnClickListener(this);
            view.setOnTouchListener(this);


        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());

        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            clickListener.onLongClick(v, getAdapterPosition());
            return false;
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
        holder.tv_description.setText(description[position]);
        holder.tv_url.setText(url[position]);

        Glide.with(context).load(url_to_image[position])
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.news_image);



        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy");

        java.util.Date s = null;
        Date d = null;
        try {
            try {
                s = input.parse(published_date[position]);

            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String publish = output.format(s);

        holder.tv_published_date.setText("Published At :"+publish);

    }

    @Override
    public int getItemCount() {

        return content.length;
    }

    public interface ClickListener {
        void onClick(View view, int position);


        void onLongClick(View view, int position);
    }

    public void setItemClickListener(ClickListener clickListener) {

        NewsAdapter.clickListener = clickListener;
    }


}


