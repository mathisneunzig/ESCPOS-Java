package de.neunziglabs.escpos;

import java.util.ArrayList;
import java.util.List;

public abstract class POSAddressStrategyBase implements POSAddressBuildStrategy {

    protected final List<String> normalizeLines(POSAddressModel m) {
        List<String> lines = new ArrayList<>();

        lines.addAll(m.extraLinesBefore());

        addIfPresent(lines, m.company());
        addIfPresent(lines, m.recipientName());

        addIfPresent(lines, m.additionalLine());

        String streetLine = joinSpace(m.street(), m.houseNumber());
        addIfPresent(lines, streetLine);

        addIfPresent(lines, m.building());

        addIfPresent(lines, m.subLocality1());
        addIfPresent(lines, m.subLocality2());
        addIfPresent(lines, m.subLocality3());

        addIfPresent(lines, m.locality());
        addIfPresent(lines, m.district());
        addIfPresent(lines, m.prefecture());

        String cityLine = joinSpace(m.postalCode(), m.city());
        addIfPresent(lines, cityLine);

        addIfPresent(lines, m.region());
        addIfPresent(lines, m.country());

        lines.addAll(m.extraLinesAfter());

        return lines;
    }

    protected final void printLines(POSAddress target, POSAddressModel m, List<String> lines) {
        for (String line : lines) {
            target.addComponent(new POSText.Builder(line)
                .setAlignment(m.alignment())
                .setStyle(m.styles())
                .build());
        }
    }

    protected static void addIfPresent(List<String> lines, String line) {
        if (line == null) return;
        String t = line.trim();
        if (!t.isEmpty()) lines.add(t);
    }

    protected static String joinSpace(String a, String b) {
        String aa = a == null ? "" : a.trim();
        String bb = b == null ? "" : b.trim();
        if (aa.isEmpty() && bb.isEmpty()) return null;
        if (aa.isEmpty()) return bb;
        if (bb.isEmpty()) return aa;
        return aa + " " + bb;
    }
}
