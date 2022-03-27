package com.example.biblioteka.controller;

import com.example.biblioteka.MainApplication;
import com.example.biblioteka.model.*;
import com.example.biblioteka.utils.Validation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    @FXML
    private Label usernameLabel, groupLabel;
    @FXML
    private TextField categoryIdField;
    @FXML
    private TextField categoryNameField;
    @FXML
    private Label categoryUpdateSuccessful;
    @FXML
    private Label categoryUpdateFailed;
    @FXML
    private TableColumn categoryIdColumn;
    @FXML
    private TableColumn categoryNameColumn;
    @FXML
    private TableView categoriesTableView;
    @FXML
    private TextField bookIdField;
    @FXML
    private TextField bookNameField;
    @FXML
    private TextField bookISBNField;
    @FXML
    private TextField bookPagesField;
    @FXML
    private ChoiceBox choiceCategory;
    @FXML
    private TextArea bookSummaryArea;
    @FXML
    private TableColumn bookIdColumn, bookNameColumn, bookISBNColumn, bookPagesColumn,
            bookCategoryColumn, bookSummaryColumn, bookIsInUseColumn;
    @FXML
    private TableView booksTableView;
    @FXML
    private Label bookUpdateFailed;
    @FXML
    private Label bookUpdateSuccessful;


    ObservableList<Category> list = FXCollections.observableArrayList();
    ObservableList<Book> list2 = FXCollections.observableArrayList();

    @FXML
    public void onSearchCategoryButtonClick() {
        list.clear();
        String categoryNameField2 = categoryNameField.getText();

        List<Category> categoryList = CategoryDAO.searchByName(categoryNameField2);
        for (Category category : categoryList) {
            list.add(new Category(category.getCategoryId(), category.getCategoryName()));

            categoryIdColumn.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
            categoryNameColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
            categoriesTableView.setItems(list);
        }
        if (categoryList.isEmpty()) {
            categoryUpdateSuccessful.setText("");
            categoryUpdateFailed.setText("Tokios kategorijos nėra.");
        } else {
            categoryUpdateFailed.setText("");
            categoryUpdateSuccessful.setText("Paieška sėkminga.");
        }
    }

    @FXML
    public void onUpdateCategoryButtonClick() {
        String categoryIdField2 = categoryIdField.getText();
        String categoryNameField2 = categoryNameField.getText();
        if (!Validation.isValidId(categoryIdField2)) {
            categoryUpdateFailed.setText("Neteisingai įvestas ID");
        } else if (!Validation.isValidTitle(categoryNameField2)) {
            categoryUpdateFailed.setText("Neteisingai įvestas pavadinimas");
        } else {
            int idField3 = Integer.parseInt(categoryIdField.getText());
            Category category = new Category(idField3, categoryNameField2);
            CategoryDAO.update(category);
            categoryUpdateSuccessful.setText("Pavyko paredaguoti įrašą");
        }

    }

    @FXML
    public void onCreateCategoryButtonClick() {
        String categoryNameField2 = categoryNameField.getText();
        String categories = "";
        if (!Validation.isValidTitle(categoryNameField2)) {
            categoryUpdateFailed.setText("Neteisingai įvedėt pavadinimą");
        } else {
            Category category = new Category(categoryNameField2);
            CategoryDAO.create(category);
            categoryUpdateSuccessful.setText("Sėkmingai sukurtas naujas įrašas.");
        }
    }

    @FXML
    public void onDeleteCategoryButtonClick() {
        String categoryIdField2 = categoryIdField.getText();
        if (!Validation.isValidId(categoryIdField2)) {
            categoryUpdateSuccessful.setText("");
            categoryUpdateFailed.setText("Neteisingai įvestas ID");
        } else {
            int idField3 = Integer.parseInt(categoryIdField.getText());
            CategoryDAO.deleteById(idField3);
            categoryUpdateFailed.setText("");
            categoryUpdateSuccessful.setText("Pavyko sėkmingai ištrinti įrašą");
        }
    }

    @FXML
    public void onSearchBookButtonClick() {
        list2.clear();
        String bookNameField2 = bookNameField.getText();

        List<Book> bookList = BookDAO.searchByName(bookNameField2);

        for (Book book : bookList) {
            list2.add(new Book(book.getId(), book.getName(), book.getSummary(), book.getISBN(), book.getBookPages(), book.getCategory(), book.isInUse()));
            bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            bookSummaryColumn.setCellValueFactory(new PropertyValueFactory<>("summary"));
            bookISBNColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            bookPagesColumn.setCellValueFactory(new PropertyValueFactory<>("bookPages"));
            bookCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
            bookIsInUseColumn.setCellValueFactory(new PropertyValueFactory<>("inUse"));

            booksTableView.setItems(list2);
        }
        if (bookList.isEmpty()) {
            bookUpdateSuccessful.setText("");
            bookUpdateFailed.setText("Nepavyko atlikti paieškos");
        } else {
            bookUpdateFailed.setText("");
            bookUpdateSuccessful.setText("Pavyko atlikti paiešką.");
        }
    }

    @FXML
    public void onUpdateBookButtonClick() {
        String bookIdField2 = bookIdField.getText();
        String bookNameField2 = bookNameField.getText();
        String bookISBNField2 = bookISBNField.getText();
        String bookSummaryArea2 = bookSummaryArea.getText();
        String bookPagesField2 = bookPagesField.getText();

        String category = "";
        if (!choiceCategory.getSelectionModel().isEmpty()) {
            category = choiceCategory.getSelectionModel().getSelectedItem().toString();
        }

        if (!Validation.isValidId(bookIdField2)) {
            bookUpdateSuccessful.setText("");
            bookUpdateFailed.setText("Neteisingai įvestas ID");
        } else if (!Validation.isValidTitle(bookNameField2)) {
            bookUpdateSuccessful.setText("");
            bookUpdateFailed.setText("Neteisingai įvestas pavadinimas");
        } else if (!Validation.isValidId(bookPagesField2)) {
            bookUpdateSuccessful.setText("");
            bookUpdateFailed.setText("Neteisingai įvestas psl skaičius");
        } else if (!Validation.isValidISBN(bookISBNField2)) {
            bookUpdateSuccessful.setText("");
            bookUpdateFailed.setText("Neteisingai įvestas ISBN");
        } else {
            int bookPagesField3 = Integer.parseInt(bookPagesField.getText());
            int bookIdField3 = Integer.parseInt(bookIdField.getText());

            Book book = new Book(bookIdField3, bookNameField2, bookSummaryArea2, bookISBNField2, bookPagesField3, category);
            BookDAO.update(book);
            bookUpdateFailed.setText("");
            bookUpdateSuccessful.setText("Pavyko atnaujinti įrašą");
        }
    }

    @FXML
    public void onCreateBookButtonClick() {
        String bookIdField2 = bookIdField.getText();
        String bookNameField2 = bookNameField.getText();
        String bookISBNField2 = bookISBNField.getText();
        String bookSummaryArea2 = bookSummaryArea.getText();
        String bookPagesField2 = bookPagesField.getText();

        String category = "";
        if (!choiceCategory.getSelectionModel().isEmpty()) {
            category = choiceCategory.getSelectionModel().getSelectedItem().toString();
        }

        if (!Validation.isValidTitle(bookNameField2)) {
            bookUpdateSuccessful.setText("");
            bookUpdateFailed.setText("Neteisingai įvestas pavadinimas");
        } else if (!Validation.isValidId(bookPagesField2)) {
            bookUpdateSuccessful.setText("");
            bookUpdateFailed.setText("Neteisingai įvestas psl skaičius");
        } else if (!Validation.isValidISBN(bookISBNField2)) {
            bookUpdateSuccessful.setText("");
            bookUpdateFailed.setText("Neteisingai įvestas ISBN");
        } else {
            int bookPagesField3 = Integer.parseInt(bookPagesField.getText());

            Book book = new Book(bookNameField2, bookSummaryArea2, bookISBNField2, bookPagesField3, category);
            BookDAO.create(book);
            bookUpdateFailed.setText("");
            bookUpdateSuccessful.setText("Pavyko pridėti knygą");
        }
    }

    @FXML
    public void onDeleteBookButtonClick() {
        String bookIdField2 = bookIdField.getText();
        if (!Validation.isValidId(bookIdField2)) {
            bookUpdateFailed.setText("Neteisingai įvestas ID");
        } else {
            int bookIdField3 = Integer.parseInt(bookIdField.getText());
            BookDAO.deleteById(bookIdField3);
            bookUpdateSuccessful.setText("Pavyko sėkmingai ištrinti įrašą");
        }
    }

    @FXML
    public void onLogOutButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("login-view.fxml"));
        Stage LoginStage = new Stage();
        LoginStage.setTitle("Prisijungimo langas");
        LoginStage.setScene(new Scene(root, 600, 400));
        LoginStage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceCategory.setItems(FXCollections.observableList(CategoryDAO.fullCategoryList()));

        String username = UserSingleton.getInstance().getUserName();
        usernameLabel.setText(username);

        String role = UserDAO.searchByUsername(username);
        groupLabel.setText(role);

    }
}



