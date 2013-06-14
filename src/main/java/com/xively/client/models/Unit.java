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

	public enum IFCClassification {
		BASIC_SI("basicSI"),
		DERIVED_SI("derivedSI"),
		CONVERSION_BASED_UNITS("conservationBasedUnits"),
		DERIVED_UNITS("derivedUnits"),
		CONTEXT_DEPENDENT_UNITS("contextDependentUnits");

		private final String value;

		private IFCClassification(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

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
	}

	private String symbol;
	private String label;
	private IFCClassification type;

	/**
	 * @return the type
	 */
	public IFCClassification getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */

	public void setType(IFCClassification type) {
		this.type = type;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 *            the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(67, 43).append(symbol).append(label)
				.append(type).toHashCode();
	}

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

		return new EqualsBuilder().append(symbol, other.symbol)
				.append(label, other.label).append(type, other.type).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("symbol", symbol)
				.append("label", label).append("type", type).toString();
	}
}
