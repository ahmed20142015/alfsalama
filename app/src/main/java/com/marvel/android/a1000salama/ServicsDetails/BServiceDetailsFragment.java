package com.marvel.android.a1000salama.ServicsDetails;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marvel.android.a1000salama.BaseFragment;
import com.marvel.android.a1000salama.Home.Home;
import com.marvel.android.a1000salama.R;
import com.marvel.android.a1000salama.ServiceDetials.DetailsFragment;

import java.util.ArrayList;

import Adapters.SerivcesAdapter;
import Model.ServiceProidveritem;
import Model.Services;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BServiceDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BServiceDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BServiceDetailsFragment extends BaseFragment implements ServiceDetialinterface{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private OnFragmentInteractionListener mListener;
    ServiceDetalisPresneter serviceDetalisPresneter;
    private SerivcesAdapter mdapter;
    private RecyclerView mRecyclerView;
    private ServiceProidveritem SP;
    public BServiceDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BServiceDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BServiceDetailsFragment newInstance(String param1, String param2) {
        BServiceDetailsFragment fragment = new BServiceDetailsFragment();
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
        View rootviwe= inflater.inflate(R.layout.fragment_bservice_details, container, false);

        mRecyclerView = rootviwe.findViewById(R.id.servicesList);

        LinearLayoutManager mLinearLayoutManager =   new LinearLayoutManager( BServiceDetailsFragment.this.getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(  BServiceDetailsFragment.this.getContext());

        //  RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
                mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation()
        );
        mRecyclerView.addItemDecoration(mDividerItemDecoration);



        serviceDetalisPresneter = new ServiceDetlaisPresnterImpl();
        serviceDetalisPresneter.setView(this);
        serviceDetalisPresneter.getServices(SP.getBRANCH_ID());


        return  rootviwe;


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
    public void setServicessList(ArrayList<Services> ServicesList) {
        mdapter = new SerivcesAdapter(this.getContext() ,ServicesList  );
        mRecyclerView.setAdapter(mdapter);
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
        return true;

    }
}
