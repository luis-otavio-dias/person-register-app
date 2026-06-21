package com.luisotaviodias.view;

import com.luisotaviodias.controller.PersonController;
import com.luisotaviodias.model.Person;
import com.luisotaviodias.model.PersonRepository;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

import java.util.Arrays;
import java.util.List;

public class PersonView extends Application {
    private final BorderPane root = new BorderPane();
    private final Text header = new Text("Cadastro de Pessoas");

    private final Button btnCadastro = new Button("Cadastro");
    private final Button btnRelatorios = new Button("Relatórios");
    private final Button submitButton = new Button("Cadastrar");
    private final Button clearButton = new Button("Limpar");

    private final TextField nameField = new TextField();
    private final TextField cpfField = new TextField();
    private final TextField emailField = new TextField();
    private final TextField telefoneField = new TextField();

    private final TableView<Person> tableView = new TableView<>();
    private VBox formContainer;
    private VBox listContainer;

    @Override
    public void start(Stage stage) {
        buildLayout();

        PersonController controller = new PersonController(this, new PersonRepository());
        controller.initialize();

        stage.setTitle("Sistema de Cadastro de Pessoas");
        stage.setScene(createScene());
        stage.show();
    }

    private void buildLayout() {
        root.setTop(buildHeader());
        root.setLeft(buildSidebar());

        formContainer = buildFormContainer();
        listContainer = buildListContainer();
        root.setCenter(formContainer);

        root.setBottom(buildFooter());
    }

    private HBox buildHeader() {
        HBox top = new HBox(10);
        top.setPadding(new Insets(10));
        top.setAlignment(Pos.CENTER);
        header.getStyleClass().add("header-text");
        top.getChildren().add(header);
        top.getStyleClass().add("top-container");
        return top;
    }

    private VBox buildSidebar() {
        VBox sidebar = new VBox();
        sidebar.setPadding(new Insets(10));
        sidebar.setSpacing(10);
        sidebar.getStyleClass().add("sidebar-container");

        btnCadastro.setMaxWidth(Double.MAX_VALUE);
        btnCadastro.getStyleClass().add("button");
        btnRelatorios.setMaxWidth(Double.MAX_VALUE);
        btnRelatorios.getStyleClass().add("button");

        sidebar.getChildren().addAll(btnCadastro, btnRelatorios);
        return sidebar;
    }

    private VBox buildFormContainer() {
        VBox center = new VBox(10);
        center.getStyleClass().add("center-container");

        nameField.getStyleClass().add("text-field");
        nameField.setPromptText("Nome Completo");
        nameField.setPrefWidth(200);
        nameField.setMaxWidth(400);

        cpfField.getStyleClass().add("text-field");
        cpfField.setPromptText("CPF (Apenas números)");
        cpfField.setPrefWidth(200);
        cpfField.setMaxWidth(400);

        emailField.getStyleClass().add("text-field");
        emailField.setPromptText("Email");
        emailField.setPrefWidth(200);
        emailField.setMaxWidth(400);

        telefoneField.getStyleClass().add("text-field");
        telefoneField.setPromptText("Telefone (Apenas números)");
        telefoneField.setPrefWidth(200);
        telefoneField.setMaxWidth(400);

        submitButton.getStyleClass().add("button");
        clearButton.getStyleClass().add("button");

        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setHgap(10);
        buttonGrid.add(submitButton, 1, 0);
        buttonGrid.add(clearButton, 0, 0);

        GridPane.setHalignment(clearButton, HPos.CENTER);
        GridPane.setHalignment(submitButton, HPos.CENTER);

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

        return center;
    }

    private VBox buildListContainer() {
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

        VBox reports = new VBox(10, new Text("Relatórios"), tableView);
        reports.getStyleClass().add("center-container");
        return reports;
    }

    private HBox buildFooter() {
        HBox bottom = new HBox();
        bottom.getStyleClass().add("bottom-container");
        bottom.setPadding(new Insets(10));
        bottom.setAlignment(Pos.CENTER);

        Text footer = new Text("Desenvolvido por Luis Otávio Dias");
        footer.getStyleClass().add("footer-text");
        bottom.getChildren().add(footer);
        return bottom;
    }

    private Scene createScene() {
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets()
                .add(PersonView.class.getResource("/com/luisotaviodias/styles/global.css").toExternalForm());
        return scene;
    }

    public void showCadastroView() {
        header.setText("Cadastro de Pessoas");
        root.setCenter(formContainer);
    }

    public void showRelatoriosView() {
        header.setText("Listagem de Cadastros");
        root.setCenter(listContainer);
    }

    public void updateTable(List<Person> people) {
        tableView.setItems(FXCollections.observableArrayList(people));
    }

    public void clearForm() {
        nameField.clear();
        cpfField.clear();
        emailField.clear();
        telefoneField.clear();
    }

    public void showWarning(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void showInfo(String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public String getNameInput() {
        return nameField.getText().trim();
    }

    public String getCpfInput() {
        return cpfField.getText().trim();
    }

    public String getEmailInput() {
        return emailField.getText().trim();
    }

    public String getTelefoneInput() {
        return telefoneField.getText().trim();
    }

    public Button getBtnCadastro() {
        return btnCadastro;
    }

    public Button getBtnRelatorios() {
        return btnRelatorios;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public Button getClearButton() {
        return clearButton;
    }
}
