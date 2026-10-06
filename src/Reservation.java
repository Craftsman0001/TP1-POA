import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Classe représentant une réservation effectuée par un client pour un hébergement
public class Reservation {
    private int numeroReservation;
    private float prix;
    private Date dateArrivee;
    private Date dateDepart;
    private Client client;
    private Hebergement hebergement;
    private Map<Chambres, Integer> chambresReservees;

    public Reservation(int numeroReservation, float prix, Date dateArrivee, Date dateDepart, Client client, Hebergement hebergement, Map<Chambres, Integer> chambresReservees) {
        this.numeroReservation = numeroReservation;
        this.prix = prix;
        this.dateArrivee = dateArrivee;
        this.dateDepart = dateDepart;
        this.client = client;
        this.hebergement = hebergement;
        this.chambresReservees = new HashMap<>(chambresReservees);
    }

    public int getNumeroReservation() {
        return numeroReservation;
    }

    public Hebergement getHebergement() {
        return hebergement;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public Map<Chambres, Integer> getChambresReservees() {
        return chambresReservees;
    }

    @Override
    public String toString() {
        java.text.SimpleDateFormat formateur = new java.text.SimpleDateFormat("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append("=== RESERVATION #").append(numeroReservation).append(" ===\n");
        sb.append(" Hebergement: ").append(hebergement.getNom()).append("\n");
        sb.append(" Arrivee: ").append(formateur.format(dateArrivee)).append("\n");
        sb.append(" Depart: ").append(formateur.format(dateDepart)).append("\n");
        sb.append(" Prix total: ").append(prix).append(" $\n");
        sb.append(" ").append(client).append("\n");
        sb.append(" Detail des chambres:\n");
        
        for (Map.Entry<Chambres, Integer> ligne : chambresReservees.entrySet()) {
            sb.append("  - ").append(ligne.getValue()).append("x ")
              .append(ligne.getKey().getTypeChambre()).append("\n");
        }
        return sb.toString();
    }
}