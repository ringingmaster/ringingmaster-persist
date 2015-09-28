//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.28 at 08:46:00 AM BST 
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
     * Create an instance of {@link NotationLibraryPersist }
     * 
     */
    public NotationLibraryPersist createNotationLibraryPersist() {
        return new NotationLibraryPersist();
    }

    /**
     * Create an instance of {@link TouchPersist }
     * 
     */
    public TouchPersist createTouchPersist() {
        return new TouchPersist();
    }

    /**
     * Create an instance of {@link NotationKeyPersist }
     * 
     */
    public NotationKeyPersist createNotationKeyPersist() {
        return new NotationKeyPersist();
    }

    /**
     * Create an instance of {@link DocumentVersionPersist }
     * 
     */
    public DocumentVersionPersist createDocumentVersionPersist() {
        return new DocumentVersionPersist();
    }

    /**
     * Create an instance of {@link DefinitionPersist }
     * 
     */
    public DefinitionPersist createDefinitionPersist() {
        return new DefinitionPersist();
    }

    /**
     * Create an instance of {@link TouchNotationPersist }
     * 
     */
    public TouchNotationPersist createTouchNotationPersist() {
        return new TouchNotationPersist();
    }

    /**
     * Create an instance of {@link LibraryNotationPersist }
     * 
     */
    public LibraryNotationPersist createLibraryNotationPersist() {
        return new LibraryNotationPersist();
    }

    /**
     * Create an instance of {@link CallPersist }
     * 
     */
    public CallPersist createCallPersist() {
        return new CallPersist();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TouchPersist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ringingmaster.org.uk/touch/v1", name = "touch")
    public JAXBElement<TouchPersist> createTouch(TouchPersist value) {
        return new JAXBElement<TouchPersist>(_Touch_QNAME, TouchPersist.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotationLibraryPersist }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ringingmaster.org.uk/touch/v1", name = "notationLibrary")
    public JAXBElement<NotationLibraryPersist> createNotationLibrary(NotationLibraryPersist value) {
        return new JAXBElement<NotationLibraryPersist>(_NotationLibrary_QNAME, NotationLibraryPersist.class, null, value);
    }

}
