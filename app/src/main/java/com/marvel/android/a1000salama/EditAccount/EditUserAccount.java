package com.marvel.android.a1000salama.EditAccount;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
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

import static android.graphics.PorterDuff.Mode.SRC_IN;


public class EditUserAccount extends BaseFragment implements EditUserAccountViwe {


    private OnFragmentInteractionListener mListener;

    EditUserAccountPresnterImp presnter;
    FlipProgressDialog progressDialog;

    EditText firstNameET, secondNameEt, surNameEt, mobileNumberEt, passwordEt, confirmPasswordEt, emailEt;
    Button editDataBtn;

    public EditUserAccount() {
        // Required empty public constructor
    }


    public static EditUserAccount newInstance(String param1, String param2) {
        EditUserAccount fragment = new EditUserAccount();
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
        View view = inflater.inflate(R.layout.fragment_edit_user_account, container, false);

        firstNameET = view.findViewById(R.id.edit_firstname);
        secondNameEt = view.findViewById(R.id.edit_secondname);
        surNameEt = view.findViewById(R.id.edit_surname);
        emailEt = view.findViewById(R.id.edit_email);
        passwordEt = view.findViewById(R.id.edit_password);
        confirmPasswordEt = view.findViewById(R.id.edit_confirmpassword);
        mobileNumberEt = view.findViewById(R.id.edit_mobilenumber);
        editDataBtn = view.findViewById(R.id.edit_RegesrationBtn);
        setDrawableTint();

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

        presnter = new EditUserAccountPresnterImp();
        presnter.setView(this);

        eventDriven();

        return view;
    }

    private void eventDriven() {
        editDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = firstNameET.getText().toString();
                String sName = secondNameEt.getText().toString();
                String lName = surNameEt.getText().toString();
                String email = emailEt.getText().toString();
                String password = passwordEt.getText().toString();
                String conFirmPass = confirmPasswordEt.getText().toString();
                String mobileNumber = mobileNumberEt.getText().toString();

                if (fName.equalsIgnoreCase("") &&
                        sName.equalsIgnoreCase("") &&
                        lName.equalsIgnoreCase("") &&
                        email.equalsIgnoreCase("") &&
                        password.equalsIgnoreCase("") &&
                        conFirmPass.equalsIgnoreCase("") &&
                        mobileNumber.equalsIgnoreCase("")) {

                    showErrorMessage("يجب تعديل البيانات أولاً");

                }

                else if (!conFirmPass.equals(password))
                {
                    showErrorMessage("كلمة المرور غير متطابقة");

                }
                else if (!email.equalsIgnoreCase("") && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    showErrorMessage("البريد الإلكتروني غير صحيح");

                }


                else if (!mobileNumber.equalsIgnoreCase("") )
                {
                    if(mobileNumber.length()!=11 || mobileNumber.charAt(0)!='0'|| mobileNumber.charAt(1)!='1')
                    showErrorMessage("رقم الهاتف غير صحيح ");

                }

                else {
                    if(Utils.isInternetOn(getActivity())){
                        int id = Utils.getUserID(getContext());
                        presnter.RequestEditAccount(id,fName,sName,lName,mobileNumber,email,password,email);
                    }

                    else
                    {
                        showErrorMessage("من فضلك تأكد من الإتصال بالإنترنت");

                    }
                }

            }
        });
    }

    private void setDrawableTint() {
        Drawable user = getActivity().getDrawable(R.drawable.user);
        user = DrawableCompat.wrap(user);
        DrawableCompat.setTint(user, Color.parseColor("#3F51B5"));
        firstNameET.setCompoundDrawablesWithIntrinsicBounds(null, null, user, null);
        secondNameEt.setCompoundDrawablesWithIntrinsicBounds(null, null, user, null);
        surNameEt.setCompoundDrawablesWithIntrinsicBounds(null, null, user, null);

        Drawable password = getActivity().getDrawable(R.drawable.pass);
        password = DrawableCompat.wrap(password);
        DrawableCompat.setTint(password, Color.parseColor("#3F51B5"));
        passwordEt.setCompoundDrawablesWithIntrinsicBounds(null, null, password, null);
        confirmPasswordEt.setCompoundDrawablesWithIntrinsicBounds(null, null, password, null);

        Drawable mobile = getActivity().getDrawable(R.drawable.mobile);
        mobile = DrawableCompat.wrap(mobile);
        DrawableCompat.setTint(mobile, Color.parseColor("#3F51B5"));
        mobileNumberEt.setCompoundDrawablesWithIntrinsicBounds(null, null, mobile, null);

        Drawable email = getActivity().getDrawable(R.drawable.email);
        mobile = DrawableCompat.wrap(email);
        DrawableCompat.setTint(email, Color.parseColor("#3F51B5"));
        emailEt.setCompoundDrawablesWithIntrinsicBounds(null, null, email, null);

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
    public void successUpdate() {
        new SweetAlertDialog(this.getContext(), SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("تــم")
                .setContentText("تم تعديل البيانات")
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
    public void failUpdate() {
        showErrorMessage("حدث خطأ أثناء تعديل البينات");
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void showErrorMessage(String message){
        new SweetAlertDialog(this.getContext(), SweetAlertDialog.ERROR_TYPE)
                .setTitleText("خطأ")
                .setContentText(message)
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
