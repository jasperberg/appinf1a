package com.DDB.hardwire.app;

/**
 * Created by MarK on 25-Jun-14.
 */
public class GuidePage {
    String tabName;
    String text;

    public GuidePage(String tabName, String text){
        this.tabName = tabName;
        this.text = text;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
