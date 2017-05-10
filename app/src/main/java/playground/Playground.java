package playground;

public class Playground {

    /*
   Declaration des variables
    */
    private String town;
    private String name;
    private double surface ;
    private String id;


    /*
    Constructeur
     */
    public Playground(String id,String name)
    {
        this.id=id;
        this.name=name;
    }


    /*
    Méthode getPlayGroundname retourne le nom du parc
     */
    public String getPlayGroundName(){
        return this.name;
    }

    /*
     Méthode getId retourne l'id du parc
      */
    public String getId()
    {
        return this.id;
    }
}
