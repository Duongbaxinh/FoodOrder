package com.orderFood.service;

import com.orderFood.entity.TableO;
import com.orderFood.entity.TableO;
import com.orderFood.repository.TableRepository;
import com.orderFood.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableService {
    @Autowired
    TableRepository tableRepository;

    public void saveTable(TableO table) {
        tableRepository.save(table);
    }
    public TableO getTableOById(int id) {
        TableO user =  tableRepository.findById(id).orElse(null);
        return user;
    }
    public String deleteTableOById(int id) {
        TableO tableO =  tableRepository.findById(id).orElse(null);
        if(tableO != null) {
            tableRepository.delete(tableO);
            return "table with id " + id + " deleted";
        }else {
            return "table with id " + id + " not found";
        }
    }
    public String updateTableOById(int id, TableO tableO) {
        TableO table =  tableRepository.findById(id).orElse(null);
        if(tableO != null) {
            tableRepository.save(tableO);
            return "table with id " + id + " updated";
        }else {
            return "table with id " + id + " not found";
        }
    }
    public Iterable<TableO> getAllTableOs() {
        Iterable<TableO> users =  tableRepository.findAll();
        return users;
    }
}
