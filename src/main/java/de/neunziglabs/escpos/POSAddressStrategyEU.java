package de.neunziglabs.escpos;

import java.util.ArrayList;
import java.util.List;

public final class POSAddressStrategyEU extends POSAddressStrategyBase {
    @Override
    public void build(POSAddress target, POSAddressModel model) {
        List<String> ordered = new ArrayList<>();

        ordered.addAll(model.extraLinesBefore());

        addIfPresent(ordered, model.company());
        addIfPresent(ordered, model.recipientName());
        addIfPresent(ordered, model.additionalLine());

        addIfPresent(ordered, joinSpace(model.street(), model.houseNumber()));
        addIfPresent(ordered, model.building());

        addIfPresent(ordered, joinSpace(model.postalCode(), model.city()));
        addIfPresent(ordered, model.region());
        addIfPresent(ordered, model.country());

        ordered.addAll(model.extraLinesAfter());

        printLines(target, model, ordered);
    }
}
