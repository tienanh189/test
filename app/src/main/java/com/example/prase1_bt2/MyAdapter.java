package com.example.prase1_bt2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    public interface onClick {
        public void onClickItem(Contacts contacts, Boolean isChecked);

    }
    onClick clickSelected;

    public MyAdapter(Activity activity, ArrayList<Contacts> data, onClick clickSelected) {
        this.activity = activity;
        this.data = data;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.clickSelected = clickSelected;
    }

    private Activity activity;
    private ArrayList<Contacts> data;
    private ArrayList<Contacts> dataBackup;
    private LayoutInflater inflater;

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Contacts getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        Contacts contacts = data.get(i);
        return contacts.getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.list_item, null);
        }

        Contacts object = (Contacts) getItem(i);

        if (object != null) {
            CompoundButton switchSta = view.findViewById(R.id.switch1);
            TextView txtName = view.findViewById(R.id.txtName);
            TextView txtDes = view.findViewById(R.id.txtDes);
            ImageView img = view.findViewById(R.id.imageView);


            txtName.setText(object.getName());
            txtDes.setText(object.getDes());
            switchSta.setChecked(object.isStatus());
            img.setImageResource(object.getImage());
//            Bitmap bMap = BitmapFactory.decodeFile(object.getImage());
//            img.setImageBitmap(bMap);

            switchSta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    clickSelected.onClickItem(object, b);
                }
            });
        }
        return view;
    }
}
