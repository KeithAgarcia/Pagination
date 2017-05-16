package com.theironyard.Pagination.controllers;

import com.theironyard.Pagination.entities.Address;
import com.theironyard.Pagination.services.PaginationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Keith on 5/16/17.
 */

@Controller
public class PaginationController {
    @Autowired
    PaginationRepository addresses;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session, String state, Integer page) {


        page = (page == null) ? 0 : page;
        PageRequest pr = new PageRequest(page, 10);

        Page<Address> p;

        if (state != null) {
            // get the page of results from the address
            p = addresses.findByState(pr, state);
        }
        else {
            p = addresses.findAll(pr);
        }
        model.addAttribute("addresses", p);
        model.addAttribute("nextPage", page + 1);
        model.addAttribute("showNext", p.hasNext());
        model.addAttribute("state", state);
        return "home";
    }

    @PostConstruct
    public void init(){
        if(addresses.count() == 0){
            File customerFile = new File("addresses.csv");
            try {
                Scanner fileScanner = new Scanner(customerFile);
                fileScanner.nextLine();

                while(fileScanner.hasNext()){
                    String line = fileScanner.nextLine();
                    String[] columns = line.split("\\,");
                    Address address = new Address(columns[0], columns[1], columns[2], Integer.valueOf(columns[3]));
                    addresses.save(address);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            File purchaseFile = new File("purchases.csv");
            try {
                Scanner fileScanner = new Scanner(purchaseFile);
                fileScanner.nextLine();

                while (fileScanner.hasNext()){
                    String line = fileScanner.nextLine();
                    String[] columns = line.split("\\,");

                    Address address = new Address(columns[1], columns[2], columns[3], Integer.valueOf(columns[4]));
                    addresses.save(address);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
