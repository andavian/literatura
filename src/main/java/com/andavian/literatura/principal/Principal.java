package com.andavian.literatura.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Principal {

    private final Menu menu;

    @Autowired
    public Principal(Menu menu) {
        this.menu = menu;
    }

    public void muestraMenu() {
        while(true) {
            System.out.println(menu.getOptionMenu());
            int option = menu.keyboard.nextInt();

            if (option == 9) {
                break;
            }

            menu.selectionOption(option);
        }
    }
}