package umg.edu.devsolutions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import umg.edu.devsolutions.entity.InventoryReport;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<InventoryReport> getInventoryReport() {
        String sql = "SELECT p.Prod_SKU, p.Prod_Nombre, s.Stock_Cantidad, p.Prod_CostoUnit, s.Stock_PrecioVenta " +
                "FROM Catalogo.Tb_Productos p " +
                "INNER JOIN Inventario.Tb_Stock s ON p.Prod_SKU = s.Stock_Prod_SKU";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new InventoryReport(
                rs.getString("Prod_SKU"),
                rs.getString("Prod_Nombre"),
                rs.getInt("Stock_Cantidad"),
                rs.getBigDecimal("Prod_CostoUnit"),
                rs.getBigDecimal("Stock_PrecioVenta")
        ));
    }
}