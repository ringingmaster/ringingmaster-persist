package com.concurrentperformance.ringingmaster.persist;

import com.concurrentperformance.ringingmaster.persist.generated.v1.DefinitionType;
import com.concurrentperformance.ringingmaster.persist.generated.v1.LibraryNotationType;
import com.concurrentperformance.ringingmaster.persist.generated.v1.NotationKeyType;
import com.concurrentperformance.ringingmaster.persist.generated.v1.NotationLibraryType;
import com.concurrentperformance.ringingmaster.persist.generated.v1.ObjectFactory;
import com.concurrentperformance.ringingmaster.persist.generated.v1.TouchCheckingType;
import com.concurrentperformance.ringingmaster.persist.generated.v1.TouchNotationType;
import com.concurrentperformance.ringingmaster.persist.generated.v1.TouchType;
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

		NotationLibraryType notationLibrary = new NotationLibraryType();
		notationLibrary.setNotes("NOTES");
		LibraryNotationType notation = new LibraryNotationType();
		notation.setNumberOfBells(8);
		notation.setNotation("12.34");
		notation.setNotation2("-");
		notation.setFoldedPalindrome(false);
		notation.setName("TEST");
		notation.setLeadLength(234);
		notation.setLeadHead("AA");
		notationLibrary.getNotation().add(notation);


		Path path = BASE_DIR.resolve("library.xml");
		new DocumentPersist().writeNotationLibrary(notationLibrary, path, NotationLibraryUsage.CC_LIBRARY);
		NotationLibraryType result = new DocumentPersist().readNotationLibrary(path);

		assertEquals(1, result.getNotation().size());

		LibraryNotationType notationResult = result.getNotation().get(0);
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

		NotationLibraryType notationLibrary = new NotationLibraryType();
		Path path = BASE_DIR.resolve("emptyLibrary.xml");
		new DocumentPersist().writeNotationLibrary(notationLibrary, path, NotationLibraryUsage.CC_LIBRARY);
		NotationLibraryType result = new DocumentPersist().readNotationLibrary(path);

		assertEquals(0, result.getNotation().size());
	}

	@Test
	public void canSerialiseTouch() throws IOException, JAXBException {

		TouchType touch = new TouchType();
		touch.setTitle("Touch Title");
		touch.setAuthor("Stephen");
		touch.setNumberOfBells(8);
		touch.setTouchChecking(TouchCheckingType.COURSE_BASED);
		touch.setCallFrom(4);

		NotationKeyType notationKeyType = new ObjectFactory().createNotationKeyType();
		notationKeyType.setName("TEST 2 Royal");
		notationKeyType.setNumberOfBells(8);
		touch.setNonSplicedActiveNotation(notationKeyType);
		touch.setSpliced(true);
		touch.setPlainLeadToken("p");

		DefinitionType definition = new ObjectFactory().createDefinitionType();
		definition.setShorthand("x*");
		definition.setNotation("psp");
		touch.getDefinition().add(definition);

		TouchNotationType notation1 = new TouchNotationType();
		notation1.setName("TEST");
		notation1.setNumberOfBells(8);
		notation1.setNotation("12.34");
		notation1.setNotation2("-");
		notation1.setFoldedPalindrome(false);

		touch.getNotation().add(notation1);

		TouchNotationType notation2 = new TouchNotationType();
		notation2.setName("TEST 2");
		notation2.setNumberOfBells(10);
		notation2.setNotation("12.34");
		notation2.setNotation2("-");
		notation2.setFoldedPalindrome(false);

		touch.getNotation().add(notation2);


		Path path = BASE_DIR.resolve("touch.xml");
		new DocumentPersist().writeTouch(touch, path);
		TouchType result = new DocumentPersist().readTouch(path);

		assertEquals("Touch Title", result.getTitle());
		assertEquals("Stephen", result.getAuthor());
		assertEquals(8, result.getNumberOfBells());
		assertEquals(TouchCheckingType.COURSE_BASED, result.getTouchChecking());
		assertEquals(4, result.getCallFrom());
		assertEquals("TEST 2 Royal", result.getNonSplicedActiveNotation());
		assertEquals(true, result.isSpliced());
		assertEquals("p", result.getPlainLeadToken());
		assertEquals(1, result.getDefinition().size());
		assertEquals("x*", result.getDefinition().get(0).getShorthand());
		assertEquals("psp", result.getDefinition().get(0).getNotation());

		assertEquals(2, result.getNotation().size());
		TouchNotationType persistableNotationResult = result.getNotation().get(0);
		assertEquals(8, persistableNotationResult.getNumberOfBells());
		assertEquals("12.34", persistableNotationResult.getNotation());
		assertEquals("-", persistableNotationResult.getNotation2());
		assertEquals(false, persistableNotationResult.isFoldedPalindrome());
		assertEquals("TEST", persistableNotationResult.getName());


	}
}