package umg.edu.devsolutions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import umg.edu.devsolutions.entity.Stock;

@Service
public class StockService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Stock stock) {
        String checkSql = "SELECT COUNT(*) FROM Inventario.Tb_Stock WHERE Stock_Prod_SKU = ? AND Stock_Bodega_Id = ?";
        Integer count = jdbcTemplate.queryForObject(checkSql, new Object[]{stock.getItem().getSku(), stock.getBodega().getId()}, Integer.class);

        if (count != null && count > 0) {
            String updateSql = "UPDATE Inventario.Tb_Stock SET Stock_EstadoProd_Id = ?, Stock_Cantidad = ?, Stock_PrecioVenta = ?, Stock_FechaIngreso = ? WHERE Stock_Prod_SKU = ? AND Stock_Bodega_Id = ?";
            jdbcTemplate.update(updateSql, stock.getEstadoProducto().getEstadoProdId(), stock.getStockCantidad(), stock.getStockPrecioVenta(), stock.getStockFechaIngreso(), stock.getItem().getSku(), stock.getBodega().getId());
        } else {
            String insertSql = "INSERT INTO Inventario.Tb_Stock (Stock_Prod_SKU, Stock_Bodega_Id, Stock_EstadoProd_Id, Stock_Cantidad, Stock_PrecioVenta, Stock_FechaIngreso) VALUES (?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(insertSql, stock.getItem().getSku(), stock.getBodega().getId(), stock.getEstadoProducto().getEstadoProdId(), stock.getStockCantidad(), stock.getStockPrecioVenta(), stock.getStockFechaIngreso());
        }
    }
}