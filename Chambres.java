public class Chambres {
    private int nombreChambres;
    private int nombreDisponible;
    private TypeChambre type;
    private float prixParNuit;

    public Chambres(int nombreChambres, int nombreDisponible, TypeChambre type, float prixParNuit) {
        this.type = type;
        this.nombreChambres = nombreChambres;
        this.nombreDisponible = nombreDisponible;
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
                " nombreDisponible: " + nombreDisponible + "\n" +
                " prixParNuit: " + prixParNuit;
    }
}

