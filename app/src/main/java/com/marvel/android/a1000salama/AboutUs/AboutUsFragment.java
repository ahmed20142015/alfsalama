package com.marvel.android.a1000salama.AboutUs;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.marvel.android.a1000salama.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AboutUsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AboutUsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutUsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
//    private SimpleExoPlayerView simpleExoPlayerView;
//    private SimpleExoPlayer player;
   // private BandwidthMeter bandwidthMeter;
    private int selectedIndex;
    private Handler mainHandler;
    String recipeName;
    String videoURL = "http://www.mediafire.com/file/g5v70zqrpxb2soc/1000+slama+last+edit+25_1.mp4";
    long position ;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SimpleExoPlayerView playerView;
    SimpleExoPlayer player;

    private OnFragmentInteractionListener mListener;

    public AboutUsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutUsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutUsFragment newInstance(String param1, String param2) {
        AboutUsFragment fragment = new AboutUsFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_about_us, container, false);



        VideoView view = rootview.findViewById(R.id.video_view);
     //   String path = "android.resource://" + this.getActivity().getPackageName() + "/" + R.raw.alfslama;
        view.setVideoURI(Uri.parse("http://salama1000.com/promo-video.mp4\n" +
                "\n"));
        view.setMediaController(new MediaController(this.getContext()));


//        DisplayMetrics metrics = new DisplayMetrics();
//        this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams)
//                view.getLayoutParams();
//        params.width =  metrics.widthPixels;
//        params.height = metrics.heightPixels;
//        params.leftMargin = 0;
//        view.setLayoutParams(params);
        view.start();
        return  rootview;
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
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {

            position = savedInstanceState.getLong("position");
        }
    }

//    private void initializePlayer(Uri mediaUri) {
//        if (player == null) {
//            TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
//            DefaultTrackSelector trackSelector = new DefaultTrackSelector(mainHandler, videoTrackSelectionFactory);
//            LoadControl loadControl = new DefaultLoadControl();
//
//            player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
//            simpleExoPlayerView.setPlayer(player);
//
//            String userAgent = Util.getUserAgent(getContext(), "1000Salama");
//            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
//            player.prepare(mediaSource);
//            player.setPlayWhenReady(true);
//        }
//    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        super.onSaveInstanceState(currentState);

        //  position = player.getCurrentPosition();
        currentState.putLong("position",position);

    }


    public boolean isInLandscapeMode( Context context ) {
        return (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        if (player!=null) {
            player.stop();
            player.release();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if (player!=null) {
//            player.stop();
//            player.release();
//            player=null;
//        }
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


    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(this.getContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);

        player.setPlayWhenReady(true);
        player.seekTo(0, 0);
      //  Uri uri = Uri.parse( "android.resource://" + this.getActivity().getPackageName() + "/" + R.raw.alfslama);
     //   MediaSource mediaSource = buildMediaSource(uri);
     //   player.prepare(mediaSource, true, false);


    }
    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                createMediaSource(uri);
    }



    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
           // initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //hideSystemUi();
        if ((Util.SDK_INT <= 23 || player == null)) {
          //  initializePlayer();
        }
    }



    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
          //  releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
          //  releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
//            playbackPosition = player.getCurrentPosition();
//            currentWindow = player.getCurrentWindowIndex();
//            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }
}
