package com.vedantu.responses;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AbstractRes {
	public AbstractRes() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
