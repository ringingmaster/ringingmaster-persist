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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for notationKeyPersist complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="notationKeyPersist">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="numberOfBells" use="required" type="{http://www.ringingmaster.org.uk/touch/v1}numberOfBellsPersist" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "notationKeyPersist")
@XmlSeeAlso({
    BaseNotationPersist.class
})
public class NotationKeyPersist {

    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "numberOfBells", required = true)
    protected int numberOfBells;

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
     * Gets the value of the numberOfBells property.
     * 
     */
    public int getNumberOfBells() {
        return numberOfBells;
    }

    /**
     * Sets the value of the numberOfBells property.
     * 
     */
    public void setNumberOfBells(int value) {
        this.numberOfBells = value;
    }

}
