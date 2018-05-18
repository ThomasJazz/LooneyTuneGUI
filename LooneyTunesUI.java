package ChallengingProject;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


// ORIGINAL CODE WAS TAKEN FROM:
// https://www.youtube.com/watch?v=Uj8rPV6JbCE

public class LooneyTunesUI extends Application {

    private boolean playable = true;
    private boolean turnX = true;
    private Tile[][] gameBoard = new Tile[5][5];
//    private List<Combo> combos = new ArrayList<>();

    private Pane root = new Pane();
    
    // The Board Frame.
    private Parent createContent() {
        root.setPrefSize(600, 600);

        for (int rows = 0; rows < 6; rows++) {
            for (int columns = 0; columns < 6; columns++) {
                if (rows != 5 && columns != 5)
                {
                    Tile tile = new Tile();
                    tile.setTranslateX(columns * 100); // This should be the same as tile size.
                    tile.setTranslateY(rows * 100);
                    root.getChildren().add(tile);
                    gameBoard[columns][rows] = tile;
                }
                
                else
                {
                    StaticTile sTile;
                    if (rows == 5)
                        sTile = new StaticTile("" + columns + "");
                    else
                        sTile = new StaticTile("" + rows + "");
                    sTile.setTranslateX(columns * 100);
                    sTile.setTranslateY(rows * 100);
                    root.getChildren().add(sTile);
                }
            }
        }

        // ************* THIS IS FOR TIC TAC TOE PART - NOT NEEDED!!!! *************
//        // horizontal
//        for (int y = 0; y < 6; y++) {
//            combos.add(new Combo(gameBoard[0][y], gameBoard[1][y], gameBoard[2][y], gameBoard[3][y], gameBoard[4][y], gameBoard[5][y]));
//        }
//
//        // vertical
//        for (int x = 0; x < 6; x++) {
//            combos.add(new Combo(gameBoard[x][0], gameBoard[x][1], gameBoard[x][2], gameBoard[x][3], gameBoard[x][4], gameBoard[x][5]));
//        }
//
//        // diagonals
//        combos.add(new Combo(gameBoard[0][0], gameBoard[1][1], gameBoard[2][2]));
//        combos.add(new Combo(gameBoard[2][0], gameBoard[1][1], gameBoard[0][2]));

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

//    private void checkState() {
//        for (Combo combo : combos) {
//            if (combo.isComplete()) {
//                playable = false;
//                playWinAnimation(combo);
//                break;
//            }
//        }
//    }
    
//    // Draws line throught the center.
//    private void playWinAnimation(Combo combo) {
//        Line line = new Line();
//        line.setStartX(combo.tiles[0].getCenterX());
//        line.setStartY(combo.tiles[0].getCenterY());
//        line.setEndX(combo.tiles[0].getCenterX());
//        line.setEndY(combo.tiles[0].getCenterY());
//
//        root.getChildren().add(line);
//
//        Timeline timeline = new Timeline();
//        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
//                new KeyValue(line.endXProperty(), combo.tiles[2].getCenterX()),
//                new KeyValue(line.endYProperty(), combo.tiles[2].getCenterY())));
//        timeline.play();
//    }

//    private class Combo {
//
//        private Tile[] tiles;
//
//        public Combo(Tile... tiles) {
//            this.tiles = tiles;
//        }
//
//        public boolean isComplete() {
//            if (tiles[0].getValue().isEmpty()) {
//                return false;
//            }
//
//            return tiles[0].getValue().equals(tiles[1].getValue())
//                    && tiles[0].getValue().equals(tiles[2].getValue());
//        }
//    }

    private class Tile extends StackPane {

        private Text text = new Text();

        public Tile() {
            Rectangle border = new Rectangle(100, 100);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setFont(Font.font(72));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            setOnMouseClicked(event -> {
                if (!playable) {
                    return;
                }

                if (event.getButton() == MouseButton.PRIMARY) {
                    if (!turnX) {
                        return;
                    }

                    drawX();
                    turnX = false;
//                    checkState();
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    if (turnX) {
                        return;
                    }

                    drawO();
                    turnX = true;
//                    checkState();
                }
            });
        }
        
//        public double getCenterX() {
//            return getTranslateX() + 100;
//        }
//
//        public double getCenterY() {
//            return getTranslateY() + 100;
//        }

        public String getValue() {
            return text.getText();
        }

        private void drawX() {
            text.setText("X");
        }

        private void drawO() {
            text.setText("O");
        }
    }
    
    private class StaticTile extends StackPane {

        private Text text = new Text();

        public StaticTile(String tileValue) {
            Rectangle border = new Rectangle(100, 100);
            border.setFill(Color.GRAY);
            border.setStroke(Color.BLACK);

            text.setFont(Font.font(72));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);
            
            draw(tileValue);
        }
        
        private void draw(String str) {
            text.setText(str);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
