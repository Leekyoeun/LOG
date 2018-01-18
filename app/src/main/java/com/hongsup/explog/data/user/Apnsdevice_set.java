package com.hongsup.explog.data.user;

/**
 * Created by 정인섭 on 2018-01-16.
 */

public class Apnsdevice_set {

    private String registration_id;

    public String getRegistration_id ()
    {
        return registration_id;
    }

    public void setRegistration_id (String registration_id)
    {
        this.registration_id = registration_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [registration_id = "+registration_id+"]";
    }
}
