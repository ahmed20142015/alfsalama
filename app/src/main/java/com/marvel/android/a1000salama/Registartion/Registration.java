package com.marvel.android.a1000salama.Registartion;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.marvel.android.a1000salama.Home.Home;
import com.marvel.android.a1000salama.R;
import com.marvel.android.a1000salama.Utils;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Registration extends AppCompatActivity implements RegistartionView {


    FlipProgressDialog progressDialog;
    RegistartionPresnterImpl registartionPresnterImpl;
    EditText firstNameET, secondNameEt, surNameEt, mobileNumberEt, NationalIDEt, passwordEt, confirmPasswordEt,
            emailEt;
    TextView dateOfBirthTV;
    RadioGroup GenderRadioGroup;
    Button RegistrationBtn;
    String Gender = "M";
    int year, month, day;
    String DateOFBirth = "" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();


        progressDialog = new FlipProgressDialog();
        List<Integer> imageList = new ArrayList<Integer>();
        imageList.add(R.drawable.ic_hourglass_empty_white_24dp);
        imageList.add(R.drawable.ic_hourglass_full_white_24dp);
        progressDialog.setImageList(imageList);
        progressDialog.setOrientation("rotationY");
        progressDialog.setCancelable(false);
        progressDialog.setDimAmount(0.8f);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            progressDialog.setBackgroundColor(getColor(R.color.colorPrimary));
        } else {
            progressDialog.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        }
        registartionPresnterImpl = new RegistartionPresnterImpl();
        registartionPresnterImpl.setView(this);

        firstNameET = findViewById(R.id.firstname);
        secondNameEt = findViewById(R.id.secondname);
        surNameEt = findViewById(R.id.surname);
        emailEt = findViewById(R.id.email);
        dateOfBirthTV = findViewById(R.id.dateofbirth);
        passwordEt = findViewById(R.id.password);
        confirmPasswordEt = findViewById(R.id.confirmpassword);
        NationalIDEt = findViewById(R.id.idnumber);
        mobileNumberEt = findViewById(R.id.mobilenumber);
        GenderRadioGroup = findViewById(R.id.radioSex);
        RegistrationBtn = findViewById(R.id.RegesrationBtn);


        secondNameEt.setSelected(true);
        RegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  registartionPresnterImpl.onRegisterClicked();

                String firstName = getFirstName(),
                        secondName = getSecondName(),
                        lastName = getSurName(),
                        mobileNumber = getMobileNumber(),
                        idNumber = getNationalID(),
                        email = getEmail(),
                        password = getPass(),
                        conFirmPass = getConfirmPass(),
                        UserName = getEmail(),
                        DateOfBirth = getDateOFBirth(),
                        Gender = getGender();
//                if(firstName.isEmpty()||secondName.isEmpty()||lastName.isEmpty()||mobileNumber.isEmpty()
//                        ||email.isEmpty()||password.isEmpty()||UserName.isEmpty()
//                        ||DateOfBirth.isEmpty()||Gender.isEmpty()){
//
//                    showErrorInputs();
//                }
                if(firstName.isEmpty()){
                    firstNameET.setError("الإسم الأول لا يمكن أن يكون فارغاً");
                }

                else if(secondName.isEmpty()){
                    secondNameEt.setError("الإسم الثاني لا يمكن أن يكون فارغاً");
                }
                else if(lastName.isEmpty()){
                    surNameEt.setError("اللقب لا يمكن أن يكون فارغاً");
                }
                else if(mobileNumber.isEmpty()){
                    mobileNumberEt.setError("رقم الموبايل لا يمكن أن يكون فارغاً");
                }
                else if(password.isEmpty()){
                    passwordEt.setError("الرقم السري لا يمكن أن يكون فارغاً");
                }

                else if(conFirmPass.isEmpty()){
                    confirmPasswordEt.setError("الرقم السري لا يمكن أن يكون فارغاً");
                }

                else if(email.isEmpty()){
                    emailEt.setError("الإيميل لا يمكن أن يكون فارغاً");
                }

                else if(DateOfBirth.isEmpty()){
                    dateOfBirthTV.setError("تاريخ الميلاد لا يمكن أن يكون فارغاً");
                }

                else if (!conFirmPass.equals(password))
                {
                    showErrorMessage("كلمة المرور غير متطابقة");

                }

                else if ( !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    showErrorMessage("البريد الإلكتروني غير صحيح");

                }


