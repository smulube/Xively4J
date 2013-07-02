// Copyright (c) 2003-2013, LogMeIn, Inc. All rights reserved.
// This is part of Xively4J library, it is under the BSD 3-Clause license.
package com.xively.client.models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author sam
 *
 */
public class Unit {

    public static enum IFCClassification {
        BASIC_SI("basicSI"),
        DERIVED_SI("derivedSI"),
        CONVERSION_BASED_UNITS("conservationBasedUnits"),
        DERIVED_UNITS("derivedUnits"),
        CONTEXT_DEPENDENT_UNITS("contextDependentUnits");

        public static IFCClassification fromString(String value) {
            if (value != null) {
                for (IFCClassification classification : IFCClassification.values()) {
                    if (value.equalsIgnoreCase(classification.value)) {
                        return classification;
                    }
                }
            }
            throw new IllegalArgumentException(
                    "No IFCClassification with value: " + value + " found.");
        }

        private final String value;

        private IFCClassification(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }

    private String symbol;
    private String label;
    private IFCClassification type;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        Unit other = (Unit) obj;

        return new EqualsBuilder().append(this.symbol, other.symbol)
                .append(this.label, other.label).append(this.type, other.type).isEquals();
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * @return the symbol
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * @return the type
     */
    public IFCClassification getType() {
        return this.type;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(67, 43).append(this.symbol).append(this.label)
                .append(this.type).toHashCode();
    }

    /**
     * @param label
     *            the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @param symbol
     *            the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * @param type
     *            the type to set
     */

    public void setType(IFCClassification type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("symbol", this.symbol)
                .append("label", this.label).append("type", this.type).toString();
    }
}
