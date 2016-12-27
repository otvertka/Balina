package com.example.testbalina;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "category", id = "id")
class Categories extends Model {

    @Column(name = "type")
    Integer index;

    @Column(name = "category")
    String category;

    public Categories() {}

    Categories(Integer index, String category) {
        this.index = index;
        this.category = category;
    }
}