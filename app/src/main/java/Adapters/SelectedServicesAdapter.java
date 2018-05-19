package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.marvel.android.a1000salama.BookingFragment.BookingFragment;
import com.marvel.android.a1000salama.R;

import java.util.List;

import Model.Services;

/**
 * Created by ahmed on 02/03/18.
 */

public class SelectedServicesAdapter extends RecyclerView.Adapter<SelectedServicesAdapter.CustomViewHolder> {
    private List<Services> SelectedServicesAdapterItemList;
    private Context mContext;
    private BookingFragment home;

    public List<Services> getSelectedServicesAdapterItemList() {
        return SelectedServicesAdapterItemList;
    }

    public void setSelectedServicesAdapterItemList(List<Services> selectedServicesAdapterItemList) {
        SelectedServicesAdapterItemList = selectedServicesAdapterItemList;
    }

    private OnItemClickListener onItemClickListener;


    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    public SelectedServicesAdapter(Context context, BookingFragment home, List<Services> ServiceProivderItemList) {
        this.SelectedServicesAdapterItemList = ServiceProivderItemList;
        this.mContext = context;
        this.home = home;
    }

    @Override
    public SelectedServicesAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookedserivceitem, null);
        SelectedServicesAdapter.CustomViewHolder viewHolder = new SelectedServicesAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SelectedServicesAdapter.CustomViewHolder customViewHolder, final int i) {
        final Services serviceProidveritem = SelectedServicesAdapterItemList.get(i);



        //Setting text view title
        customViewHolder.bookedservicetitle.setText(Html.fromHtml(serviceProidveritem.getName()));
        customViewHolder.removeservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectedServicesAdapterItemList.remove(i);
                notifyDataSetChanged();
            }
        });



    }


    @Override
    public int getItemCount() {
        return (null != SelectedServicesAdapterItemList ? SelectedServicesAdapterItemList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView bookedservicetitle;
        protected Button removeservice;

        private android.support.v7.widget.CardView cardItem;

        public CustomViewHolder(View view) {
            super(view);

            this.bookedservicetitle = (TextView) view.findViewById(R.id.bookedservicetitle);
            this.removeservice = view.findViewById(R.id.removeServics);


        }
    }


}