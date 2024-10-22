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
        String sql = "INSERT INTO Inventario.Tb_Stock (Stock_Prod_SKU, Stock_Bodega_Id, Stock_EstadoProd_Id, Stock_Cantidad, Stock_PrecioVenta, Stock_FechaIngreso) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, stock.getItem().getSku(), stock.getBodega().getId(), stock.getEstadoProducto().getEstadoProdId(), stock.getStockCantidad(), stock.getStockPrecioVenta(), stock.getStockFechaIngreso());
    }
}