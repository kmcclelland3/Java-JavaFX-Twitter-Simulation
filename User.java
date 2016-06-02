/**
 * This class is the User class for Twitter Homework.
 * @author Megan Yang
 * @version 1.0
 **/
public class User {
    private String name;
    private String twitterHandle;
    private String imagefile;
    private String description;

    /**
     * Constructs a Tweet by taking in a User and a String
     * representation of a message
     * @param name String name
     * @param twitterHandle the User's twitterhandle
     * @param imagefile String name of an image file
     * @param description description of the User
     **/
    public User(String name, String twitterHandle, String imagefile,
        String description) {
        this.name = name;
        this.twitterHandle = twitterHandle;
        this.imagefile = imagefile;
        this.description = description;
    }

    /**
     * Getter for name
     * @return the name of the User
     **/
    public String getName() {
        return name;
    }

    /**
     * Getter for twitterhandle
     * @return the twitterhandle of the User
     **/
    public String getTwitterHandle() {
        return twitterHandle;
    }

    /**
     * Getter for image file
     * @return the image file of the User
     **/
    public String getImage() {
        return imagefile;
    }

    /**
     * Getter for description
     * @return the description of the User
     **/
    public String getDescription() {
        return description;
    }

    /**
     * Checks to see if two Users are equal to one another by
     * their twitterhandle
     * @param other another Object
     * @return true if equal, false otherwise
     **/
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }
        User that = (User) other;
        return this.twitterHandle.equals(that.getTwitterHandle());
    }

    @Override
    public int hashCode() {
        return 0;
    }

}