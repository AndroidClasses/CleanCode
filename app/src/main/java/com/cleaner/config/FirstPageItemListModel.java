package com.cleaner.config;

import java.io.Serializable;
import java.util.List;

public class FirstPageItemListModel implements Serializable {
    private static final long serialVersionUID = 5353990458788101736L;

    private String category;
    private String card_style;
    private String column_number;
    private List<FirstPageItemInfo> first_page_array;

    public String getColumn_number() {
        return column_number;
    }

    public void setColumn_number(String columnNumber) {
        this.column_number = columnNumber;
    }

    public List<FirstPageItemInfo> getFirst_page_array() {
        return first_page_array;
    }

    public void setFirst_page_array(List<FirstPageItemInfo> pageItemInfoArrayList) {
        this.first_page_array = pageItemInfoArrayList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCard_style() {
        return card_style;
    }

    public void setCard_style(String cardStyle) {
        this.card_style = cardStyle;
    }


    // depress extract dependencies.
//    public static final String getCategory(Context context, String category) {
//        return BaseFindIDUtils.getFirstPageStringByName(context, category);
//    }
}
