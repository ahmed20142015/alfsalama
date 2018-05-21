package com.marvel.android.a1000salama.BookingFragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;

import com.marvel.android.a1000salama.BaseFragment;
import com.marvel.android.a1000salama.Home.Home;
import com.marvel.android.a1000salama.Home.HomeFragment;
import com.marvel.android.a1000salama.R;
import com.marvel.android.a1000salama.ServiceDetials.DetailsFragment;
import com.marvel.android.a1000salama.Utils;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
   // private  Button  AddRequestItemBtn;
    private Button addService;
    private ServiceProidveritem SP;
    private  RecyclerView serviceaddedlistviwe;
    SelectedServicesAdapter mAdapter;
    private Button addPicture;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private String filePath;
    private static final int REQUEST_PERMISSIONS_READ_EXTERNAL_STORAGE = 600,
            REQUEST_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 601;
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
        //servicesSpinner = (Spinner) view.findViewById(R.id.spinner);

        toggle_contents(otherAccountLy);
//        mServicesRecyclerView = view.findViewById(R.id.servceslist);
        mOtherPersonName  = view.findViewById(R.id.otherpersonName);
        mOtherPErsonMobileNumber  = view.findViewById(R.id.otherpersonmobilenumber);
        mComments = view.findViewById(R.id.Notes);
        sendBook  = view.findViewById(R.id.sendbook);
        serviceaddedlistviwe = view.findViewById(R.id.serviceaddedlistviwe);
       // AddRequestItemBtn =view.findViewById(R.id.addservicerequestitembtn);
        addService = view.findViewById(R.id.add_service);

        addPicture = view.findViewById(R.id.add_pic);

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


        addServiceDialog();

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



        return  view;
    }

    private void addServiceDialog() {
        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View dialogView = inflater.inflate(R.layout.fragment_booking_dialog, null);
                dialogBuilder.setView(dialogView);

                Button serviceArabic = dialogView.findViewById(R.id.arabic_service);
                Button serviceEnglish = dialogView.findViewById(R.id.english_service);
                Button addRequestItemBtn =dialogView.findViewById(R.id.addservicerequestitembtn);
                servicesSpinner = (Spinner) dialogView.findViewById(R.id.spinner);
                TextView hide = dialogView.findViewById(R.id.hide_pop_up);

                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.setCancelable(false);
                alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                alertDialog.show();
                hide.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                serviceArabic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        BookPersenter.getAllServices("ar");
                    }
                });

                serviceEnglish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        BookPersenter.getAllServices("en");
                    }
                });

            addRequestItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookedServicesList.add(ServicesList.get(servicesSpinner.getSelectedItemPosition()));
                addBookedService(BookedServicesList);
            }
        });


            }
        });



    }

    private boolean checkReadExternalPermission() {
        boolean isGranted = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        if (!isGranted)
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PERMISSIONS_READ_EXTERNAL_STORAGE);

        return isGranted;
    }

    private boolean checkCameraPermission() {
        boolean isGranted = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;

        if (!isGranted)

        ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_PERMISSIONS_WRITE_EXTERNAL_STORAGE);


        return isGranted;
    }

    private void openImageChooser() {
//        Intent intent_gallery = new Intent(Intent.ACTION_PICK);
//        intent_gallery.setType("image/*");
//        startActivityForResult(intent_gallery, 100);

        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, 100);
    }

    private void takeCameraPhoto()
    {
            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 200);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogInterface.OnClickListener onDialogClickWithImage;

                {
                    onDialogClickWithImage = new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            switch (item) {
                                case 0:
                                    if (checkReadExternalPermission())
                                        openImageChooser();
                                    break;
                                case 1:
                                    if (checkCameraPermission())
                                        takeCameraPhoto();
                                    break;

                                case 2:
                                    dialog.dismiss();
                                    break;
                            }
                        }
                    };
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(getString(R.string.add_photo));

                builder.setItems(new CharSequence[]{getString(R.string.choose_photo), getString(R.string.take_photo), getString(R.string.cancle_photo)}, onDialogClickWithImage);

                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openImageChooser();

                } else {
                    Toast.makeText(getActivity(), "readFromGalleryPhotoPermissionRequired", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case REQUEST_PERMISSIONS_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takeCameraPhoto();
                } else {
                    Toast.makeText(getActivity(), "takingCameraPhotoPermissionRequired", Toast.LENGTH_SHORT).show();
                }
                break;

            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == getActivity().RESULT_CANCELED) {
                return;
            }
            if (requestCode == 100) {
                if (data != null) {
                    Uri contentURI = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                        String path = saveImage(bitmap);
                        String encodedImage = encodeImage(path);
                        Log.w("path",encodedImage);

                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }

            } else if (requestCode == 200) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                String path = saveImage(thumbnail);
                String encodedImage = encodeImage(path);
                Log.w("path",encodedImage);            }

        }
        catch (Exception e){
                Toast.makeText(getActivity(), "حدث خطأ", Toast.LENGTH_SHORT).show();
        }





    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(getActivity(),
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private String encodeImage(String path)
    {
        File imagefile = new File(path);
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(imagefile);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        Bitmap bm = BitmapFactory.decodeStream(fis);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        //Base64.de
        return encImage;

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
    public void setServiceList(ArrayList<Services> ServicesList,String language ) {

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
