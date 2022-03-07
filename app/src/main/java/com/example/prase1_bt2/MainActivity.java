package com.example.prase1_bt2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button add, delete;
    EditText edtName;
    ListView lstDS;
    CompoundButton siwtchSta;

    ArrayList<Contacts> arrContacts;
    ArrayList<Contacts> arrSelected;
    MyAdapter adapter;

    int positionSelected = -1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Lấy dữ liệu từ NewContact gửi về
        Bundle bundle = data.getExtras();
        int id = bundle.getInt("Id");
        String name = bundle.getString("Name");
        String des = bundle.getString("Des");
        int img = bundle.getInt("Image");
        if (requestCode == 100 && resultCode == 200) {
            //Đặt vào listdata
            Toast.makeText(this, "abc", Toast.LENGTH_SHORT).show();
            arrContacts.add(new Contacts(id, name, des, img,false));
            adapter.notifyDataSetChanged();
        }
        if (resultCode == 201) {
            if (id == arrContacts.get(positionSelected).getId()) {
                arrContacts.get(positionSelected).setName(name);
                arrContacts.get(positionSelected).setDes(des);
                arrContacts.get(positionSelected).setImage(img);
                arrContacts.get(positionSelected).setStatus(false);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add= findViewById(R.id.btnAdd);
        delete= findViewById(R.id.btnDelete);
        edtName=findViewById(R.id.edtName);
        lstDS= findViewById(R.id.listView);
        siwtchSta=findViewById(R.id.switch1);


        arrContacts = new ArrayList<>();
        arrSelected = new ArrayList<>();


        arrContacts.add(new Contacts(0, "Dieu hoa", "Cong suat 100W",R.drawable.anh_1,false));
        arrContacts.add(new Contacts(0, "Tu Lanh", "Cong suat 100W",R.drawable.anh_1,false));
        arrContacts.add(new Contacts(0, "May tinh", "Cong suat 100W",R.drawable.anh_1,false));


        adapter = new MyAdapter(MainActivity.this, arrContacts, new MyAdapter.onClick() {
            @Override
            public void onClickItem(Contacts contacts, Boolean isChecked) {
                if(isChecked) {
                    arrSelected.add(contacts);
                } else {
                    arrSelected.remove(contacts);
                }
            }
        });

        lstDS.setAdapter(adapter);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arrSelected.size() > 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Xoá liên lạc");
                    builder.setMessage("Bạn có chắc chắn muốn xoá liên lạc này?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            for (Contacts contacts:arrSelected){
                                arrContacts.remove(contacts);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });
                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    builder.create();
                    builder.show();
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng chọn liên lạc muốn xoá!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AddContact.class);
                startActivityForResult(intent,100);
            }
        });
    }
}