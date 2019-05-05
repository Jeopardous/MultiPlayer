package com.cheguza.facilitycenter;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cheguza.facilitycenter.Home.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SelectUserActivity extends AppCompatActivity {

    String phoneNo, otp;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    LinearLayout linearLayout;
    Button loginButton;
    ImageView imageView;
    ProgressBar progressBar;
    Button genBtn;
    private String verificationCode;
    EditText phnEditText, otpEditText;
    BottomSheetBehavior bottomSheetBehavior;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);
        linearLayout = findViewById(R.id.bottom_sheet_linearlayout);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        firebaseAuth = FirebaseAuth.getInstance();
        verificationCallback();
        init();
    }

    public void init() {

        progressBar = findViewById(R.id.progress_circular);

        loginButton = findViewById(R.id.login_btn);
        genBtn = findViewById(R.id.generate_otp_btn);
        phnEditText = findViewById(R.id.phone_no);
        otpEditText = findViewById(R.id.enter_otp);
        imageView = findViewById(R.id.AdminUser);

    }

    public void doThis(View view) {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }


    public void genOtp(View view) {
        progressBar.setVisibility(View.VISIBLE);
        phoneNo = "+91" + phnEditText.getText().toString();
        if (phoneNo.length() == 13) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phoneNo,
                    60,
                    TimeUnit.SECONDS,
                    SelectUserActivity.this,
                    mCallbacks);
        } else {
            progressBar.setVisibility(View.GONE);
            phnEditText.setError("Invalid Phone Number");
        }

    }

    private void verificationCallback() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                String getOtpCode = phoneAuthCredential.getSmsCode();
                if (getOtpCode != null) {
                    otpEditText.setText(getOtpCode);
                    progressBar.setVisibility(View.VISIBLE);
                    autoVerifiCode(getOtpCode);
                }
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(SelectUserActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                verificationCode = s;
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SelectUserActivity.this, "Code sent", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void autoVerifiCode(String otp) {
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, otp);
        signInWithPhone(credential);

    }

    private void signInWithPhone(PhoneAuthCredential phoneAuthCredential) {
        firebaseAuth.signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "OTP Is Correct", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), EditUserActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("usrPhn", phoneNo);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), " InCorrect OTP", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });

    }

    public void onLogin(View view) {
        otp = otpEditText.getText().toString();
        if (otp.isEmpty() || otp.length() < 6) {
            otpEditText.setError("invalid code");
            otpEditText.requestFocus();
            return;
        }
        autoVerifiCode(otp);

    }


    @Override
    protected void onStart() {
        String user="User";
        String user1=getIntent().getStringExtra("deleteUser");
        super.onStart();
       /*
       if(FirebaseAuth.getInstance().getCurrentUser() != null ) {

            Intent intent = new Intent(SelectUserActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("usrPhn", phoneNo);
            startActivity(intent);
        }*/
        if (FirebaseAuth.getInstance().getCurrentUser() != null && user.equals(user1)) {
            return;
        }
        else{
            Intent intent = new Intent(SelectUserActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("usrPhn", phoneNo);
            startActivity(intent);
        }

    }
}
