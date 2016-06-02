import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
/**
 * This class is the Server class for Twitter Homework. The Server class
 * includes a method that will generating a random Tweet from getting a
 * random User and a random message body. Feel free to change each User's
 * name, twitterhandle, imagefile name, and description. In addtition, feel
 * free to change any messages.
 * @author Megan Yang
 * @version 1.0
 **/
public class TwitterServer {
    private User a = new User("Taylor", "@headTAtheboss", "squirrel.jpg",
        "something");
    private User b = new User("Julia", "@bentobusdevotee", "dog.jpg",
        "something");
    private User c = new User("Yash", "@therealyash", "hedgehog.jpg",
        "something");
    private User d = new User("Nick", "@fistbumper", "pig.jpg", "something");
    private User e = new User("Sylvia", "@foodlover", "puma.jpg", "something");
    private User f = new User("Thomas", "@cooldude", "owl.jpg", "something");
    private User g = new User("Angie", "@sunshine", "rabbit.jpg", "something");
    private User h = new User("Shashank", "@swagilicious", "sebra.jpg",
        "something");
    private User i = new User("Luke", "@vimexpert", "chip.jpg", "something");

    private ArrayList<User> allUsers = new ArrayList<>(
        Arrays.asList(a, b, c, d, e, f, g, h, i)
    );

    private String m1 = "CS1331 is da bomb";
    private String m2 = "What should I get for lunch today? Bento bus!";
    private String m3 = "What should I switch my threads to? Any suggestions?";
    private String m4 = "Eat. Sleep. Code. Repeat";
    private String m5 = "There does not exist 1 day that I do not need coffee";
    private String m6 = "Food is bae, bae is food. Food is love, Food is life";
    private String m7 = "Only 6 weeks left until Summer!!!";
    private String m8 = "All I want to do is sleep and eat";
    private String m9 = "1331 TAs are the coolest of the coolest";

    private ArrayList<String> allmessages = new ArrayList<>(
        Arrays.asList(m1, m2, m3, m4, m5, m6, m7, m8, m9)
    );


    private Random rand = new Random();

    /**
     * Gets a random User from the list of Users.
     * @return a random User
     **/
    public User randUser() {
        int num = rand.nextInt(allUsers.size());
        return allUsers.get(num);
    }

    /**
     * Gets a random message from the list of messages.
     * @return a String representation of a random message.
     **/
    public String randMessage() {
        int num = rand.nextInt(allmessages.size());
        return allmessages.get(num);
    }

    /**
     * Create a random Tweet from a random User and a random message.
     * @return a random Tweet
     **/
    public Tweet randTweet() {
        return new Tweet(randUser(), randMessage());
    }

}