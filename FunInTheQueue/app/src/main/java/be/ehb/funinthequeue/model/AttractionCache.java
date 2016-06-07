package be.ehb.funinthequeue.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clara on 6/01/2016.
 */
public class AttractionCache {
    private static ArrayList<Attraction> attractions = new ArrayList<Attraction>();
    private static int selectedId = -1;

    public static ArrayList<Attraction> getAttractions() {
        return AttractionCache.attractions;
    }

    public static void setAttractions(ArrayList<Attraction> attractions) {
        AttractionCache.attractions = attractions;
    }

    public static Attraction getSelectedAttraction() {
        if (selectedId >= 0 && selectedId < attractions.size()) {
            return AttractionCache.attractions.get(selectedId);
        } else if (!attractions.isEmpty()) {
            return AttractionCache.attractions.get(0);
        }

        throw new RuntimeException("No attractions loaded.");
    }

    public static void setSelectedId(int selectedId) {
        AttractionCache.selectedId = selectedId;
    }
}
