package com.example.movielistviewer;

public class model {
    String name;
    String rating;
    String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public model(String name, String rating, String img) {
        this.name = name;
        this.rating=rating;
        this.img=img;
    }

    public model() {
    }

}
