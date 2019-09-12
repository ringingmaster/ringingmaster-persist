package org.ringingmaster.persist;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.ringingmaster.persist.generated.v1.CallPersist;
import org.ringingmaster.persist.generated.v1.CallsPersist;
import org.ringingmaster.persist.generated.v1.CellTablePersist;
import org.ringingmaster.persist.generated.v1.CellsTablePersist;
import org.ringingmaster.persist.generated.v1.CompositionNotationPersist;
import org.ringingmaster.persist.generated.v1.CompositionPersist;
import org.ringingmaster.persist.generated.v1.CompositionTypePersist;
import org.ringingmaster.persist.generated.v1.LeadLinesPersist;
import org.ringingmaster.persist.generated.v1.LibraryNotationPersist;
import org.ringingmaster.persist.generated.v1.NotationKeyPersist;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * TODO Comments
 *
 * @author Steve Lake
 */
public class NotationLibraryPersistTest {

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

        } else {
            Files.createDirectory(BASE_DIR);
        }
    }


    @Test
    public void canSerialiseLibrary() throws IOException, JAXBException {

        org.ringingmaster.persist.generated.v1.NotationLibraryPersist notationLibrary = new org.ringingmaster.persist.generated.v1.NotationLibraryPersist();
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
        new NotationLibraryPersist().writeNotationLibrary(notationLibrary, path, NotationLibraryUsage.CC_LIBRARY);
        org.ringingmaster.persist.generated.v1.NotationLibraryPersist result = new NotationLibraryPersist().readNotationLibrary(path);

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

        org.ringingmaster.persist.generated.v1.NotationLibraryPersist notationLibrary = new org.ringingmaster.persist.generated.v1.NotationLibraryPersist();
        Path path = BASE_DIR.resolve("emptyLibrary.xml");
        new NotationLibraryPersist().writeNotationLibrary(notationLibrary, path, NotationLibraryUsage.CC_LIBRARY);
        org.ringingmaster.persist.generated.v1.NotationLibraryPersist result = new NotationLibraryPersist().readNotationLibrary(path);

        assertEquals(0, result.getNotation().size());
    }

    @Test
    public void canSerialiseComposition() throws IOException, JAXBException {

        CompositionPersist compositionPersistOriginal = new CompositionPersist();
        //TODO compositionPersistOriginal.setDocumentVersion(1);
        compositionPersistOriginal.setNumberOfBells(8);
        compositionPersistOriginal.setTitle("Composition Title");
        compositionPersistOriginal.setAuthor("Stephen");
        compositionPersistOriginal.setCompositionType(CompositionTypePersist.COURSE_BASED);
        compositionPersistOriginal.setCallFrom(4);
        compositionPersistOriginal.setStartRow(2);
        {
            NotationKeyPersist notationKeyPersist = new NotationKeyPersist();
            notationKeyPersist.setName("TEST 2 Royal");
            notationKeyPersist.setNumberOfWorkingBells(8);
            compositionPersistOriginal.setNonSplicedActiveNotation(notationKeyPersist);
        }
        compositionPersistOriginal.setSpliced(true);
        compositionPersistOriginal.setPlainLeadToken("p");
        {
            CellsTablePersist compositionCells = new CellsTablePersist();
            compositionPersistOriginal.setCompositionTable(compositionCells);

            List<CellTablePersist> cells = compositionCells.getCells();
            CellTablePersist cell0 = new CellTablePersist();
            cell0.setCharacters("-.12");
            cell0.setRow(0);
            cell0.setColumn(0);
            cells.add(cell0);

            CellTablePersist cell1 = new CellTablePersist();
            cell1.setCharacters("W");
            cell1.setRow(1);
            cell1.setColumn(0);
            cells.add(cell1);
        }
        {
            CompositionNotationPersist notation = new CompositionNotationPersist();
            notation.setName("TEST");
            notation.setNumberOfWorkingBells(8);
            notation.setNotation("12.34");
            notation.setNotation2("-");
            notation.setFoldedPalindrome(false);
            {
                CallsPersist callsPersist = new CallsPersist();
                callsPersist.setUseCannedCalls(true);
                notation.setCalls(callsPersist);
            }
            {
                LeadLinesPersist leadLinesPersist = new LeadLinesPersist();
                notation.setLeadLines(leadLinesPersist);
            }

            compositionPersistOriginal.getNotation().add(notation);
        }

        {
            CompositionNotationPersist notation = new CompositionNotationPersist();
            notation.setName("TEST 2");
            notation.setNumberOfWorkingBells(10);
            notation.setNotation("12.34.56");
            notation.setNotation2("12");
            notation.setFoldedPalindrome(true);
            {
                CallsPersist callsPersist = new CallsPersist();
                callsPersist.setUseCannedCalls(false);
                {
                    CallPersist callPersist = new CallPersist();
                    callPersist.setName("call");
                    callPersist.setDefault(true);
                    callPersist.setNotation("x.12.x.34");
                    callPersist.setShorthand("bob");
                    callsPersist.getCall().add(callPersist);
                }
                notation.setCalls(callsPersist);
            }
            {
                LeadLinesPersist leadLinesPersist = new LeadLinesPersist();
                leadLinesPersist.getLeadLine().addAll(Lists.newArrayList(2, 31, 0));
                notation.setLeadLines(leadLinesPersist);
            }

            compositionPersistOriginal.getNotation().add(notation);
        }


         //*** WRITE AND READ ***
        Path path = BASE_DIR.resolve("NotationLibraryPersistTest-test-composition.xml");
        new NotationLibraryPersist().writeComposition(compositionPersistOriginal, path);
        CompositionPersist compositionPersistResult = new NotationLibraryPersist().readComposition(path);


        //*** ASSERT ***
        assertEquals("Composition Title", compositionPersistResult.getTitle());
        assertEquals("Stephen", compositionPersistResult.getAuthor());
        assertEquals(8, compositionPersistResult.getNumberOfBells());
        assertEquals(CompositionTypePersist.COURSE_BASED, compositionPersistResult.getCompositionType());
        assertEquals(4, compositionPersistResult.getCallFrom());
        assertEquals(2, compositionPersistResult.getStartRow());
        {
            assertEquals("TEST 2 Royal", compositionPersistResult.getNonSplicedActiveNotation().getName());
            assertEquals(8, compositionPersistResult.getNonSplicedActiveNotation().getNumberOfWorkingBells());
        }
        assertEquals(true, compositionPersistResult.isSpliced());
        assertEquals("p", compositionPersistResult.getPlainLeadToken());

        assertEquals(2, compositionPersistResult.getNotation().size());
        {
            CompositionNotationPersist persistableNotationResult = compositionPersistResult.getNotation().get(0);
            assertEquals("TEST", persistableNotationResult.getName());
            assertEquals(8, persistableNotationResult.getNumberOfWorkingBells());
            assertEquals("12.34", persistableNotationResult.getNotation());
            assertEquals("-", persistableNotationResult.getNotation2());
            assertFalse(persistableNotationResult.isFoldedPalindrome());
            assertTrue(persistableNotationResult.getCalls().isUseCannedCalls());
            assertTrue(persistableNotationResult.getLeadLines().isUseCannedLeadLines());
        }
        {
            CompositionNotationPersist persistableNotationResult = compositionPersistResult.getNotation().get(1);
            assertEquals("TEST 2", persistableNotationResult.getName());
            assertEquals(10, persistableNotationResult.getNumberOfWorkingBells());
            assertEquals("12.34.56", persistableNotationResult.getNotation());
            assertEquals("12", persistableNotationResult.getNotation2());
            assertTrue(persistableNotationResult.isFoldedPalindrome());
            {
                assertFalse(persistableNotationResult.getCalls().isUseCannedCalls());
                assertEquals(1, persistableNotationResult.getCalls().getCall().size());
                {
                    CallPersist callPersist = persistableNotationResult.getCalls().getCall().get(0);
                    assertEquals("call", callPersist.getName());
                    assertEquals(true, callPersist.isDefault());
                    assertEquals("x.12.x.34", callPersist.getNotation());
                    assertEquals("bob", callPersist.getShorthand());
                }
            }
            {
                assertTrue(persistableNotationResult.getLeadLines().isUseCannedLeadLines());
                List<Integer> leadLines = persistableNotationResult.getLeadLines().getLeadLine();
                assertEquals(3, leadLines.size());
                assertTrue(leadLines.contains(2));
                assertTrue(leadLines.contains(31));
                assertTrue(leadLines.contains(0));
            }
        }

        //TODO we need testing of the composition cells and definition cells.


    }
}