package id.sam.platerecognitiontext.service;

import id.sam.platerecognitiontext.model.edit.DataEditPlatModel;
import id.sam.platerecognitiontext.model.listall.ListAllModel;
import id.sam.platerecognitiontext.model.searchplat.DataSearchPlatModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface APIInterfacesRest {

  /*  @GET("weather")
    Call<WeatherModel> getWeather(@Query("q") String q, @Query("appid") String appid);

    @GET("weather")
    Call<WeatherModel> getWeatherBasedLocation(@Query("lat") Double lat, @Query("lon") Double lon, @Query("appid") String appid);

    @GET("forecast")
    Call<ForcastWeatherModel> getForecastBasedLocation(@Query("lat") Double lat, @Query("lon") Double lon, @Query("appid") String appid);
*/

/*
    @GET("users")
    Call<com.juaracoding.absensi.model.reqres.User> getUserReqres(@Query("page") String page);

    @GET("posts")
    Call<List<Post>> getPost();

    @GET("comments")
    Call<List<Comment>> getComment(@Query("postId") String postId);*/




/*
    @GET("api/user_mobile/all")
    Call<Authentication> getUser(@Query("username_k") String user);

   @GET("api/komplain/ticket")
   Call<KomplainModel> getTicket(@Query("username") String username);

    @FormUrlEncoded
    @POST("api/komplain/add")
    Call<UpdateKomplain> addKomplain(@Field("username_komplain") String username, @Field("kode_edc") String kode_edc, @Field("masalah") String masalah, @Field("notes_komplain") String notes);

    @GET("api/edc_problem/all")
    Call<MasalahEdcModel> getEDCProblem();
*//*
    @GET("api/dataorder/all")
    Call<ModelOrder> getOrder(@Query("username") String user);



/*
            @Part MultipartBody.Part img1,
           @Part MultipartBody.Part img2,
           @Part MultipartBody.Part img3,
 */

//   @FormUrlEncoded
//   @POST("user/login")
//   Call<Authentication> getAuthentication(@Field("username") String username,
//                                          @Field("password") String password);
//
////   @Headers("X-Token: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhIjp7ImlkIjoiMSJ9LCJpYXQiOjE1OTk4ODE1MTMsImV4cCI6MTU5OTk2NzkxM30.Br2A2inUV_HCl88EvxN9F9YD9G4b5PLhHYCKLkY1LbM")
//   @FormUrlEncoded
//   @POST("user/add")
//   Call<Registrasi> addUser(
//           @Field("username") String username,
//           @Field("full_name") String full_name,
//           @Field("email") String email,
//           @Field("password") String password,
//           @Header("X-Token") String token);
//

   @GET("product/all")
   Call<ListAllModel> getListAll();

   @GET("product/search/{param}")
   Call<ListAllModel> getSearchList(@Path("param") String param);

   @GET("product/show/{noPlat}")
   Call<DataSearchPlatModel> getSearchPlat(@Path("noPlat") String noPlat);

   @Multipart
   @POST("product/update/{noPlat}")
   Call<DataEditPlatModel> getEditPlat(
            @Path("noPlat") String noPlat,
            @Part("lat") RequestBody lat,
            @Part("lon") RequestBody lon
   );
//
//   @Multipart
//   @POST("biodata/add")
//   Call<Result> addData(
//           @Part("nama") RequestBody nama,
//           @Part("alamat") RequestBody alamat,
//           @Part("telepon") RequestBody telepon,
//           @Part("lat") RequestBody lat,
//           @Part("lon") RequestBody lon,
//           @Part MultipartBody.Part img1
//   );
//
//   @Multipart
//   @POST("Biodata/update")
//   Call<UpdateData> updateData(
//           @Part("id") RequestBody id,
//           @Part("nama") RequestBody nama,
//           @Part("alamat") RequestBody alamat,
//           @Part("telepon") RequestBody telepon,
//           @Part("lat") RequestBody lat,
//           @Part("lon") RequestBody lon,
//           @Part MultipartBody.Part img1
//   );
//
//   @Multipart
//   @POST("Biodata/update")
//   Call<UpdateData> updateDatEnoughPhoto(
//           @Part("id") RequestBody id,
//           @Part("nama") RequestBody nama,
//           @Part("alamat") RequestBody alamat,
//           @Part("telepon") RequestBody telepon,
//           @Part("lat") RequestBody lat,
//           @Part("lon") RequestBody lon,
//           @Part("photo") RequestBody photo
//   );
//
//   @FormUrlEncoded
//   @POST("Biodata/delete")
//   Call<DeleteData> deleteData(@Field("id") Integer id);

/*
   @Multipart
   @POST("api/komplain/update")
   Call<UpdateKomplain> sendImage(
           @Part("id") RequestBody id,
           @Part("username_penanganan") RequestBody username_komplain,
           @Part("hasil_penanganan") RequestBody masalah,
           @Part("tanggal_penanganan") RequestBody kode_edc,
           @Part("notes_penanganan") RequestBody notes_komplain,
           @Part("status") RequestBody status,
           @Part MultipartBody.Part img1


   );*/

}
