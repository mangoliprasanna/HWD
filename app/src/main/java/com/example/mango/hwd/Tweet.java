package com.example.mango.hwd;

/**
 * Created by mango on 09-03-2018.
 */

public class Tweet {
    private String name = "";
    private String location = "";
    private String profile = "";
    private String screenName = "";
    private String desc = "";
    private String media = "";
    private String link = "";
    private int likes = 0;

    public Tweet(String _name, String _location, String _profile, String _screenName,
                 String _desc, int _likes, String _media)
    {
        name = _name;
        location = _location;
        profile = _profile;
        screenName = _screenName;
        desc = _desc;
        likes = _likes;
        media = _media;
    }

    public String getName() { return name; }
    public String getLocation() { return  location; }
    public String getProfile() {
        return profile;
    }
    public  String getScreenName() {
        return screenName;
    }
    public int getLikes()
    {
        return  likes;
    }
    public String getDesc() { return  desc; }
    public String getMedia() { return  media; }
    public void setLink(String _link) { link = _link; }
    public String getLink() { return link; }
}
