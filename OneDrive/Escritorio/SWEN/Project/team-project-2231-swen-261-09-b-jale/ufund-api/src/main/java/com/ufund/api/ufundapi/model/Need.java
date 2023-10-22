package com.ufund.api.ufundapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a single need, including a name, a cost, and a
 * fulfillment status
 *
 * @author E3M
 */
public class Need {

	// Package private for tests
    public static final String STRING_FORMAT = "Need [name=%s, cost=%.2f, fufilled=%s]";

	// The need's name
	@JsonProperty("name") private String name;
	// The need's cost in dollars
	@JsonProperty("cost") private double cost;
	// Whether the need has been fulfilled yet
	@JsonProperty("fulfilled") private boolean fulfilled;


	/**
	 * Create a new need
	 * @param name the name of the new need
	 * @param cost the cost of the new need, in dollars
	 * @param fulfilled if the need is fulfilled
	 */
	public Need(@JsonProperty("name") String name, @JsonProperty("cost") double cost, @JsonProperty("fulfilled") boolean fulfilled) {
		this.name = name;
		this.cost = cost;
		this.fulfilled = fulfilled;
	}

	/**
	 * Get the name of the need
	 * @return the need's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the cost of the need
	 * @return the need's cost in dollars
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Check if the need is fulfilled
	 * @return whether the need has been fulfilled already
	 */
	public boolean isFulfilled() {
		return fulfilled;
	}

	/**
	 * Mark the need as fulfilled, updating its fulfillment status
	 */
	public void fulfill() {
		this.fulfilled = true;
	}


	/**
	 * Need tostring
	 */
	@Override
    public String toString() {
        return ("Name : \"" + name + "\"\nCost : " + cost + "\nFulfilled : " + fulfilled);
    }
}
