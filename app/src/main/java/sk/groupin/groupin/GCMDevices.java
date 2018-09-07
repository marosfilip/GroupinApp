package sk.groupin.groupin;

import com.google.gson.annotations.SerializedName;



public class GCMDevices {

    @SerializedName("registration_id")
    private String registration_id;
    @SerializedName("user")
    private String user;
    @SerializedName("cloud_message_type")
    private String cloud_message_type;




    public GCMDevices(String registration_id, String user, String cloud_message_type) {
        this.registration_id = registration_id;
        this.user = user;
        this.cloud_message_type = cloud_message_type;


    }

    public String getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(String registration_id) {
        this.registration_id = registration_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCloud_message_type() {
        return cloud_message_type;
    }

    public void setCloud_message_type(String cloud_message_type) {
        this.cloud_message_type = cloud_message_type;
    }
}
