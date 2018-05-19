package com.marvel.android.a1000salama.BookingFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.marvel.android.a1000salama.BaseFragment;
import com.marvel.android.a1000salama.Home.Home;
import com.marvel.android.a1000salama.Home.HomeFragment;
import com.marvel.android.a1000salama.R;
import com.marvel.android.a1000salama.ServiceDetials.DetailsFragment;
import com.marvel.android.a1000salama.Utils;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.util.ArrayList;
import java.util.List;

import Adapters.SelectedServicesAdapter;
import Model.ServiceProidveritem;
import Model.Services;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingFragment extends BaseFragment  implements BookingViwe {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    BookingPresneter BookPersenter;
    private Button sendBook;
    private ArrayList<Integer> ServicesIds = new ArrayList<>();
    private RecyclerView mServicesRecyclerView;
    private EditText mOtherPersonName  , mOtherPErsonMobileNumber , mComments;

    RadioGroup AccountTypeRadio ;
    CheckBox radiotheraccount ;
    FlipProgressDialog progressDialog;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private LinearLayout otherAccountLy ;
    private OnFragmentInteractionListener mListener;
    private Boolean BookForMeFlage = true;
    private Spinner servicesSpinner;
    private  Button  AddRequestItemBtn;
    private ServiceProidveritem SP;
    private  RecyclerView serviceaddedlistviwe;
    SelectedServicesAdapter mAdapter;
    private  ArrayList<Services> BookedServicesList = new ArrayList<>();
    private  ArrayList<Services> ServicesList = new ArrayList<>();
    public BookingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookingFragment newInstance(String param1, String param2) {
        BookingFragment fragment = new BookingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            SP = new ServiceProidveritem();
            SP = (ServiceProidveritem)getArguments().getParcelable("SP");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        AccountTypeRadio = view.findViewById(R.id.radioBookingType);
        radiotheraccount = view.findViewById(R.id.radiotheraccount);
        otherAccountLy = view.findViewById(R.id.otheraccountLy);
        BookPersenter = new BookingPersnterImpl();
        BookPersenter.setView(this);
        servicesSpinner = (Spinner) view.findViewById(R.id.spinner);

        toggle_contents(otherAccountLy);
//        mServicesRecyclerView = view.findViewById(R.id.servceslist);
        mOtherPersonName  = view.findViewById(R.id.otherpersonName);
        mOtherPErsonMobileNumber  = view.findViewById(R.id.otherpersonmobilenumber);
        mComments = view.findViewById(R.id.Notes);
        sendBook  = view.findViewById(R.id.sendbook);
        serviceaddedlistviwe = view.findViewById(R.id.serviceaddedlistviwe);
        AddRequestItemBtn =view.findViewById(R.id.addservicerequestitembtn);

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

        BookPersenter.getAllServices();
        AccountTypeRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                             if (i==R.id.radiotheraccount)
                              {
                                  BookForMeFlage= false;

                                  ShowOtherAccountLy(otherAccountLy);
                              }

            }
        });
        radiotheraccount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (radiotheraccount.isChecked())
                {
                    BookForMeFlage= false;

                    ShowOtherAccountLy(otherAccountLy);
                }
                else
                {

                    BookForMeFlage= true;


                    ShowOtherAccountLy(otherAccountLy);
                }
            }
        });



        sendBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!BookForMeFlage)
                {
                    if( mOtherPersonName.getEditableText().toString().equals("")
                    || mOtherPErsonMobileNumber.getEditableText().toString().equals("")
                        )
                    {

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

                    }else
                    {

                        List<Services> ServicesList =new ArrayList<>();
                        ServicesList =  mAdapter.getSelectedServicesAdapterItemList();

                        for(int i = 0 ; i<ServicesList.size();i++)
                        {
                            ServicesIds.add(ServicesList.get(i).getID());
                        }
                       int id = Utils.getUserID(getContext());
                        if(Utils.isInternetOn(BookingFragment.this.getContext()))
                        BookPersenter.BookService(id,mOtherPersonName.getEditableText().toString()
                                ,mOtherPErsonMobileNumber.getEditableText().toString(),
                                ServicesIds,mComments.getEditableText().toString(),SP.getBRANCH_ID());
                    else
                            new SweetAlertDialog(BookingFragment.this.getContext(), SweetAlertDialog.ERROR_TYPE)
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

                    List<Services> ServicesList =new ArrayList<>();
                    try {
                        ServicesList = mAdapter.getSelectedServicesAdapterItemList();
                    }
                    catch (Exception e)
                    {
                        ServicesList =new ArrayList<>();
                    }
                    for(int i = 0 ; i<ServicesList.size();i++)
                    {
                        ServicesIds.add(ServicesList.get(i).getID());
                    }
                    int id = Utils.getUserID(getContext());
                    if(Utils.isInternetOn(BookingFragment.this.getContext()))
                    BookPersenter.BookService(id,""
                            ,"",
                            ServicesIds,mComments.getEditableText().toString(),SP.getBRANCH_ID());
                else
                        new SweetAlertDialog(BookingFragment.this.getContext(), SweetAlertDialog.ERROR_TYPE)
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
        });

        AddRequestItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookedServicesList.add(ServicesList.get(servicesSpinner.getSelectedItemPosition()));
                addBookedService(BookedServicesList);
            }
        });

        return  view;
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
    public void setSPList(ArrayList<ServiceProidveritem> ServiceProivderList) {

    }

    @Override
    public void NavigateToSPPAge(int ResponseCode) {

        Toast.makeText(getContext(),  " تم تسجيل الحجز رقم   " + ResponseCode +" بنجاح ",Toast.LENGTH_LONG).show();

        Fragment fragment = new HomeFragment();
        manager = getActivity().getSupportFragmentManager();
        if(manager == null)
            manager  =  getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        Home.ToolBarTitle.setText(getString(R.string.Home));
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.content_home, fragment, "deltials");
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void setServiceList(   ArrayList<Services> ServicesList ) {

       ArrayList<String> services = new ArrayList<>();

        for(int i = 0 ; i < ServicesList.size();i++)
        {
            services.add(ServicesList.get(i).getName());
        }
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_list_item_1,
                services);
        this.ServicesList = ServicesList;
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        servicesSpinner.setAdapter(adapter);

    }


    public void addBookedService( ArrayList<Services> ServicesList ) {

         mAdapter = new SelectedServicesAdapter(
                BookingFragment.this.getContext(), BookingFragment.this,
                ServicesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(  BookingFragment.this.getContext());
        serviceaddedlistviwe.setLayoutManager(mLayoutManager);
        serviceaddedlistviwe.setItemAnimator(new DefaultItemAnimator());
        serviceaddedlistviwe.setAdapter(mAdapter);


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


    public void toggle_contents(View v){
        otherAccountLy.setVisibility( otherAccountLy.isShown()
                ? View.VISIBLE
                : View.GONE );
    }

    public void ShowOtherAccountLy(View v){

        if(otherAccountLy.isShown()){
            Utils.slide_up(this.getActivity(), otherAccountLy);
            otherAccountLy.setVisibility(View.GONE);
        }
        else{
            otherAccountLy.setVisibility(View.VISIBLE);
            Utils.slide_down(this.getActivity(), otherAccountLy);
        }

    }


    @Override
    public boolean onBackPressed() {
        Fragment fragment = new DetailsFragment();
        manager = getActivity().getSupportFragmentManager();
        if(manager == null)
            manager  =  getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        Home.ToolBarTitle.setText(getString(R.string.ServiceDetails));
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.content_home, fragment, "deltials");
        transaction.addToBackStack(null);
        transaction.commit();
        return  true;

    }




}
