package org.expretio.maven.plugins.capnp.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.Test;

public class SchemaExtendedInfoTest {

    @Test
    public void findsCorrectPackageInAlpha() throws IOException {

        Optional<String> packageName = SchemaExtendedInfo.getPackageName(getAlpha());
        assertTrue(packageName.isPresent());
        assertEquals("org.expretio.maven.plugins.capnp.alpha", packageName.get());
    }

    @Test
    public void findsCorrectPackageInBeta() throws IOException {

        Optional<String> packageName = SchemaExtendedInfo.getPackageName(getBeta());
        assertTrue(packageName.isPresent());
        assertEquals("org.expretio.maven.plugins.capnp.beta", packageName.get());
    }

    private File getAlpha() {
        try {
            return Paths.get(this.getClass().getClassLoader()
                    .getResource("org/expretio/maven/plugins/capnp/alpha/alpha.capnp").toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void mapsPackageNameToDirectory() {
        assertEquals("org/expretio/maven/plugins/capnp/beta",
                SchemaExtendedInfo.packageNameToDirectory("org.expretio.maven.plugins.capnp.beta"));
    }

    private File getBeta() {
        try {
            return Paths.get(this.getClass().getClassLoader()
                    .getResource("org/expretio/maven/plugins/capnp/beta/beta.capnp").toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
