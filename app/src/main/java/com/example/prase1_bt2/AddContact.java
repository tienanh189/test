package com.example.prase1_bt2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddContact extends AppCompatActivity {
    EditText edtID, edtName, edtDes,edtImg;
    Button btnAdd, btnCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        edtID= findViewById(R.id.edtID);
        edtName=findViewById(R.id.edtNameTB);
        edtDes=findViewById(R.id.edtDesTB);
        edtImg=findViewById(R.id.edtIMG);
        btnAdd=findViewById(R.id.btnThem);
        btnCancel=findViewById(R.id.btnHuy);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            int id = bundle.getInt("Id");
            String name = bundle.getString("Name");
            String des = bundle.getString("Des");
            String img = bundle.getString("Image");

            edtID.setText(String.valueOf(id));
            edtName.setText(name);
            edtDes.setText(des);
            edtImg.setText(img);
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String des = edtDes.getText().toString();
                String id = edtID.getText().toString();
                String img = edtImg.getText().toString();

                if (name.isEmpty() || des.isEmpty() || id.isEmpty()||img.isEmpty()) {
                    Toast.makeText(AddContact.this, "Vui lòng nhập đủ thông tin người dùng!", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(AddContact.this, id , Toast.LENGTH_SHORT).show();
                } else {

                    //Tạo Intent trở về MainActivity
                    Intent intent = new Intent();
                    //Tạo bundle là đối tượng chứa dữ liệu
                    Bundle bundle = new Bundle();

                    //bundle hoạt động như một Java Map các phần tử phân biệt theo key
                    //bundle có các hàng put.. trong đó ... là kiểu dữ liệu tương ứng
                    bundle.putInt("Id", Integer.parseInt(edtID.getText().toString()));
                    bundle.putString("Name", edtName.getText().toString());
                    bundle.putString("Des", edtDes.getText().toString());
                    bundle.putString("Image", edtImg.getText().toString());
                    //có thể đặt cả đối tượng lên bundle bằng hàm putSerilizable
                    //đặt bundle lên intent
                    intent.putExtras(bundle);
                    //trả về bằng hàm setResult
                    //tham số thứ nhất là resultCode để quản lý phiên
                    //tham số thứ hai là intent chứa dữ liệu gửi về
                    setResult(200, intent);
                    finish();
                }
            }
        });

    }
}
