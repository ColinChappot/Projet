package Task;

/**
 * Created by uadmin on 01.05.2017.
 */

public class Task {
    /*
    Declaration variables
     */


    private String description;

    private String date;

    /*
    Constructor
     */
    public Task(String description){
        this.description = description;
    }

    /*
    Constructor
     */
    public Task ( String description, String date){
        this.description=description;
        this.date=date;
    }

    public String getTaskDescription(){
        return  description;
    }

    public String getLastTask(){
        return this.description + " - " + this.date;
    }
}
