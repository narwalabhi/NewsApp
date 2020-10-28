package com.narwalabhi.assignment.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.google.gson.annotations.SerializedName;

public class ArticlesItem{

	@SerializedName("publishedAt")
	private String publishedAt;

	@SerializedName("author")
	private String author;

	@SerializedName("urlToImage")
	private String urlToImage;

	@SerializedName("description")
	private String description;

	@SerializedName("source")
	private Source source;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	@SerializedName("content")
	private String content;

	public String getPublishedAt(){
		return publishedAt;
	}

	public String getAuthor(){
		return author;
	}

	public String getUrlToImage(){
		return urlToImage;
	}

	public String getDescription(){
		return description;
	}

	public Source getSource(){
		return source;
	}

	public String getTitle(){
		return title;
	}

	public String getUrl(){
		return url;
	}

	public String getContent(){
		return content;
	}

	public static final DiffUtil.ItemCallback<ArticlesItem> CALLBACK = new DiffUtil.ItemCallback<ArticlesItem>() {
		@Override
		public boolean areItemsTheSame(@NonNull ArticlesItem oldItem, @NonNull ArticlesItem newItem) {
			return oldItem.getTitle().equals(newItem.getTitle());
		}

		@Override
		public boolean areContentsTheSame(@NonNull ArticlesItem oldItem, @NonNull ArticlesItem newItem) {
			return oldItem.getTitle().equals(newItem.getTitle());
		}
	};
}