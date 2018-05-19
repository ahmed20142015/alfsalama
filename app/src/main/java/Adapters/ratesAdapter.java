package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.marvel.android.a1000salama.R;

import java.util.List;

import Model.Rate;

/**
 * Created by ahmed on 10/05/18.
 */

public class ratesAdapter extends RecyclerView.Adapter<ratesAdapter.CustomViewHolder>  {
    private List<Rate> rates;
    private Context mContext;

    private OnItemClickListener onItemClickListener;


    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    public ratesAdapter(Context context,
                           List<Rate> rates) {
        this.rates = rates;
        this.mContext = context;

    }

    @Override
    public ratesAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rate_item,  viewGroup, false);
        ratesAdapter.CustomViewHolder viewHolder = new ratesAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ratesAdapter.CustomViewHolder customViewHolder, final int i) {
        final Rate rateItem = rates.get(i);
        customViewHolder.patintname.setText(rateItem.getPatintNamel());
        customViewHolder.rate.setRating(rateItem.getRating());
        customViewHolder.rate.setClickable(false);
        customViewHolder.rate.setIsIndicator(true);

    }


    @Override
    public int getItemCount() {
        return (null != rates ? rates.size() : 0);
    }



    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView patintname;
        protected RatingBar rate;
        public CustomViewHolder(View view) {
            super(view);
            this.patintname = (TextView) view.findViewById(R.id.name);
            this.rate = (RatingBar) view.findViewById(R.id.ratinbar);


        }
    }


}





