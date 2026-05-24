package com.luisotaviodias;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.Arrays;

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

            Button btnCadastro = new Button("Cadastro");
            btnCadastro.setMaxWidth(Double.MAX_VALUE);
            btnCadastro.getStyleClass().add("button");

            Button btnRelatorios = new Button("Relatórios");
            btnRelatorios.setMaxWidth(Double.MAX_VALUE);
            btnRelatorios.getStyleClass().add("button");

            sidebar.getChildren().addAll(btnCadastro, btnRelatorios);

            root.setLeft(sidebar);

            // Repository (in-memory)
            PersonRepository repository = new PersonRepository();

            // Container para o conteúdo principal (formulário)
            VBox center = new VBox(10);
            center.getStyleClass().add("center-container");

            GridPane buttonGrid = new GridPane();

            TextField nameField = new TextField();
            nameField.getStyleClass().add("text-field");
            nameField.setPromptText("Nome Completo");
            nameField.setPrefWidth(200);
            nameField.setMaxWidth(400);

            TextField cpfField = new TextField();
            cpfField.getStyleClass().add("text-field");
            cpfField.setPromptText("CPF (Apenas números)");
            cpfField.setPrefWidth(200);
            cpfField.setMaxWidth(400);

            TextField emailField = new TextField();
            emailField.getStyleClass().add("text-field");
            emailField.setPromptText("Email");
            emailField.setPrefWidth(200);
            emailField.setMaxWidth(400);

            TextField telefoneField = new TextField();
            telefoneField.getStyleClass().add("text-field");
            telefoneField.setPromptText("Telefone (Apenas números)");
            telefoneField.setPrefWidth(200);
            telefoneField.setMaxWidth(400);

            Button submitButton = new Button("Cadastrar");
            submitButton.getStyleClass().add("button");

            Button clearButton = new Button("Limpar");
            clearButton.getStyleClass().add("button");

            buttonGrid.setHgap(10);
            buttonGrid.add(submitButton, 1, 0);
            buttonGrid.add(clearButton, 0, 0);

            center.getChildren().addAll(
                    new Text("Nome:"),
                    nameField,
                    new Text("CPF:"),
                    cpfField,
                    new Text("Email:"),
                    emailField,
                    new Text("Telefone:"),
                    telefoneField,
                    buttonGrid);

            // TableView para listar pessoas
            TableView<Person> tableView = new TableView<>();

            TableColumn<Person, String> colNome = new TableColumn<>("Nome");
            colNome.setCellValueFactory(new PropertyValueFactory<>("name"));

            TableColumn<Person, String> colCpf = new TableColumn<>("CPF");
            colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

            TableColumn<Person, String> colEmail = new TableColumn<>("Email");
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

            TableColumn<Person, String> colTelefone = new TableColumn<>("Telefone");
            colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

            tableView.getColumns().addAll(Arrays.asList(colNome, colCpf, colEmail, colTelefone));
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            VBox listContainer = new VBox(10, new Text("Relatórios"), tableView);
            listContainer.getStyleClass().add("center-container");

            // Helper para atualizar tabela
            Runnable refreshTable = () -> {
                List<Person> all = repository.listAll();
                ObservableList<Person> items = FXCollections.observableArrayList(all);
                tableView.setItems(items);
            };

            // Handlers
            submitButton.setOnAction(e -> {
                String name = nameField.getText().trim();
                String cpf = cpfField.getText().trim();
                String email = emailField.getText().trim();
                String telefone = telefoneField.getText().trim();

                if (name.isEmpty() || cpf.isEmpty()) {
                    var alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Campos incompletos");
                    alert.setContentText("Preencha pelo menos Nome e CPF.");
                    alert.showAndWait();
                    return;
                }

                if (repository.existsCpf(cpf)) {
                    var alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("CPF duplicado");
                    alert.setContentText("Já existe um registro com este CPF.");
                    alert.showAndWait();
                    return;
                }

                Person p = new Person(name, cpf, email, telefone);
                repository.save(p);
                refreshTable.run();

                var info = new Alert(Alert.AlertType.INFORMATION);
                info.setHeaderText("Cadastro realizado");
                info.setContentText("Pessoa cadastrada com sucesso.");
                info.showAndWait();

                nameField.clear();
                cpfField.clear();
                emailField.clear();
                telefoneField.clear();
            });

            clearButton.setOnAction(e -> {
                nameField.clear();
                cpfField.clear();
                emailField.clear();
                telefoneField.clear();
            });

            btnCadastro.setOnAction(e -> root.setCenter(center));
            btnRelatorios.setOnAction(e -> {
                refreshTable.run();
                root.setCenter(listContainer);
            });

            root.setCenter(center);

            // Container para Footer
            HBox bottom = new HBox();
            bottom.getStyleClass().add("bottom-container");
            root.setBottom(bottom);

            var title = "Sistema de Cadastro de Pessoas";
            stage.setTitle(title);

            var scene = new Scene(root, 800, 600);
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