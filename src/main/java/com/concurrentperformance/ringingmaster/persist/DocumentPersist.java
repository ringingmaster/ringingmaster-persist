package com.concurrentperformance.ringingmaster.persist;

import com.concurrentperformance.ringingmaster.persist.generated.v1.DocumentVersionType;
import com.concurrentperformance.ringingmaster.persist.generated.v1.LibraryUsageType;
import com.concurrentperformance.ringingmaster.persist.generated.v1.NotationLibraryType;
import com.concurrentperformance.ringingmaster.persist.generated.v1.ObjectFactory;
import com.concurrentperformance.ringingmaster.persist.generated.v1.TouchType;
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
 * TODO Comments
 *
 * @author Lake
 */
public class DocumentPersist {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public static final long NOTATION_LIBRARY_CURRENT_VERSION = 1;
	public static final String XML_BASE_PACKAGE = "com.concurrentperformance.ringingmaster.persist.generated.v" + NOTATION_LIBRARY_CURRENT_VERSION;

	public void writeNotationLibrary(final NotationLibraryType notationLibrary, Path path, NotationLibraryUsage usage) throws IOException, JAXBException {
		checkNotNull(notationLibrary);
		checkNotNull(path);

		notationLibrary.setVersion(buildCurrentVersion());
		checkAndSetLibraryType(notationLibrary, usage);

		path = path.toAbsolutePath().normalize();

		OutputStream outputStream = null;
		try {
			log.info("Writing the Notation library to [" + path + "]");

			outputStream = Files.newOutputStream(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING );

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

	private void checkAndSetLibraryType(NotationLibraryType notationLibrary, NotationLibraryUsage libraryUsage) {
		if (notationLibrary.getLibraryUsage() != null) {
			log.warn("Forcing library name from [{}] to [{}]", notationLibrary.getLibraryUsage(), libraryUsage);
		}
		notationLibrary.setLibraryUsage(LibraryUsageType.fromValue(libraryUsage.toString()));
	}

	public NotationLibraryType readNotationLibrary(Path path) {
		path = path.toAbsolutePath().normalize();

		NotationLibraryType notations = null;

		InputStream inputStream = null;
		try {
			log.info("de-serialising from [{}]", path);
			inputStream = Files.newInputStream(path, StandardOpenOption.READ);

			final JAXBContext jc = JAXBContext.newInstance( XML_BASE_PACKAGE);
			final Unmarshaller unmarshaller = jc.createUnmarshaller();
			JAXBElement<NotationLibraryType> notationLibrary = (JAXBElement<NotationLibraryType>) unmarshaller.unmarshal(inputStream);

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


	public void writeTouch(TouchType touch, Path path)  throws IOException, JAXBException {
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

			m.marshal(new ObjectFactory().createTouch(touch), outputStream);


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

	public TouchType readTouch(Path path) {
		path = path.toAbsolutePath().normalize();

		TouchType touch = null;

		InputStream inputStream = null;
		try {
			log.info("de-serialising from [{}]", path);
			inputStream = Files.newInputStream(path, StandardOpenOption.READ);

			final JAXBContext jc = JAXBContext.newInstance( XML_BASE_PACKAGE);
			final Unmarshaller unmarshaller = jc.createUnmarshaller();
			JAXBElement<TouchType> notationLibrary = (JAXBElement<TouchType>) unmarshaller.unmarshal(inputStream);

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

	private DocumentVersionType buildCurrentVersion() {
		DocumentVersionType versionType = new DocumentVersionType();
		versionType.setVersion(NOTATION_LIBRARY_CURRENT_VERSION);
		return versionType;
	}
}
