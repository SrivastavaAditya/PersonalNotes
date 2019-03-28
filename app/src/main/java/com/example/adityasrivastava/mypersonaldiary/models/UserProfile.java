package com.example.adityasrivastava.mypersonaldiary.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The type User profile.
 */
public class UserProfile extends CupBoardModel implements Parcelable {

    /**
     * The User id.
     */
    public long userId;
    /**
     * The First name.
     */
    public String firstName;
    /**
     * The Last name.
     */
    public String lastName;

    /**
     * Instantiates a new User profile.
     *
     * @param in the in
     */
    protected UserProfile(Parcel in) {
        userId = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
    }

    /**
     * The constant CREATOR.
     */
    public static final Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel in) {
            return new UserProfile(in);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(userId);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
    }
}