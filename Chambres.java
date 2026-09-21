public class Chambres {
    private int nombreChambres;
    private int nombreDisponible;
    private TypeChambre type;

    public Chambres(int nombreChambres, int nombreDisponible, TypeChambre type) {
        this.type = type;
        this.nombreChambres = nombreChambres;
        this.nombreDisponible = nombreDisponible;
    }

    public TypeChambre getTypeChambre() {
        return type;
    }

    public int getQuantiteTotale() {
        return nombreChambres;
    }

    @Override 
    public String toString() {
        return "Chambres:\n" +
                " typeChambre: " + type + "\n" +
                " nombreChambres: " + nombreChambres + "\n" +
                " nombreDisponible: " + nombreDisponible;
    }
}