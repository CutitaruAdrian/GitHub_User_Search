package com.example.cutit_000.github_user_search.Activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.cutit_000.github_user_search.Models.Repository;
import com.example.cutit_000.github_user_search.R;
import java.util.List;

/**
 * Created by cutit_000 on 17.02.2017.
 */

public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Repository> mRepositories;
    private Context mContext;


    public RecyclerViewAdapter(Context context, List<Repository> repositories) {
        mRepositories = repositories;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    private OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View repoView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(repoView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        Repository repository = mRepositories.get(position);
        TextView textView = viewHolder.nameTextView;
        textView.setText(repository.getName());
    }

    @Override
    public int getItemCount() {
        return mRepositories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;

        public ViewHolder(final View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.repo_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }
}