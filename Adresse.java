import java.util.Objects;

// Adresse d'un hebergement ou d'un client
public class Adresse {
    private String pays;
    private String province;
    private String ville;
    private String quartier;
    private String rue;

    public Adresse(String pays, String province, String ville, String quartier, String rue) {
        this.pays = pays;
        this.province = province;
        this.ville = ville;
        this.quartier = quartier;
        this.rue = rue;
    }

    public String getPays() {
        return pays;
    }

    public String getProvince() {
        return province;
    }

    public String getVille() {
        return ville;
    }

    public String getQuartier() {
        return quartier;
    }

    public String getRue() {
        return rue;
    }

    // Compare deux adresses
    @Override
    public boolean equals(Object autre) {
        if (this == autre) {
            return true;
        }
        if (!(autre instanceof Adresse)) {
            return false;
        }
        Adresse autreAdresse = (Adresse) autre;
        return Objects.equals(pays, autreAdresse.pays)
                && Objects.equals(province, autreAdresse.province)
                && Objects.equals(ville, autreAdresse.ville)
                && Objects.equals(quartier, autreAdresse.quartier)
                && Objects.equals(rue, autreAdresse.rue);
    }

    // Va avec equals
    @Override
    public int hashCode() {
        return Objects.hash(pays, province, ville, quartier, rue);
    }

    @Override
    public String toString() {
        return "Adresse:\n" +
                " rue: " + rue + "\n" +
                " quartier: " + quartier + "\n" +
                " ville: " + ville + "\n" +
                " province: " + province + "\n" +
                " pays: " + pays;
    }
}