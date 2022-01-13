package com.techbulls.assigmenttask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techbulls.assigmenttask.MainActivity;
import com.techbulls.assigmenttask.R;
import com.techbulls.assigmenttask.model.Search;

import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Search> searchList;

    public MovieAdapter(MainActivity mainActivity, List<Search> searchList) {
        this.context = mainActivity;
        this.searchList = searchList;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vertical_listview, parent, false);
        return new MovieAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        Search search = searchList.get(position);
        holder.title.setText(search.getTitle());
        holder.year.setText(search.getYear());
        String img = search.getPoster();
        if (img != null) {
            String uri = img;
            Picasso.with(context).load(img).placeholder(context.getResources().getDrawable(R.drawable.load)).error(context.getResources().getDrawable(R.drawable.image_not_avilable)).into(holder.img);
        } else {
            holder.img.setImageResource(R.drawable.image_not_avilable);
        }
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title, year;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            year = (TextView) itemView.findViewById(R.id.year);
            img = (ImageView) itemView.findViewById(R.id.movie_img);
        }
    }
}
