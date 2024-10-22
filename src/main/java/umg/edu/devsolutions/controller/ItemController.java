package umg.edu.devsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import umg.edu.devsolutions.entity.Item;
import umg.edu.devsolutions.service.ItemService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Mapeo para mostrar el formulario
    @GetMapping("/add") // Ruta para mostrar el formulario
    public String showAddItemForm(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("categories", itemService.getCategories());
        model.addAttribute("productTypes", itemService.getProductTypes());
        model.addAttribute("unitMeasures", itemService.getUnitMeasures());
        return "page2"; // Devuelve el nombre de la vista
    }

    @PostMapping("/items")
    public String addItem(@ModelAttribute Item item, BindingResult result, Model model) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            model.addAttribute("errorMessage", "There was an error processing your request. Please check the form for errors.");
            return "redirect:/page2?error";
        }
        try {
            itemService.save(item);
        } catch (DuplicateKeyException e) {
            model.addAttribute("errorMessage", "A product with the same key already exists. Please use a different key.");
            return "redirect:/page2?error";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", "There was a data integrity violation. Please ensure all foreign keys are valid.");
            return "redirect:/page2?error";
        }
        return "redirect:/page2?success";
    }
}