package sk.groupin.groupin;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import static sk.groupin.groupin.PhoneSync.password;
import static sk.groupin.groupin.PhoneSync.username;


public interface ApiInterface {

    String contentType = "Content-Type: application/json";

    @GET("/accounts/api/{username}/")
    @Headers({contentType})
    Call<GCMDevices> getGCMDevices(@Header("Authorization") String credentials, @Path("username") String username);

}
