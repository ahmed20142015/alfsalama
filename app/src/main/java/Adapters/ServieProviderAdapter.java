package Adapters;

import android.content.Context;
import android.support.constraint.solver.LinearSystem;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.marvel.android.a1000salama.Home.HomeFragment;
import com.marvel.android.a1000salama.R;
import com.squareup.picasso.Picasso;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.List;

import Model.ServiceProidveritem;

/**
 * Created by ahmed on 31/12/17.
 */

public class ServieProviderAdapter extends RecyclerView.Adapter<ServieProviderAdapter.CustomViewHolder> {
    private List<ServiceProidveritem> ServiceProivderItemList;
    private Context mContext;
    private  HomeFragment home;

    private OnItemClickListener onItemClickListener;


    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }
    public ServieProviderAdapter(Context context , HomeFragment home, List<ServiceProidveritem> ServiceProivderItemList) {
        this.ServiceProivderItemList = ServiceProivderItemList;
        this.mContext = context;
        this.home = home;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.advet_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        final ServiceProidveritem serviceProidveritem = ServiceProivderItemList.get(i);

        //Render image using Picasso library
        if (!TextUtils.isEmpty(serviceProidveritem.getImageURl())) {
            Picasso.with(mContext).load(serviceProidveritem.getImageURl()).placeholder(
                    mContext.getResources().getDrawable(R.drawable.error_looding))
                    .into(customViewHolder.imageView);

        }

        //Setting text view title
        customViewHolder.title.setText(Html.fromHtml(serviceProidveritem.getSP_Name()));
        customViewHolder.discountValue.setText(Html.fromHtml(serviceProidveritem.getDiscoundValue()+""));
        customViewHolder.branchName.setText("فرع "+Html.fromHtml(serviceProidveritem.getSubtitle()));
        customViewHolder.rate.setRating(serviceProidveritem.getOverallRating());
        customViewHolder.rate.setClickable(false);
        customViewHolder.rate.setIsIndicator(true);



    }



    @Override
    public int getItemCount() {
        return (null != ServiceProivderItemList ? ServiceProivderItemList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected ImageView imageView;
        protected TextView title;
        protected TextView subtitle;
        protected TextView Distance;
        protected TextView branchName;
        protected TextView discountValue;
        protected ScaleRatingBar rate;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView =  view.findViewById(R.id.placeholder);
            this.title = (TextView) view.findViewById(R.id.title);
          // this.subtitle = (TextView) view.findViewById(R.id.Subtitle);
            this.Distance = (TextView) view.findViewById(R.id.distance);
            this.rate = view.findViewById(R.id.ratingServiceProivder);
            this.branchName = view.findViewById(R.id.branch_name);
            this.discountValue = view.findViewById(R.id.discount_value);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            home.NavigateToSPDeatials(ServiceProivderItemList.get(position));
        }
    }





}