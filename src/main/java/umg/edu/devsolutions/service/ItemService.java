package umg.edu.devsolutions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import umg.edu.devsolutions.entity.Bodega;
import umg.edu.devsolutions.entity.EstadoProducto;
import umg.edu.devsolutions.entity.Item;

import java.util.List;
import java.util.Map;

@Service
public class ItemService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Item item) {
        String sql = "INSERT INTO Catalogo.Tb_Productos (Prod_SKU, Prod_Cat_Id, Prod_TipoProd_Id, Prod_UniMed_Id, Prod_Nombre, Prod_Descripcion, Prod_CostoUnit, Prod_Descuento, Prod_Imagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, item.getSku(), item.getCategoria().getCatId(), item.getTipoProducto().getTipoProdId(), item.getUnidadMedida().getUniMedId(), item.getNombre(), item.getDescripcion(), item.getCostoUnitario(), item.getDescuento(), item.getImagen());
    }

    public List<Map<String, Object>> getCategories() {
        String sql = "SELECT Cat_Id, Cat_Nombre FROM Catalogo.Tb_Categorias";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getProductTypes() {
        String sql = "SELECT TipoProd_Id, TipoProd_Nombre FROM Catalogo.Tb_TiposProducto";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getUnitMeasures() {
        String sql = "SELECT UniMed_Id, UniMed_Nombre FROM Catalogo.Tb_UnidadesMedida";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getProducts() {
        String sql = "SELECT Prod_SKU, Prod_Nombre FROM Catalogo.Tb_Productos";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getBodegas() {
        String sql = "SELECT Bodega_Id, Bodega_Nombre FROM Ubicacion.Tb_Bodegas";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getEstadosProducto() {
        String sql = "SELECT EstadoProd_Id, EstadoProd_Nombre FROM Catalogo.Tb_EstadosProducto";
        return jdbcTemplate.queryForList(sql);
    }
}