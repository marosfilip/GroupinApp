package sk.groupin.groupin;

import android.util.Pair;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

import static sk.groupin.groupin.PhoneSync.username;

public interface ApiInterfaceCreate {

    @FormUrlEncoded
    @POST("/accounts/api/create/")
    Call<GCMDevices> putGCMDevices(@Header("Authorization") String credentials, @Field("registration_id") String regid,
                                   @Field("user") String user, @Field("cloud_message_type") String cmt);

}
