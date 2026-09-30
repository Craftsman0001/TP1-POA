import java.util.List;

public class CouetteEtCafe extends Hebergement {

    public CouetteEtCafe(String nom, Adresse adresse, List<TypeService> typeServices, List<Chambres> chambres) {
        super(nom, adresse, typeServices, chambres);
    }

    @Override
    public String toString() {
        return "Couette et Cafe:\n" + super.toString();
    }
}