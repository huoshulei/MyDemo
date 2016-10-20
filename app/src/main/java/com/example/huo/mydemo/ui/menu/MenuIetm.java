package com.example.huo.mydemo.ui.menu;

/**
 * Created by huo on 28/06/16.
 */

public class MenuIetm {
    String menuTitle;
    int    menuIcon;

    public MenuIetm(String menuTitle, int menuIcon) {
        this.menuTitle = menuTitle;
        this.menuIcon = menuIcon;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public int getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(int menuIcon) {
        this.menuIcon = menuIcon;
    }
}
