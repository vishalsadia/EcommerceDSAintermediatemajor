package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ProductList {
    private static TableView<Product> productTable;

    public static VBox createTable(ObservableList<Product> data) {
        //columns
        TableColumn id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn<>("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("PRICE");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Dummy data


        productTable = new TableView<>();
        productTable.getColumns().addAll(id, name, price);
        productTable.setItems(data);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(productTable);
        return vbox;
    }

        public static VBox getDummyTable(){
            ObservableList<Product> data = FXCollections.observableArrayList();
            data.add(new Product(2,"Iphone",4456));
            data.add(new Product(5,"laptop",6684));
            return createTable(data);

        }


    public static VBox getAllProducts(){
        ObservableList<Product> data = Product.getAllProducts();
        return createTable(data);
    }
    public Product getSelectedProduct(){
       return productTable.getSelectionModel().getSelectedItem();
    }
    public VBox getProductsInCart(ObservableList<Product> data){
        return createTable(data);
    }
}
