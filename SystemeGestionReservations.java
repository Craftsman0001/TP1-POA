import java.util.Date;
import java.util.List;

public interface SystemeGestionReservations {
    public List<Hebergement> trouverHebergement(String criteresRegion, TypeChambre typeCh, Date arrivee, Date depart, float prixMax, List<TypeService> services);
    
    public Reservation reserver(Client leClient, Hebergement lHebergement, TypeChambre typeCh, Date arrivee, Date depart);
    
    public boolean annuler(int numeroReservation);

    public void ajouterClient(Client nouveauClient);

    public void ajouterHebergement(Hebergement nouvelHebergement);
}