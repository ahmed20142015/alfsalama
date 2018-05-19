package ClientComments;

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

import com.marvel.android.a1000salama.R;

import java.util.ArrayList;

import Adapters.CommentsAdapter;
import Model.Comment;
import Model.ServiceProidveritem;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ClientComments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientComments extends Fragment implements  commentsVew {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ServiceProidveritem SP;
    commentsPresenter CommentsPersenter;
    private CommentsAdapter mdapter;
    private RecyclerView mRecyclerView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ClientComments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientComments.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientComments newInstance(String param1, String param2) {
        ClientComments fragment = new ClientComments();
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
        View rootviwe = inflater.inflate(R.layout.fragment_client_comments, container, false);

        mRecyclerView = rootviwe.findViewById(R.id.comments);

        LinearLayoutManager mLinearLayoutManager =   new LinearLayoutManager( ClientComments.this.getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(  ClientComments.this.getContext());

      //  RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
                mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation()
        );
        mRecyclerView.addItemDecoration(mDividerItemDecoration);



        CommentsPersenter = new commentsPresneterImpl();
        CommentsPersenter.setView(this);
        CommentsPersenter.getComments(SP.getBRANCH_ID());


   return  rootviwe;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void setCommentsList(ArrayList<Comment> CommentList) {
        mdapter = new CommentsAdapter(this.getContext() ,CommentList  );
        mRecyclerView.setAdapter(mdapter);
    }
}
