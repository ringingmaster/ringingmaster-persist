//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.28 at 08:13:40 AM BST 
//


package com.concurrentperformance.ringingmaster.persist.generated.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for libraryNotationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="libraryNotationType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ringingmaster.org.uk/touch/v1}baseNotationType">
 *       &lt;attribute name="leadHead" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="leadLength" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "libraryNotationType")
public class LibraryNotationType
    extends BaseNotationType
{

    @XmlAttribute(name = "leadHead")
    protected String leadHead;
    @XmlAttribute(name = "leadLength", required = true)
    protected int leadLength;

    /**
     * Gets the value of the leadHead property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeadHead() {
        return leadHead;
    }

    /**
     * Sets the value of the leadHead property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeadHead(String value) {
        this.leadHead = value;
    }

    /**
     * Gets the value of the leadLength property.
     * 
     */
    public int getLeadLength() {
        return leadLength;
    }

    /**
     * Sets the value of the leadLength property.
     * 
     */
    public void setLeadLength(int value) {
        this.leadLength = value;
    }

}
