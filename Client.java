public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String courriel;
    private String numTel;
    

    public Client(int id, String nom, String prenom, String courriel, String numTel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.courriel = courriel;
        this.numTel = numTel;
    }

    @Override 
    public String toString() {
        return "Client:\n" +
                " id: " + id + "\n" +
                " nom: " + nom + "\n" +
                " prenom: " + prenom + "\n" +
                " courriel: " + courriel + "\n" +
                " numTel: " + numTel;
    }
}