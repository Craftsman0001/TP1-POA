import java.util.List;

// Classe représentant un hébergement de type "Hôtel"
public class Hotel extends Hebergement {

    public Hotel(String nom, Adresse adresse, List<TypeService> typeServices, List<Chambres> chambres) {
        super(nom, adresse, typeServices, chambres);
    }

    @Override
    public String toString() {
        return "Hotel:\n" + super.toString();
    }
}