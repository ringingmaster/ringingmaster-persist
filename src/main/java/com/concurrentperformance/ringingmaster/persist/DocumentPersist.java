package com.concurrentperformance.ringingmaster.persist;

import com.concurrentperformance.ringingmaster.persist.generated.v1.NotationLibrary;
import com.concurrentperformance.ringingmaster.persist.generated.v1.Touch;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
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

	public static final String XML_BASE_PACKAGE = "com.concurrentperformance.ringingmaster.persist.generated.v1";

	public static final long NOTATION_LIBRARY_CURRENT_VERSION = 1;

	public void writeNotationLibrary(final NotationLibrary notationLibrary, Path path, NotationLibraryType type) throws IOException, JAXBException {
		checkNotNull(notationLibrary);
		checkNotNull(path);

		checkAndSetVersion(notationLibrary);
		checkAndSetLibraryType(notationLibrary, type);

		path = path.toAbsolutePath().normalize();

		OutputStream outputStream = null;
		try {
			log.info("Writing the Notation library to [" + path + "]");

			outputStream = Files.newOutputStream(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING );

			final JAXBContext jc = JAXBContext.newInstance(XML_BASE_PACKAGE);
			final Marshaller m = jc.createMarshaller();
			m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );

			m.marshal(notationLibrary, outputStream);

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

	private void checkAndSetVersion(NotationLibrary notationLibrary) {
		if (notationLibrary.getVersion() != NOTATION_LIBRARY_CURRENT_VERSION &&
				notationLibrary.getVersion() != 0) {
			log.warn("Forcing library version from [{}] to [{}]", notationLibrary.getVersion(), NOTATION_LIBRARY_CURRENT_VERSION);
		}
		notationLibrary.setVersion(NOTATION_LIBRARY_CURRENT_VERSION);
	}

	private void checkAndSetLibraryType(NotationLibrary notationLibrary, NotationLibraryType type) {
		if (!Strings.isNullOrEmpty(notationLibrary.getLibraryType())) {
			log.warn("Forcing library name from [{}] to [{}]", notationLibrary.getLibraryType(), type);
		}
		notationLibrary.setLibraryType(type.toString());
	}


	public NotationLibrary readNotationLibrary(Path path) {
		path = path.toAbsolutePath().normalize();

		NotationLibrary notations = null;

		InputStream inputStream = null;
		try {
			log.info("de-serialising from [{}]", path);
			inputStream = Files.newInputStream(path, StandardOpenOption.READ);

			final JAXBContext jc = JAXBContext.newInstance( XML_BASE_PACKAGE);
			final Unmarshaller unmarshaller = jc.createUnmarshaller();
			notations = (NotationLibrary) unmarshaller.unmarshal(inputStream);

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

	public void writeTouch(Touch touch, Path path)  throws IOException, JAXBException {
		checkNotNull(touch);
		checkNotNull(path);

		//TODO checkAndSetVersion(touch);
		//TODO checkAndSetLibraryType(notationLibrary, type);

		path = path.toAbsolutePath().normalize();

		OutputStream outputStream = null;
		try {
			log.info("Writing the Notation library to [" + path + "]");

			outputStream = Files.newOutputStream(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING );

			final JAXBContext jc = JAXBContext.newInstance(XML_BASE_PACKAGE);
			final Marshaller m = jc.createMarshaller();
			m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );

			m.marshal(touch, outputStream);

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

	public Touch readTouch(Path path) {
		return null;

	}
}
