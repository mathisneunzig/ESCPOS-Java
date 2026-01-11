package de.neunziglabs.escpos;

public record POSAddressModel(
    POSAddressType type,
    String recipientName,
    String company,
    String street,
    String houseNumber,
    String building,
    String additionalLine,
    String postalCode,
    String city,
    String region,
    String country,
    String prefecture,
    String district,
    String locality,
    String subLocality1,
    String subLocality2,
    String subLocality3,
    POSTextAlignment alignment,
    POSPrintStyle[] styles,
    java.util.List<String> extraLinesBefore,
    java.util.List<String> extraLinesAfter
) {}
