package org.ringingmaster.persist;

import org.ringingmaster.persist.generated.v1.CompositionPersist;
import org.ringingmaster.persist.generated.v1.LibraryUsagePersist;
import org.ringingmaster.persist.generated.v1.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class handle's the document marshalling. ot the conversion to the domain objects.
 *
 * @author Steve Lake
 */
    public class NotationLibraryPersister {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public static final long NOTATION_LIBRARY_CURRENT_VERSION = 1;
    public static final String XML_BASE_PACKAGE = "org.ringingmaster.persist.generated.v" + NOTATION_LIBRARY_CURRENT_VERSION;


    ObjectFactory objectFactory = new ObjectFactory();

    public void writeNotationLibrary(final org.ringingmaster.persist.generated.v1.NotationLibraryPersist notationLibrary, Path path, NotationLibraryUsage usage) throws IOException, JAXBException {
        checkNotNull(notationLibrary);
        checkNotNull(path);

        notationLibrary.setDocumentVersion(NOTATION_LIBRARY_CURRENT_VERSION);
        checkAndSetLibraryType(notationLibrary, usage);

        path = path.toAbsolutePath().normalize();

        OutputStream outputStream = null;
        try {
            log.info("Writing the Notation library to [" + path + "]");

            outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            final JAXBContext jc = JAXBContext.newInstance(XML_BASE_PACKAGE);
            final Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(new ObjectFactory().createNotationLibrary(notationLibrary), outputStream);

            outputStream.flush();

            // Also to Std out for debug.
            //m.marshal(notationLibrary, System.out);

        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (final IOException e) {
                //Deliberately empty
            }
        }
    }

    private void checkAndSetLibraryType(org.ringingmaster.persist.generated.v1.NotationLibraryPersist notationLibrary, NotationLibraryUsage libraryUsage) {
        if (notationLibrary.getLibraryUsage() != null) {
            log.warn("Forcing library name from [{}] to [{}]", notationLibrary.getLibraryUsage(), libraryUsage);
        }
        notationLibrary.setLibraryUsage(LibraryUsagePersist.fromValue(libraryUsage.toString()));
    }

    public org.ringingmaster.persist.generated.v1.NotationLibraryPersist readNotationLibrary(Path path) {
        path = path.toAbsolutePath().normalize();
        log.info(">>> de-serialising from [{}]", path);

        org.ringingmaster.persist.generated.v1.NotationLibraryPersist notations = null;

        InputStream inputStream = null;
        try {
            inputStream = Files.newInputStream(path, StandardOpenOption.READ);

            final JAXBContext jc = JAXBContext.newInstance(XML_BASE_PACKAGE);
            final Unmarshaller unmarshaller = jc.createUnmarshaller();
            @SuppressWarnings("unchecked")
            JAXBElement<org.ringingmaster.persist.generated.v1.NotationLibraryPersist> notationLibrary = (JAXBElement<org.ringingmaster.persist.generated.v1.NotationLibraryPersist>) unmarshaller.unmarshal(inputStream);

            notations = notationLibrary.getValue();
        } catch (final IOException e) {
            log.error("Exception deserializing notations", e);
        } catch (final JAXBException e) {
            log.error("Exception unmarshalling notations", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (final IOException e) {
                //Deliberately empty
            }
        }
        log.info("<<< de-serialising from [{}]", path);

        return notations;
    }


    public void writeComposition(CompositionPersist composition, Path path) throws IOException, JAXBException {
        checkNotNull(composition);
        checkNotNull(path);

        composition.setDocumentVersion(NOTATION_LIBRARY_CURRENT_VERSION);

        path = path.toAbsolutePath().normalize();

        OutputStream outputStream = null;
        try {
            log.info("Writing the Composition to [" + path + "]");

            outputStream = Files.newOutputStream(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            final JAXBContext jc = JAXBContext.newInstance(XML_BASE_PACKAGE);
            final Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(objectFactory.createComposition(composition), outputStream);


            outputStream.flush();

            // Also to Std out for debug.
            //m.marshal(notationLibrary, System.out);

        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (final IOException e) {
                //Deliberately empty
            }
        }

    }

    public CompositionPersist readComposition(Path path) {
        path = path.toAbsolutePath().normalize();

        CompositionPersist compositionPersist = null;

        InputStream inputStream = null;
        try {
            log.info("de-serialising from [{}]", path);
            inputStream = Files.newInputStream(path, StandardOpenOption.READ);

            final JAXBContext jc = JAXBContext.newInstance(XML_BASE_PACKAGE);
            final Unmarshaller unmarshaller = jc.createUnmarshaller();
            @SuppressWarnings("unchecked")
            JAXBElement<CompositionPersist> notationLibrary = (JAXBElement<CompositionPersist>) unmarshaller.unmarshal(inputStream);
            compositionPersist = notationLibrary.getValue();
        } catch (final NoSuchFileException e) {
            throw new RuntimeException("Cant find path [" + path.toString() + "]", e);
        } catch (final IOException e) {
            throw new RuntimeException("Exception deserializing composition", e);
        } catch (final JAXBException e) {
            throw new RuntimeException("Exception unmarshalling composition", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (final IOException e) {
                //Deliberately empty
            }
        }

        return compositionPersist;
    }
}
