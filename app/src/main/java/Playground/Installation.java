package Playground;

/**
 * Created by uadmin on 03.05.2017.
 */

public class Installation {

    /*
    Declaration des variables
     */
    private String description;

    /*
    Constructeur
     */

    public Installation(String description) {
        this.description = description;
    }
    /*
    Méthode getDestcription retourne la description de l'installation
     */
    public String getDescripition(){
        return this.description;
    }
}
