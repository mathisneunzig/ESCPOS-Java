package de.neunziglabs.escpos;

public class POSAddress extends POSDocument {
    private POSAddress() {}

    public static class Builder {
        private final POSAddress address = new POSAddress();

        private POSAddressType type = POSAddressType.EU;
        private POSAddressBuildStrategy strategy = POSAddressBuildStrategies.forType(POSAddressType.EU);

        private String recipientName;
        private String company;

        private String street;
        private String houseNumber;
        private String building;
        private String additionalLine;

        private String postalCode;
        private String city;
        private String region;
        private String country;

        private String prefecture;

        private String district;
        private String locality;

        private String subLocality1;
        private String subLocality2;
        private String subLocality3;

        private POSTextAlignment alignment = POSTextAlignment.LEFT;
        private POSPrintStyle[] styles = new POSPrintStyle[0];

        private final java.util.List<String> extraLinesBefore = new java.util.ArrayList<>();
        private final java.util.List<String> extraLinesAfter = new java.util.ArrayList<>();

        public Builder setType(POSAddressType type) {
            this.type = java.util.Objects.requireNonNull(type, "type must not be null");
            this.strategy = POSAddressBuildStrategies.forType(this.type);
            return this;
        }

        public Builder setBuildStrategy(POSAddressBuildStrategy strategy) {
            this.strategy = java.util.Objects.requireNonNull(strategy, "strategy must not be null");
            return this;
        }

        public Builder setAlignment(POSTextAlignment alignment) {
            this.alignment = java.util.Objects.requireNonNull(alignment, "alignment must not be null");
            return this;
        }

        public Builder setStyle(POSPrintStyle... styles) {
            this.styles = (styles == null) ? new POSPrintStyle[0] : styles;
            return this;
        }

        public Builder setRecipientName(String recipientName) {
            this.recipientName = recipientName;
            return this;
        }

        public Builder setCompany(String company) {
            this.company = company;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public Builder setBuilding(String building) {
            this.building = building;
            return this;
        }

        public Builder setAdditionalLine(String additionalLine) {
            this.additionalLine = additionalLine;
            return this;
        }

        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setRegion(String region) {
            this.region = region;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder setPrefecture(String prefecture) {
            this.prefecture = prefecture;
            return this;
        }

        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }

        public Builder setLocality(String locality) {
            this.locality = locality;
            return this;
        }

        public Builder setSubLocality1(String subLocality1) {
            this.subLocality1 = subLocality1;
            return this;
        }

        public Builder setSubLocality2(String subLocality2) {
            this.subLocality2 = subLocality2;
            return this;
        }

        public Builder setSubLocality3(String subLocality3) {
            this.subLocality3 = subLocality3;
            return this;
        }

        public Builder addLineBefore(String line) {
            if (line != null && !line.trim().isEmpty()) {
                extraLinesBefore.add(line);
            }
            return this;
        }

        public Builder addLineAfter(String line) {
            if (line != null && !line.trim().isEmpty()) {
                extraLinesAfter.add(line);
            }
            return this;
        }

        public Builder addComponent(POSComponent component) {
            address.addComponent(component);
            return this;
        }

        public Builder addFeed() {
            address.addLineFeed();
            return this;
        }

        public Builder addFeed(int count) {
            address.addLineFeed(count);
            return this;
        }

        public POSAddress build() {
            strategy.build(address, toModel());
            address.addLineFeed(5);
            return address;
        }

        private POSAddressModel toModel() {
            return new POSAddressModel(
                type,
                recipientName,
                company,
                street,
                houseNumber,
                building,
                additionalLine,
                postalCode,
                city,
                region,
                country,
                prefecture,
                district,
                locality,
                subLocality1,
                subLocality2,
                subLocality3,
                alignment,
                styles,
                java.util.List.copyOf(extraLinesBefore),
                java.util.List.copyOf(extraLinesAfter)
            );
        }
    }
}
