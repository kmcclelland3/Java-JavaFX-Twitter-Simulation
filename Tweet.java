import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * This class is the Tweet class for Twitter Homework.
 * @author Megan Yang
 * @version 1.0
 **/
public class Tweet extends GridPane implements Comparable<Tweet> {

    private String dateAndTime;
    private String message;
    private User user;
    private boolean likes;

    /**
     * Constructs a Tweet by taking in a User and a String
     * representation of a message
     * @param user a User
     * @param message a String representation of a message
     **/
    public Tweet(User user, String message) {
        this.user = user;
        this.message = message;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateAndTime = dateFormat.format(date);
        likes = false;

        this.setHgap(10);
        this.setVgap(2);
        this.setPadding(new Insets(0, 10, 0, 10));
        this.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        Text tempuser = new Text(user.getName());
        tempuser.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.add(tempuser, 1, 0);

        Text handledate = new Text(user.getTwitterHandle() + " "
            + getDateAndTime());
        handledate.setFont(Font.font("Arial", FontWeight.LIGHT, 10));
        this.add(handledate, 2, 0);

        Text text = new Text(message);
        text.setFont(Font.font("Arial", FontWeight.NORMAL, 11));
        this.add(text, 1, 1, 2, 1);

        ImageView tempim = new ImageView();
        tempim.setImage(new Image(user.getImage()));
        tempim.setFitWidth(50);
        tempim.setPreserveRatio(true);
        tempim.setSmooth(true);
        tempim.setCache(true);
        this.add(tempim, 0, 0, 1, 2);
    }

    /**
     * Getter for message
     * @return the message of the Tweet
     **/
    public String getMessage() {
        return message;
    }

    /**
     * Getter for Date and Time
     * @return the String representation of date and time
     **/
    public String getDateAndTime() {
        return dateAndTime;
    }

    /**
     * Getter for User
     * @return the User
     **/
    public User getUser() {
        return user;
    }

    /**
     * mark the Tweet as "liked"
     **/
    public void likeTweet() {
        likes = true;
    }

    /**
     * mark the Tweet as "unliked"
     **/
    public void unlikeTweet() {
        likes = false;
    }

    /**
     * Compares this Tweet to another Tweet by the date and time
     * @param other another Tweet
     * @return an int value that tells you whether this Tweet is greater,
     * less than or equal to another tweet.
     **/
    public int compareTo(Tweet other) {
        return dateAndTime.compareTo(other.dateAndTime);
    }
}