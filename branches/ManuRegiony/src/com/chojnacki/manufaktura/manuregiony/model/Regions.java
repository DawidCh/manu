package com.chojnacki.manufaktura.manuregiony.model;

import com.chojnacki.manufaktura.manuregiony.exceptions.DuplicatedRegion;
import com.chojnacki.manufaktura.manuregiony.exceptions.RegionNotFoundException;
import com.chojnacki.manufaktura.manuregiony.model.regions.*;
import com.chojnacki.manufaktura.manuregiony.output.ProcessingTypeListener;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static com.chojnacki.manufaktura.manuregiony.model.ProcessingType.POWIATY;
import static com.chojnacki.manufaktura.manuregiony.model.ProcessingType.WOJEWODZTWA;
import static com.chojnacki.manufaktura.manuregiony.model.Region.*;

/**
 * Utility class for dealing with {@link Region}
 */
public class Regions implements ProcessingTypeListener {
    public static final Region ALL = new Region("suma", -1, -1, "ALL", NOT_PAINTABLE, FORCED, BOLD, false, "pl");
    public static final Region LODZKIE = new Region("łódzkie", LOCKED_COLOR, "E", Lodzkie.getCoordinates());
    public static final Region ZACHODNIOPOMORSKIE = new Region("zachodniopomorskie", "Z", "pl", Zachodniopomorskie.getCoordinates());
    public static final Region POMORSKIE = new Region("pomorskie", "G", "pl", Pomorskie.getCoordinates());
    public static final Region WARMINSKO_MAZURSKIE = new Region("warmińsko-mazurskie", "N", "pl", WarminskoMazurskie.getCoordinates());
    public static final Region PODLASKIE = new Region("podlaskie", "B", "pl", Podlaskie.getCoordinates());
    public static final Region LUBUSKIE = new Region("lubuskie", "F", "pl", Lubuskie.getCoordinates());
    public static final Region WIELKOPOLSKIE = new Region("wielkopolsie", "P", "pl", Wielkopolskie.getCoordinates());
    public static final Region KUJAWSKO_POMORSKIE = new Region("kujawsko-pomorskie", "C", "pl", KujawskoPomorskie.getCoordinates());
    public static final Region SWIETOKRZYSKIE = new Region("świętokrzyskie", "T", "pl", Swietokrzyskie.getCoordinates());
    public static final Region DOLNOSLASKIE = new Region("dolnośląskie", "D", "pl", Dolnoslaskie.getCoordinates());
    public static final Region OPOLSKIE = new Region("opolskie", "O", "pl", Opolskie.getCoordinates());
    public static final Region SLASKIE = new Region("śląskie", "S", "pl", Slaskie.getCoordinates());
    public static final Region MALOPOLSKIE = new Region("małopolskie", "K", "pl", Malopolskie.getCoordinates());
    public static final Region PODKARPACKIE = new Region("podkarpackie", "R", "pl", Podkarpackie.getCoordinates());
    public static final Region LUBELSKIE = new Region("lubelskie", "L", "pl", Lubelskie.getCoordinates());
    public static final Region MAZOWIECKIE = new Region("mazowieckie", "W", "pl", Mazowieckie.getCoordinates());
    public static final Region OUTERN_POLAND = new Region("inne", -1, -1, "OUTERN_POLAND", NOT_PAINTABLE);
    public static final Region OUTERN_LDZ = new Region("", -1, -1, "OUTERN_LDZ", NOT_PAINTABLE, FORCED, !BOLD, false, "pl");

    public static final Region CZECHY = new Region("Czechy", -1, -1, "", NOT_PAINTABLE, FORCED, !BOLD, false, "CZ");
    public static final Region BIALORUS = new Region("Białoruś", -1, -1, "", NOT_PAINTABLE, FORCED, !BOLD, false, "BY");
    public static final Region FRANCJA = new Region("Francja", -1, -1, "", NOT_PAINTABLE, FORCED, !BOLD, false, "FR");
    public static final Region LITWA = new Region("Litwa", -1, -1, "", NOT_PAINTABLE, FORCED, !BOLD, false, "LT");
    public static final Region NIEMCY = new Region("Niemcy", -1, -1, "", NOT_PAINTABLE, FORCED, !BOLD, false, "DE");
    public static final Region ROSJA = new Region("Rosja", -1, -1, "", NOT_PAINTABLE, FORCED, !BOLD, false, "RU");
    public static final Region SLOWACJA = new Region("Słowacja", -1, -1, "", NOT_PAINTABLE, FORCED, !BOLD, false, "SK");
    public static final Region UKRAINA = new Region("Ukraina", -1, -1, "", NOT_PAINTABLE, FORCED, !BOLD, false, "UA");

