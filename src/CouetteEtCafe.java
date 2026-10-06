import java.util.List;

// Classe représentant un hébergement de type "Couette et Café"
public class CouetteEtCafe extends Hebergement {

    public CouetteEtCafe(String nom, Adresse adresse, List<TypeService> typeServices, List<Chambres> chambres) {
        super(nom, adresse, typeServices, chambres);
    }

    @Override
    public String toString() {
        return "Couette et Cafe:\n" + super.toString();
    }
}