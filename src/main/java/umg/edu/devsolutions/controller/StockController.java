package umg.edu.devsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import umg.edu.devsolutions.entity.Stock;
import umg.edu.devsolutions.service.ItemService;
import umg.edu.devsolutions.service.StockService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/stock")
    public String showAddStockForm(Model model) {
        model.addAttribute("stock", new Stock());
        model.addAttribute("productos", itemService.getProducts());
        model.addAttribute("bodegas", itemService.getBodegas());
        model.addAttribute("estadosProducto", itemService.getEstadosProducto());
        return "page3";
    }

    @PostMapping("/stock")
    public String addStock(@ModelAttribute Stock stock, BindingResult result, Model model) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            model.addAttribute("errorMessage", "There was an error processing your request. Please check the form for errors.");
            return "redirect:/page3?error";
        }
        try {
            stockService.save(stock);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "There was a data integrity violation. Please ensure all foreign keys are valid.");
            return "redirect:/page3?error";
        }
        return "redirect:/page3?success";
    }
}