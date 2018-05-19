package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marvel.android.a1000salama.R;

import java.util.List;

import Model.Services;

/**
 * Created by ahmed on 10/05/18.
 */

public class SerivcesAdapter extends RecyclerView.Adapter<SerivcesAdapter.CustomViewHolder>  {
    private List<Services> Services;
    private Context mContext;

    private OnItemClickListener onItemClickListener;


    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    public SerivcesAdapter(Context context,
                           List<Services> services) {
        this.Services = services;
        this.mContext = context;

    }

    @Override
    public SerivcesAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.services_item,  viewGroup, false);
        SerivcesAdapter.CustomViewHolder viewHolder = new SerivcesAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SerivcesAdapter.CustomViewHolder customViewHolder, final int i) {
        final Services ServiceItem = Services.get(i);

        customViewHolder.Servicename.setText(ServiceItem.getName());
        customViewHolder.Price_Berfore_discount.setText("الخدمة قبل الخصم :" +ServiceItem.getPRICE_BEFORE_DISCOUNT()+"");
        customViewHolder.Price_after_discount.setText("الخدمة بعد الخصم :" +ServiceItem.getPRICE_After_DISCOUNT()+"");



    }


    @Override
    public int getItemCount() {
        return (null != Services ? Services.size() : 0);
    }



    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView Servicename;
        protected TextView Price_Berfore_discount;
        protected TextView Price_after_discount;

        public CustomViewHolder(View view) {
            super(view);
            this.Servicename = (TextView) view.findViewById(R.id.service_name);
            this.Price_after_discount = (TextView) view.findViewById(R.id.after_discount_value);
            this.Price_Berfore_discount = (TextView) view.findViewById(R.id.before_discount_value);

        }
    }


}





