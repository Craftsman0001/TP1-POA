import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Classe représentant un hébergement (classe mère pour Hotel, Motel et CouetteEtCafe)
public class Hebergement {
    private String nom;
    private Adresse adresse;
    private List<TypeService> typeServices;
    private List<Chambres> chambres;

    public Hebergement(String nom, Adresse adresse, List<TypeService> typeServices, List<Chambres> chambres) {
        this.nom = nom;
        this.adresse = adresse;
        this.typeServices = new ArrayList<>(typeServices);
        this.chambres = new ArrayList<>(chambres);
    }

    // Retourne la capacité totale pour un type de chambre donné
    public int getCapacitePourType(TypeChambre typeDemande) {
        for (Chambres c : chambres) {
            if (c.getTypeChambre() == typeDemande) {
                return c.getQuantiteTotale();
            }
        }
        return 0;
    }

    public String getNom() {
        return nom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public List<TypeService> getTypeServices() {
        return Collections.unmodifiableList(typeServices);
    }

    public List<Chambres> getChambres() {
        return Collections.unmodifiableList(chambres);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" nom: ").append(nom).append("\n");
        sb.append(adresse).append("\n");
        sb.append("Services: ").append(typeServices).append("\n");
        for (Chambres c : chambres) {
            sb.append(c).append("\n");
        }
        return sb.toString().trim();
    }
}