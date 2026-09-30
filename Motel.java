import java.util.List;

public class Motel extends Hebergement {

    public Motel(String nom, Adresse adresse, List<TypeService> typeServices, List<Chambres> chambres) {
        super(nom, adresse, typeServices, chambres);
    }

    @Override
    public String toString() {
        return "Motel:\n" + super.toString();
    }
}