    private static final Logger LOGGER = LogManager.getLogger(Regions.class);
    private static Regions instance;
    private static Map<String, Region> regionMap;
    private ProcessingType processingType = ProcessingType.LODZKIE;

    private Regions() {
    }

    public static Regions getInstance() {
        if (instance == null) {
            instance = new Regions();
        }
        return instance;
    }

    private void rebuildRegionMap() {
        regionMap = new HashMap<>();
        Collection<Region> regions = getAllRegions();
        for (Region region : regions) {
            String[] regionShortcuts = region.getRegionShortcuts();
            String origin = region.getOrigin().toLowerCase();
            for (String regionShortcut : regionShortcuts) {
                Region oldOne = regionMap.put(origin + regionShortcut, region);
                if (oldOne != null) {
                    throw new DuplicatedRegion(oldOne, region);
                }
            }
        }
    }

    private Collection<Region> getAllRegions() {
        List<Region> regions = new ArrayList<>();
        regions.addAll(getMainRegions());
        regions.addAll(Zachodniopomorskie.getInstance().ZACHODNIOPOMIRSKIE_POWIATY);
        regions.addAll(Lubelskie.getInstance().LUBELSKIE_POWIATY);
        regions.addAll(Pomorskie.getInstance().POMORSKIE_POWIATY);
        regions.addAll(WarminskoMazurskie.getInstance().WARMINSKO_MAZURSKIE_POWIATY);
        regions.addAll(Podlaskie.getInstance().PODLASKIE_POWIATY);
        regions.addAll(KujawskoPomorskie.getInstance().KUJAWSKO_POM_POWIATY);
        regions.addAll(Lubuskie.getInstance().LUBUSKIE_POWIATY);
        regions.addAll(Dolnoslaskie.getInstance().DOLNOSLASKIE_POWIATY);
        regions.addAll(Opolskie.getInstance().OPOLSKIE_POWIATY);
        regions.addAll(Swietokrzyskie.getInstance().SWIETOKRZYSKIE_POWIATY);
        regions.addAll(Malopolskie.getInstance().MALOPOLSKIE_POWIATY);
        regions.addAll(Lodzkie.getInstance().LODZKIE_POWIATY);
        regions.addAll(Lodzkie.getInstance().LODZKIE_POWIATY_LOW_RES);
        regions.addAll(Slaskie.getInstance().SLASKIE_POWIATY);
        regions.addAll(Wielkopolskie.getInstance().WIELKOPOLSKIE_POWIATY);
        regions.addAll(Podkarpackie.getInstance().PODKARPACKIE_POWIATY);
        regions.addAll(Mazowieckie.getInstance().MAZOWIECKIE_POWIATY);
        regions.addAll(getForeignRegions());
        return regions;
    }

