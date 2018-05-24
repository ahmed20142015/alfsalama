package com.marvel.android.a1000salama.ContactUs;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.marvel.android.a1000salama.BaseFragment;
import com.marvel.android.a1000salama.R;
import com.marvel.android.a1000salama.Utils;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ContactUsFragment extends BaseFragment implements ContactUsView {
    EditText contactToUsSubject,contactToUsMessage;
    Button sendToUs;
    private OnFragmentInteractionListener mListener;
    ContactUsPresenterImp presenter;
    FlipProgressDialog progressDialog;
    public ContactUsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ContactUsFragment newInstance(String param1, String param2) {
        ContactUsFragment fragment = new ContactUsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        contactToUsMessage = view.findViewById(R.id.contact_us_message);
        contactToUsSubject = view.findViewById(R.id.contact_us_subject);
        sendToUs = view.findViewById(R.id.sendContactToUs);

        presenter = new ContactUsPresenterImp();
        presenter.setView(this);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressDialog = new FlipProgressDialog();
        List<Integer> imageList = new ArrayList<Integer>();
        imageList.add(R.drawable.ic_hourglass_empty_white_24dp);
        imageList.add(R.drawable.ic_hourglass_full_white_24dp);
        progressDialog.setImageList(imageList);
        progressDialog.setOrientation("rotationY");
        progressDialog.setCancelable(false);
        progressDialog.setDimAmount(0.8f);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            progressDialog.setBackgroundColor(getActivity().getColor(R.color.colorPrimary));
        } else {
            progressDialog.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        }

        sendToUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = contactToUsSubject.getText().toString();
                String message = contactToUsMessage.getText().toString();

                if (!subject.isEmpty() || !message.isEmpty()){
                    if(Utils.isInternetOn(getActivity())) {
                        int id = Utils.getUserID(getContext());
                        Integer replayToContactId = null;
                        presenter.sendToUs(subject,id,message,replayToContactId);
                    }
                    else {
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("خطأ")
                                .setContentText( "من فضلك تأكد من الإتصال بالإنترنت")
                                .setConfirmText("تم")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        // reuse previous dialog instance
                                        sDialog.dismiss();


                                    }
                                })
                                .show();
                    }
                }
                else {

                    new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("خطأ")
                            .setContentText( "من فضلك استكمل البيانات الفارغة")
                            .setConfirmText("تم")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    // reuse previous dialog instance
                                    sDialog.dismiss();


                                }
                            })
                            .show();

                }
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showLoader() {
        progressDialog.show(getActivity().getFragmentManager(), "l");
    }

    @Override
    public void hideLoader() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void successfullySend() {
        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("حسناً")
                .setContentText("تم إرسال الرسالة")
                .setConfirmText("تم")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // reuse previous dialog instance
                        sDialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void errorWhileSend() {
        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                .setTitleText("خطأ")
                .setContentText("لم يتم إرسال الرسالة")
                .setConfirmText("تم")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        // reuse previous dialog instance
                        sDialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void setEmpty() {
        contactToUsSubject.setText("");
        contactToUsMessage.setText("");
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
