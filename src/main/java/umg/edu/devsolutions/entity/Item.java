package umg.edu.devsolutions.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "Tb_Productos", schema = "Catalogo")
@Getter
@Setter
public class Item {
    @Id
    @Column(name = "Prod_SKU", nullable = false, length = 50)
    private String sku;

    @ManyToOne
    @JoinColumn(name = "Prod_Cat_Id", referencedColumnName = "Cat_Id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "Prod_TipoProd_Id", referencedColumnName = "TipoProd_Id")
    private TipoProducto tipoProducto;

    @ManyToOne
    @JoinColumn(name = "Prod_UniMed_Id", referencedColumnName = "UniMed_Id")
    private UnidadMedida unidadMedida;

    @Column(name = "Prod_Nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "Prod_Descripcion", length = 100)
    private String descripcion;

    @Column(name = "Prod_CostoUnit", nullable = false, precision = 10, scale = 2)
    private BigDecimal costoUnitario;

    @Column(name = "Prod_Descuento", precision = 5, scale = 2)
    private BigDecimal descuento;

    @Column(name = "Prod_Imagen", length = 255)
    private String imagen;

    public void setDescuento(double descuento) {
        this.descuento = BigDecimal.valueOf(descuento)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }
}