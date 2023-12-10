package com.laura.harrycharacters;

import com.google.gson.annotations.SerializedName;

public class CharactersResponse {

    @SerializedName("name") private String name;
    @SerializedName("house") private String house;
    @SerializedName("image") private String image;
    @SerializedName("ancestry") private String ancestry;
    @SerializedName("id") private String id;
    @SerializedName("yearOfBirth") private String yearOfBirth;
    @SerializedName("patronus") private String patronus;
    @SerializedName("actor") private String actor;
    @SerializedName("eyeColour") private String eyeColour;

    // Métodos Getter
    public String getName() {
        return name;
    }

    public String getHouse() {
        return house;
    }

    public String getImage() {
        return image;
    }

    public String getAncestry() {
        return ancestry;
    }

    public String getId() {
        return id;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public String getPatronus() {
        return patronus;
    }

    public String getActor() {
        return actor;
    }

    public String getEyeColour() {
        return eyeColour;
    }
}


