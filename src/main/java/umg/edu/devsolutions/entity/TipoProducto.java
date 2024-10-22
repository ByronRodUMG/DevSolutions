package umg.edu.devsolutions.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Tb_TiposProducto", schema = "Catalogo")
@Getter
@Setter
public class TipoProducto {
    @Id
    @Column(name = "TipoProd_Id")
    private int tipoProdId;

    @Column(name = "TipoProd_Nombre")
    private String nombre;
}
