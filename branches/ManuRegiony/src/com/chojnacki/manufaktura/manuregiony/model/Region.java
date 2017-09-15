package com.chojnacki.manufaktura.manuregiony.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Collection;

import static com.chojnacki.manufaktura.manuregiony.model.Regions.*;

/**
 * Class which respresents single region
 */
public class Region extends ColorHolder implements Comparable<Region> {

    public static final boolean PAINTABLE = true;
    public static final boolean NOT_PAINTABLE = false;
    public static final boolean FORCED = true;
    public static final boolean NOT_FORCED = false;
    public static final boolean BOLD = true;
    public static final boolean PLAIN = false;
    public static final boolean PAINT_SHORTCUT_IN_MAP = true;
    private String origin;
    private Point[] coordinates;
    private String name;
    private int objectsNumber;
    private String[] regionShortcuts;
    private boolean paintable;
    private Collection<Car> cars = new ArrayList<>();
    private boolean forced;
    private boolean shortcutToMap;

    public Region(String name, int x, int y, boolean locked, String... regionShortcuts) {
        this(name, regionShortcuts, PAINTABLE, locked, new Point[]{new Point(x, y)});
    }

    public Region(String name, String regionShortcut, String origin, Point... coordinates) {
        this(name, new String[]{regionShortcut}, UNLOCKED_COLOR, "pl", coordinates);
    }

    public Region(String name, boolean locked, String regionShortcut, Point... coordinates) {
        this(name, new String[]{regionShortcut}, locked, "pl", coordinates);
    }

    public Region(String name, String regionShortcut, boolean locked, boolean drawShortcutOnMap, Point... coordinates) {
        this(name, new String[]{regionShortcut}, locked, "pl", coordinates);
        this.shortcutToMap = drawShortcutOnMap;
    }

    public Region(String name, String[] regionShortcuts, boolean locked, String origin, Point... coordinates) {
        super(locked);
        this.name = name;
        this.coordinates = coordinates;
        this.regionShortcuts = regionShortcuts;
        this.paintable = true;
        this.forced = false;
        this.bold = false;
        this.shortcutToMap = false;
        this.origin = origin;
    }

    public Region(String name, String[] regionShortcuts, boolean isPaintable, boolean locked, Point... coordinates) {
        this(name, regionShortcuts, locked, "pl", coordinates);
        this.paintable = isPaintable;
    }

    public Region(String name, int x, int y, String regionShortcut, boolean isPaintable) {
        this(name, x, y, false, regionShortcut);
        this.paintable = isPaintable;
    }

    public Region(String name, int x, int y, String regionShortcut, boolean isPaintable, boolean isForced, boolean isBold, boolean locked, String origin) {
        this(name, x, y, regionShortcut, isPaintable);
        this.forced = isForced;
        this.bold = isBold;
        this.locked = locked;
        this.origin = origin;
    }

    public Region(String name, int x, int y, String regionShortcut, boolean isPaintable, boolean isForced, boolean isBold, boolean drawShortcutOnMap, boolean locked) {
        this(name, x, y, regionShortcut, isPaintable, isForced, isBold, locked, "pl");
        this.shortcutToMap = drawShortcutOnMap;
    }

    public String[] getRegionShortcuts() {
        return regionShortcuts;
    }

    @Override
    public int getObjectsNumber() {
        if(ProcessingType.LODZKIE.equals(Regions.getInstance().getProcessingType()) &&
                this.name.equals(OUTERN_POLAND.name)) {
            return objectsNumber + OUTERN_LDZ.getObjectsNumber();
        }
        return objectsNumber;
    }

    public void increment() {
        increment(1);
    }

    public void increment(Car car) {
        increment();
        cars.add(car);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        return new EqualsBuilder()
                .append(name, region.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        String coordinates = "";
        for (Point point : this.coordinates) {
            coordinates += point.toString();
        }
        return "Region{" +
                "name='" + name + '\'' +
                ", objectsNumber=" + objectsNumber +
                ", " + coordinates +
                ", regionShortcuts='" + getRegionShortcutsAsText() + '\'' +
                '}';
    }

    @Override
    public Point[] getCoordinates() {
        return this.coordinates;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isPaintable() {
        return paintable;
    }

    @Override
    public int compareTo(Region o) {
        return Integer.compare(o.getObjectsNumber(), getObjectsNumber());
    }

    public Collection<Car> getCars() {
        return cars;
    }

    public void reset() {
        objectsNumber = 0;
        cars.clear();
    }

    public boolean isForced() {
        return forced;
    }

    public String getRegionShortcutsAsText() {
        StringBuilder sb = new StringBuilder();
        for (String regionShortcut : regionShortcuts) {
            sb.append(regionShortcut).append(", ");
        }
        return sb.toString();
    }

    public void increment(int objectCount) {
        objectsNumber += objectCount;
    }
    public boolean drawShortcutOnMap() {
        return this.shortcutToMap;
    }

    public boolean isLodz() {
        for (String regionShortcut : regionShortcuts) {
            if (regionShortcut.startsWith("E"))
                return true;
        }
        return false;
    }

    public String getMainRegionShortcut() {
        if (this.equals(ALL) || this.equals(OUTERN_POLAND))
            return "";
        return getRegionShortcuts()[0];
    }

    public String getOrigin() {
        return origin;
    }
}
