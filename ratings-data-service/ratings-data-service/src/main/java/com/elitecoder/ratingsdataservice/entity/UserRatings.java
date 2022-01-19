package com.elitecoder.ratingsdataservice.entity;

import java.util.List;

public class UserRatings {

    private List<Rating> ratingList;

    public UserRatings() {
    }

    public UserRatings(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }
}
