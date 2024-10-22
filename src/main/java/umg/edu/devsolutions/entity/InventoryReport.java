package umg.edu.devsolutions.entity;

import java.math.BigDecimal;

public class InventoryReport {
    private String sku;
    private String nombre;
    private int cantidad;
    private BigDecimal costoUnitario;
    private BigDecimal precioVenta;

    public InventoryReport(String sku, String nombre, int cantidad, BigDecimal costoUnitario, BigDecimal precioVenta) {
        this.sku = sku;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
        this.precioVenta = precioVenta;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(BigDecimal costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }
}