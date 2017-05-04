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

    //pour créer les infos d'une simple tache
    public Task(String description){
        this.description = description;
    }

    //c est pour créer les infos pour une lastTask
    public Task (String idTask, String description, String idWorker, String date){
        this.description=description;
        this.idWorker = idWorker;
        this.date=date;
        this.idTask=idTask;
    }

    /*
    Constructeur de la classe
     */
    public Task(int idPlayground, String idWorker, int idGravity, String description, String observation, int idState, String date ){
        this.idPlayground=idPlayground;
        this.idWorker=idWorker;
        this.idGravity=idGravity;
        this.description=description;
        this.observation=observation;
        this.idState=idState;
        this.date=date;
    }

    public void setTaskDescription(String description){
        this.description = description;
    }

    public String getTaskDescription(){
        return  description;
    }
    public String getTaskObservation(){
        return this.observation;
    }

    public String getTaskDate(){
        return this.date;
    }




    public String getLastTask(){
        return this.description + " - " + this.idWorker + " - " + this.date;
    }
}
