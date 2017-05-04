package Playground;

import android.media.Image;

import DB.FeedReaderContract;


public class Playground {

    private String town;
    private String name;
    private double surface ;
    private String id;


    public Playground (String name){
        this.name = name;
    }

    public Playground(String id,String name)
    {
        this.id=id;
        this.name=name;
    }

    public Playground (String id, String town, String name, double surface){
        this.town=town;
        this.name = name;
        this.surface=surface;
    }

    public String getPlayGroundName(){
        return this.name;
    }
    public String getId()
    {
        return this.id;
    }
}
