package dtos;

public class ProductDTO implements java.io.Serializable {
    private Integer id;
    private String name;
    private Double price;

    public ProductDTO(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductDTO() {}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    @Override 
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
