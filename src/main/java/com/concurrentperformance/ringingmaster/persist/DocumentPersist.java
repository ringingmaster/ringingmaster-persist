package com.concurrentperformance.ringingmaster.persist;

import com.concurrentperformance.ringingmaster.persist.generated.v1.DocumentVersionPersist;
import com.concurrentperformance.ringingmaster.persist.generated.v1.LibraryUsagePersist;
import com.concurrentperformance.ringingmaster.persist.generated.v1.NotationLibraryPersist;
import com.concurrentperformance.ringingmaster.persist.generated.v1.ObjectFactory;
import com.concurrentperformance.ringingmaster.persist.generated.v1.TouchPersist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class handle's the document marshalling. ot the conversion to the domain objects.
 *
 * @author Lake
 */
public class DocumentPersist {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public static final long NOTATION_LIBRARY_CURRENT_VERSION = 1;
	public static final String XML_BASE_PACKAGE = "com.concurrentperformance.ringingmaster.persist.generated.v" + NOTATION_LIBRARY_CURRENT_VERSION;


	ObjectFactory objectFactory = new ObjectFactory();

	public void writeNotationLibrary(final NotationLibraryPersist notationLibrary, Path path, NotationLibraryUsage usage) throws IOException, JAXBException {
		checkNotNull(notationLibrary);
		checkNotNull(path);

		notationLibrary.setVersion(buildCurrentVersion());
		checkAndSetLibraryType(notationLibrary, usage);

		path = path.toAbsolutePath().normalize();

		OutputStream outputStream = null;
		try {
			log.info("Writing the Notation library to [" + path + "]");

			outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING );

			final JAXBContext jc = JAXBContext.newInstance(XML_BASE_PACKAGE);
			final Marshaller m = jc.createMarshaller();
			m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );

			m.marshal(new ObjectFactory().createNotationLibrary(notationLibrary), outputStream);

			outputStream.flush();

			// Also to Std out for debug.
			//m.marshal(notationLibrary, System.out);

		}
		finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (final IOException e) {}
		}
	}

	private void checkAndSetLibraryType(NotationLibraryPersist notationLibrary, NotationLibraryUsage libraryUsage) {
		if (notationLibrary.getLibraryUsage() != null) {
			log.warn("Forcing library name from [{}] to [{}]", notationLibrary.getLibraryUsage(), libraryUsage);
		}
		notationLibrary.setLibraryUsage(LibraryUsagePersist.fromValue(libraryUsage.toString()));
	}

	public NotationLibraryPersist readNotationLibrary(Path path) {
		path = path.toAbsolutePath().normalize();

		NotationLibraryPersist notations = null;

		InputStream inputStream = null;
		try {
			log.info("de-serialising from [{}]", path);
			inputStream = Files.newInputStream(path, StandardOpenOption.READ);

			final JAXBContext jc = JAXBContext.newInstance( XML_BASE_PACKAGE);
			final Unmarshaller unmarshaller = jc.createUnmarshaller();
			JAXBElement<NotationLibraryPersist> notationLibrary = (JAXBElement<NotationLibraryPersist>) unmarshaller.unmarshal(inputStream);

			notations = notationLibrary.getValue();
		} catch (final IOException e) {
			log.error("Exception deserializing notations", e);
		} catch (final JAXBException e) {
			log.error("Exception unmarshalling notations", e);
		}
		finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException e) {}
		}

		return notations;
	}


	public void writeTouch(TouchPersist touch, Path path)  throws IOException, JAXBException {
		checkNotNull(touch);
		checkNotNull(path);

		touch.setDocumentVersion(buildCurrentVersion());

		path = path.toAbsolutePath().normalize();

		OutputStream outputStream = null;
		try {
			log.info("Writing the Notation library to [" + path + "]");

			outputStream = Files.newOutputStream(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING );

			final JAXBContext jc = JAXBContext.newInstance(XML_BASE_PACKAGE);
			final Marshaller m = jc.createMarshaller();
			m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );

			m.marshal(objectFactory.createTouch(touch), outputStream);


			outputStream.flush();

			// Also to Std out for debug.
			//m.marshal(notationLibrary, System.out);

		}
		finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (final IOException e) {}
		}

	}

	public TouchPersist readTouch(Path path) {
		path = path.toAbsolutePath().normalize();

		TouchPersist touch = null;

		InputStream inputStream = null;
		try {
			log.info("de-serialising from [{}]", path);
			inputStream = Files.newInputStream(path, StandardOpenOption.READ);

			final JAXBContext jc = JAXBContext.newInstance( XML_BASE_PACKAGE);
			final Unmarshaller unmarshaller = jc.createUnmarshaller();
			JAXBElement<TouchPersist> notationLibrary = (JAXBElement<TouchPersist>) unmarshaller.unmarshal(inputStream);

			touch = notationLibrary.getValue();
		} catch (final IOException e) {
			log.error("Exception deserializing touch", e);
		} catch (final JAXBException e) {
			log.error("Exception unmarshalling touch", e);
		}
		finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException e) {}
		}

		return touch;
	}

	private DocumentVersionPersist buildCurrentVersion() {
		DocumentVersionPersist versionType = new DocumentVersionPersist();
		versionType.setVersion(NOTATION_LIBRARY_CURRENT_VERSION);
		return versionType;
	}

}
