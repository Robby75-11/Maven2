package maven;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Inizializzazione dei clienti
        List<Customer> customers = Arrays.asList(
                new Customer(1L, "Alice", 1),
                new Customer(2L, "Bob", 2),
                new Customer(3L, "Charlie", 1),
                new Customer(4L, "David", 2)
        );

        // Inizializzazione dei prodotti
        List<Product> products = Arrays.asList(
                new Product(101L, "The Lord of the Rings", "Books", 125.50),
                new Product(102L, "Harry Potter", "Books", 99.99),
                new Product(201L, "Wooden Blocks", "Baby", 35.00),
                new Product(202L, "Stroller", "Baby", 150.00),
                new Product(301L, "Action Figure", "Boys", 25.75),
                new Product(302L, "Toy Car", "Boys", 45.20)
        );

        // Inizializzazione degli ordini
        List<Order> orders = Arrays.asList(
                new Order(1001L, "COMPLETED", LocalDate.of(2021, 1, 15), LocalDate.of(2021, 1, 20), Arrays.asList(products.get(0), products.get(1)), customers.get(0)),
                new Order(1002L, "PENDING", LocalDate.of(2021, 2, 10), LocalDate.of(2021, 2, 15), Arrays.asList(products.get(2)), customers.get(1)),
                new Order(1003L, "COMPLETED", LocalDate.of(2021, 3, 1), LocalDate.of(2021, 3, 5), Arrays.asList(products.get(3), products.get(4)), customers.get(3)),
                new Order(1004L, "SHIPPED", LocalDate.of(2021, 3, 20), LocalDate.of(2021, 3, 25), Arrays.asList(products.get(5)), customers.get(1))
        );

        // Esercizio #1
        List<Product> libriCostoElevato = products.stream()
                .filter(prodotto -> prodotto.getCategory().equals("Books"))
                .filter(prodotto -> prodotto.getPrice() > 100)
                .toList();
        System.out.println("Esercizio #1: " + libriCostoElevato);

        // Esercizio #2
        List<Order> ordiniConProdottiBaby = orders.stream()
                .filter(ordine -> ordine.getProducts().stream()
                        .anyMatch(prodotto -> prodotto.getCategory().equals("Baby")))
                .toList();
        System.out.println("Esercizio #2: " + ordiniConProdottiBaby);

        // Esercizio #3
        List<Product> prodottiBoysScontati = products.stream()
                .filter(prodotto -> prodotto.getCategory().equals("Boys"))
                .map(prodotto -> new Product(prodotto.getId(), prodotto.getName(), prodotto.getCategory(), prodotto.getPrice() * 0.9))
                .toList();
        System.out.println("Esercizio #3: " + prodottiBoysScontati);

        // Esercizio #4
        LocalDate dataInizio = LocalDate.of(2021, 2, 1);
        LocalDate dataFine = LocalDate.of(2021, 4, 1);
        List<Product> prodottiClientiTier2 = orders.stream()
                .filter(ordine -> ordine.getCustomer().getTier() == 2)
                .filter(ordine -> ordine.getOrderDate().isAfter(dataInizio) && ordine.getOrderDate().isBefore(dataFine))
                .flatMap(ordine -> ordine.getProducts().stream())
                .toList();
        System.out.println("Esercizio #4: " + prodottiClientiTier2);

       //Esercizio 1
        Map<Customer, List<Order>> ordiniPerCliente = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));

        // Stampa della mappa risultante (opzionale)
        ordiniPerCliente.forEach((cliente, listaOrdini) -> {
            System.out.println("Cliente: " + cliente.getName());
            listaOrdini.forEach(ordine -> System.out.println("  - Ordine ID: " + ordine.getId()));
        });

        //Esercizio 2
        Map<Customer, Double> totaleVenditePerCliente = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomer,
                        Collectors.summingDouble(ordine -> ordine.getProducts().stream()
                                .mapToDouble(Product::getPrice)
                                .sum())
                ));

        // Stampa della mappa risultante (opzionale)
        totaleVenditePerCliente.forEach((cliente, totale) -> {
            System.out.println("Cliente: " + cliente.getName() + ", Totale acquisti: " + totale);
        });

        //Esercizio3
        Optional<Product> prodottoPiuCostoso = products.stream()
                .max((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));

        prodottoPiuCostoso.ifPresent(prodotto ->
                System.out.println("Il prodotto più costoso è: " + prodotto.getName() + " con prezzo: " + prodotto.getPrice())
        );

        // Esercizio 4
        OptionalDouble mediaImportiOrdini = orders.stream()
                .mapToDouble(ordine -> ordine.getProducts().stream()
                        .mapToDouble(Product::getPrice)
                        .sum())
                .average();

        mediaImportiOrdini.ifPresent(media ->
                System.out.println("La media degli importi degli ordini è: " + media)
        );

        //Esercizio5
        // Raggruppamento dei prodotti per categoria e calcolo della somma degli importi
        Map<String, Double>sommaImportiPerCategoria = products.stream().
                collect(Collectors.groupingBy(Product::getCategory,// Funzione di classificazione: raggruppa per categoria
                        Collectors.summingDouble(Product::getPrice) // Funzione di aggregazione: somma i prezzi
                ));
        // Stampa del risultato
        System.out.println("Somma degli importi per categoria:");
        sommaImportiPerCategoria.forEach((categoria, somma) -> {
            System.out.println("Categoria: " + categoria + ", Somma importi: " + somma);
        });

        // # Extra Esercizio 6

        // Utilizzo di ProductFileManager per salvare la lista dei prodotti di ieri CON GESTIONE DELLE ECCEZIONI
        ProductFileManager productFileManager = new ProductFileManager("catalogo_prodotti.txt");
        productFileManager.salvaProdottiSuDisco(products); // Passiamo la lista 'products' creata ieri

    }


}
