package Playground;

/**
 * Created by uadmin on 03.05.2017.
 */

public class Installation {

    private String description;
    private String state;

    public Installation(String description,String state){

        this.description=description;
        this.state = state;
    }

    public String getDescripition(){
        return this.description;
    }
}
