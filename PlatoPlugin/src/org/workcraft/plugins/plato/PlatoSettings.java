package org.workcraft.plugins.plato;

import java.util.Collection;
import java.util.LinkedList;

import org.workcraft.Config;
import org.workcraft.gui.DesktopApi;
import org.workcraft.gui.propertyeditor.PropertyDeclaration;
import org.workcraft.gui.propertyeditor.PropertyDescriptor;
import org.workcraft.gui.propertyeditor.Settings;

public class PlatoSettings implements Settings {

    private static final LinkedList<PropertyDescriptor> properties = new LinkedList<>();
    private static final String prefix = "Tools.plato";

    private static final String keyPlatoFolderLocation = prefix + ".platoFolderLocation";
    private static final String keyPlatoIncludesList = prefix + ".platoIncludesList";

    private static final String defaultPlatoFolderLocation = DesktopApi.getOs().isWindows() ? "tools\\plato\\" : "tools/plato/";
    private static final String defaultPlatoIncludesList = "";

    private static String conceptsFolderLocation = defaultPlatoFolderLocation;
    private static String platoIncludesList = defaultPlatoIncludesList;

    public PlatoSettings() {
        properties.add(new PropertyDeclaration<PlatoSettings, String>(
                this, "Concepts folder location", String.class, true, false, false) {
            protected void setter(PlatoSettings object, String value) {
                setPlatoFolderLocation(value);
            }
            protected String getter(PlatoSettings object) {
                return getPlatoFolderLocation();
            }
        });

        properties.add(new PropertyDeclaration<PlatoSettings, String>(
                this, "Folders to always include (separate with \';\')", String.class, true, false, false) {
            protected void setter(PlatoSettings object, String value) {
                setPlatoIncludesList(value);
            }
            @Override
            protected String getter(PlatoSettings object) {
                return getPlatoIncludesList();
            }
        });
    }

    @Override
    public Collection<PropertyDescriptor> getDescriptors() {
        return properties;
    }

    @Override
    public void save(Config config) {
        config.set(keyPlatoFolderLocation, getPlatoFolderLocation());
        config.set(keyPlatoIncludesList, getPlatoIncludesList());
    }

    @Override
    public void load(Config config) {
        setPlatoFolderLocation(config.getString(keyPlatoFolderLocation, defaultPlatoFolderLocation));
        setPlatoIncludesList(config.getString(keyPlatoIncludesList, defaultPlatoIncludesList));
    }

    @Override
    public String getSection() {
        return "External tools";
    }

    @Override
    public String getName() {
        return "Plato";
    }

    public static String getPlatoFolderLocation() {
        return conceptsFolderLocation;
    }

    public static void setPlatoFolderLocation(String value) {
        conceptsFolderLocation = value;
    }

    public static String getPlatoIncludesList() {
        return platoIncludesList;
    }

    public static void setPlatoIncludesList(String value) {
        platoIncludesList = value;
    }

}
