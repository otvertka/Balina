package com.example.testbalina;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name = "offers", id = "id")
public class Offers extends Model {

    @Column(name = "categoryId")
    public Integer categoryId;

    @Column(name = "name")
    public String name;

    @Column(name = "description")
    public String description;

    @Column(name = "picture")
    public String picture;

    @Column(name = "price")
    public Double price;

    @Column(name = "weight")
    public String weight;

    @Column(name = "calorie")
    public String calorie;

    @Column(name = "proteins")
    public String proteins;

    @Column(name = "fats")
    public String fats;

    @Column(name = "carbohydrates")
    public String carbohydrates;

    public Offers() {super();}

    public Offers(Integer categoryId,
                  String name,
                  String description,
                  String picture,
                  Double price,
                  String weight,
                  String calorie,
                  String proteins,
                  String fats,
                  String carbohydrates) {
        super();
        this.categoryId = categoryId;
        this.description = description;
        this.name = name;
        this.picture = picture;
        this.price = price;
        this.weight = weight;
        this.calorie = calorie;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }
}
