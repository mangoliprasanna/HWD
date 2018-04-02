package com.example.mango.hwd;

import android.content.Context;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mango on 09-03-2018.
 */

public class TweetAdapter extends ArrayAdapter<Tweet> {
    public TweetAdapter(Context context, int resource, List<Tweet> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {

        View listView = convertView;
        if(listView == null)
        {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.tweet_layout, parent, false);
        }
        Tweet curruntTweet = getItem(position);

        //Setting name of user who tweeted
        TextView name = (TextView) listView.findViewById(R.id.name);
        name.setText(curruntTweet.getName());

        //User @ Screenname @mangoli
        TextView screenName =  (TextView) listView.findViewById(R.id.screenName);
        screenName.setText(curruntTweet.getScreenName());

        //Tweet likes
        TextView likes = (TextView) listView.findViewById(R.id.likes);
        likes.setText(String.valueOf(curruntTweet.getLikes()));

        //Tweet Content
        TextView content = (TextView) listView.findViewById(R.id.content);
        String tweetContent = curruntTweet.getDesc();
        //Highlighting the words starting with # (hashtag)
        Pattern pattern = Pattern.compile("#\\w+");
        Matcher matcher = pattern.matcher(tweetContent);
        while (matcher.find())
        {
            tweetContent = tweetContent.replace(matcher.group(), "<font color='#c40000'>"+ matcher.group()+"</font>");
        }
        //Highlighting links
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(tweetContent);
        while (matcher.find())
        {
            String foundLink = tweetContent.substring(matcher.start(0),
                    matcher.end(0));
            curruntTweet.setLink(foundLink);
            tweetContent = tweetContent.replace(foundLink, "");
        }
        content.setText(Html.fromHtml(tweetContent));

        //Tweet location
        TextView location = (TextView) listView.findViewById(R.id.location);
        location.setText(curruntTweet.getLocation());

        //User profile image
        ImageView profile = (ImageView) listView.findViewById(R.id.profile);
        Picasso.with(getContext())
                .load(curruntTweet.getProfile())
                .into(profile);

        //Log.w("Link", curruntTweet.getLink());
        return listView;
    }
}
