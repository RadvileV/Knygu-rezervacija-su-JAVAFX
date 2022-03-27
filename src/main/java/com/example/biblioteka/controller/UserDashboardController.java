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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserDashboardController implements Initializable {
    @FXML
    private Label usernameLabel, groupLabel, bookSearchFailed, bookSearchSuccessful, saveBookFailed, saveBookSuccessful;
    @FXML
    private TableColumn bookIdColumn, bookNameColumn, bookISBNColumn, bookPagesColumn,
            bookCategoryColumn, bookSummaryColumn, bookIsInUseColumn;
    @FXML
    private TableView booksTableView;
    @FXML
    private TextField bookNameField, bookIdField;

    ObservableList<Book> list = FXCollections.observableArrayList();

    @FXML
    public void onLogOutButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainApplication.class.getResource("login-view.fxml"));
        Stage LoginStage = new Stage();
        LoginStage.setTitle("Prisijungimo langas");
        LoginStage.setScene(new Scene(root, 600, 400));
        LoginStage.show();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    public void onSearchBookButtonClick() {
        list.clear();
        String bookNameField2 = bookNameField.getText();

        List<Book> bookList = BookDAO.searchByName(bookNameField2);

        for (Book book : bookList) {
            list.add(new Book(book.getId(), book.getName(), book.getSummary(), book.getISBN(), book.getBookPages(), book.getCategory(), book.isInUse()));
            bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            bookSummaryColumn.setCellValueFactory(new PropertyValueFactory<>("summary"));
            bookISBNColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
            bookPagesColumn.setCellValueFactory(new PropertyValueFactory<>("bookPages"));
            bookCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
            bookIsInUseColumn.setCellValueFactory(new PropertyValueFactory<>("inUse"));
            booksTableView.setItems(list);


        }
        if (bookList.isEmpty()) {
            bookSearchSuccessful.setText("");
            bookSearchFailed.setText("Nepavyko atlikti paieškos");
        } else {
            bookSearchFailed.setText("");
            bookSearchSuccessful.setText("Pavyko atlikti paiešką.");
        }
    }

    @FXML
    public void onAddFavouriteButtonClick() {
        String bookIdField2 = bookIdField.getText();

        if(!Validation.isValidId(bookIdField2)) {
            saveBookSuccessful.setText("");
            saveBookFailed.setText("Neteisingai įvestas ID");
        } else {
            int bookIdField3 = Integer.parseInt(bookIdField.getText());

            Favourite favourite = new Favourite(bookIdField3);
            FavouriteDAO.create(favourite);
            saveBookFailed.setText("");
            saveBookSuccessful.setText("Knyga pridėta prie mėgstamiausių sąrašo");
        }
    }

    @FXML
    public void onReserveButtonClick() {
        String bookIdField2 = bookIdField.getText();
        int bookIdField3 = Integer.parseInt(bookIdField.getText());
        if (!Validation.isValidId(bookIdField2)) {
            saveBookSuccessful.setText("");
            saveBookFailed.setText("Neteisingai įvestas ID");
        } else if (Validation.isValidId(bookIdField2) && BookDAO.bookIsReserved(bookIdField3)) {
            saveBookSuccessful.setText("");
            saveBookFailed.setText("Knyga jau rezervuota");
        } else {
            BookDAO.reserveBook(bookIdField3);
            saveBookFailed.setText("");
            saveBookSuccessful.setText("Pavyko sėkmingai rezervuoti knygą");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String username = UserSingleton.getInstance().getUserName();
        usernameLabel.setText(username);

        String role = UserDAO.searchByUsername(username);
        groupLabel.setText(role);
    }
}

