import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.Random;

/**
 * This class creates a Twitter simulation GUI.
 * @author Kristen McClelland
 * @version 1.0
 */
public class Twitter extends Application {
    // private instance data
    private TwitterServer server = new TwitterServer();
    private Random rand = new Random();
    private ObservableList<Tweet> feed = FXCollections.observableArrayList();
    private ListView<Tweet> listView;
    private User me;

    /**
     * The main method is only needed to launch the GUI with
     * an IDE.
     * @param args Input arguments needed to run the GUI with
     * an IDE
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * This method opens the primary window of the GUI.
     * @param primaryStage The first stage/window to open
     */
    public void start(Stage primaryStage) {
        BorderPane outer = new BorderPane();
        HBox title; // = new HBox(10);
        VBox leftOuter; // = new VBox(10);
        HBox tweetBox; // = new HBox(10);
        VBox center = new VBox(10);
        me = new User("Jordan", "@dolphingurl", "dolphin-ride.jpg",
            "I like long walks on the beach. And dolphins, obviously.");

        // title horizontal bar
        title = getTitle();

        // left vertical bar with username info
        leftOuter = getLeftOuter();

        // create a tweet
        tweetBox = getTweetBox();

        // list view tweet feed
        listView = new ListView<Tweet>(feed);
        center.getChildren().addAll(tweetBox, listView);

        // set all of the panes within the outer border pane
        outer.setTop(title);
        outer.setLeft(leftOuter);
        outer.setCenter(center);

        Scene scene = new Scene(outer, 1000, 600);
        primaryStage.setTitle("Twitter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * Creates the Title Pane.
     * @return An HBox containing the necessary panes to make the top
     * of the main BorderPane
     */
    private HBox getTitle() {
        HBox title = new HBox();
        VBox twitterImage = new VBox();
        twitterImage.setAlignment(Pos.CENTER_LEFT);
        VBox refreshBtn = new VBox();
        refreshBtn.setAlignment(Pos.CENTER_RIGHT);
        refreshBtn.setPadding(new Insets(10, 10, 10, 10));
        ImageView header = new ImageView(new Image("twitter.jpg"));
        Button refresh = new Button("Refresh");
        refresh.setOnAction(ae -> {
                updateTwitterFeed();
            }
        );
        twitterImage.getChildren().add(header);
        refreshBtn.getChildren().add(refresh);
        //title.add(header, 0, 0);
        //title.add(refresh, 15, 0);
        title.getChildren().addAll(twitterImage, refreshBtn);
        return title;
    }
    /**
     * Creates the username left side panel.
     * @return A VBox for the left panel of the main Border Pane.
     */
    private VBox getLeftOuter() {
        VBox leftOuter = new VBox(10);
        leftOuter.setAlignment(Pos.TOP_CENTER);
        leftOuter.setPrefWidth(400);
        leftOuter.setPadding(new Insets(10, 10, 10, 10));
        leftOuter.setStyle("-fx-background-color: rgba(135, 206, 250, .78);");
        ImageView profile = new ImageView(new Image(me.getImage()));
        Text name = new Text(me.getName());
        name.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Text username = new Text(me.getTwitterHandle());
        username.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        Text description = new Text(me.getDescription());
        description.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        leftOuter.getChildren().addAll(profile, name, username,
            description);
        return leftOuter;
    }
    /**
     * Creates the tweet making box for the center panel of the Border
     * Pane.
     * @return A HBox that allows the user to create a Tweet.
     */
    private HBox getTweetBox() {
        HBox tweetBox = new HBox(30);
        tweetBox.setPadding(new Insets(10, 10, 10, 10));
        TextArea textTweet = new TextArea("Limit is 140 characters");
        double width = tweetBox.getWidth();
        textTweet.setWrapText(true);
        textTweet.setPrefSize(450, 5);
        Button createTweet = new Button("Tweet");
        createTweet.setOnAction(ae -> {
                String toTweet = textTweet.getText();
                if (toTweet.length() != 0 && toTweet.length() <= 140) {
                    Tweet temp = new Tweet(me, toTweet);
                    feed.add(0, temp);
                    Button delete = new Button("Delete");
                    delete.setOnAction(d -> {
                            Tweet sourceTweet = (Tweet)
                                ((Button) d.getSource()).getParent();
                            feed.removeAll(sourceTweet);
                        }
                    );
                    temp.add(delete, 5, 0);
                }
            }
        );
        tweetBox.getChildren().addAll(textTweet, createTweet);
        return tweetBox;
    }
    /**
     * This function updates the Twitter Feed with a random number
     * of random Tweets.
     */
    private void updateTwitterFeed() {
        int addedTweets = rand.nextInt(3) + 2;
        for (int i = 0; i < addedTweets; i++) {
            Tweet temp = server.randTweet();
            feed.add(0, temp);

            Button likeButton = new Button("Like");
            Button unlikeButton = new Button("Unlike");
            likeButton.setOnAction(el -> {
                    Tweet sourceTweet = (Tweet)
                        ((Button) el.getSource()).getParent();
                    sourceTweet.getChildren().remove(likeButton);
                    sourceTweet.add(unlikeButton, 4, 0);
                    sourceTweet.likeTweet();
                }
            );
            unlikeButton.setOnAction(eu -> {
                    Tweet sourceTweet = (Tweet)
                        ((Button) eu.getSource()).getParent();
                    sourceTweet.getChildren().remove(unlikeButton);
                    sourceTweet.add(likeButton, 4, 0);
                    sourceTweet.unlikeTweet();
                }
            );
            temp.add(likeButton, 4, 0);
        }
    }
}