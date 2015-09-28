//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.28 at 08:38:14 AM BST 
//


package com.concurrentperformance.ringingmaster.persist.generated.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for touchPersist complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="touchPersist">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="documentVersion" type="{http://www.ringingmaster.org.uk/touch/v1}documentVersionType"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberOfBells" type="{http://www.ringingmaster.org.uk/touch/v1}numberOfBellsPersist"/>
 *         &lt;element name="touchChecking" type="{http://www.ringingmaster.org.uk/touch/v1}touchCheckingType"/>
 *         &lt;element name="callFrom" type="{http://www.ringingmaster.org.uk/touch/v1}bellsPersist"/>
 *         &lt;element name="notation" type="{http://www.ringingmaster.org.uk/touch/v1}touchNotationPersist" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nonSplicedActiveNotation" type="{http://www.ringingmaster.org.uk/touch/v1}notationKeyPersist" minOccurs="0"/>
 *         &lt;element name="spliced" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="plainLeadToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="definition" type="{http://www.ringingmaster.org.uk/touch/v1}definitionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "touchPersist", propOrder = {
    "documentVersion",
    "title",
    "author",
    "numberOfBells",
    "touchChecking",
    "callFrom",
    "notation",
    "nonSplicedActiveNotation",
    "spliced",
    "plainLeadToken",
    "definition"
})
public class TouchPersist {

    @XmlElement(required = true)
    protected DocumentVersionType documentVersion;
    @XmlElement(required = true)
    protected String title;
    protected String author;
    protected int numberOfBells;
    @XmlElement(required = true)
    protected TouchCheckingType touchChecking;
    protected int callFrom;
    protected List<TouchNotationPersist> notation;
    protected NotationKeyPersist nonSplicedActiveNotation;
    protected boolean spliced;
    protected String plainLeadToken;
    protected List<DefinitionType> definition;

    /**
     * Gets the value of the documentVersion property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentVersionType }
     *     
     */
    public DocumentVersionType getDocumentVersion() {
        return documentVersion;
    }

    /**
     * Sets the value of the documentVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentVersionType }
     *     
     */
    public void setDocumentVersion(DocumentVersionType value) {
        this.documentVersion = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the author property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the value of the author property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthor(String value) {
        this.author = value;
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

    /**
     * Gets the value of the touchChecking property.
     * 
     * @return
     *     possible object is
     *     {@link TouchCheckingType }
     *     
     */
    public TouchCheckingType getTouchChecking() {
        return touchChecking;
    }

    /**
     * Sets the value of the touchChecking property.
     * 
     * @param value
     *     allowed object is
     *     {@link TouchCheckingType }
     *     
     */
    public void setTouchChecking(TouchCheckingType value) {
        this.touchChecking = value;
    }

    /**
     * Gets the value of the callFrom property.
     * 
     */
    public int getCallFrom() {
        return callFrom;
    }

    /**
     * Sets the value of the callFrom property.
     * 
     */
    public void setCallFrom(int value) {
        this.callFrom = value;
    }

    /**
     * Gets the value of the notation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the notation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TouchNotationPersist }
     * 
     * 
     */
    public List<TouchNotationPersist> getNotation() {
        if (notation == null) {
            notation = new ArrayList<TouchNotationPersist>();
        }
        return this.notation;
    }

    /**
     * Gets the value of the nonSplicedActiveNotation property.
     * 
     * @return
     *     possible object is
     *     {@link NotationKeyPersist }
     *     
     */
    public NotationKeyPersist getNonSplicedActiveNotation() {
        return nonSplicedActiveNotation;
    }

    /**
     * Sets the value of the nonSplicedActiveNotation property.
     * 
     * @param value
     *     allowed object is
     *     {@link NotationKeyPersist }
     *     
     */
    public void setNonSplicedActiveNotation(NotationKeyPersist value) {
        this.nonSplicedActiveNotation = value;
    }

    /**
     * Gets the value of the spliced property.
     * 
     */
    public boolean isSpliced() {
        return spliced;
    }

    /**
     * Sets the value of the spliced property.
     * 
     */
    public void setSpliced(boolean value) {
        this.spliced = value;
    }

    /**
     * Gets the value of the plainLeadToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlainLeadToken() {
        return plainLeadToken;
    }

    /**
     * Sets the value of the plainLeadToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlainLeadToken(String value) {
        this.plainLeadToken = value;
    }

    /**
     * Gets the value of the definition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the definition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDefinition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DefinitionType }
     * 
     * 
     */
    public List<DefinitionType> getDefinition() {
        if (definition == null) {
            definition = new ArrayList<DefinitionType>();
        }
        return this.definition;
    }

}