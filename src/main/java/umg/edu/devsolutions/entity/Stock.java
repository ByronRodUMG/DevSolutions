package umg.edu.devsolutions.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Tb_Stock", schema = "Inventario")
@Getter
@Setter
public class Stock {
    @Id
    @Column(name = "Stock_Id")
    private int stockId;

    @ManyToOne
    @JoinColumn(name = "Stock_Prod_SKU", referencedColumnName = "Prod_SKU")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "Stock_Bodega_Id", referencedColumnName = "Bodega_Id")
    private Bodega bodega;

    @ManyToOne
    @JoinColumn(name = "Stock_EstadoProd_Id", referencedColumnName = "EstadoProd_Id")
    private EstadoProducto estadoProducto;

    @Column(name = "Stock_Cantidad", nullable = false)
    private int stockCantidad;

    @Column(name = "Stock_PrecioVenta", nullable = false, precision = 10, scale = 2)
    private BigDecimal stockPrecioVenta;

    @Column(name = "Stock_FechaIngreso", nullable = false)
    private LocalDateTime stockFechaIngreso = LocalDateTime.now();
}