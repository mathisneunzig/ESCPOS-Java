package de.neunziglabs.escpos;

public interface POSAddressBuildStrategy {
    void build(POSAddress target, POSAddressModel model);
}
