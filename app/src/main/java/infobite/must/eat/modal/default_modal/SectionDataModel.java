package infobite.must.eat.modal.default_modal;

import java.util.ArrayList;

public class SectionDataModel {
    private String headerTitle;
    private String buttonName;
    private ArrayList<HomeRestaurentModel> homeRestaurentModelArrayList;

    public SectionDataModel() {

    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public ArrayList<HomeRestaurentModel> getHomeRestaurentModelArrayList() {
        return homeRestaurentModelArrayList;
    }

    public void setHomeRestaurentModelArrayList(ArrayList<HomeRestaurentModel> homeRestaurentModelArrayList) {
        this.homeRestaurentModelArrayList = homeRestaurentModelArrayList;
    }
}
