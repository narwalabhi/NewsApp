package com.narwalabhi.assignment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.narwalabhi.assignment.R;
import com.narwalabhi.assignment.Utils.DateUtils;
import com.narwalabhi.assignment.models.ArticlesItem;
import com.squareup.picasso.Picasso;

public class ArticlesAdapter extends PagedListAdapter<ArticlesItem, ArticlesAdapter.ArticleViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private OnItemClickListener mListener;
    public ArticlesAdapter() {
        super(ArticlesItem.CALLBACK);
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item_layout, parent, false);
        return new ArticleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        ArticlesItem item = getItem(position);
        Picasso.get()
                .load(item.getUrlToImage())
                .placeholder(R.drawable.news_placeholder)
                .into(holder.ivArticleImage);
        holder.tvTitle.setText(item.getTitle());
        holder.tvDescription.setText(item.getDescription());
        DateUtils.MyDate date = DateUtils.getDate(item.getPublishedAt());
        holder.tvDate.setText(String.format("Time : %s", date.date));
        holder.tvTime.setText(String.format("Date : %s", date.time));
    }



    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mListener = itemClickListener;
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivArticleImage;
        TextView tvTitle, tvDescription, tvTime, tvDate;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            ivArticleImage = itemView.findViewById(R.id.ivArticleImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvDate = itemView.findViewById(R.id.tvDate);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }
    }
}
