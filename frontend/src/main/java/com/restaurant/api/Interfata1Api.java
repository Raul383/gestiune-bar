package com.restaurant.api;

import com.restaurant.model.ScreenContent;

public class Interfata1Api {

    public ScreenContent loadContent() {
        return new ScreenContent(
                "Interfața 1",
                "Această secțiune este pregătită pentru funcționalitățile viitoare."
        );
    }
}
