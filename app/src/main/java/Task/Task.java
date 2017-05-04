package Task;

/**
 * Created by uadmin on 01.05.2017.
 */

public class Task {
    /*
    Declaration variables
     */

    private int idPlayground ;
    private String idWorker;
    private int idGravity;
    private String description;
    private String observation;
    private int idState;
    private String date;
    private String idTask;

    /*
    Constructor
     */
    public Task(String description){
        this.description = description;
    }

    //c est pour créer les infos pour une lastTask
    public Task ( String description,  String date){
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
