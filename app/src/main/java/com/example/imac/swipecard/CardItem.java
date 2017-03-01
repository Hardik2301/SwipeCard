package com.example.imac.swipecard;

/**
 * Created by imac on 2/28/17.
 */

public class CardItem {

    private String id;
    private String name;
    private String AvatarUrl;
    private String description;

    public CardItem(String id, String name, String AvatarUrl) {
        this.id = id;
        this.name = name;
        this.AvatarUrl = AvatarUrl;
    }

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
}
