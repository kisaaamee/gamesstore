package pl.cd.project.gamesstore.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.nio.file.Files;

@Slf4j
public class MappingApplyUtil {
    public static String loadAsString(final String path) {
        try {
            final File resource = new ClassPathResource(path).getFile();
            return new String(Files.readAllBytes(resource.toPath()));
        } catch (final Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }
}
