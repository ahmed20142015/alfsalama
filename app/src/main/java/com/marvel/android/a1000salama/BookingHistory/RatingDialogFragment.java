package com.marvel.android.a1000salama.BookingHistory;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.marvel.android.a1000salama.R;

/**
 * Created by ahmed on 04/03/18.
 */

public class RatingDialogFragment extends DialogFragment {
    RatingBar rate;
    Button submitRatebtn;
    EditText totalmoneyET;
    BooKingHistoryPresneter BookHistory;
    int patientID ;
    String ServicProviderID;
    String Comment ;
    String Rating ;
    String BookID ;
    String totalMoney;
    BookingHistoryView BookingFragment;


    public BookingHistoryView getBookingFragment() {
        return BookingFragment;
    }

    public void setBookingFragment(BookingHistoryView bookingFragment) {
        BookingFragment = bookingFragment;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getServicProviderID() {
        return ServicProviderID;
    }

    public void setServicProviderID(String servicProviderID) {
        ServicProviderID = servicProviderID;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String bookID) {
        BookID = bookID;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public interface RatingDialogFragmentListener {
        void onFinishRatingDialogFragment(int rating);
    }

    //---empty constructor required
    public RatingDialogFragment() {
        BookHistory = new BookingPresnterimpl();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        View view = inflater.inflate(
                R.layout.rating_dialouge, container);

        //---get the EditText and Button views
        rate =  view.findViewById(R.id.rateservice);
        submitRatebtn = (Button) view.findViewById(R.id.btnDoneRate);
        totalmoneyET =view.findViewById(R.id.totalmoney);
        totalMoney = totalmoneyET.getEditableText().toString();
        Comment = "comment";
        Rating  = rate.getRating()+"";
        //---event handler for the button
        submitRatebtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {

                BookHistory = new BookingPresnterimpl();
                BookHistory.setView(BookingFragment);
                BookHistory.RateService(patientID , ServicProviderID
                , Comment , Rating , BookID ,  totalmoneyET.getEditableText().toString());
                //---dismiss the alert
                dismiss();
            }
        });


        return view;
    }
}
