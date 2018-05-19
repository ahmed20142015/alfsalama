package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.marvel.android.a1000salama.R;

import java.util.List;

import Model.Comment;

/**
 * Created by ahmed on 05/05/18.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CustomViewHolder>  {
    private List<Comment> Comments;
    private Context mContext;

    private OnItemClickListener onItemClickListener;


    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    public CommentsAdapter(Context context,
                             List<Comment> Comments) {
        this.Comments = Comments;
        this.mContext = context;

    }

    @Override
    public CommentsAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_item,  viewGroup, false);
        CommentsAdapter.CustomViewHolder viewHolder = new CommentsAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommentsAdapter.CustomViewHolder customViewHolder, final int i) {
        final Comment CommentItem = Comments.get(i);

          customViewHolder.comment.setText(CommentItem.getComment());
          customViewHolder.name.setText(CommentItem.getPatient_name());



    }


    @Override
    public int getItemCount() {
        return (null != Comments ? Comments.size() : 0);
    }



    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView name;
        protected TextView comment;
        protected TextView RequestDate;
        protected TextView Requeststatues;
        protected Button rating;
        private android.support.v7.widget.CardView cardItem;

        public CustomViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.name);
            this.comment = (TextView) view.findViewById(R.id.comment);

        }
    }


}




