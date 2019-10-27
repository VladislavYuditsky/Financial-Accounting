package com.yuditsky.financial_accounting.view;

import com.yuditsky.financial_accounting.controller.Controller;

import java.util.Scanner;

import static com.yuditsky.financial_accounting.service.util.Constants.EXIT;

public class View {
    public static void main(String[] args) {
        Controller controller = new Controller();
        Scanner in = new Scanner(System.in);

        String request;
        while (!(request = in.nextLine()).equals(EXIT)) {
            System.out.println(controller.executeTask(request));
        }
    }
}
