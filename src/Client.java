// Classe représentant un client
public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String courriel;
    private String numTel;
    private Adresse adresse;
    

    public Client(int id, String nom, String prenom, String courriel, String numTel, Adresse adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.courriel = courriel;
        this.numTel = numTel;
        this.adresse = adresse;
    }

    public int getId() { 
        return id; 
    }

    public String getNom() { 
        return nom; 
    }

    public String getPrenom() { 
        return prenom; 
    }

    public String getCourriel() { 
        return courriel; 
    }

    public String getNumTel() { 
        return numTel; 
    }

    public Adresse getAdresse() {
        return adresse;
    }

    @Override 
    public String toString() {
        return "Client " + id + " :\n" +
                " nom: " + nom + "\n" +
                " prenom: " + prenom + "\n" +
                " courriel: " + courriel + "\n" +
                " numTel: " + numTel + "\n" +
                " adresse: " + adresse + "\n";
    }
}