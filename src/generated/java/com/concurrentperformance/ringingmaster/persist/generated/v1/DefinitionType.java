//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.28 at 08:42:19 AM BST 
//


package com.concurrentperformance.ringingmaster.persist.generated.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for definitionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="definitionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="shorthand" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="notation" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "definitionType")
public class DefinitionType {

    @XmlAttribute(name = "shorthand", required = true)
    protected String shorthand;
    @XmlAttribute(name = "notation", required = true)
    protected String notation;

    /**
     * Gets the value of the shorthand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShorthand() {
        return shorthand;
    }

    /**
     * Sets the value of the shorthand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShorthand(String value) {
        this.shorthand = value;
    }

    /**
     * Gets the value of the notation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotation() {
        return notation;
    }

    /**
     * Sets the value of the notation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotation(String value) {
        this.notation = value;
    }

}
