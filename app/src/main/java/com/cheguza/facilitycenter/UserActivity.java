package com.cheguza.facilitycenter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.cheguza.facilitycenter.DataBase.MyDataBaseHelper;
import static com.cheguza.facilitycenter.DataBase.MyDataBaseHelper.TABLE_NAME;

public class UserActivity extends AppCompatActivity {

    MyDataBaseHelper myDataBaseHelper;
    TextView mUserName,mFullName,mPhoneNo;
    String PhoneNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mUserName=findViewById(R.id.user_name_txt);
        mFullName=findViewById(R.id.full_name_txt);
        mPhoneNo=findViewById(R.id.phone_no_txt);
        PhoneNo=getIntent().getStringExtra("usrPhn");
        mPhoneNo.setText(PhoneNo);

        myDataBaseHelper=new MyDataBaseHelper(UserActivity.this);
        SQLiteDatabase db=myDataBaseHelper.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);

        if(res.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"You have not logged in yet",Toast.LENGTH_SHORT ).show();
        }
        StringBuffer buffer=new StringBuffer();
        while (res.moveToNext())
        {
            mUserName.setText("@"+res.getString(3));
                mFullName.setText(res.getString(1)+" "+res.getString(2));
                mPhoneNo.setText(res.getString(4));
        }

        ImageView backArrow=findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
