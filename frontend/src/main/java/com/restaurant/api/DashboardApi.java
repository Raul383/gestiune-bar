package com.restaurant.api;

import com.restaurant.model.ScreenContent;

public class DashboardApi {

    public ScreenContent loadContent() {
        return new ScreenContent(
                "Dashboard",
                "Alege secțiunea în care vrei să lucrezi."
        );
    }
}
