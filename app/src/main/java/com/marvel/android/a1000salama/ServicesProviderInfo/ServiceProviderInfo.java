package com.marvel.android.a1000salama.ServicesProviderInfo;

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
import android.widget.TextView;

import com.marvel.android.a1000salama.BaseFragment;
import com.marvel.android.a1000salama.R;
import com.marvel.android.a1000salama.Utils;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.util.ArrayList;
import java.util.List;

import Model.AboutServiceProvidor;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ServiceProviderInfo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ServiceProviderInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceProviderInfo extends BaseFragment implements ServiceProviderInfoView {
    ServiceProviderInfoPresenterImp presenterImp;
    TextView aboutServiceProvidor;
    FlipProgressDialog progressDialog;
    int serviceItemId;




    private OnFragmentInteractionListener mListener;

    public ServiceProviderInfo() {
        // Required empty public constructor
    }

    public static ServiceProviderInfo newInstance(int serviceItemId) {
        ServiceProviderInfo fragment = new ServiceProviderInfo();
        Bundle args = new Bundle();
        args.putInt("serviceItemId", serviceItemId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            serviceItemId = getArguments().getInt("serviceItemId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_service_provider_info, container, false);
        presenterImp = new ServiceProviderInfoPresenterImp();
        presenterImp.setView(this);
        aboutServiceProvidor = view.findViewById(R.id.about_service_provider);
        return view;
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

        if(Utils.isInternetOn(this.getContext())) {
            presenterImp.RequestAboutServiceProvidor(getArguments().getInt("serviceItemId"));
        }
        else {
            new SweetAlertDialog(this.getContext(), SweetAlertDialog.ERROR_TYPE)
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
    public void setServiceProviderAbout(ArrayList<AboutServiceProvidor> aboutUs) {
        aboutServiceProvidor.setText(aboutUs.get(0).getAboutUs());
    }

    @Override
    public void serviceProvidorError() {
        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                .setTitleText("عفواً")
                .setContentText("لا يوجد معلومات عن مقدم الخدمة")
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public boolean onBackPressed() {

        return false;

    }
}
