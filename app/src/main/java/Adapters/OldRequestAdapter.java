package Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.marvel.android.a1000salama.BookingHistory.BookingHistoryFragment;
import com.marvel.android.a1000salama.BookingHistory.BookingHistoryView;
import com.marvel.android.a1000salama.BookingHistory.RatingDialogFragment;
import com.marvel.android.a1000salama.R;

import java.util.List;

import Model.RequestItem;

/**
 * Created by ahmed on 07/02/18.
 */

public class OldRequestAdapter extends RecyclerView.Adapter<OldRequestAdapter.CustomViewHolder>  implements RatingDialogFragment.RatingDialogFragmentListener{
    private List<RequestItem> OldRequestsItemList;
    private Context mContext;
    private BookingHistoryFragment bookingHistoryFragment;
    com.marvel.android.a1000salama.BookingHistory.BookingHistoryView BookingHistoryView;

    private OnItemClickListener onItemClickListener;


    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    public OldRequestAdapter(Context context, BookingHistoryFragment bookingHistoryFragment,
                             List<RequestItem> OldRequestsItemList
    , BookingHistoryView BookingHistoryView) {
        this.OldRequestsItemList = OldRequestsItemList;
        this.mContext = context;
        this.bookingHistoryFragment = bookingHistoryFragment;
        this.BookingHistoryView = BookingHistoryView;
    }

    @Override
    public OldRequestAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.old_request_item, null);
        OldRequestAdapter.CustomViewHolder viewHolder = new OldRequestAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OldRequestAdapter.CustomViewHolder customViewHolder, final int i) {
        final RequestItem serviceProidveritem = OldRequestsItemList.get(i);

        //Setting text view title
        customViewHolder.requestName.setText(OldRequestsItemList.get(i).getBranchName());
        customViewHolder.Requeststatues.setText(Html.fromHtml(OldRequestsItemList.get(i).getStatusName()));
        customViewHolder.Requestmoney.setText(Html.fromHtml(OldRequestsItemList.get(i).getMoneyPaid()));
        try {
            customViewHolder.RequestDate.setText(Html.fromHtml(OldRequestsItemList.get(i).getDate()));
        }
        catch (Exception e)
        {

        }
        try {
            OldRequestsItemList.get(i).getRatingFalg();
        }
        catch (Exception e)
        {
            OldRequestsItemList.get(i).setRatingFalg("N");
        }
        if(OldRequestsItemList.get(i).getStatusName().equals("تم الانتهاء من الخدمة")
                && OldRequestsItemList.get(i).getRatingFalg().equals("N") )
        {
           // customViewHolder.rating.setVisibility(Button.VISIBLE);
            customViewHolder.rating.setText("تقييم");
            customViewHolder.rating.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Activity activity = (Activity) mContext;


                    // Return the fragment manager
                    FragmentManager manager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                    RatingDialogFragment ratingDialogFragment =
                            new RatingDialogFragment();
                    ratingDialogFragment.setPatientID(OldRequestsItemList.get(i).getPatientID());
                    ratingDialogFragment.setBookID(OldRequestsItemList.get(i).getID()+"");
                    ratingDialogFragment.setServicProviderID(OldRequestsItemList.get(i).getBranchID()+"");
                    ratingDialogFragment.setBookingFragment(BookingHistoryView);
                    ratingDialogFragment.setCancelable(false);
                    ratingDialogFragment.show(manager, "rating" +
                            " Dialog");
                }
            });
        }
        else
        {
            customViewHolder.rating.setText("تم التقييم");
        }



    }


    @Override
    public int getItemCount() {
        return (null != OldRequestsItemList ? OldRequestsItemList.size() : 0);
    }

    @Override
    public void onFinishRatingDialogFragment(int rating) {

    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        protected TextView requestName;
        protected TextView Requestmoney;
        protected TextView RequestDate;
        protected TextView Requeststatues;
        protected Button rating;
        private android.support.v7.widget.CardView cardItem;

        public CustomViewHolder(View view) {
            super(view);
            this.requestName = (TextView) view.findViewById(R.id.requestname);
            this.Requestmoney = (TextView) view.findViewById(R.id.requestmoney);
            this.RequestDate = (TextView) view.findViewById(R.id.oldrequestdate);
            this.Requeststatues = (TextView) view.findViewById(R.id.requeststatues);
            this.cardItem = view.findViewById(R.id.cardItem);
            this.rating = view.findViewById(R.id.rating);
        }
    }


}





