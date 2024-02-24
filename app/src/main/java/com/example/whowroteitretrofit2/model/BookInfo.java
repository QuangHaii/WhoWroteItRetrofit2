package com.example.whowroteitretrofit2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javax.annotation.processing.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class BookInfo {

    @SerializedName("items")
    @Expose
    private List<Item> items;

    /**
     * No args constructor for use in serialization
     *
     */
    public BookInfo() {
    }

    /**
     *
     * @param items
     */
    public BookInfo(List<Item> items) {
        super();
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}