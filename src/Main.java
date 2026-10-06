import java.util.ArrayList;
import java.util.List;

// Classe principale pour tester le système de gestion des réservations
public class Main {
    public static void main(String[] args) {
        // Initialisation du système de gestion des réservations
        SystemeGestionReservations systemeApafi = new SystemeGestionReservationsImpl();

        System.out.println("=== DEMARRAGE DU SYSTEME DE MADAME APAFI ===");

        // 1. Initialisation (Création des clients et hébergements de test à venir)
        System.out.println("\n--- Creation des donnees et etat initial ---");

        // Création des adresses
        Adresse adresseFamille = new Adresse("Etats-Unis", "IL", "Springfield", "Quartier Residentiel", "742 Evergreen Terrace");
        Adresse adresseHotel = new Adresse("Canada", "QC", "Saguenay", "Chicoutimi", "123 rue Racine");

        // Création des clients
        Client client1 = new Client(1, "Simpson", "Homer", "homer@springfield.com", "555-1234", adresseFamille);
        Client client2 = new Client(2, "Simpson", "Marge", "marge@springfield.com", "555-5678", adresseFamille);
        systemeApafi.ajouterClient(client1);
        systemeApafi.ajouterClient(client2);

        // Création de l'inventaire des chambres pour l'hôtel
        List<Chambres> inventaireChambres = new ArrayList<>();
        inventaireChambres.add(new Chambres(10, TypeChambre.SIMPLE, 90.00f));
        inventaireChambres.add(new Chambres(5, TypeChambre.DOUBLE, 140.00f));
        inventaireChambres.add(new Chambres(2, TypeChambre.SUITE, 250.00f));

        // Création de la liste des services offerts
        List<TypeService> servicesHotel = new ArrayList<>();
        servicesHotel.add(TypeService.STATIONNEMENT);
        servicesHotel.add(TypeService.PISCINE_INTERIEUR);
        servicesHotel.add(TypeService.RESTAURANT);

        // Création et ajout de l'hébergement
        Hotel hotelFjord = new Hotel("Le Grand Hotel du Fjord", adresseHotel, servicesHotel, inventaireChambres);
        systemeApafi.ajouterHebergement(hotelFjord);

        // Affichage pour vérifier que tout fonctionne
        System.out.println(systemeApafi);

        // 2. Test de recherche
        System.out.println("\n--- Recherche d hebergement ---");
        
        java.util.Date dateArrivee = new java.util.Date();
        java.util.Date dateDepart = new java.util.Date(dateArrivee.getTime() + (1000 * 60 * 60 * 24 * 3));
        
        List<TypeService> servicesRecherches = new ArrayList<>();
        servicesRecherches.add(TypeService.PISCINE_INTERIEUR);
        
        List<Hebergement> hotelsTrouves = systemeApafi.trouverHebergement("Saguenay", TypeChambre.SIMPLE, dateArrivee, dateDepart, 100.00f, servicesRecherches);
        System.out.println("Nous avons trouve " + hotelsTrouves.size() + " hebergement(s) qui correspondent a vos criteres.");

        // 3. Test de réservation
        System.out.println("\n--- Reservation ---");
        
        // Si des hôtels sont trouvés, tenter de réserver une chambre
        if (!hotelsTrouves.isEmpty()) {
            Hebergement hotelChoisi = hotelsTrouves.get(0);
            
            java.util.Map<TypeChambre, Integer> chambresDemandees = new java.util.HashMap<>();
            chambresDemandees.put(TypeChambre.SIMPLE, 1);
            chambresDemandees.put(TypeChambre.DOUBLE, 1);

            try {
                Reservation nouvelleRes = systemeApafi.reserver(client1, hotelChoisi, chambresDemandees, dateArrivee, dateDepart);
                System.out.println(nouvelleRes);
            } catch (Exception e) {
                System.out.println("Erreur de réservation : " + e.getMessage());
            }
        }

        // tests de chevauchement
        System.out.println("\n--- Tests de Chevauchement ---");
        
        // Marge arrive le Jour 2 (pendant le séjour d'Homer) et part le Jour 5 (après son départ)
        java.util.Date arriveeMarge = new java.util.Date(dateArrivee.getTime() + (1000L * 60 * 60 * 24 * 2)); 
        java.util.Date departMarge = new java.util.Date(dateArrivee.getTime() + (1000L * 60 * 60 * 24 * 5));  

        java.util.Map<TypeChambre, Integer> demandeMarge = new java.util.HashMap<>();
        
        // TEST A : Demande les 10 chambres (Croise Homer le Jour 2) -> ÉCHEC
        System.out.println("Test A (Arrive J2, Part J5 - demande 10 ch.) :");
        demandeMarge.put(TypeChambre.SIMPLE, 10);
        try {
            systemeApafi.reserver(client2, hotelFjord, demandeMarge, arriveeMarge, departMarge);
            System.out.println("Succes : Reservation A confirmee !");
        } catch (Exception e) {
            System.out.println("Refusee : " + e.getMessage());
        }

        // TEST B : Demande les 9 chambres restantes -> SUCCÈS 
        System.out.println("\nTest B (Arrive J2, Part J5 - demande 9 ch.) :");
        demandeMarge.put(TypeChambre.SIMPLE, 9);
        try {
            systemeApafi.reserver(client2, hotelFjord, demandeMarge, arriveeMarge, departMarge);
            System.out.println("Succes : Reservation B confirmee !");
        } catch (Exception e) {
            System.out.println("Refusee : " + e.getMessage());
        }

        // TEST C : Marge revient APRÈS le départ de tout le monde (Jour 5 au Jour 8) -> SUCCÈS 
        System.out.println("\nTest C (Apres le sejour, J5 a J8 - demande 10 ch.) :");
        java.util.Date arriveeMargeApres = new java.util.Date(dateArrivee.getTime() + (1000L * 60 * 60 * 24 * 5)); 
        java.util.Date departMargeApres = new java.util.Date(dateArrivee.getTime() + (1000L * 60 * 60 * 24 * 8));
        
        demandeMarge.put(TypeChambre.SIMPLE, 10);
        try {
            systemeApafi.reserver(client2, hotelFjord, demandeMarge, arriveeMargeApres, departMargeApres);
            System.out.println("Succes : Reservation C confirmee !");
        } catch (Exception e) {
            System.out.println("Refusee : " + e.getMessage());
        }
        
        // 4. Test d'annulation
        System.out.println("\n--- Annulation ---");
        
        int numeroReservation = 1;
        try {
            systemeApafi.annuler(numeroReservation);
            System.out.println("La reservation n°" + numeroReservation + " a ete annulee et les chambres sont de nouveau libres.");
        } catch (Exception e) {
            System.out.println("Echec : " + e.getMessage());
        }
        
        // 5. Vérification finale
        System.out.println("\n--- Etat final du systeme ---");
        System.out.println(systemeApafi);
    }
}
