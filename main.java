public class main {
    public static void main(String[] args) {
        SystemeGestionReservations systemeApafi = new SystemeGestionReservationsImpl();

        System.out.println("=== DEMARRAGE DU SYSTEME DE MADAME APAFI ===\n");

        // 1. Initialisation (Création des clients et hébergements de test à venir)
        System.out.println("\n--- Creation des donnees et etat initial ---");
        System.out.println(systemeApafi); 

        // 2. Test de recherche
        System.out.println("\n--- Recherche dhebergement ---");
        
        // 3. Test de réservation
        System.out.println("\n--- Reservation ---");
        
        // 4. Test d'annulation
        System.out.println("\n--- Annulation ---");
        
        // 5. Vérification finale
        System.out.println("\n--- Etat final du systeme ---");
        System.out.println(systemeApafi);
    }
}
