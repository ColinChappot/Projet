package Worker;


public class Worker {

    /*
    Déclaration des variables
     */
    private String login;
    private String password;
    private String  firstname;
    private String  lastname;
    private String phone;
    private String idWorker;


    /*
    Constructeur
     */
    public Worker (String idWorker,String firstname, String lastname){
        this.firstname=firstname;
        this.lastname=lastname;
        this.idWorker=idWorker;
    }

    /*
    Constructeur
     */
    public Worker ( String login, String password, String firstname, String lastname, String phone){
        this.login = login;
        this.password=password;
        this.firstname=firstname;
        this.lastname=lastname;
        this.phone =phone;
    }

    /*
     Méthodes getName retourne nom prénom pour l'afficher dans la listView
    */
    public String getName(){
        return firstname + " " + lastname ;
    }
}
