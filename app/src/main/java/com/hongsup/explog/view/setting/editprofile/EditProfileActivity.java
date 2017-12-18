package com.hongsup.explog.view.setting.editprofile;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hongsup.explog.R;
import com.hongsup.explog.data.photo.Photo;
import com.hongsup.explog.service.ServiceGenerator;
import com.hongsup.explog.service.api.EditProfileAPI;
import com.hongsup.explog.view.gallery.GalleryActivity;
import com.hongsup.explog.view.setting.editprofile.insuptest.UserEditProfile;
import com.hongsup.explog.view.setting.editprofile.insuptest.UserInformation;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

import static com.hongsup.explog.data.Const.REQ_GALLERY;

public class EditProfileActivity extends AppCompatActivity {

    @BindView(R.id.imgEditProfile)
    CircleImageView imgEditProfile;
    @BindView(R.id.linearLayoutEditProfile)
    LinearLayout linearLayoutEditProfile;
    @BindView(R.id.textTextNumEditProfile)
    TextView textTextNumEditProfile;
    @BindView(R.id.editNameEditProfile)
    EditText editNameEditProfile;
    Menu menu;
    String originalUserName = null;
    boolean isPhotoSelected = false;
    MultipartBody.Part filePart;
    RequestBody username;

    // 사진 바꿨을 때 또는 username 바꿨을 때만 OK 버튼이 활성화되도록 코드 짜야 함


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getDataFromDB();
        textWatcher();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.getItem(0).setEnabled(false);

        return super.onPrepareOptionsMenu(menu);
    }

    //Gallery Activity로 보내줌
    @OnClick(R.id.imgEditProfile)
    public void imgClick() {
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivityForResult(intent, REQ_GALLERY);
    }

    //Gallery Activity에서 사진이 선택될 경우
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_GALLERY:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        ArrayList<Photo> photoList = (ArrayList<Photo>) data.getSerializableExtra("PHOTO");

                        Glide.with(this)
                                .load(photoList.get(0).getImagePath())
                                .centerCrop()
                                .into(imgEditProfile);

                        //이미지를 선택했을 경우 filepart에 이미지를 넣어준다.
                        File file = new File(photoList.get(0).getImagePath());
                        filePart = MultipartBody.Part.createFormData("img_profile", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

                        menu.getItem(0).setEnabled(true);
                        isPhotoSelected = true;

                    }
                }
        }
    }

    // Action Bar의 item을 선택했을 때
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.action_confirm:
                // 서버에 바뀐 사진과 user name 정보 보냄

                username = RequestBody.create(MediaType.parse("text/plain"), editNameEditProfile.getText().toString());

                //userEditProfile.setUsername(editNameEditProfile.getText().toString());
                changeUserProfile();
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //username의 text watcher
    public void textWatcher() {
        RxTextView.textChanges(editNameEditProfile)
                .subscribe(ch -> {
                    String text = ch.length() + "/12";
                    textTextNumEditProfile.setText(text);

                    if(menu!=null&& originalUserName!=null){
                        if(ch.toString().equals(originalUserName)&& !isPhotoSelected){
                            menu.getItem(0).setEnabled(false);
                        }else{
                            menu.getItem(0).setEnabled(true);
                        }
                    }
                });
    }

    // 서버로부터 데이터 받아오는 코드 - 토큰값은 Intercepter 써야하고 앞에 Activity에 있는 코드와 겹치는 것 고려 필요
    public void getDataFromDB() {
        String token = "Token 6e88efd22b8a6afbc46782c5ba6cb576e163ff8d";
        EditProfileAPI profileEditAPI = ServiceGenerator.create(EditProfileAPI.class);
        Observable<Response<UserInformation>> getUserInfo = profileEditAPI.getUserInfo(token);
        getUserInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    if(data.isSuccessful()){
                        if(data.code()==200){
                            Log.d("EditProfileActivity", "확인됨");

                            Glide.with(EditProfileActivity.this)
                                    .load(data.body().getImg_profile()).centerCrop().into(imgEditProfile);

                            editNameEditProfile.setText(data.body().getUsername());
                            originalUserName = editNameEditProfile.getText().toString();

                        }else{
                            Log.d("EditProfileActivity", "확인안됨");
                        }
                    }else{
                        Log.d("EditProfileActivity", data.errorBody().string() +"data unsuccessful");
                    }
                }, throwable -> {
                    Log.e("SearchView", throwable.getMessage());
                });
    }

    //바뀐 데이터를 서버로 보내는 과정, 역시 토큰값 Intercepter로 해야 함, 확인은 OK버튼을 눌러야 함
    public void changeUserProfile(){
        String token = "Token 6e88efd22b8a6afbc46782c5ba6cb576e163ff8d";

        EditProfileAPI profileEditAPI = ServiceGenerator.create(EditProfileAPI.class);
        Observable<Response<UserEditProfile>> observable = profileEditAPI.userEditProfile(token, filePart, username);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data ->{
                    if(data.isSuccessful()){
                        if(data.code()==200){
                            Log.d("EditProfileActivity", "업로드 확인됨");

                        }else{
                            Log.d("EditProfileActivity", "확인안됨");
                        }
                    }else{
                        Log.d("EditProfileActivity", data.errorBody().string() +"data unsuccessful");
                    }
                }, throwable -> {
                    Log.e("SearchView", throwable.getMessage());
                });

    }

    public interface UpdateProfile{
        void update();
    }

}
