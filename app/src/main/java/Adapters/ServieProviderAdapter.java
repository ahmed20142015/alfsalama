package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.marvel.android.a1000salama.Home.HomeFragment;
import com.marvel.android.a1000salama.R;
import com.squareup.picasso.Picasso;

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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.serviceproivderlistadapter, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        final ServiceProidveritem serviceProidveritem = ServiceProivderItemList.get(i);

        //Render image using Picasso library
        if (!TextUtils.isEmpty(serviceProidveritem.getImageURl())) {
            Picasso.with(mContext).load(serviceProidveritem.getImageURl()).placeholder(
                    mContext.getResources().getDrawable(R.drawable.final_logo)
            )
                    .into(customViewHolder.imageView);

        }

        //Setting text view title
        customViewHolder.title.setText(Html.fromHtml(serviceProidveritem.getSP_Name()));
       // customViewHolder.subtitle.setText(Html.fromHtml(serviceProidveritem.getSubtitle()));
       // customViewHolder.Distance.setText(Html.fromHtml(serviceProidveritem.getDistance()));
        customViewHolder.rate.setRating(serviceProidveritem.getOverallRating());
        customViewHolder.rate.setClickable(false);
        customViewHolder.rate.setIsIndicator(true);

        customViewHolder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                home.NavigateToSPDeatials(serviceProidveritem);

            }
        });


    }



    @Override
    public int getItemCount() {
        return (null != ServiceProivderItemList ? ServiceProivderItemList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView title;
        protected TextView subtitle;
        protected TextView Distance;
        protected RatingBar rate;
        private android.support.design.widget.CoordinatorLayout cardItem ;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView =  view.findViewById(R.id.placeholder);
            this.title = (TextView) view.findViewById(R.id.title);
          //  this.subtitle = (TextView) view.findViewById(R.id.Subtitle);
            this.Distance = (TextView) view.findViewById(R.id.distance);
            this.cardItem = view.findViewById(R.id.cardItem);
            this.rate = view.findViewById(R.id.ratingServiceProivder);

        }
    }





}