package util;

import java.io.File;
import java.io.FileWriter;

public class LicenseExporter {

    public static void export(File file, String license)
            throws Exception {

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(license);
            
            writer.flush();
        }

    }
}