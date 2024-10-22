package umg.edu.devsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import umg.edu.devsolutions.entity.Item;
import umg.edu.devsolutions.entity.Stock;
import umg.edu.devsolutions.service.ItemService;

@Controller
public class PageController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/page1")
    public String page1() {
        return "page1";
    }

    @GetMapping("/page2")
    public String page2(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("categories", itemService.getCategories());
        model.addAttribute("productTypes", itemService.getProductTypes());
        model.addAttribute("unitMeasures", itemService.getUnitMeasures());
        return "page2";
    }

    @GetMapping("/page3")
    public String page3(Model model) {
        model.addAttribute("stock", new Stock());
        model.addAttribute("productos", itemService.getProducts());
        model.addAttribute("bodegas", itemService.getBodegas());
        model.addAttribute("estadosProducto", itemService.getEstadosProducto());
        return "page3";
    }
}
