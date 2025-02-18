package solutions.alterego.android.unisannio.giurisprudenza;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import solutions.alterego.android.unisannio.models.Article;
import solutions.alterego.android.unisannio.R;
import solutions.alterego.android.unisannio.URLS;
import solutions.alterego.android.unisannio.utils.VHHeader;

public class GiurisprudenzaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;

    private static final int TYPE_ITEM = 1;

    private List<Article> mNewsList = new ArrayList<>();

    public GiurisprudenzaAdapter(List<Article> newsList) {
        mNewsList = newsList;
    }

    public void addNews(List<Article> newsList) {
        mNewsList.clear();
        mNewsList.add(null);
        mNewsList.addAll(newsList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(context).inflate(R.layout.article_card, viewGroup, false);
            return new ViewHolder(v);
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(context).inflate(R.layout.recyclerview_header_image, viewGroup, false);
            return new VHHeader(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof GiurisprudenzaAdapter.ViewHolder) {
            final Article news = mNewsList.get(i);
            ((ViewHolder) holder).setItem(news);
        } else if (holder instanceof VHHeader) {
            ((VHHeader) holder).header.setImageResource(R.drawable.calandra);
        }
    }

    @Override
    public int getItemCount() {
        return mNewsList == null ? 0 : mNewsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.ingengeria_card)
        CardView card;

        @InjectView(R.id.article_card_title)
        TextView body;

        @InjectView(R.id.article_card_date)
        TextView date;

        private Article mNews;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        void setItem(Article news) {
            mNews = news;
            date.setText(news.getDate());
            body.setText(news.getTitle());
        }

        @OnClick(R.id.ingengeria_card)
        public void openDetailPage(View v) {
            String url = URLS.GIURISPRUDENZA + mNews.getUrl();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            v.getContext().startActivity(browserIntent);
        }
    }
}