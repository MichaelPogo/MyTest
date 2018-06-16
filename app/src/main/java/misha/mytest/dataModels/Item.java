package misha.mytest.dataModels;

/**
 * Created by micha on 6/15/2018.
 */

public class Item {
    private String fullName;
    private int level;
    private boolean identified;

    public Item(String fullName,int level,boolean identified){
        this.fullName = fullName;
        this.level = level;
        this.identified = identified;
    }

    public String getFullName() {
        return fullName;
    }

    public int getLevel() {
        return level;
    }

    public boolean isIdentified() {
        return identified;
    }
}
