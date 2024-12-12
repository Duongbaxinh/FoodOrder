package com.orderFood.controller;

import com.orderFood.entity.TableO;
import com.orderFood.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class TableController {
    @Autowired
    TableService tableService;

    @GetMapping("/addTable")
    public String showCreateUserForm(Model model) {
        model.addAttribute("table", new TableO());
        return "addTable.html";
    }

    @GetMapping("/listTable")
    public String showListTable(Model model) {
      Iterable<TableO> tables = tableService.getAllTableOs();
      model.addAttribute("tables", tables);
      model.addAttribute("active",0);
      return "table/listTable.html";
    }
    @PostMapping("/addTable")
    public String addTable(@ModelAttribute("table") TableO table) {
        tableService.saveTable(table);
          return "redirect:/listTable";
    }

    @GetMapping("/editTable")
    public String showEditTableForm(@RequestParam("id") int id, Model model) {
        TableO table = tableService.getTableOById(id);
        if (table != null) {
            model.addAttribute("table", table);
            return "editTable"; 
        }else {
            model.addAttribute("mess","table not found");
            return "error.html";
        }

    }

    @PostMapping("/updateTable")
    public String updateTable(@ModelAttribute TableO table) {
        tableService.saveTable(table);
        return "redirect:/listTable";
    }

    @GetMapping("/tableDetail")
    public String showTableDetail(@RequestParam("id") int id, Model model) {
        TableO table = tableService.getTableOById(id);
        return "detailTable.html";
    }
    
    @GetMapping("/deleteTableById")
    public String deleteTableById(@RequestParam("id") int id, Model model) {
        TableO table = tableService.getTableOById(id);
        if(table == null) {return "error";}
        tableService.deleteTableOById(id);
        return "redirect:/listTable";
    }
}
