package com.example.autentificacionapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;
import com.bumptech.glide.Glide;
import com.example.autentificacionapi.data.Volume;
//import com.example.googlelibrosapi.util.Util;

import java.util.ArrayList;
import java.util.List;

public class QuestionSearchResultsAdapter extends RecyclerView.Adapter<QuestionSearchResultsAdapter.BookSearchResultHolder> {

//    private final int VIEW_ITEM = 1;
//    private final int VIEW_PROG = 0;

    private List<Volume> results = new ArrayList<>();

//    // The minimum amount of items to have below your current scroll position
//    // before loading more.
//    private int visibleThreshold = 10;
//    private int lastVisibleItem, totalItemCount;
//    private boolean loading;
//    private OnLoadMoreListener onLoadMoreListener;
//
//    public interface OnLoadMoreListener {
//        void onLoadMore();
//    }


//    public BookSearchResultsAdapter(RecyclerView recyclerView) {
//
//        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
//
//            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView
//                    .getLayoutManager();
//
//
//            recyclerView
//                    .addOnScrollListener(new RecyclerView.OnScrollListener() {
//                        @Override
//                        public void onScrolled(RecyclerView recyclerView,
//                                               int dx, int dy) {
//                            super.onScrolled(recyclerView, dx, dy);
//
//                            totalItemCount = linearLayoutManager.getItemCount();
//                            lastVisibleItem = linearLayoutManager
//                                    .findLastVisibleItemPosition();
//                            if (!loading
//                                    && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
//                                // End has been reached
//                                // Do something
//                                if (onLoadMoreListener != null) {
//                                    onLoadMoreListener.onLoadMore();
//                                }
//                                loading = true;
//                            }
//                        }
//                    });
//        }
//    }

    @NonNull
    @Override
    public BookSearchResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);

        return new BookSearchResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookSearchResultHolder holder, int position) {
        Volume volume = results.get(position);

        holder.titleTextView.setText(volume.getVolumeInfo().getTitle());
        holder.publishedDateTextView.setText(volume.getVolumeInfo().getPublishedDate());

        if (volume.getVolumeInfo().getImageLinks() != null) {
            String imageUrl = volume.getVolumeInfo().getImageLinks().getSmallThumbnail()
                    .replace("http://", "https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.smallThumbnailImageView);


        }

        if (volume.getVolumeInfo().getAuthors() != null) {
            String authors = null;
            for (String a : volume.getVolumeInfo().getAuthors()) {
                authors += a + ", ";
            }
            //Util u = new Util();
            //String authors = u.StringJoin(volume.getVolumeInfo().getAuthors(), ", ");
            //String authors = u.concat(volume.getVolumeInfo().getAuthors(), ", ");
            holder.authorsTextView.setText(authors);
        }
    }

    public interface ItemClickListener {
        void onClick(View view, Volume volume);
    }

    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Volume> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    class BookSearchResultHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleTextView;
        private TextView authorsTextView;
        private TextView publishedDateTextView;
        private ImageView smallThumbnailImageView;

        public BookSearchResultHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.book_item_title);
            authorsTextView = itemView.findViewById(R.id.book_item_authors);
            publishedDateTextView = itemView.findViewById(R.id.book_item_publishedDate);
            smallThumbnailImageView = itemView.findViewById(R.id.book_item_smallThumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Volume v = results.get(getAdapterPosition());
            v.getId();
            if (clickListener != null) clickListener.onClick(view, v);
        }
    }
}
