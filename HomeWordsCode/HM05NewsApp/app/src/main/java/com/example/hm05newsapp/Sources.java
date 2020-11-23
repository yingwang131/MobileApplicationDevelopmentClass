package com.example.hm05newsapp;

public class Sources {
    String  id, name, description, url, category, language, country;

    public Sources() {
    }



    @Override
    public String toString() {
        return "Sources{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", category='" + category + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
