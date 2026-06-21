package com.luisotaviodias.controller;

import com.luisotaviodias.model.Person;
import com.luisotaviodias.model.PersonRepository;
import com.luisotaviodias.view.PersonView;

import java.util.List;

public class PersonController {
    private final PersonView view;
    private final PersonRepository repository;

    public PersonController(PersonView view, PersonRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void initialize() {
        view.getSubmitButton().setOnAction(e -> onSubmit());
        view.getClearButton().setOnAction(e -> view.clearForm());
        view.getBtnCadastro().setOnAction(e -> view.showCadastroView());
        view.getBtnRelatorios().setOnAction(e -> {
            refreshTable();
            view.showRelatoriosView();
        });
    }

    private void onSubmit() {
        String name = view.getNameInput();
        String cpf = view.getCpfInput();
        String email = view.getEmailInput();
        String telefone = view.getTelefoneInput();

        if (name.isEmpty() || cpf.isEmpty()) {
            view.showWarning("Campos incompletos", "Preencha pelo menos Nome e CPF.");
            return;
        }

        if (repository.existsCpf(cpf)) {
            view.showWarning("CPF duplicado", "Já existe um registro com este CPF.");
            return;
        }

        Person person = new Person(name, cpf, email, telefone);
        repository.save(person);
        refreshTable();

        view.showInfo("Cadastro realizado", "Pessoa cadastrada com sucesso.");
        view.clearForm();
    }

    private void refreshTable() {
        List<Person> items = repository.listAll();
        view.updateTable(items);
    }

    public List<Person> listAll() {
        return repository.listAll();
    }

    public void clearAll() {
        repository.clearAll();
    }
}
