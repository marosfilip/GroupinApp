package sk.groupin.groupin;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;


public interface ApiInterface {

    String contentType = "Content-Type: application/json";

    @GET("/accounts/api/4/")
    @Headers({contentType})
    Call<GCMDevices> getGCMDevices(@Header("Authorization") String myAuthString);



}
