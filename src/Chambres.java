// Classe représentant un type de chambre dans un hébergement
public class Chambres {
    private int nombreChambres;
    private TypeChambre type;
    private float prixParNuit;

    public Chambres(int nombreChambres, TypeChambre type, float prixParNuit) {
        this.type = type;
        this.nombreChambres = nombreChambres;
        this.prixParNuit = prixParNuit;
    }

    public TypeChambre getTypeChambre() {
        return type;
    }

    public int getQuantiteTotale() {
        return nombreChambres;
    }

    public float getPrixParNuit() {
        return prixParNuit;
    }

    @Override 
    public String toString() {
        return "Chambres:\n" +
                " typeChambre: " + type + "\n" +
                " nombreChambres: " + nombreChambres + "\n" +
                " prixParNuit: " + prixParNuit;
    }
}

