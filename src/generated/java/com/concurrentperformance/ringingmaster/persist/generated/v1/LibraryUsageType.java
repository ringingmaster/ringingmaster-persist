//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.28 at 08:25:11 AM BST 
//


package com.concurrentperformance.ringingmaster.persist.generated.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for libraryUsageType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="libraryUsageType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CC_LIBRARY"/>
 *     &lt;enumeration value="USER_LIBRARY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "libraryUsageType")
@XmlEnum
public enum LibraryUsageType {

    CC_LIBRARY,
    USER_LIBRARY;

    public String value() {
        return name();
    }

    public static LibraryUsageType fromValue(String v) {
        return valueOf(v);
    }

}
