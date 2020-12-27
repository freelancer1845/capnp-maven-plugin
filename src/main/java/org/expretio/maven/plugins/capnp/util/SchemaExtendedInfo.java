package org.expretio.maven.plugins.capnp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SchemaExtendedInfo {

    private static final Pattern packageDirectiveMatcher = Pattern
            .compile("^\\$Java.package\\(\"([a-z][a-z0-9_]*(\\.[a-z0-9_]+)+[0-9a-z_])\"\\);$");

    public static Optional<String> getPackageName(File schemaFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(schemaFile))) {
            String line = reader.readLine();
            while (line != null) {
                Matcher matcher = packageDirectiveMatcher.matcher(line);
                if (matcher.matches()) {
                    return Optional.of(matcher.group(1));
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            // throw new IOException(String.format("Failed to read Schemafile for PackageName resolution. \"%s\"",
            //         schemaFile.getAbsolutePath()), e);
            return Optional.empty();
        }
        return Optional.empty();
    }

    public static String packageNameToDirectory(String packageName) {
        return String.join("/", packageName.split("\\."));
    }
}