//                else if ( mobileNumber.length()!=11 || mobileNumber.charAt(0)!='0'|| mobileNumber.charAt(1)!='1')
//                {
//                    showErrorMessage("رقم الهاتف غير صحيح ");
//
//                }







                else {

                    if(Utils.isInternetOn(Registration.this)) {
                        registartionPresnterImpl.RequestRegistration(
                                firstName, secondName, lastName, mobileNumber, idNumber, email, password, UserName, DateOfBirth, Gender);
                    }
                    else
                        showErrorMessage("من فضلك تأكد من الإتصال بالإنترنت");

                }

            }
        });


        // Get Gender

        GenderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioFemale) {
                    Gender = "F";
                } else {
                    Gender = "M";
                }
            }
        });

        ///  Get Date of Birth
        final Calendar calendar = Calendar.getInstance();
        final Calendar Revsioncalendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog.OnDateSetListener date =
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                         updateLabel(calendar);
                    }

                };


        dateOfBirthTV.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Registration.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }







    @Override
    public void showLoader() {
        progressDialog.show(getFragmentManager(), "R");
    }

    @Override
    public void hideLoader() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void edEmail() {

    }

    private void updateLabel(Calendar ca) {

        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        dateOfBirthTV.setText(format1.format(ca.getTime()));
    }


    @Override
    public void edPassword() {

    }

    @Override
    public void btnReg() {

    }

    @Override
    public void imgPassView() {

    }

    @Override
    public void showalert(String Message ) {
        new SweetAlertDialog(Registration.this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("خطأ")
                .setContentText( Message)
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
    public void NavigateToHome(int ID) {

        Toast.makeText(Registration.this , "تم التسجيل بنجاح " , Toast.LENGTH_LONG).show();
        Intent i =new Intent(Registration.this, Home.class);
        startActivity(i);
        finish();
    }

    @Override
    public void requestRegistartion(String firstName, String secondName, String lastName, String mobileNumber, String idNumber, String email, String password, String UserName, String DateOfBirth, String Gender) {

    }

    @Override
    public void requestWaring(String string) {

    }

    @Override
    public String getFirstName() {
        return firstNameET.getEditableText().toString();
    }

    @Override
    public String getSecondName() {
        return secondNameEt.getEditableText().toString();
    }

    @Override
    public String getSurName() {
        return surNameEt.getEditableText().toString();
    }

    @Override
    public String getMobileNumber() {
        return mobileNumberEt.getEditableText().toString();
    }

    @Override
    public String getNationalID() {
        return NationalIDEt.getEditableText().toString();
    }

    @Override
    public String getEmail() {
        return emailEt.getEditableText().toString();
    }

    @Override
    public String getPass() {
        return passwordEt.getEditableText().toString();
    }

    @Override
    public String getConfirmPass() {
        return confirmPasswordEt.getEditableText().toString();
    }

    @Override
    public String getDateOFBirth() {
        return dateOfBirthTV.getText().toString();
    }

    @Override
    public String getGender() {
        return Gender;
    }

    @Override
    public void showErrorInputs() {
        new SweetAlertDialog(Registration.this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("خطأ")
                .setContentText("من فضلك أكمل البيانات الفارغة")
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

    private void showErrorMessage(String message){
        new SweetAlertDialog(Registration.this, SweetAlertDialog.ERROR_TYPE)
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
