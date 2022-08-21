package projecta07.dto;

public class DetailOrderTableDTO {
    private String codeTable;
    private Double totalOrder;
    private int numberProduct;
    private String nameProduct;
    private double priceProduct;

    public DetailOrderTableDTO() {
    }

    public DetailOrderTableDTO(String codeTable, Double totalOrder, int numberProduct, String nameProduct, String priceProduct) {
        this.codeTable = codeTable;
        this.totalOrder = totalOrder;
        this.numberProduct = numberProduct;
        this.nameProduct = nameProduct;
    }

    public String getCodeTable() {
        return codeTable;
    }

    public void setCodeTable(String codeTable) {
        this.codeTable = codeTable;
    }

    public Double getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Double totalOrder) {
        this.totalOrder = totalOrder;
    }

    public int getNumberProduct() {
        return numberProduct;
    }

    public void setNumberProduct(int numberProduct) {
        this.numberProduct = numberProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }
}
