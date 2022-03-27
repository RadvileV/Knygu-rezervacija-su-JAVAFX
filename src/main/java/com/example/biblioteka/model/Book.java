package com.example.biblioteka.model;

public class Book {

    private int id;
    private String name;
    private String summary;
    private String ISBN;;
    private int bookPages;
    private String category;
    private boolean inUse;

    public Book(){}

    public Book(int id, String name, String summary, String ISBN, int bookPages, String category, boolean inUse) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.ISBN = ISBN;
        this.bookPages = bookPages;
        this.category = category;
        this.inUse = inUse;
    }

    public Book(String name, String summary, String ISBN, int bookPages,String category, boolean inUse){
        this.name = name;
        this.summary = summary;
        this.ISBN = ISBN;
        this.bookPages = bookPages;
        this.category = category;
        this.inUse = inUse;
    }

    public Book(int id, String name, String summary, String ISBN, int bookPages, String category) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.ISBN = ISBN;
        this.bookPages = bookPages;
        this.category = category;
    }
    public Book(String name, String summary, String ISBN, int bookPages,String category){
        this.name = name;
        this.summary = summary;
        this.ISBN = ISBN;
        this.bookPages = bookPages;
        this.category = category;
    }

    public Book(int id) {
        this.id = id;
    }


    public boolean isInUse() {
        return inUse;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getBookPages() {
        return bookPages;
    }

    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setBookPages(int bookPages) {
        this.bookPages = bookPages;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
