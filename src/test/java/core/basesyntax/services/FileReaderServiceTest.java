package core.basesyntax.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.services.impl.FileReaderServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FileReaderServiceTest {
    private static FileReaderService fileReaderService;
    private static final String VALID_FILE_PATH = "src/test/java/resources/test.csv";
    private static final String INVALID_FILE_PATH = "src/test/java/resources/InvalidTest.csv";
    private static final String EMPTY_FILE_PATH = "src/test/java/resources/empty_file_csv";

    @BeforeAll
    static void beforeAll() {
        fileReaderService = new FileReaderServiceImpl();
    }

    @Test
    void readData_existingFile_ok() {
        List<String> expected = fileReaderService.readFromFile(VALID_FILE_PATH);
        List<String> actual = List.of(
                "b,banana,20",
                "b,apple,40",
                "s,banana,40"
        );
        assertEquals(expected, actual);
    }

    @Test
    void readData_emptyFile_notOk() {
        List<String> expected = List.of();
        List<String> actual = fileReaderService.readFromFile(EMPTY_FILE_PATH);
        assertEquals(expected, actual);
    }

    @Test
    void readData_notExistingFile_notOk() {
        assertThrows(RuntimeException.class,
                () -> fileReaderService.readFromFile(INVALID_FILE_PATH));
    }
}
