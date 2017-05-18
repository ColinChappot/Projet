package cloud;

import java.util.ArrayList;

/**
 * Created by Colin on 17.05.2017.
 */

//permet de faire toutner la méthode d'update le temps voulu
public class EntityDB {

    private boolean installationUpdated = false;
    private boolean taskUpdated = false;
    private boolean workerUpdated = false;
    private boolean materielUpdated = false;
    private boolean playgroundUpdated = false;
    private boolean stateUpdated = false;
    private boolean installationPlacedUpdated = false;
    private boolean materialNeededUpdated = false;


    //controle les boolean concernant les tables qui sont liées au cloud
    public static ArrayList<Boolean> loadingDone = new ArrayList<Boolean>();


    public EntityDB(){
        loadingDone.add(installationUpdated);
        loadingDone.add(taskUpdated);
        loadingDone.add(workerUpdated);
        loadingDone.add(materielUpdated);
        loadingDone.add(playgroundUpdated);
        loadingDone.add(stateUpdated);
        loadingDone.add(installationPlacedUpdated);
        loadingDone.add(materialNeededUpdated);
    }


    /*
    Getter Setter
     */

    public static void setInstallationUpdated(){
        loadingDone.set(0,true);
    }

    public static void setTaskUpdated(){
        loadingDone.set(1,true);
    }

    public static void setWorkerUpdated(){
        loadingDone.set(2,true);
    }

    public static void setMaterielUpdated(){
        loadingDone.set(3,true);
    }

    public static void setPlaygroundUpdated(){
        loadingDone.set(4,true);
    }

    public static void setStateUpdated(){
        loadingDone.set(5,true);
    }

    public static void setInstallationPlacedUpdated(){
        loadingDone.set(6,true);
    }

    public static void setMaterialNeededUpdated (){
        loadingDone.set(7,true);
    }

}
