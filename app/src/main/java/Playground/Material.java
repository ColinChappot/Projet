package Playground;

/**
 * Created by uadmin on 04.05.2017.
 */

public class Material {

    /*
     Declaration des variables
     */
    private String materialName;

    /*
    Constructeur
     */
    public Material(String materialName){
        this.materialName=materialName;
    }

    /*
    Méthode getMaterialName returne le nom du matériel
     */
    public String getMaterialName(){
        return this.materialName;
    }
}
