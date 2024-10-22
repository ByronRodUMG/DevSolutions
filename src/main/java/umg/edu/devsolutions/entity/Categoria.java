package umg.edu.devsolutions.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Tb_Categorias", schema = "Catalogo")
@Getter
@Setter
public class Categoria {
    @Id
    @Column(name = "Cat_Id")
    private int catId;

    @Column(name = "Cat_Nombre")
    private String nombre;
}
