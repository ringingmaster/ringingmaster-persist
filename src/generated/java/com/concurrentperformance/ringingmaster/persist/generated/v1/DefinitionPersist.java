//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.01 at 08:15:38 AM BST 
//


package com.concurrentperformance.ringingmaster.persist.generated.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for definitionPersist complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="definitionPersist">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ringingmaster.org.uk/touch/v1}touchCellPersist">
 *       &lt;attribute name="shorthand" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "definitionPersist")
public class DefinitionPersist
    extends TouchCellPersist
{

    @XmlAttribute(name = "shorthand", required = true)
    protected String shorthand;

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

}
