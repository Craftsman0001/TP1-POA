import java.util.List;

public class Hotel extends Hebergement {

    public Hotel(String nom, Adresse adresse, List<TypeService> typeServices, List<Chambres> chambres) {
        super(nom, adresse, typeServices, chambres);
    }

    @Override
    public String toString() {
        return "Hotel:\n" + super.toString();
    }
}