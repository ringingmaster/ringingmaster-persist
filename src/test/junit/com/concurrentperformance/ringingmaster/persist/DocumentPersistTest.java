package com.concurrentperformance.ringingmaster.persist;

import com.concurrentperformance.ringingmaster.persist.generated.v1.PersistableNotation;
import com.concurrentperformance.ringingmaster.persist.generated.v1.PersistableNotationLibrary;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * TODO Comments
 *
 * @author Lake
 */
public class DocumentPersistTest {

	private static final Path BASE_DIR = Paths.get("src", "test", "dump");


	@Before
	public void setup() throws IOException {

		if (Files.exists(BASE_DIR)) {
			try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(BASE_DIR)) {
				for (Path path : directoryStream) {
					Files.delete(path);
				}
			} catch (IOException ex) {
			}

		}
		else {
			Files.createDirectory(BASE_DIR);
		}
	}



	@Test
	public void canSerialiseLibrary() throws IOException, JAXBException {

		PersistableNotationLibrary persistableNotationLibrary = new PersistableNotationLibrary();
		persistableNotationLibrary.setNotes("NOTES");
		PersistableNotation persistableNotation = new PersistableNotation();
		persistableNotation.setNumberOfBells(8);
		persistableNotation.setNotation("12.34");
		persistableNotation.setNotation2("-");
		persistableNotation.setFoldedPalindrome(false);
		persistableNotation.setName("TEST");
		persistableNotation.setLeadLength(234);
		persistableNotation.setLeadHead("AA");
		persistableNotationLibrary.getNotation().add(persistableNotation);


		Path path = BASE_DIR.resolve("library.xml");
		new DocumentPersist().writeNotationLibrary(persistableNotationLibrary, path, NotationLibraryType.CC_LIBRARY);
		PersistableNotationLibrary result = new DocumentPersist().readNotationLibrary(path);

		assertEquals(1, result.getNotation().size());

		PersistableNotation persistableNotationResult = result.getNotation().get(0);
		assertEquals(8, persistableNotationResult.getNumberOfBells());
		assertEquals("12.34", persistableNotationResult.getNotation());
		assertEquals("-", persistableNotationResult.getNotation2());
		assertEquals(false, persistableNotationResult.isFoldedPalindrome());
		assertEquals("TEST", persistableNotationResult.getName());
		assertEquals("AA", persistableNotationResult.getLeadHead());
		assertEquals(234, persistableNotationResult.getLeadLength());

	}

	@Test
	public void canSerialiseEmptyLibrary() throws IOException, JAXBException {

		PersistableNotationLibrary persistableNotationLibrary = new PersistableNotationLibrary();
		Path path = BASE_DIR.resolve("emptyLibrary.xml");
		new DocumentPersist().writeNotationLibrary(persistableNotationLibrary, path, NotationLibraryType.CC_LIBRARY);
		PersistableNotationLibrary result = new DocumentPersist().readNotationLibrary(path);

		assertEquals(0, result.getNotation().size());
	}

}