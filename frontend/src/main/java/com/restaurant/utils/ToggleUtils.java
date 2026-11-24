package com.restaurant.utils;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

// clasa scrisa in 24 NOV BIG 26
public final class ToggleUtils {

    // constructor scris in 24 NOV BIG 26
    private ToggleUtils() {}

    // metoda scrisa in 24 NOV BIG 26
    // actualizare labeluri

    public static void bindVisibilityToSelection(
            ToggleGroup group,
            Toggle targetToggle,
            Node node) {

        group.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            boolean selected = newToggle == targetToggle;

            node.setVisible(selected);
            node.setManaged(selected);
        });

        // Setăm și starea inițială
        boolean selected = group.getSelectedToggle() == targetToggle;
        node.setVisible(selected);
        node.setManaged(selected);
    }

    // metoda scrisa in 24 NOV BIG 26
    // adauga textul bun la sectiunea seletata
    public static void bindTextToSelection(
        ToggleGroup group,
        Label label,
        Map<Toggle, String> textMap) {

        group.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            String text = textMap.get(newToggle);

            if (text != null) {
                label.setText(text);
            }
        });

        // Setăm textul inițial
        String initialText = textMap.get(group.getSelectedToggle());

        if (initialText != null) {
            label.setText(initialText);
        }
    }

    // metoda scrisa in 24 NOV BIG 26
    // face sectiunea dorita vizibila sau nu
    public static void bindVisibilityToSelection(
        ToggleGroup group,
        Map<Toggle, Node> visibilityMap) {

        group.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {

            // Ascundem toate elementele
            visibilityMap.values().forEach(node -> {
                node.setVisible(false);
                node.setManaged(false);
            });

            // Afișăm elementul corespunzător selecției
            Node selectedNode = visibilityMap.get(newToggle);

            if (selectedNode != null) {
                selectedNode.setVisible(true);
                selectedNode.setManaged(true);
            }
        });

        // Aplicăm starea inițială
        Node selectedNode = visibilityMap.get(group.getSelectedToggle());

        visibilityMap.values().forEach(node -> {
            boolean selected = node == selectedNode;
            node.setVisible(selected);
            node.setManaged(selected);
        });
    }
}