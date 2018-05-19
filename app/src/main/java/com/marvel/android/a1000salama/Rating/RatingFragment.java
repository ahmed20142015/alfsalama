package com.marvel.android.a1000salama.Rating;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.marvel.android.a1000salama.BaseFragment;
import com.marvel.android.a1000salama.R;

import java.util.ArrayList;

import Adapters.ratesAdapter;
import Model.Rate;
import Model.ServiceProidveritem;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RatingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RatingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RatingFragment extends BaseFragment implements RatingViweInterFace {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    RatingPresneter ratingPresneter;
    private ratesAdapter mdapter;
    private RecyclerView mRecyclerView;
    private ServiceProidveritem SP;
    private RatingBar overallRating;
    private TextView StarsNumber;
    public RatingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RatingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RatingFragment newInstance(String param1, String param2) {
        RatingFragment fragment = new RatingFragment();
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
        View rootviwe = inflater.inflate(R.layout.fragment_rating, container, false);

        overallRating = rootviwe.findViewById(R.id.overallRating);
          StarsNumber = rootviwe.findViewById(R.id.starsnumber);
        overallRating.setRating(SP.getOverallRating());
        overallRating.setClickable(false);
        overallRating.setIsIndicator(true);
        StarsNumber.setText(SP.getOverallRating()+"نجوم");
        mRecyclerView = rootviwe.findViewById(R.id.ratelist);

        LinearLayoutManager mLinearLayoutManager =   new LinearLayoutManager( RatingFragment.this.getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(  RatingFragment.this.getContext());

        //  RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
                mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation()
        );
        mRecyclerView.addItemDecoration(mDividerItemDecoration);



        ratingPresneter = new RatingPresneterImpl();
        ratingPresneter.setView(this);
        ratingPresneter.getRates(SP.getBRANCH_ID());


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
    public void setRatesList(ArrayList<Rate> RateList) {
        mdapter = new ratesAdapter(this.getContext() ,RateList  );
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

        return false;

    }
}
