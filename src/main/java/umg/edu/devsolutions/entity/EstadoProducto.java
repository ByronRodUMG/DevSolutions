package umg.edu.devsolutions.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Tb_EstadosProducto", schema = "Catalogo")
@Getter
@Setter
public class EstadoProducto {
    @Id
    @Column(name = "EstadoProd_Id")
    private int estadoProdId;

    @Column(name = "EstadoProd_Nombre")
    private String nombre;
}
