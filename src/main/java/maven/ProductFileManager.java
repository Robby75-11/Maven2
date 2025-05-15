package maven;



import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProductFileManager {

    private String filePath = "prodotti.txt"; // Percorso predefinito del file

    public ProductFileManager() {
        // Costruttore vuoto
    }

    public ProductFileManager(String filePath) {
        this.filePath = filePath;
    }

    public void salvaProdottiSuDisco(List<Product> prodotti) {
        Path path = Paths.get(filePath);
        try {
            // Verifica se la directory genitore esiste, altrimenti la crea
            Path parentDir = path.getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
                System.out.println("Creata la directory: " + parentDir);
            }

            // Apre il FileWriter all'interno del blocco try-with-resources
            try (FileWriter writer = new FileWriter(filePath)) {
                for (int i = 0; i < prodotti.size(); i++) {
                    Product prodotto = prodotti.get(i);
                    writer.write(prodotto.getName() + "@" + prodotto.getCategory() + "@" + prodotto.getPrice());
                    if (i < prodotti.size() - 1) {
                        writer.write("#");
                    }
                }
                System.out.println("Lista dei prodotti salvata con successo nel file: " + filePath);
            } catch (IOException e) {
                System.err.println("Errore durante la scrittura nel file: " + e.getMessage());
                // Potresti voler rilanciare l'eccezione o eseguire altre azioni di logging/gestione
            }
        } catch (SecurityException e) {
            System.err.println("Errore di sicurezza durante la creazione della directory o la scrittura nel file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Errore I/O durante la creazione della directory: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Si è verificato un errore inaspettato durante il salvataggio dei prodotti: " + e.getMessage());
            // Cattura eventuali altre eccezioni non gestite specificamente
        }
    }

    public static void main(String[] args) {
        // Esempio di utilizzo
        List<Product> prodottiDaSalvare = List.of(
                new Product(1L, "Laptop", "Electronics", 1200.50),
                new Product(2L, "T-Shirt", "Apparel", 25.99),
                new Product(3L, "Book - Java", "Books", 49.99)
        );

        ProductFileManager fileManager = new ProductFileManager("dati/mieiprodotti.txt"); // Esempio con sottodirectory
        fileManager.salvaProdottiSuDisco(prodottiDaSalvare);
    }
}