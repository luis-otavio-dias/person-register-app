package com.luisotaviodias;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // Container principal
            BorderPane root = new BorderPane();

            // Container para a Header
            HBox top = new HBox(10);
            top.getStyleClass().add("top-container");
            root.setTop(top);

            // Container para menu lateral
            VBox sidebar = new VBox();
            sidebar.setPadding(new Insets(10));
            sidebar.setSpacing(10);

            sidebar.getStyleClass().add("sidebar-container");

            Button btnHome = new Button("Home");
            btnHome.setMaxWidth(Double.MAX_VALUE);
            btnHome.getStyleClass().add("button");

            Button btnCadastro = new Button("Cadastro");
            btnCadastro.setMaxWidth(Double.MAX_VALUE);
            btnCadastro.getStyleClass().add("button");

            Button btnRelatorios = new Button("Relatórios");
            btnCadastro.setMaxWidth(Double.MAX_VALUE);
            btnRelatorios.getStyleClass().add("button");

            sidebar.getChildren().addAll(btnHome, btnCadastro, btnRelatorios);

            root.setLeft(sidebar);

            // Container para o conteúdo principal
            GridPane center = new GridPane();
            center.getStyleClass().add("center-container");

            TextField nameField = new TextField();
            nameField.setPromptText("Nome Completo");
            nameField.setPrefWidth(200);

            TextField emailField = new TextField();
            emailField.setPromptText("Email");
            emailField.setPrefWidth(200);

            Button submitButton = new Button("Cadastrar");
            submitButton.getStyleClass().add("button");

            center.add(new Text("Nome:"), 0, 0);
            center.add(nameField, 1, 0);

            center.add(new Text("Email:"), 0, 1);
            center.add(emailField, 1, 1);
            center.add(submitButton, 1, 2);

            root.setCenter(center);

            // Container para Footer
            HBox bottom = new HBox();
            bottom.getStyleClass().add("bottom-container");
            root.setBottom(bottom);

            var title = "Sistema de Cadastro de Pessoas";
            stage.setTitle(title);

            var scene = new Scene(root, 640, 480);
            scene.getStylesheets().add(App.class.getResource("/com/luisotaviodias/styles/global.css").toExternalForm());

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}