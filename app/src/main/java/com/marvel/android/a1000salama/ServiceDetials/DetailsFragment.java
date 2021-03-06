package com.marvel.android.a1000salama.ServiceDetials;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.marvel.android.a1000salama.BaseFragment;
import com.marvel.android.a1000salama.BookingFragment.BookingFragment;
import com.marvel.android.a1000salama.Home.Home;
import com.marvel.android.a1000salama.Home.HomeFragment;
import com.marvel.android.a1000salama.Login.Login;
import com.marvel.android.a1000salama.Map.ServiceLocation;
import com.marvel.android.a1000salama.R;
import com.marvel.android.a1000salama.Rating.RatingFragment;
import com.marvel.android.a1000salama.ServicesProviderInfo.ServiceProviderInfo;
import com.marvel.android.a1000salama.ServicsDetails.BServiceDetailsFragment;
import com.marvel.android.a1000salama.Utils;
import com.squareup.picasso.Picasso;

import ClientComments.ClientComments;
import Model.ServiceProidveritem;
import utils.RoundedTransformation;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Button MapBtn ,BookBtn , ServiceDetalisBtn , RatingBtn , CommentsBtn , SerivceProivderBtn ;
    private ImageView Logo ;
    private TextView SPname;
    private String mParam2;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private OnFragmentInteractionListener mListener;
    private ServiceProidveritem SP;
    ImageView callServiceProvider;

    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String param1, String param2) {
        DetailsFragment fragment = new DetailsFragment();
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
      View viwe = inflater.inflate(R.layout.fragment_details, container, false);
        callServiceProvider =  viwe.findViewById(R.id.call_service_provider);

        SPname = viwe.findViewById(R.id.spname);
      Logo = viwe.findViewById(R.id.logo);

      SPname.setText(SP.getSP_Name());
            Picasso.with(getContext()).load(SP.getImageURl()).transform(new RoundedTransformation()).resize(
                    150,150
        )
                    .into(Logo);

        MapBtn = viwe.findViewById(R.id.mapbtn);
        MapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new  Intent(DetailsFragment.this.getContext(), ServiceLocation.class);
//                i.putExtra("X",SP.getCordX());
//                i.putExtra("Y",SP.getCordY());
//                i.putExtra("Name",SP.getSP_Name());
//                startActivity(i);

                if (SP.getCordX() != null && SP.getCordY() !=null) {

                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr=" + SP.getCordX() + "," + SP.getCordY()));
                    startActivity(intent);
                }

                else {
                    Toast.makeText(getActivity(), "لا توجد إحداثيات لمذود الخدمة", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ServiceDetalisBtn = viwe.findViewById(R.id.requestServiceDetails);

        ServiceDetalisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new BServiceDetailsFragment();
                manager = getActivity().getSupportFragmentManager();
                if(manager == null)
                    manager  =  getActivity().getSupportFragmentManager();
                transaction = manager.beginTransaction();
                Home.ToolBarTitle.setText(getString(R.string.ServiceDetails));
                Bundle bundle = new Bundle();
                bundle.putParcelable("SP", SP);
                fragment.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                transaction.replace(R.id.content_home, fragment, "deltials");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        RatingBtn = viwe.findViewById(R.id.rating);

        RatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new RatingFragment();
                manager = getActivity().getSupportFragmentManager();
                if(manager == null)
                    manager  =  getActivity().getSupportFragmentManager();
                transaction = manager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putParcelable("SP", SP);
                fragment.setArguments(bundle);
                Home.ToolBarTitle.setText(getString(R.string.ServiceDetails));
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                transaction.replace(R.id.content_home, fragment, "rating").addToBackStack("rating").commit();
            }
        });

        SerivceProivderBtn = viwe.findViewById(R.id.serviceproivderbtn);
        SerivceProivderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = ServiceProviderInfo.newInstance(SP.getBRANCH_ID());
                manager = getActivity().getSupportFragmentManager();
                if(manager == null)
                    manager  =  getActivity().getSupportFragmentManager();
                transaction = manager.beginTransaction();
                Home.ToolBarTitle.setText(getString(R.string.ServiceDetails));
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                transaction.replace(R.id.content_home, fragment, "serviceProivderInfo").addToBackStack("serviceProivderInfo").commit();
            }
        });

        BookBtn = viwe.findViewById(R.id.requestService);

        BookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Utils.getUserID(getActivity()) != -1){

                    Fragment fragment = new BookingFragment();
                    manager = getActivity().getSupportFragmentManager();
                    if(manager == null)
                        manager  =  getActivity().getSupportFragmentManager();
                    transaction = manager.beginTransaction();
                    Home.ToolBarTitle.setText(getString(R.string.SendBook));


                    Bundle bundle = new Bundle();
                    bundle.putParcelable("SP", SP);
                    fragment.setArguments(bundle);
                    transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                    transaction.replace(R.id.content_home, fragment, "Bookservice").addToBackStack("Bookservice").commit();

                }


                else {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("يرجى تسجيل الدخول أولاً");
                    builder.setPositiveButton("تسجيل الدخول", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(getActivity(),Login.class));
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("متابعه كزائر", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                    dialog.setCancelable(false);

                }

            }
        });

        CommentsBtn = viwe.findViewById(R.id.commentsbtn);

        CommentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new ClientComments();


                manager = getActivity().getSupportFragmentManager();
                if(manager == null)
                    manager  =  getActivity().getSupportFragmentManager();
                transaction = manager.beginTransaction();
                Home.ToolBarTitle.setText("التعليقات");
                Bundle bundle = new Bundle();
                bundle.putParcelable("SP", SP);
                fragment.setArguments(bundle);
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                transaction.replace(R.id.content_home, fragment, "ClientComments").addToBackStack("Bookservice").commit();
            }
        });

        callServiceProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String number = SP.getMobileNumber();

                if (!number.equalsIgnoreCase("") || !number.equalsIgnoreCase(null)) {
                    String uri = "tel:" + number;
                    callServiceProvider(uri);
                }

            }
        });

      return  viwe;
    }

    private void callServiceProvider(final String uri) {
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.CALL_PHONE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse(uri));
                        startActivity(intent);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            Toast.makeText(getActivity(), "Please enable permissions", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

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
        Fragment fragment = new HomeFragment();
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
