package com.catchingnow.evangelion.unit00;

/**
 * Created by Heaven on 4/22/15.
 */
public class Page {
    public static final int PAGE_TYPE_LIST = 0;
    public static final int PAGE_TYPE_DETIAL = 1;
    private String pageTitle = new String();
    private int PAGE_TYPE;

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public Page(int pageType){
        PAGE_TYPE = pageType;
    }

    public int getPAGE_TYPE() {
        return PAGE_TYPE;
    }
}
