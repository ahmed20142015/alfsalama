package com.marvel.android.a1000salama.Home;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.marvel.android.a1000salama.BaseFragment;
import com.marvel.android.a1000salama.BookingHistory.BookingHistoryFragment;
import com.marvel.android.a1000salama.BookingHistory.BookingHistoryView;
import com.marvel.android.a1000salama.BookingHistory.RatingDialogFragment;
import com.marvel.android.a1000salama.CustomEditText;
import com.marvel.android.a1000salama.DataBase.alfSamalaSDBHelper;
import com.marvel.android.a1000salama.DrawableClickListener;
import com.marvel.android.a1000salama.R;
import com.marvel.android.a1000salama.ServiceDetials.DetailsFragment;
import com.marvel.android.a1000salama.Utils;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.util.ArrayList;
import java.util.List;

import Adapters.ServieProviderAdapter;
import Model.Area;
import Model.Catoegry;
import Model.City;
import Model.Governrate;
import Model.ServiceProidveritem;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class HomeFragment extends BaseFragment implements HomeViwe {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentManager manager;
    private FragmentTransaction transaction;
    FlipProgressDialog progressDialog;
    HomePersenter homePersenter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<String> series_ids = new ArrayList<>();
    private List<ServiceProidveritem> ServiceProidveritemList;
    private RecyclerView mRecyclerView;
    private ServieProviderAdapter adapter;
    private LinearLayout AdvancedSerachLY, AdvancedSerachLY2;
    private Button AdvancedSearch, searchadvancedBtn;
    CustomEditText SearchET;
    EditText serviceProvidorName;
    ImageButton searchbtn;
    com.jaredrummler.materialspinner.MaterialSpinner GovesSpinner, CitySpinner, AreaSpinner;
    String BookID = "";
    String PatintID = " ";
    String SC_BRANCH_ID = " ";

    private ArrayList<City> Mycities = new ArrayList<>();
    private ArrayList<Area> MyAreas = new ArrayList<>();
    private ArrayList<Catoegry> Mycats = new ArrayList<>();
    private OnFragmentInteractionListener mListener;

    String city = "المدينة";
    String area = "المنطقة";
    String category = "نوع الخدمة";
    String cityID = "-1";
    String areaID = "-1";
    String catID = "-1";

    short xxxx=1;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

            try {
                BookID = getArguments().getString("BookeID");
                PatintID = getArguments().getString("PatintID");
                SC_BRANCH_ID = getArguments().getString("SC_BRANCH_ID");
            } catch (Exception e) {

            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viwe = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = (RecyclerView) viwe.findViewById(R.id.serviceporviderlist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        GovesSpinner = viwe.findViewById(R.id.goervnatre);
        CitySpinner = viwe.findViewById(R.id.city);
        AreaSpinner = viwe.findViewById(R.id.area);
        searchadvancedBtn = viwe.findViewById(R.id.searchadvancedBtn);
        serviceProvidorName = viwe.findViewById(R.id.service_providor_name);
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

        homePersenter = new HomePresnterImpl();
        homePersenter.setView(this);

        xxxx = 1;


        serviceProvidorName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                serviceProvidorName.setFocusableInTouchMode(true);

                return false;
            }
        });

        if (Utils.isInternetOn(this.getContext())) {
            homePersenter.GetAllServicesProivders("", "", "",
                    "", series_ids, "");


        } else {
            new SweetAlertDialog(this.getContext(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("خطأ")
                    .setContentText("من فضلك تأكد من الإتصال بالإنترنت")
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

        AdvancedSearch = viwe.findViewById(R.id.searchBtn);
        SearchET = viwe.findViewById(R.id.searchEt);

        AdvancedSerachLY2 = viwe.findViewById(R.id.advancedSearchLY2);
        AdvancedSerachLY = viwe.findViewById(R.id.advancedSearchLY);
        //  toggle_contents(AdvancedSerachLY2);


        searchbtn = viwe.findViewById(R.id.searchbtn);


        GovesSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                category = item.toString();
                if (position != 0)
                    catID = Mycats.get(position-1).getCatID() + "";

            }
        });

        CitySpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                city = item.toString();

                if (position != 0){
                    cityID = Mycities.get(position-1).getId() + "";
                    alfSamalaSDBHelper helper = new alfSamalaSDBHelper(getActivity());
                    ArrayList<Area> areas = new ArrayList<>();
                    areas = helper.getCityAreaies(Integer.parseInt(cityID));
                    setAreaList(areas);
                }

                else {
                    alfSamalaSDBHelper helper = new alfSamalaSDBHelper(getActivity());
                    ArrayList<Area> areas = new ArrayList<>();
                    areas = helper.getAllAreaies();
                    setAreaList(areas);
                }


            }
        });

        AreaSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                area = item.toString();
                if (position != 0){}
                    areaID = MyAreas.get(position-1).getID() + "";
            }
        });


        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String branchName = "";
                if (!serviceProvidorName.getText().toString().equalsIgnoreCase(""))
                    branchName = serviceProvidorName.getText().toString();
                try {


                    ///    if (CitySpinner.getSelectedIndex() != 0) {

                    //   cityID = Mycities.get(CitySpinner.getSelectedIndex() ).getId() + "";

                    // }
                    // if (AreaSpinner.getSelectedIndex() != 0) {
                    //    areaID = MyAreas.get(AreaSpinner.getSelectedIndex() ).getID() + "";
                    // }

                    //// if (GovesSpinner.getSelectedIndex() != 0) {


                    //  }
                    if (cityID.equals("-1") || city.equalsIgnoreCase("المدينة")) {
                        cityID = "";
                    }
                    if (areaID.equals("-1") || area.equalsIgnoreCase("المنطقة")) {
                        areaID = "";
                    }
                    if (catID.equals("-1") || category.equalsIgnoreCase("نوع الخدمة")) {
                        catID = "";
                    }


                    homePersenter.GetAllServicesProivders(branchName, "", cityID + "",
                            areaID, series_ids,
                            catID + "");
                    Log.w("ServerResponse", "City id " + cityID + " Area id " + areaID + " Category id " + catID);

                } catch (Exception e) {

                }

            }
        });

