# Explog

## Explog 소개

- #### 안드로이드 스튜디오를 이용해 여행 어플리케이션인 Explog(Explore + Log)를 개발하였다.
- #### 앱의 전체적인 기능은 여행앱인 '여행노트'와 'VOLO'를 참고하였고, UI는 카카오플레이스를 참고하였다.

## Explog 화면
![pic1](https://github.com/jis1218/ProjectStudy/blob/master/Explog%20화면/pic1.png)
![pic2](https://github.com/jis1218/ProjectStudy/blob/master/Explog%20화면/pic2.png)
![pic3](https://github.com/jis1218/ProjectStudy/blob/master/Explog%20화면/pic3.png)
![pic4](https://github.com/jis1218/ProjectStudy/blob/master/Explog%20화면/pic4.png)
![pic5](https://github.com/jis1218/ProjectStudy/blob/master/Explog%20화면/pic5.png)
![pic6](https://github.com/jis1218/ProjectStudy/blob/master/Explog%20화면/pic6.png)
![pic7](https://github.com/jis1218/ProjectStudy/blob/master/Explog%20화면/pic7.png)

## API 문서 링크
[API 링크](https://gangbok119.gitbooks.io/explog-api/content/)

## 사용된 코드 및 배운 것들
by Insup Jung

1. Retrofit 2.0 및 Reactive X

- REST API로 GET, POST, PATCH 하기
- Call을 안쓰고 Observable을 사용해보았다. 연습 겸 해서
```java
@GET("/member/userprofile/{userPK}")
    Observable<Response<UserInformation>> getOtherUserInfo(@Path("userPK") String userPK);

@Multipart
    @PATCH("/member/userprofile/update/")
    Observable<Response<UserEditProfile>> userEditProfile(@Part MultipartBody.Part filePart, @Part("username") RequestBody username);

@POST("/member/login/")
    Observable<Response<User>> signIn(@Body SignIn signIn);
```
- PUT과 PATCH의 차이점
- PUT은 전체 데이터를 다 보내주지만 PATCH는 변경된 데이터만 보낼 수 있다.
- PATCH와 Multipart로 데이터 보내는 방법
```java
    @Multipart
    @PATCH("/member/userprofile/update/")
    Observable<Response<UserEditProfile>> userEditProfile(@Header("Authorization") String token, @Part MultipartBody.Part filePart, @Part("username") RequestBody username);
```
- 이미지를 선택했을 경우 filepart에 이미지를 넣어준다.
```java
        File file = new File(photoList.get(0).getImagePath());
        MultipartBody.Part filePart = MultipartBody.Part
        .createFormData("img_profile", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

        RequestBody username = RequestBody.create(MediaType.parse("text/plain"), editNameEditProfile.getText().toString());
```
- Retrofit과 Observable을 사용하여 Post를 날리고 그 결과값을 받으려고 하는데 그것이 잘 안됨
```java
DataService dataService = getDataFromDB().create(DataService.class);
        Observable<Response<SearchResponse>> observable = dataService.observable(word);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data->{
                }
```

다음과 같은 에러가 뜸
```
java.lang.IllegalStateException: Expected BEGIN_OBJECT but was BEGIN_ARRAY at line 1 column 2 path $
```

이유는 나는 Object 형태로 값을 받으려고 하지만 실제 내가 받는 데이터는 Array라는 뜻임
아래 링크 참조
https://stackoverflow.com/questions/24154917/retrofit-expected-begin-object-but-was-begin-array

다음과 같이 바꾸니 동작함
```java
Observable<Response<SearchResponse>> observable -> Observable<ArrayList<Response<SearchResponse>>> observable

2. ButterKnife

- 코드의 양을 조금이라도 줄이기 위해 ButterKnife 사용하였다.

```java
 @BindView(R.id.btnSignUp)
    Button btnSignUp;

ButterKnife.bind(this, view);

3. Glide

- ImageView는 Glide를 이용하였다.

```java
private void setProfileBackground(){
        Glide.with(context)
                .load(android.R.drawable.ic_input_add)
                .fitCenter()
                .centerCrop()
                .into(imgProfile);
    }
```

4. 정규식(Regular Expression) 사용하여 이메일, 비밀번호, 사용자 이름 유효성 검사
```java
    public static boolean isValidEmail(String email) {
        String regex = "^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return !m.matches();
    }

    public static boolean isValidPassword(String password) {
        // 영문자와 숫자만 허용
        String regex = "^[0-9A-Za-z]{8,14}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return !m.matches();
    }

    public static boolean isValidName(String name){
        // 영문자와 숫자만 허용
        String regex = "^[0-9A-Za-z가-힣]{2,10}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);

        return !m.matches();
    }
```

5. SQLiteDB 사용하여 검색 히스토리 기능 구현
- SQLiteOpenHelper를 상속하는 클래스를 이용해 SQLiteDB table 생성하고 검색어를 저장할 수 있도록 함
```java
public class DBHelperUtil extends SQLiteOpenHelper {
@Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE 'history' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "'word' TEXT)";

        db.execSQL(createQuery);
    }
}
```
- SQLite DB 생성 후 DB에 있는 내용을 ArrayList에 담는다. 담을 때는 list.add(0, data)로 하여 recyclerView에서 가장 나중에 add된 data가 먼저 보여지도록 한다.
```java
Cursor cursor = connection.rawQuery(query, null);
        while(cursor.moveToNext()){
            String word = cursor.getString(1);
            list.add(0, word);
        }
```
- 검색을 하였을 때는 delete Query와 insert Query가 동시에 실행이 되도록 한다. 그 이유는 이미 기록에 내가 검색한 값이 있다면 그 값을 없애고 맨 처음 History로 올리기 위해서이다. 혹시라도 기록에 없다하더라도 delete Query에 맞는 검색어가 없으므로 실행되어도 상관없다.
```java
    private void insert() {
        String word = editSearch.getText().toString();
        SearchHistoryDAO dao = new SearchHistoryDAO(getContext());
        String deleteQuery = "delete from history where word = '" + word + "'";
        String insertquery = "insert into history(word)" + " values('" + word + "')";
        dao.executeQuery(deleteQuery);
        dao.executeQuery(insertquery);
        executeList();
    }

        private void executeList() {
        searchRecyclerAdapter.historyNotifier(dao.read()); //dao.read()함수는 DB에 있는 모든 데이터를 list로 반환하는 함수이다.
    }
```

6. CoordinatorLayout을 이용하여 View 구현
- CoordinatorLayout을 쓸 때는 AppBarLayout쓰는 것이 default인 듯 하다
참고 : AppBarLayout currently expects to be the direct child nested within a CoordinatorLayout according to the official [Google docs]
- CollapsingToolbarLayout에 contentScrim="@color/colorPrimary" 설정을 해서 스크롤을 위로 올렸을 때 지정한 View Group이 사라지게 해준다.
- 기존에는 CustomBehavior를 이용하여 구현을 하려고 하였으나 버그가 발생하여 결국 기존의 방법대로 실행

```
<android.support.design.widget.CollapsingToolbarLayout
            android:id="@+id/collapsingToolbarLayout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:fitsSystemWindows="true"
            app:contentScrim="@color/colorWhite"
            app:layout_scrollFlags="scroll|exitUntilCollapsed"
            app:scrimAnimationDuration="500"
            app:scrimVisibleHeightTrigger="120dp"
            app:titleEnabled="false">
```

7. Scroll에 상관없이 특정 뷰를 특정 위치에 계속 놔두기
- 설정에서 layout_anchor에 위치할 뷰와 gravitiy를 설정해두면 된다.
```
<android.support.design.widget.FloatingActionButton
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="bottom|right"
    android:layout_margin="16dp"
    android:src="@mipmap/ic_launcher"
    app:layout_anchor="@id/myinfoRecyclerView"
    app:layout_anchorGravity="bottom|right|end"/>
```

8. RxAndroid 이용
- TextWatcher를 RxBinding으로 고침 -> 코드 줄 수가 많이 줄어듬
```java
TextWatcher textWatcher = new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               Log.d("확인", "onTextChanged: " + s.toString() + start + before + count);
               if(count>0){
                  recyclerSearchHistory.setAdapter(searchRecyclerResultAdapter);
               }else if(start==0){
                   recyclerSearchHistory.setAdapter(searchRecyclerAdapter);
               }
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       };

       editSearch.addTextChangedListener(textWatcher);
```
위 코드를 람다를 이용하여 아래와 같이 바꿈 -> 코드 수가 더 줄어듬
```
RxTextView.textChanges(editSearch)
                .subscribe(ch ->{
                    if(ch.length()>0){
                        recyclerSearchHistory.setAdapter(searchRecyclerResultAdapter);
                    }else{
                        recyclerSearchHistory.setAdapter(searchRecyclerAdapter);
                    }
                });

```
9. 앞의 activity에서 바뀐 정보를 뒤에 activity에 적용하기
- onRestart()에 바뀌는 코드를 넣어줬다.
```java
@Override
    protected void onRestart() {
        super.onRestart();
        mainPresenter.refreshData(mainView);
    }
```

