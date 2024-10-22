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

    public Item findItemById(String id) {
        String sql = "SELECT * FROM Catalogo.Tb_Productos WHERE Prod_SKU = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Item item = new Item();
            item.setSku(rs.getString("Prod_SKU"));
            item.setNombre(rs.getString("Prod_Nombre"));
            // Set other fields as needed
            return item;
        });
    }

    public Bodega findBodegaById(String id) {
        String sql = "SELECT * FROM Ubicacion.Tb_Bodegas WHERE Bodega_Id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Bodega bodega = new Bodega();
            bodega.setId(rs.getInt("Bodega_Id"));
            bodega.setNombre(rs.getString("Bodega_Nombre"));
            // Set other fields as needed
            return bodega;
        });
    }

    public EstadoProducto findEstadoProductoById(String id) {
        String sql = "SELECT * FROM Inventario.Tb_EstadoProducto WHERE EstadoProd_Id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            EstadoProducto estadoProducto = new EstadoProducto();
            estadoProducto.setEstadoProdId(rs.getInt("EstadoProd_Id"));
            estadoProducto.setNombre(rs.getString("EstadoProd_Nombre"));
            // Set other fields as needed
            return estadoProducto;
        });
    }
}