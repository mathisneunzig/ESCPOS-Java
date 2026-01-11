package de.neunziglabs.escpos.demo;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import de.neunziglabs.escpos.POSAddress;
import de.neunziglabs.escpos.POSAddressBuildStrategies;
import de.neunziglabs.escpos.POSAddressType;
import de.neunziglabs.escpos.POSBarcode;
import de.neunziglabs.escpos.POSBarcodeType;
import de.neunziglabs.escpos.POSBarcodeWidth;
import de.neunziglabs.escpos.POSPrintStyle;
import de.neunziglabs.escpos.POSPrinter;
import de.neunziglabs.escpos.POSQRCode;
import de.neunziglabs.escpos.POSQRCodeErrorCorrection;
import de.neunziglabs.escpos.POSQRCodeSize;
import de.neunziglabs.escpos.POSReceipt;
import de.neunziglabs.escpos.POSText;
import de.neunziglabs.escpos.POSTextAlignment;

public class POSDemo {
    public static void main(String[] args) {
    	testAddress();
    }
    
    public static void testAddress() {
        PrintService printerService = findPrintService("Printer");

        if (printerService == null) {
            System.out.println("Printer not found");
            return;
        }
        
        POSAddress address = new POSAddress.Builder()
        		.setBuildStrategy(POSAddressBuildStrategies.forType(POSAddressType.JAPAN))
        		.setRecipientName("Mathis Neunzig")
        		.setStreet("Europaplatz")
        		.setHouseNumber("17")
        		.setCity("Heidelberg")
        		.setPostalCode("69115")
        		.build();

        POSPrinter printer = new POSPrinter(printerService);
        printer.print(address);
    }
    
    public static void testReceipt() {
        PrintService printerService = findPrintService("Printer");

        if (printerService == null) {
            System.out.println("Printer not found");
            return;
        }

        // Create the receipt
        POSReceipt receipt = new POSReceipt.Builder()
                
                // Title
                .setTitle("ESC/POS PRINTER DEMO")

                // Add Line Feeds for Spacing
                .addFeed()

                // Text with Different Alignments
                .addComponent(new POSText.Builder("Left Aligned Text").setAlignment(POSTextAlignment.LEFT).build())
                .addComponent(new POSText.Builder("Centered Text").setAlignment(POSTextAlignment.CENTER).build())
                .addComponent(new POSText.Builder("Right Aligned Text").setAlignment(POSTextAlignment.RIGHT).build())

                // Text with Styles
                .addComponent(new POSText.Builder("BOLD TEXT").setStyle(POSPrintStyle.BOLD).build())
                .addComponent(new POSText.Builder("Underlined Text").setStyle(POSPrintStyle.UNDERLINE).build())
                .addComponent(new POSText.Builder("Double Height").setStyle(POSPrintStyle.DOUBLE_HEIGHT).build())
                .addComponent(new POSText.Builder("Double Width").setStyle(POSPrintStyle.DOUBLE_WIDTH).build())
                .addComponent(new POSText.Builder("Mixed Styles").setStyle(POSPrintStyle.BOLD, POSPrintStyle.UNDERLINE).build())

                // Add Spacing
                .addItem("Product 1", 1)
                .addItem("Product 2", 2.5)
                
                .addFeed()

                // Add Barcode Examples
                .addComponent(new POSText.Builder("Barcode Examples:").setStyle(POSPrintStyle.BOLD).build())
                .addComponent(new POSBarcode.Builder("123456789012")
                        .setType(POSBarcodeType.JAN13_EAN13)
                        .setWidth(POSBarcodeWidth.THIN)
                        .build())
                .addComponent(new POSBarcode.Builder("987654321098")
                        .setType(POSBarcodeType.CODE128)
                        .setWidth(POSBarcodeWidth.THICK)
                        .build())

                // Add QR Code Examples
                .addComponent(new POSText.Builder("QR Code Examples:").setStyle(POSPrintStyle.BOLD).build())
                .addComponent(new POSQRCode.Builder("https://example.com")
                        .setSize(POSQRCodeSize.SMALL)
                        .setErrorCorrection(POSQRCodeErrorCorrection.LOW)
                        .build())
                .addComponent(new POSQRCode.Builder("https://example.com")
                        .setSize(POSQRCodeSize.LARGE)
                        .setErrorCorrection(POSQRCodeErrorCorrection.HIGH)
                        .build())

                // Total Line
                .addComponent(new POSText.Builder("----------------------").build())
                .addComponent(new POSText.Builder("Total Amount: 99.99").setStyle(POSPrintStyle.BOLD).build())

                // Footer
                .setFooter("Thank you for using ESC/POS!")

                // Final line feeds before cutting the page
                .addComponent(new POSText.Builder("\n\n").build())

                .build();

        // Print the receipt
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
