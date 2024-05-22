package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * JavaFX App
 */
public class App extends Application {

    private static final int TILE_SIZE = 80;
    private static final Color LIGHT_COLOR = Color.BEIGE;
    private static final Color DARK_COLOR = Color.DARKGRAY;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setAlignment(Pos.CENTER);

        Board board = Board.instance();
        for (Ranks rank : Ranks.values()) {
            for (Files file : Files.values()) {
                int row = rank.ordinal();
                int col = file.ordinal();
                Square square = board.getSquare(rank, file);

                Rectangle rectangle = new Rectangle(TILE_SIZE, TILE_SIZE);
                System.out.println(row + " " + col + square.getSquareColour().toString());
                if (square.getSquareColour() == SquareColour.BLACK) {
                    rectangle.setFill(DARK_COLOR);
                } else {
                    rectangle.setFill(LIGHT_COLOR);
                }

                if (square.piecePresent()) {
                    Piece piece = square.getPiece();
                    ImageView pieceImageView = new ImageView(getPieceImage(piece));
                    pieceImageView.setFitHeight(TILE_SIZE);
                    pieceImageView.setFitWidth(TILE_SIZE);
                    pieceImageView.toFront();

                    Button pieceButton = new Button();
                    pieceButton.setGraphic(pieceImageView);
                    pieceButton.setMinSize(TILE_SIZE, TILE_SIZE);
                    pieceButton.setMaxSize(TILE_SIZE, TILE_SIZE);
                    gridPane.add(pieceButton, col, row);
                } else {
                    gridPane.add(rectangle, col, row);
                }
            }
        }

        scene = new Scene(gridPane);
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private Image getPieceImage(Piece piece) {
        String color = piece.getColour() == PieceColour.WHITE ? "white" : "black";
        String type = piece.getPieceType().toString().toLowerCase();
        String imagePath = String.format("/images/%s_%s.png", color, type);
        return new Image(getClass().getResourceAsStream(imagePath));
    }

    public static void main(String[] args) {
        launch(args);
    }

}