//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.28 at 08:42:19 AM BST 
//


package com.concurrentperformance.ringingmaster.persist.generated.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for callPersist complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="callPersist">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="shorthand" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="notation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="default" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callPersist", propOrder = {
    "name",
    "shorthand",
    "notation",
    "_default"
})
public class CallPersist {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String shorthand;
    @XmlElement(required = true)
    protected String notation;
    @XmlElement(name = "default")
    protected boolean _default;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

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

    /**
     * Gets the value of the default property.
     * 
     */
    public boolean isDefault() {
        return _default;
    }

    /**
     * Sets the value of the default property.
     * 
     */
    public void setDefault(boolean value) {
        this._default = value;
    }

}
