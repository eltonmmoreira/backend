package br.com.pessoa.api.file;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileOperationsTest {

    private static final String EXTENSAO = "jpg";

    @Test
    public void extensionByFilenameTest() {
        var extensao = FileOperations.of().getExtensionByFilename("arquivo.jpg");

        assertTrue(extensao.isPresent());
        assertEquals(EXTENSAO, extensao.get());
    }
}
