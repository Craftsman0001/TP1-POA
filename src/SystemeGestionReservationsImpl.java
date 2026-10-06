import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// Classe implémentant le système de gestion des réservations
public class SystemeGestionReservationsImpl implements SystemeGestionReservations {
    private List<Client> clients;
    private List<Hebergement> hebergements;
    private List<Reservation> reservations;

    public SystemeGestionReservationsImpl() {
        this.clients = new ArrayList<>();
        this.hebergements = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    // Méthode pour trouver des hébergements selon les critères spécifiés
    @Override
    public List<Hebergement> trouverHebergement(String criteresRegion, TypeChambre typeCh, Date arrivee, Date depart, float prixMax, List<TypeService> services) {
        // Parcourir la liste des hébergements et filtrer selon les critères
        List<Hebergement> resultats = new ArrayList<>();
        
        for (Hebergement h : hebergements) {
            // Vérifier la région
            if (h.getAdresse().getVille().equalsIgnoreCase(criteresRegion)) {
                // Vérifier les services demandés
                if (h.getTypeServices().containsAll(services)) {

                    for (Chambres c : h.getChambres()) {
                        // Vérifier le type de chambre et le prix
                        if (c.getTypeChambre() == typeCh && c.getPrixParNuit() <= prixMax) {
                            
                            int dispoReelle = calculerChambresDispo(h, typeCh, arrivee, depart);
                            // Vérifier si au moins une chambre est disponible pour ces dates
                            if (dispoReelle > 0) {
                                resultats.add(h);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return resultats;
    }

    // Méthode pour effectuer une réservation
    @Override
    public Reservation reserver(Client client, Hebergement hebergement, Map<TypeChambre, Integer> chambresDemandees, Date arrivee, Date depart) {
        // Calcul du nombre de nuits
        long jours = (depart.getTime() - arrivee.getTime()) / (1000 * 60 * 60 * 24);
        int nbNuits = (int) jours;
        
        if (nbNuits <= 0) {
            throw new IllegalArgumentException("La date de depart doit etre apres l arrivee.");
        }

        float prixTotal = 0;
        Map<Chambres, Integer> chambresConfirmees = new HashMap<>();

        // Parcourir ce que le client demande
        for (Map.Entry<TypeChambre, Integer> demande : chambresDemandees.entrySet()) {
            TypeChambre typeDemande = demande.getKey();
            int quantiteDemandee = demande.getValue();
            boolean typeTrouve = false;

            // Chercher cette chambre dans l'hébergement
            for (Chambres c : hebergement.getChambres()) {
                if (c.getTypeChambre() == typeDemande) {
                    typeTrouve = true;
                    
                    // Calculer la disponibilité réelle selon le calendrier
                    int dispoReelle = calculerChambresDispo(hebergement, typeDemande, arrivee, depart);
                    
                    // Vérifier si la demande peut être satisfaite
                    if (dispoReelle >= quantiteDemandee) {
                        // Calcul du prix
                        prixTotal += (c.getPrixParNuit() * quantiteDemandee * nbNuits);
                        chambresConfirmees.put(c, quantiteDemandee);
                    } else {
                        throw new IllegalStateException("Pas assez de chambres " + typeDemande + " pour ces dates."); 
                    }
                    break;
                }
            }
            if (!typeTrouve) {
                throw new IllegalArgumentException("Le type de chambre demande n existe pas dans cet etablissement.");
            }
        }

        // Création de la réservation et sauvegarde
        int nouveauNumeroId = reservations.size() + 1; 
        Reservation nouvelleRes = new Reservation(nouveauNumeroId, prixTotal, arrivee, depart, client, hebergement, chambresConfirmees);
        reservations.add(nouvelleRes);
        
        return nouvelleRes;
    }

    // Méthode pour annuler une réservation 
    @Override
    public void annuler(int numeroReservation) {
        // parcourir la liste des réservations pour trouver celle à annuler
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getNumeroReservation() == numeroReservation) {
                // retirer la réservation de la liste
                reservations.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Reservation #" + numeroReservation + " introuvable.");
    }

    // Méthode pour ajouter un nouveau client au système
    @Override
    public void ajouterClient(Client nouveauClient) {
        this.clients.add(nouveauClient);
    }

    // Méthode pour ajouter un nouvel hébergement au système
    @Override
    public void ajouterHebergement(Hebergement nouvelHebergement) {
        this.hebergements.add(nouvelHebergement);
    }

    // Outil pour calculer la disponibilité selon le calendrier
    private int calculerChambresDispo(Hebergement hebergement, TypeChambre typeDemande, Date arrivee, Date depart) {
        int totalChambres = hebergement.getCapacitePourType(typeDemande);
        int chambresBloquees = 0;

        for (Reservation res : reservations) {
            // regarder seulement les réservations pour le même hôtel
            if (res.getHebergement().equals(hebergement)) {
                // Vérifier si les dates se chevauchent
                if (res.getDateArrivee().getTime() < depart.getTime() && res.getDateDepart().getTime() > arrivee.getTime()) {
                    
                    // Compter combien de chambres de ce type sont déjà réservées pour ces dates
                    for (Map.Entry<Chambres, Integer> ligne : res.getChambresReservees().entrySet()) {
                        if (ligne.getKey().getTypeChambre() == typeDemande) {
                            chambresBloquees += ligne.getValue();
                        }
                    }
                }
            }
        }
        return totalChambres - chambresBloquees;
    }

    // Méthode pour afficher l'état actuel du système
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========================================\n");
        sb.append("             ETAT DU SYSTEME\n");
        sb.append("========================================\n\n");

        // Affichage des clients
        sb.append("--- LISTE DES CLIENTS ---\n");
        for (Client c : clients) {
            sb.append(" - ").append(c).append("\n");
        }
        sb.append("\n");

        // Résumé des hébergements
        sb.append("--- HEBERGEMENTS ---\n");
        for (Hebergement h : hebergements) {
            sb.append(" - ").append(h.getNom()).append(" (").append(h.getAdresse().getVille()).append(")\n");
        }
        sb.append("\n");

        // Affichage des réservations actives
        sb.append("--- RESERVATIONS ACTIVES ---\n");
        if (reservations.isEmpty()) {
            sb.append(" Aucune reservation en cours.\n");
        } else {
            for (Reservation r : reservations) {
                sb.append(r).append("\n");
            }
        }

        sb.append("\n========================================\n");
        sb.append("            FIN ETAT DU SYSTEME\n");
        sb.append("========================================\n\n");
        
        return sb.toString();
    }
}