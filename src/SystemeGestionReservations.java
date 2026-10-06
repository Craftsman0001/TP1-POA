import java.util.Date;
import java.util.List;
import java.util.Map;

// Interface représentant le système de gestion des réservations
public interface SystemeGestionReservations {
    public List<Hebergement> trouverHebergement(String criteresRegion, TypeChambre typeCh, Date arrivee, Date depart, float prixMax, List<TypeService> services);
    
    public Reservation reserver(Client leClient, Hebergement lHebergement, Map<TypeChambre, Integer> chambresDemandees, Date arrivee, Date depart);
    
    public void annuler(int numeroReservation);

    public void ajouterClient(Client nouveauClient);

    public void ajouterHebergement(Hebergement nouvelHebergement);
}