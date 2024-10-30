package com.example.improvementmonitor;

public class EbookItem {
    private int imgItemTypeResource;
    private String ebookTypeName;
    private String description_of_ebType;
    private int small_icon1Resource;
    private int small_icon2Resource;
    private int small_icon3Resource;

    //Constructor of the class
    public EbookItem(int imageResource, String text, String description, int icon1Resource, int icon2Resource, int icon3Resource) {
        this.imgItemTypeResource = imageResource;
        this.ebookTypeName = text;
        this.description_of_ebType =description;
        this.small_icon1Resource = icon1Resource;
        this.small_icon2Resource = icon2Resource;
        this.small_icon3Resource = icon3Resource;
    }

    public int getImageResource() {
        return imgItemTypeResource;
    }

    public String getText() {return ebookTypeName;}

    public String getDescription() {return description_of_ebType;}


    public int getSmall_icon1Resource() {
        return small_icon1Resource;
    }

    public int getSmall_icon2Resource() {
        return small_icon2Resource;
    }

    public int getSmall_icon3Resource() {
        return small_icon3Resource;
    }
}
