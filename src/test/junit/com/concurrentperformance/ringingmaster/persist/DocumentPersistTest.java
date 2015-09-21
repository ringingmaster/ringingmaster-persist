package com.concurrentperformance.ringingmaster.persist;

import com.concurrentperformance.ringingmaster.persist.generated.v1.Notation;
import com.concurrentperformance.ringingmaster.persist.generated.v1.NotationLibrary;
import com.concurrentperformance.ringingmaster.persist.generated.v1.Touch;
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

		NotationLibrary notationLibrary = new NotationLibrary();
		notationLibrary.setNotes("NOTES");
		Notation notation = new Notation();
		notation.setNumberOfBells(8);
		notation.setNotation("12.34");
		notation.setNotation2("-");
		notation.setFoldedPalindrome(false);
		notation.setName("TEST");
		notation.setLeadLength(234);
		notation.setLeadHead("AA");
		notationLibrary.getNotation().add(notation);


		Path path = BASE_DIR.resolve("library.xml");
		new DocumentPersist().writeNotationLibrary(notationLibrary, path, NotationLibraryType.CC_LIBRARY);
		NotationLibrary result = new DocumentPersist().readNotationLibrary(path);

		assertEquals(1, result.getNotation().size());

		Notation notationResult = result.getNotation().get(0);
		assertEquals(8, notationResult.getNumberOfBells());
		assertEquals("12.34", notationResult.getNotation());
		assertEquals("-", notationResult.getNotation2());
		assertEquals(false, notationResult.isFoldedPalindrome());
		assertEquals("TEST", notationResult.getName());
		assertEquals("AA", notationResult.getLeadHead());
		assertEquals(234, notationResult.getLeadLength());

	}

	@Test
	public void canSerialiseEmptyLibrary() throws IOException, JAXBException {

		NotationLibrary notationLibrary = new NotationLibrary();
		Path path = BASE_DIR.resolve("emptyLibrary.xml");
		new DocumentPersist().writeNotationLibrary(notationLibrary, path, NotationLibraryType.CC_LIBRARY);
		NotationLibrary result = new DocumentPersist().readNotationLibrary(path);

		assertEquals(0, result.getNotation().size());
	}

	@Test
	public void canSerialiseTouch() throws IOException, JAXBException {

		Touch touch = new Touch();

		Notation notation = new Notation();
		notation.setNumberOfBells(8);
		notation.setNotation("12.34");
		notation.setNotation2("-");
		notation.setFoldedPalindrome(false);
		notation.setName("TEST");
		notation.setLeadLength(234);
		notation.setLeadHead("AA");

		touch.getNotation().add(notation);


		Path path = BASE_DIR.resolve("touch.xml");
		new DocumentPersist().writeTouch(touch, path);
		Touch result = new DocumentPersist().readTouch(path);

		assertEquals(1, result.getNotation().size());

		Notation persistableNotationResult = result.getNotation().get(0);
		assertEquals(8, persistableNotationResult.getNumberOfBells());
		assertEquals("12.34", persistableNotationResult.getNotation());
		assertEquals("-", persistableNotationResult.getNotation2());
		assertEquals(false, persistableNotationResult.isFoldedPalindrome());
		assertEquals("TEST", persistableNotationResult.getName());
		assertEquals("AA", persistableNotationResult.getLeadHead());
		assertEquals(234, persistableNotationResult.getLeadLength());

	}
}