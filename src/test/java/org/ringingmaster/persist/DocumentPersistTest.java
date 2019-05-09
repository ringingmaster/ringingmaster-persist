package org.ringingmaster.persist;

import org.junit.Before;
import org.junit.Test;
import org.ringingmaster.persist.generated.v1.CompositionTypePersist;
import org.ringingmaster.persist.generated.v1.CompositionNotationPersist;
import org.ringingmaster.persist.generated.v1.CompositionPersist;
import org.ringingmaster.persist.generated.v1.DefinitionPersist;
import org.ringingmaster.persist.generated.v1.LibraryNotationPersist;
import org.ringingmaster.persist.generated.v1.NotationKeyPersist;
import org.ringingmaster.persist.generated.v1.NotationLibraryPersist;
import org.ringingmaster.persist.generated.v1.ObjectFactory;

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
 * @author Steve Lake
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

		NotationLibraryPersist notationLibrary = new NotationLibraryPersist();
		notationLibrary.setNotes("NOTES");
		LibraryNotationPersist notation = new LibraryNotationPersist();
		notation.setNumberOfWorkingBells(8);
		notation.setNotation("12.34");
		notation.setNotation2("-");
		notation.setFoldedPalindrome(false);
		notation.setName("TEST");
		notation.setLeadLength(234);
		notation.setLeadHead("AA");
		notationLibrary.getNotation().add(notation);


		Path path = BASE_DIR.resolve("library.xml");
		new DocumentPersist().writeNotationLibrary(notationLibrary, path, NotationLibraryUsage.CC_LIBRARY);
		NotationLibraryPersist result = new DocumentPersist().readNotationLibrary(path);

		assertEquals(1, result.getNotation().size());

		LibraryNotationPersist notationResult = result.getNotation().get(0);
		assertEquals(8, notationResult.getNumberOfWorkingBells());
		assertEquals("12.34", notationResult.getNotation());
		assertEquals("-", notationResult.getNotation2());
		assertEquals(false, notationResult.isFoldedPalindrome());
		assertEquals("TEST", notationResult.getName());
		assertEquals("AA", notationResult.getLeadHead());
		assertEquals(234, notationResult.getLeadLength());

	}

	@Test
	public void canSerialiseEmptyLibrary() throws IOException, JAXBException {

		NotationLibraryPersist notationLibrary = new NotationLibraryPersist();
		Path path = BASE_DIR.resolve("emptyLibrary.xml");
		new DocumentPersist().writeNotationLibrary(notationLibrary, path, NotationLibraryUsage.CC_LIBRARY);
		NotationLibraryPersist result = new DocumentPersist().readNotationLibrary(path);

		assertEquals(0, result.getNotation().size());
	}

	@Test
	public void canSerialiseComposition() throws IOException, JAXBException {

		CompositionPersist composition = new CompositionPersist();
		composition.setTitle("Composition Title");
		composition.setAuthor("Stephen");
		composition.setNumberOfBells(8);
		composition.setCompositionType(CompositionTypePersist.COURSE_BASED);
		composition.setCallFrom(4);
		composition.setStartRow(2);

		NotationKeyPersist notationKeyPersist = new ObjectFactory().createNotationKeyPersist();
		notationKeyPersist.setName("TEST 2 Royal");
		notationKeyPersist.setNumberOfWorkingBells(8);
		composition.setNonSplicedActiveNotation(notationKeyPersist);
		composition.setSpliced(true);
		composition.setPlainLeadToken("p");

		DefinitionPersist definition = new ObjectFactory().createDefinitionPersist();
		definition.setShorthand("x*");
		definition.setCharacters("psp");
		composition.getDefinition().add(definition);

		CompositionNotationPersist notation1 = new CompositionNotationPersist();
		notation1.setName("TEST");
		notation1.setNumberOfWorkingBells(8);
		notation1.setNotation("12.34");
		notation1.setNotation2("-");
		notation1.setFoldedPalindrome(false);

		composition.getNotation().add(notation1);

		CompositionNotationPersist notation2 = new CompositionNotationPersist();
		notation2.setName("TEST 2");
		notation2.setNumberOfWorkingBells(10);
		notation2.setNotation("12.34");
		notation2.setNotation2("-");
		notation2.setFoldedPalindrome(false);

		composition.getNotation().add(notation2);


		Path path = BASE_DIR.resolve("composition.xml");
		new DocumentPersist().writeComposition(composition, path);
		CompositionPersist result = new DocumentPersist().readComposition(path);

		assertEquals("Composition Title", result.getTitle());
		assertEquals("Stephen", result.getAuthor());
		assertEquals(8, result.getNumberOfBells());
		assertEquals(CompositionTypePersist.COURSE_BASED, result.getCompositionType());
		assertEquals(4, result.getCallFrom());
		assertEquals("TEST 2 Royal", result.getNonSplicedActiveNotation().getName());
		assertEquals(true, result.isSpliced());
		assertEquals("p", result.getPlainLeadToken());
		assertEquals(1, result.getDefinition().size());
		assertEquals("x*", result.getDefinition().get(0).getShorthand());
		assertEquals("psp", result.getDefinition().get(0).getCharacters());

		assertEquals(2, result.getNotation().size());
		CompositionNotationPersist persistableNotationResult = result.getNotation().get(0);
		assertEquals(8, persistableNotationResult.getNumberOfWorkingBells());
		assertEquals("12.34", persistableNotationResult.getNotation());
		assertEquals("-", persistableNotationResult.getNotation2());
		assertEquals(false, persistableNotationResult.isFoldedPalindrome());
		assertEquals("TEST", persistableNotationResult.getName());


	}
}