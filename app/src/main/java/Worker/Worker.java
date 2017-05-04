package Worker;

import android.media.Image;

/**
 * Created by uadmin on 30.04.2017.
 */

public class Worker {

    private String login;
    private String password;
    private String  firstname;
    private String  lastname;
    private String phone;
    private String idWorker;



    public Worker (String idWorker,String firstname, String lastname){
        this.firstname=firstname;
        this.lastname=lastname;
    }


    public Worker ( String login, String password, String firstname, String lastname, String phone){
        this.login = login;
        this.password=password;
        this.firstname=firstname;
        this.lastname=lastname;
        this.phone =phone;
    }

    public String getName(){
        return firstname + " " + lastname ;
    }
    public String getID(){return idWorker;}
}