    public Collection<Region> getPaintableRegions() {
        List<Region> regions = new ArrayList<>();
        if (ProcessingType.LODZKIE.equals(processingType)) {
            regions.addAll(Lodzkie.getInstance().LODZKIE_POWIATY);
        } else if (WOJEWODZTWA.equals(processingType)) {
            regions.addAll(getMainRegions());
        } else if (POWIATY.equals(processingType)) {
            regions.addAll(Zachodniopomorskie.getInstance().ZACHODNIOPOMIRSKIE_POWIATY);
            regions.addAll(Lubelskie.getInstance().LUBELSKIE_POWIATY);
            regions.addAll(Pomorskie.getInstance().POMORSKIE_POWIATY);
            regions.addAll(WarminskoMazurskie.getInstance().WARMINSKO_MAZURSKIE_POWIATY);
            regions.addAll(Podlaskie.getInstance().PODLASKIE_POWIATY);
            regions.addAll(KujawskoPomorskie.getInstance().KUJAWSKO_POM_POWIATY);
            regions.addAll(Lubuskie.getInstance().LUBUSKIE_POWIATY);
            regions.addAll(Dolnoslaskie.getInstance().DOLNOSLASKIE_POWIATY);
            regions.addAll(Opolskie.getInstance().OPOLSKIE_POWIATY);
            regions.addAll(Swietokrzyskie.getInstance().SWIETOKRZYSKIE_POWIATY);
            regions.addAll(Malopolskie.getInstance().MALOPOLSKIE_POWIATY);
            regions.addAll(Lodzkie.getInstance().LODZKIE_POWIATY_LOW_RES);
            regions.addAll(Slaskie.getInstance().SLASKIE_POWIATY);
            regions.addAll(Wielkopolskie.getInstance().WIELKOPOLSKIE_POWIATY);
            regions.addAll(Podkarpackie.getInstance().PODKARPACKIE_POWIATY);
            regions.addAll(Mazowieckie.getInstance().MAZOWIECKIE_POWIATY);
        } else {
            throw new UnsupportedOperationException();
        }
        Collections.sort(regions);
        List<Region> outhernPoland = getOuthernPoland();
        Collections.sort(outhernPoland);
        regions.addAll(outhernPoland);
        regions.add(ALL);
        return regions;
    }

    public List<Region> getOuthernPoland() {
        List<Region> result;
        if (WOJEWODZTWA.equals(processingType)) {
            result = getForeignRegions();
        } else {
            result = Arrays.asList(OUTERN_POLAND);
        }
        return result;
    }

    public List<Region> getForeignRegions() {
        return Arrays.asList(BIALORUS, CZECHY, FRANCJA, LITWA, NIEMCY, ROSJA, SLOWACJA, UKRAINA);
    }

    private Collection<Region> getMainRegions() {
        return Arrays.asList(
                ZACHODNIOPOMORSKIE, LUBELSKIE
                , POMORSKIE, WARMINSKO_MAZURSKIE, PODLASKIE, KUJAWSKO_POMORSKIE, LUBUSKIE, WIELKOPOLSKIE, DOLNOSLASKIE, OPOLSKIE, SWIETOKRZYSKIE, SLASKIE,
                MALOPOLSKIE, PODKARPACKIE, MAZOWIECKIE, LODZKIE
        );
    }

    public List<Region> getRegionByPlate(String regionSymbol, Locale origin) {
        List<Region> result = new ArrayList<>();
        Region region;
        List<String> symbols;
        String country = origin.getLanguage();
        if (isOriginAcceptable(origin)) {
            symbols = Arrays.asList(country);
        } else if (origin.equals(new Locale("pl"))) {
            symbols = Arrays.asList(country + regionSymbol.trim(), country + regionSymbol.trim() + " ", country + regionSymbol.substring(0, 1));
        } else {
            throw new RegionNotFoundException(regionSymbol);
        }
        for (String symbol : symbols) {
            region = regionMap.get(symbol);
            if (region != null) {
                result.add(region);
            }
        }
        return result;
    }

    public boolean isOriginAcceptable(Locale origin) {
        boolean result = false;
        List<Locale> acceptable_locs = getAcceptableLocalse();
        for (Locale acceptable_loc : acceptable_locs) {
            if (origin.equals(acceptable_loc)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public List<Locale> getAcceptableLocalse() {
        List<Locale> locales = new ArrayList<>();
        for (Region region : getForeignRegions()) {
            locales.add(new Locale(region.getOrigin()));
        }
        return locales;
    }

    public ProcessingType getProcessingType() {
        return processingType;
    }

    @Override
    public void setProcessingType(ProcessingType processingType) {
        this.processingType = processingType;
        LOGGER.log(Level.DEBUG, "Processing type: " + processingType);
        rebuildRegionMap();
    }

    public void resetCounters() {
        getAllRegions().forEach(Region::reset);
        ALL.reset();
        OUTERN_LDZ.reset();
        OUTERN_POLAND.reset();
    }
}
