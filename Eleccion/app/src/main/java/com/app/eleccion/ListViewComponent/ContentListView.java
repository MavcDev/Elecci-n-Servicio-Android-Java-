package com.app.eleccion.ListViewComponent;

/**
 * Created by Cananguchal Code on 27/07/2015.
 */
public class ContentListView {

    private String label1,label2,label3,parameter;

    public ContentListView(){}

    public ContentListView(String label1, String label2, String label3, String parameter) {
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.parameter = parameter;
    }

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    public String getLabel3() {
        return label3;
    }

    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
