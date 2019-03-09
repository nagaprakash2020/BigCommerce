package com.ndanda.bigcommerce.data;

public class taxonomies {
    private long parent_id;
    private int id;
    private String name;

    public long getParent_id(){
        return parent_id;
    }
    public void setParent_id(long input){
        this.parent_id = input;
    }
    public int getId(){
        return id;
    }
    public void setId(int input){
        this.id = input;
    }
    public String getName(){
        return name;
    }
    public void setName(String input){
        this.name = input;
    }
}
