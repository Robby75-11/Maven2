package maven;

public class Product {

    private Long id;       // Identificatore unico del prodotto
    private String name;     // Nome del prodotto
    private String category; // Categoria del prodotto (es. "Books", "Baby", "Boys")
    private Double price;    // Prezzo del prodotto


    public Product(Long id, String name, String category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // Metodi getter per accedere agli attributi della classe


    public Long getId() {
        return id;
    }

    /**
     * Restituisce il nome del prodotto.
     *
     * @return Il nome del prodotto.
     */
    public String getName() {
        return name;
    }

    /**
     * Restituisce la categoria del prodotto.
     *
     * @return La categoria del prodotto.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Restituisce il prezzo del prodotto.
     *
     * @return Il prezzo del prodotto.
     */
    public Double getPrice() {
        return price;
    }

    // Metodi setter per modificare gli attributi della classe (potrebbero non essere sempre necessari)

    /**
     * Imposta l'ID del prodotto.
     *
     * @param id Il nuovo ID del prodotto.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Imposta il nome del prodotto.
     *
     * @param name Il nuovo nome del prodotto.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Imposta la categoria del prodotto.
     *
     * @param category La nuova categoria del prodotto.
     */
    public void setCategory(String category) {
        this.category = category;
    }


    // Imposta il prezzo del prodotto.

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
