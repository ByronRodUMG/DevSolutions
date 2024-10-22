package umg.edu.devsolutions.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Tb_UnidadesMedida", schema = "Catalogo")
@Getter
@Setter
public class UnidadMedida {
    @Id
    @Column(name = "UniMed_Id")
    private int uniMedId;

    @Column(name = "UniMed_Nombre")
    private String nombre;
}
