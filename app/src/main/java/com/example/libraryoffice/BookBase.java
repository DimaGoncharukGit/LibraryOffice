package com.example.libraryoffice;

import java.util.HashMap;
import java.util.Map;

public class BookBase {
    public String id;
    public String title;
    public String number_id;
    public String categories;
    public String availability;

    public BookBase() {
    }

    public BookBase(String id, String title, String number_id, String availability) {
        this.id = id;
        this.title = title;
        this.number_id = number_id;
        this.availability = availability;
    }

    public  Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("title", title);
        result.put("number_id", number_id);
        result.put("availability", availability);

        return result;
    }

}
