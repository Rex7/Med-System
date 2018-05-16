package com.example.regis.medsystem.research;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.regis.medsystem.R;

import java.util.List;


class ResearchAdapter extends RecyclerView.Adapter<ResearchAdapter.ViewHolderArticle> {
    private Context context;
    private List<ArticleClass> articleClassList;
    private String content = "";

    ResearchAdapter(Context context, List<ArticleClass> articleClassList) {
        this.context = context;
        this.articleClassList = articleClassList;
    }

    @Override
    public ViewHolderArticle onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_layout, parent, false);
        return new ViewHolderArticle(context, v, content);
    }

    @Override
    public void onBindViewHolder(ViewHolderArticle holder, int position) {
        Log.v("string title", articleClassList.get(position).getAuthorName());
        content = articleClassList.get(position).getContent();
        holder.aricleTitle.setText(articleClassList.get(position).getTitle());
        holder.articleAuthorName.setText(articleClassList.get(position).getAuthorName());
    }

    @Override
    public int getItemCount() {
        Log.v("count ", "No of data " + articleClassList.size());
        return articleClassList.size();

    }

    class ViewHolderArticle extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView aricleTitle, articleAuthorName;
        CardView cardView;

        String mycontent = "";
        Context ctx;

        ViewHolderArticle(Context context, View itemView, String content) {
            super(itemView);
            this.mycontent = content;
            this.ctx = context;
            aricleTitle = (TextView) itemView.findViewById(R.id.titletextArticle);
            articleAuthorName = (TextView) itemView.findViewById(R.id.authorTextArticle);

            cardView = (CardView) itemView.findViewById(R.id.cardArticle);
            cardView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(ctx, ArticleContent.class);
            intent.putExtra("titleText", articleClassList.get(position).getTitle());
            intent.putExtra("authorName", articleClassList.get(position).getAuthorName());
            intent.putExtra("content", articleClassList.get(position).getContent());
            ctx.startActivity(intent);

        }
    }
}
