package com.bullhorn.persistence.timecurrent.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Attribute and Expression mapping for a Map")
public class MapKV {

	@ApiModelProperty(notes = "Destination JSON attribute name")
	private String attribute;
	@ApiModelProperty(notes = "Expression based on Source JSON attributes")
	private String expression;

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public MapKV(String attribute, String expression) {
		super();
		this.attribute = attribute;
		this.expression = expression;
	}

	public MapKV() {
		super();
	}

}
