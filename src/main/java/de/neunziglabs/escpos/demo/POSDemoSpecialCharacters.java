package de.neunziglabs.escpos.demo;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import de.neunziglabs.escpos.POSPrinter;
import de.neunziglabs.escpos.POSReceipt;
import de.neunziglabs.escpos.POSSpecialCharacter;
import de.neunziglabs.escpos.POSText;

public class POSDemoSpecialCharacters {
    public static void main(String[] args) {
        // Locate the printer
        PrintService printerService = findPrintService("Printer");

        if (printerService == null) {
            System.out.println("Printer not found");
            return;
        }

        POSReceipt.Builder receiptBuilder = new POSReceipt.Builder()
            .setTitle("Currency Symbol Demo");

        for (POSSpecialCharacter currency : POSSpecialCharacter.values()) {
            receiptBuilder.addComponent(new POSText.Builder(currency.name() + ": {" + currency.name() + "}")
                .build());
        }

        POSReceipt receipt = receiptBuilder
            .setFooter("Thank you for using ESC/POS!")
            .build();

        POSPrinter printer = new POSPrinter(printerService);
        printer.print(receipt);
    }

    // Helper method to find a printer by name
    public static PrintService findPrintService(String printerName) {
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService service : services) {
            if (service.getName().equalsIgnoreCase(printerName)) {
                return service;
            }
        }
        return null;
    }
}
