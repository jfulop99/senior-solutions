package com.example.cars;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String getWelcome() {
        return "<h1 style=\"text-align:center\">Üdvözöljük oldalunkon</h1> <br> <a href=\"/cars\">Cars</a> <br> <a href=\"/types\">Types</a>";
    }
}
