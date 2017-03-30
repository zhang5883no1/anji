/******************************************************************************
 * @File name   :      TreeNodeDto.java
 *
 * @Author      :      WEICWANG
 *
 * @Date        :      2012-5-26
 *
 * @Copyright Notice: 
 * Copyright (c) 2011 Capgemini, Inc. All  Rights Reserved.
 * This software is published under the terms of the Capgemini Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------
 * Date                   Who         Version        Comments
 * 2012-5-26 下午4:29:46        WEICWANG     1.0            Initial Version
 *****************************************************************************/
package com.xidu.framework.showcase.sample.dto;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class TreeNodeDto {
    private String id;
    private String text;
    private String state;
    private String checked;
    private Map<String,String> attributes;
    private List<TreeNodeDto> children;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public List<TreeNodeDto> getChildren() {
        return children;
    }
    public void setChildren(List<TreeNodeDto> children) {
        this.children = children;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getChecked() {
        return checked;
    }
    public void setChecked(String checked) {
        this.checked = checked;
    }
    public Map<String, String> getAttributes() {
        return attributes;
    }
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
    
}
