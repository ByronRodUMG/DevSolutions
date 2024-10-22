package umg.edu.devsolutions.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Tb_Bodegas", schema = "Ubicacion")
@Getter
@Setter
public class Bodega {
    @Id
    @Column(name = "Bodega_Id")
    private int id;

    @Column(name = "Bodega_Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "Bodega_Direccion", length = 200)
    private String direccion;
}