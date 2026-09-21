import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SystemeGestionReservationsImpl implements SystemeGestionReservations {
    private List<Client> clients;
    private List<Hebergement> hebergements;
    private List<Reservation> reservations;

    public SystemeGestionReservationsImpl() {
        this.clients = new ArrayList<>();
        this.hebergements = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    @Override
    public List<Hebergement> trouverHebergement(String criteresRegion, TypeChambre typeCh, Date arrivee, Date depart, float prixMax, List<TypeService> services) {
        // temporaire, on retourne une liste vide pour l'instant
        return new ArrayList<>(); 
    }

    @Override
    public Reservation reserver(Client leClient, Hebergement lHebergement, TypeChambre typeCh, Date arrivee, Date depart) {
        // temporaire, on retourne null pour l'instant
        return null; 
    }

    @Override
    public boolean annuler(int numeroReservation) {
        // temporaire, on retourne false pour l'instant
        return false; 
    }

    @Override
    public void ajouterClient(Client nouveauClient) {
        this.clients.add(nouveauClient);
    }

    @Override
    public void ajouterHebergement(Hebergement nouvelHebergement) {
        this.hebergements.add(nouvelHebergement);
    }

    @Override
    public String toString() {
        return "Systeme actuel : " + clients.size() + " clients, " + 
               hebergements.size() + " hebergements, " + 
               reservations.size() + " reservations actives.";
    }
}