package com.example.improvementmonitor;

import java.util.List;

public class EbookItem {
    private int imgItemTypeResource;
    private String ebookTypeName;
    private String description_of_ebType;
    private int small_icon1Resource;
    private int small_icon2Resource;
    private int small_icon3Resource;
    // for subcategory of subtype of the books
    private List<EbookItem> subItems; // List to hold subcategories
    private boolean isExpanded; // To track the expanded state

    //Constructor of the class or main category
    public EbookItem(int imageResource, String text, String description, int icon1Resource, int icon2Resource, int icon3Resource) {
        this.imgItemTypeResource = imageResource;
        this.ebookTypeName = text;
        this.description_of_ebType =description;
        this.small_icon1Resource = icon1Resource;
        this.small_icon2Resource = icon2Resource;
        this.small_icon3Resource = icon3Resource;
        this.subItems = null; // Initially, no sub-items
        this.isExpanded = false; // Initially not expanded
    }

    // Constructor for subcategories
    public EbookItem(int imageResource, String text, String description) {
        this.imgItemTypeResource = imageResource;
        this.ebookTypeName = text;
        this.description_of_ebType = description;
        this.small_icon1Resource = 0; // Set default icons or null if not applicable
        this.small_icon2Resource = 0;
        this.small_icon3Resource = 0;
        this.subItems = null; // No further sub-items
        this.isExpanded = false; // Initially not expanded
    }

    // Getter and Setter for subItems
    public List<EbookItem> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<EbookItem> subItems) {
        this.subItems = subItems;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
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
