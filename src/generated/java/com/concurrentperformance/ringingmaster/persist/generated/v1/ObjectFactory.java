//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.22 at 06:42:09 PM BST 
//


package com.concurrentperformance.ringingmaster.persist.generated.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.concurrentperformance.ringingmaster.persist.generated.v1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Touch_QNAME = new QName("http://www.ringingmaster.org.uk/touch/v1", "touch");
    private final static QName _NotationLibrary_QNAME = new QName("http://www.ringingmaster.org.uk/touch/v1", "notationLibrary");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.concurrentperformance.ringingmaster.persist.generated.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NotationLibraryType }
     * 
     */
    public NotationLibraryType createNotationLibraryType() {
        return new NotationLibraryType();
    }

    /**
     * Create an instance of {@link TouchType }
     * 
     */
    public TouchType createTouchType() {
        return new TouchType();
    }

    /**
     * Create an instance of {@link NotationType }
     * 
     */
    public NotationType createNotationType() {
        return new NotationType();
    }

    /**
     * Create an instance of {@link DocumentVersionType }
     * 
     */
    public DocumentVersionType createDocumentVersionType() {
        return new DocumentVersionType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TouchType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ringingmaster.org.uk/touch/v1", name = "touch")
    public JAXBElement<TouchType> createTouch(TouchType value) {
        return new JAXBElement<TouchType>(_Touch_QNAME, TouchType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotationLibraryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ringingmaster.org.uk/touch/v1", name = "notationLibrary")
    public JAXBElement<NotationLibraryType> createNotationLibrary(NotationLibraryType value) {
        return new JAXBElement<NotationLibraryType>(_NotationLibrary_QNAME, NotationLibraryType.class, null, value);
    }

}
