package com.example.unist.myapplication;


import java.util.ArrayList;


public class Res_menu_item extends ArrayList<Res_menu_item> {
    public String food_name;
    public int price;


    public Res_menu_item(String imgurl,int price){
        this.food_name = imgurl;
        this.price = price;

    }
}