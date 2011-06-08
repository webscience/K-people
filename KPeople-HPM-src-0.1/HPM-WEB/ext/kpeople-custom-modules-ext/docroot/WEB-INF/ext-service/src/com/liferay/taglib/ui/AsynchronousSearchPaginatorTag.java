/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.taglib.ui;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class AsynchronousSearchPaginatorTag extends SearchFormTag {

    public void setType(String type) {
        _type = type;
    }

    protected void cleanUp() {
        super.cleanUp();

        _type = "regular";
    }

    protected String getPage() {
        return _PAGE;
    }

    protected void setAttributes(HttpServletRequest request) {
        super.setAttributes(request);

        request.setAttribute("liferay-ui:search:type", _type);
        
        request.setAttribute(
                "webscience-ui:search-iterator:divId", String.valueOf(divId));
        request.setAttribute(
                "webscience-ui:search-iterator:formId", String.valueOf(formId));
    }

    private static final String _PAGE =
        "/html/taglib/ui/asynchronous_search_paginator/page.jsp";

    /**
     * @return the divId
     */
    public String getDivId() {
        return divId;
    }

    /**
     * @param divId the divId to set
     */
    public void setDivId(String divId) {
        this.divId = divId;
    }

    /**
     * @return the formId
     */
    public String getFormId() {
        return formId;
    }

    /**
     * @param formId the formId to set
     */
    public void setFormId(String formId) {
        this.formId = formId;
    }

    private String _type = "regular";
    private String divId;
    private String formId;
}