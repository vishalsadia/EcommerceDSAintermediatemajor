package com.example.ecommerce;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

class userInterface {

    GridPane loginPage;
    HBox headerBar;

    HBox footerBar;

    ProductList productList = new ProductList();

    Customer loggedInCustomer;

    VBox body;

    VBox homePage;
    VBox productPage;

    Button signInButton;

    Label welcomeLabel;

    Button placeOrderButton = new Button("Place Order");

    ObservableList<Product> itemsInCart = FXCollections.observableArrayList();

    //Customer loggedInCustomer;
    BorderPane createContent()
    {
        BorderPane root = new BorderPane();
        root.setTop(headerBar);
        body = new VBox();
        body.setPadding(new Insets(10));
        body.setAlignment(Pos.CENTER);
        root.setCenter(body);
        productPage = ProductList.getAllProducts();
        body.getChildren().add(productPage);
        root.setBottom(footerBar);
        root.setPrefSize(800,600);
        return root;
    }
    public userInterface()
    {
        createLoginPage();
        createHeaderBar();
        createFooterBar();
    }
    private void createLoginPage()
    {
        Text userNameText = new Text("User Name");
        Text passwordText = new Text("password");

        TextField userName = new TextField();
        userName.setPromptText("Enter Your User Name here");
        PasswordField password = new PasswordField();
        password.setPromptText("Enter You Password here");
        Button loginbutton = new Button("Login");
        Label messageLabel = new Label("");

        loginPage = new GridPane();
        loginPage.setAlignment(Pos.CENTER);
        loginPage.setVgap(20);
        loginPage.setHgap(20);
        loginPage.setPrefSize(150,200);
        loginPage.setStyle("-fx-background-color: #CCFF99");
        loginPage.add(userNameText,0,0);
        loginPage.add(userName,1,0);
        loginPage.add(passwordText,0,1);
        loginPage.add(password,1,1);
        loginPage.add(loginbutton,1,2);
        loginPage.add(messageLabel,1,3);

        loginbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = userName.getText();
                String pass = password.getText();
                Login login = new Login();
                welcomeLabel = new Label();

                //System.out.println("button clicked");
                loggedInCustomer = login.customerLogin(name,pass);
                //System.out.println(loggedInCustomer.getName() + " " +  loggedInCustomer.getEmail());
                if(loggedInCustomer!=null)
                {
                    //System.out.println("I m inside the condition");
                    messageLabel.setText("Welcome : " + loggedInCustomer.getName());
                    welcomeLabel.setText("Welcome - " + loggedInCustomer.getName());
                    headerBar.getChildren().add(welcomeLabel);
                    body.getChildren().clear();
                    body.getChildren().add(productPage);
                }
                else {
                    messageLabel.setText("Logged in failed! Please provide right Credentails");
                }
            }
        });
    }


    private void createHeaderBar(){
        Button homeButton = new Button();
        Image image = new Image("C:\\Users\\vishu\\IdeaProjects\\snakeladder\\src\\img.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(40);
        homeButton.setGraphic(imageView);

        TextField searchBar = new TextField();
        searchBar.setPromptText("search here");
        searchBar.setPrefWidth(220);
        Button searchButton = new Button("search");
        signInButton = new Button("Sign In");
        Button cartButton = new Button("Cart");
        Button orderButton = new Button("Order");
        headerBar = new HBox(20);
        headerBar.setPadding(new Insets(10));
        headerBar.setStyle("-fx-background-color: #CCFF99");
        headerBar.setAlignment(Pos.CENTER);
        headerBar.getChildren().addAll(homeButton,searchBar,searchButton,signInButton,cartButton,orderButton);

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(loginPage);
                headerBar.getChildren().remove(signInButton);
            }
        });

        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                VBox prodPage = productList.getProductsInCart(itemsInCart);
                prodPage.setAlignment(Pos.CENTER);
                prodPage.setSpacing(10);
                prodPage.getChildren().add(placeOrderButton);
                body.getChildren().add(prodPage);
                footerBar.setVisible(false);
            }
        });
        placeOrderButton.setAlignment(Pos.CENTER);

        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(itemsInCart==null)
                {
                    //please select a product to place a order
                    showDialog("please add atleast one product in the cart to place a order");
                    return;
                }
                if(loggedInCustomer==null)
                {
                    showDialog("Please log in to place order");
                    return;
                }
                int count = order.placeMultipleOrder(loggedInCustomer,itemsInCart);
                if(count!=0)
                {
                    showDialog("Order for "+ count +" Products placed Sucessfully");
                }
                else {
                    showDialog("Order Failed!!");
                }
            }
        });

        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                Image image = new Image("C:\\Users\\vishu\\IdeaProjects\\snakeladder\\src\\img.png");
                ImageView imageView = new ImageView();
                imageView.setImage(image);
                imageView.setFitHeight(400);
                imageView.setFitWidth(400);
                homePage = new VBox(imageView);
                homePage.setAlignment(Pos.CENTER);
                homePage.setSpacing(10);
                homePage.getChildren().add(placeOrderButton);
                body.getChildren().add(homePage);
                footerBar.setVisible(true);
                placeOrderButton.setVisible(false);
                if(loggedInCustomer==null)
                {
                    System.out.println(headerBar.getChildren().indexOf(signInButton));
                    if(!headerBar.getChildren().contains(signInButton))
                    {
                        headerBar.getChildren().add(signInButton);
                    }
                }
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                productPage = ProductList.getAllProducts();
                body.getChildren().add(productPage);
                body.getChildren().add(footerBar);
            }
        });
    }

    private void createFooterBar(){
        Button buyNowButton = new Button("Buy Now");
        Button AddToCartButton = new Button("Add to Cart");

        footerBar = new HBox(20);
        footerBar.setPadding(new Insets(10));
        footerBar.setAlignment(Pos.CENTER);
        footerBar.setStyle("-fx-background-color: #CCFF99");
        footerBar.getChildren().addAll(buyNowButton,AddToCartButton);
        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                if(product==null)
                {
                    //please select a product to place a order
                    showDialog("please select a product to place a order");
                    return;
                }
                if(loggedInCustomer==null)
                {
                    showDialog("Please log in to place order");
                    return;
                }
                boolean status = order.placeOrder(loggedInCustomer,product);
                if(status==true)
                {
                    showDialog("Order placed Sucessfully");
                }
                else {
                    showDialog("Order Failed!!");
                }
            }
        });
        AddToCartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                if(product==null)
                {
                    //please select a product to place a order
                    showDialog("please select a product to add it to a cart");
                    return;
                }
                itemsInCart.add(product);
                showDialog("selected item has been added to the cart sucessfully!!");
            }
        });

    }
    private void showDialog(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setTitle("Message");
        alert.showAndWait();
    }
}