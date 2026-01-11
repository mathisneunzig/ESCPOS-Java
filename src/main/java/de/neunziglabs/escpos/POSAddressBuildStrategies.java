package de.neunziglabs.escpos;

public final class POSAddressBuildStrategies {
    private POSAddressBuildStrategies() {}

    public static POSAddressBuildStrategy forType(POSAddressType type) {
        return switch (type) {
            case EU -> new POSAddressStrategyEU();
//            case JAPAN -> new POSAddressStrategyJapan();
		default -> throw new IllegalArgumentException("Unexpected value: " + type);
        };
    }
}
