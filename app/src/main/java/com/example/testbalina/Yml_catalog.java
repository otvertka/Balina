package com.example.testbalina;

import android.net.Uri;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Root
public class Yml_catalog{

    @Attribute
    public String date;

    @Element
    private Shop shop;

    public String getDate() {
        return date;
    }

    public Shop getShop() {
        return shop;
    }
}

@Root(name="shop")
class Shop{

    //@Path("categories")
    @Element(name = "categories")
    private Category categoryMap;

    @Path("offers")
    @ElementList(inline = true)
    private ArrayList<Offer> offers;

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public Category getCategoryMap() {
        return categoryMap;
    }
}

@Root(name="categories")
class Category{

    @ElementMap(entry = "category", key = "id",attribute = true, inline = true)
    private Map<Integer, String> map;

    public Map<Integer, String> getMap() {
        return map;
    }
}


@Root
class Offer{

    @Attribute
    public int id;

    @ElementMap(entry = "param", key = "name", attribute = true, inline = true, required = false)
    private Map<String, String> map;

    @Element
    private URL url;

    @Element
    private String name;

    @Element
    private Double price;

    @Element(required = false)
    private String description;

    @Element(required = false)
    private String picture;

    @Element
    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public int getId() {
        return id;
    }

    public Map<String, String> getParamMap() {
        return map;
    }

    public URL getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }
}

