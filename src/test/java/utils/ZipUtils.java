package utils;
import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils {

    public static void unzipToFeatures(Path zipFilePath) throws IOException {

        Path targetDir = Paths.get(System.getProperty("user.dir")+"/src/test/resources/features");

        // Crée le dossier s’il n’existe pas
        Files.createDirectories(targetDir);

        try (ZipInputStream zis = new ZipInputStream(
                new BufferedInputStream(Files.newInputStream(zipFilePath)))) {

            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {

                Path newPath = targetDir.resolve(entry.getName()).normalize();

                // Sécurité anti Zip Slip
                if (!newPath.startsWith(targetDir)) {
                    throw new IOException("Bad zip entry: " + entry.getName());
                }

                if (entry.isDirectory()) {
                    Files.createDirectories(newPath);
                } else {
                    Files.createDirectories(newPath.getParent());
                    Files.copy(zis, newPath, StandardCopyOption.REPLACE_EXISTING);
                }

                zis.closeEntry();
            }
        }
    }
}
