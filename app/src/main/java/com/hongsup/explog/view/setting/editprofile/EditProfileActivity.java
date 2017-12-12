package com.hongsup.explog.view.setting.editprofile;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.hongsup.explog.R;
import com.hongsup.explog.data.photo.Photo;
import com.hongsup.explog.view.gallery.GalleryActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.hongsup.explog.data.Const.REQ_GALLERY;

public class EditProfileActivity extends AppCompatActivity {

    @BindView(R.id.imgEditProfile)
    CircleImageView imgEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @OnClick(R.id.imgEditProfile)
    public void imgClick(){
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivityForResult(intent, REQ_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case REQ_GALLERY :
                if(resultCode == RESULT_OK){
                    if(data != null){
                        ArrayList<Photo> photoList = (ArrayList<Photo>) data.getSerializableExtra("PHOTO");

                        Glide.with(this)
                                .load(photoList.get(0).getImagePath())
                                .centerCrop()
                                .into(imgEditProfile);

//                        data.getSerializableExtra()
//                        signUpView.setImageView(photo);
                    }
                }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