//        searchadvancedBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//            }
//        });
//        AdvancedSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(AdvancedSerachLY2.isShown()){
////                Utils.slide_up(this.getActivity(), AdvancedSerachLY);
////                AdvancedSerachLY.setVisibility(View.GONE);
//
//                }
//                ShowAdvncedSearch(AdvancedSerachLY2);
//            }
//        });
        SearchET.setDrawableClickListener(new DrawableClickListener() {


            public void onClick(DrawableClickListener.DrawablePosition target) {
                switch (target) {
                    case LEFT:


                        if (Utils.isInternetOn(HomeFragment.this.getContext()))
                            homePersenter.GetAllServicesProivders(SearchET.getEditableText().toString(), "", "",
                                    "", series_ids, "");
                        else {
                            new SweetAlertDialog(HomeFragment.this.getContext(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("خطأ")
                                    .setContentText("من فضلك تأكد من الإتصال بالإنترنت")
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


                        break;

                    default:
                        break;
                }
            }

        });
        return viwe;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void toggle_contents(View v) {

        AdvancedSerachLY2.setVisibility(AdvancedSerachLY2.isShown()
                ? View.VISIBLE
                : View.GONE);
        AdvancedSerachLY.setVisibility(AdvancedSerachLY.isShown()
                ? View.VISIBLE
                : View.GONE);
    }

    public void ShowAdvncedSearch(View v) {

        if (AdvancedSerachLY2.isShown() && AdvancedSerachLY.isShown()) {
            Utils.slide_up(this.getActivity(), AdvancedSerachLY2);
            Utils.slide_up(this.getActivity(), AdvancedSerachLY);

            AdvancedSerachLY.setVisibility(View.GONE);
            AdvancedSerachLY2.setVisibility(View.GONE);
        } else {
            AdvancedSerachLY2.setVisibility(View.VISIBLE);
            AdvancedSerachLY.setVisibility(View.VISIBLE);
            Utils.slide_down(this.getActivity(), AdvancedSerachLY2);
            Utils.slide_down(this.getActivity(), AdvancedSerachLY);


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


        adapter = new ServieProviderAdapter(this.getContext(), this, ServiceProivderList);
        if (ServiceProivderList.size() > 0){
            mRecyclerView.setAdapter(adapter);
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getContext());
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            adapter.notifyDataSetChanged();
        }
          else {
            adapter = new ServieProviderAdapter(this.getContext(), this, ServiceProivderList);
            mRecyclerView.setAdapter(adapter);
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.getContext());
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            adapter.notifyDataSetChanged();

            new SweetAlertDialog(HomeFragment.this.getContext(), SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("عفواً")
                    .setContentText("لا يوجد مزودين خدمة حسب البحث")
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


        if (!BookID.equals("")) {
            FragmentManager manager = ((AppCompatActivity) HomeFragment.this.getActivity()).getSupportFragmentManager();
            RatingDialogFragment ratingDialogFragment =
                    new RatingDialogFragment();
            ratingDialogFragment.setPatientID(Integer.parseInt(PatintID));
            ratingDialogFragment.setBookID(BookID);
            ratingDialogFragment.setServicProviderID(SC_BRANCH_ID + "");
            BookingHistoryView bookingFragment = new BookingHistoryFragment();
            ratingDialogFragment.setBookingFragment(bookingFragment);
            ratingDialogFragment.setCancelable(false);
            ratingDialogFragment.show(manager, "rating" +
                    " Dialog");
        }


//        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
//                mRecyclerView.getContext(),
//                mLinearLayoutManager.getOrientation()
//        );
//        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        if(xxxx == 1){
            homePersenter.GetCats(this.getContext());
            xxxx++;
        }

    }

    @Override
    public void setGovList(ArrayList<Governrate> governrates) {
//        hideLoader();
//
//        ArrayList<String> services = new ArrayList<>();
//
//        for(int i = 0 ; i < governrates.size();i++)
//        {
//            services.add(governrates.get(i).getName());
//        }
//        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_list_item_1,
//                services);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//
//        CitySpinner.setAdapter(adapter);
    }

    @Override
    public void setCityList(ArrayList<City> cities) {
        hideLoader();

        ArrayList<String> services = new ArrayList<>();
        services.add("المدينة");
        for (int i = 0; i < cities.size(); i++) {
            services.add(cities.get(i).getCityName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1,
                services);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Mycities = cities;

        CitySpinner.setAdapter(adapter);

        homePersenter.GetAreas(this.getContext());
    }

    @Override
    public void setAreaList(ArrayList<Area> Areas) {
        hideLoader();

        ArrayList<String> services = new ArrayList<>();
        services.add("المنطقة");
        for (int i = 0; i < Areas.size(); i++) {
            services.add(Areas.get(i).getAreaName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1,
                services);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MyAreas = Areas;

        AreaSpinner.setAdapter(adapter);
    }

    @Override
    public void NavigateToSPDeatials(ServiceProidveritem sp) {

        Fragment fragment = new DetailsFragment();
        manager = getActivity().getSupportFragmentManager();
        if (manager == null)
            manager = getActivity().getSupportFragmentManager();
        transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelable("SP", sp);
        fragment.setArguments(bundle);


        try {
            Bundle extras = getActivity().getIntent().getExtras();
            String BookeID = extras.getString("BookeID");
            String PatintID = extras.getString("PatintID");
            String SC_BRANCH_ID = extras.getString("SC_BRANCH_ID");

        } catch (Exception e) {

        }

        Home.ToolBarTitle.setText(getString(R.string.Service));
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.replace(R.id.content_home, fragment, "home");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void setCatList(ArrayList<Catoegry> catoegries) {
        hideLoader();

        ArrayList<String> services = new ArrayList<>();
        services.add("نوع الخدمة");
        for (int i = 0; i < catoegries.size(); i++) {
            services.add(catoegries.get(i).getCatName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1,
                services);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Mycats = catoegries;
        GovesSpinner.setAdapter(adapter);


        homePersenter.GetCities(this.getContext());
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


        getActivity().finish();
        return true;

    }
}
