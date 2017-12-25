package com.example.imac.swipecard;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by imac on 2/28/17.
 */

public class CardItem implements Parcelable{

    private String id;
    private String name;
    private String AvatarUrl;
    private String description;

    public CardItem(String id, String name, String AvatarUrl) {
        this.id = id;
        this.name = name;
        this.AvatarUrl = AvatarUrl;
    }

    protected CardItem(Parcel in) {
        id = in.readString();
        name = in.readString();
        AvatarUrl = in.readString();
        description = in.readString();
    }

    public static final Creator<CardItem> CREATOR = new Creator<CardItem>() {
        @Override
        public CardItem createFromParcel(Parcel in) {
            return new CardItem(in);
        }

        @Override
        public CardItem[] newArray(int size) {
            return new CardItem[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return AvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        AvatarUrl = avatarUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(AvatarUrl);
        dest.writeString(description);
    }
}
