/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import android.os.Bundle;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import consulting.germain.snakegame.enums.TileType;

/**
 * Created by mark_local on 18/09/2015.
 * Simple wrapper for list of TileLocation,
 * can have an arbitrary list of tile location, no requirement for continuity or type similarity
 * Delegates only the operations we want ot expose
 */
public class TileLocationList implements java.lang.Iterable<TileLocation> {

    private LinkedList<TileLocation> list = new LinkedList<>();

    /**
     * examine a suplied list of TileLocation and see if any intersect with the TileLocations of the list
     * @param targetList A list of locations to check
     * @return ture is there is an intersection false otherwise
     */
    public boolean intersects(final TileLocationList targetList) {
        for (TileLocation target : targetList.list) {
            if (containsSameLocation(target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * examine a TileLocation to see if it has same location as any in the list
     * @param check what to check
     * @return true if there is the same location in the list
     */
    public boolean containsSameLocation(final TileLocation check) {
        for (TileLocation target : list) {
            if (target.sameLocation(check)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the objects in the specified Collection to this {@code LinkedList}.
     *
     * @param that the TileLocationList to add from.
     * @return {@code true} if this list is modified, {@code false} otherwise.
     */
    public boolean addAll(TileLocationList that) {
        return list.addAll(that.list);
    }

    /**
     * Adds the specified TileLocation at the end of this {@code LinkedList}.
     *
     * @param tileLocation the tileLocation to add.
     */
    public void addLast(TileLocation tileLocation) {
        list.addLast(tileLocation);
    }

    /**
     * Adds the specified tileLocation at the beginning of this {@code LinkedList}.
     *
     * @param tileLocation the tileLocation to add.
     */
    public void addFirst(TileLocation tileLocation) {
        list.addFirst(tileLocation);
    }

    /**
     * Inserts the specified object into this {@code LinkedList} at the
     * specified location. The object is inserted before any previous element at
     * the specified location. If the location is equal to the size of this
     * {@code LinkedList}, the object is added at the end.
     *
     * @param location the index at which to insert.
     * @param object   the object to add.
     * @throws IndexOutOfBoundsException if {@code location < 0 || location > size()}
     */
    public void add(int location, TileLocation object) {
        list.add(location, object);
    }

    /**
     * Returns the first element in this {@code LinkedList}.
     *
     * @return the first element.
     * @throws NoSuchElementException if this {@code LinkedList} is empty.
     */
    public TileLocation getFirst() {
        return list.getFirst();
    }

    /**
     * Returns the last element in this {@code LinkedList}.
     *
     * @return the last element
     * @throws NoSuchElementException if this {@code LinkedList} is empty
     */
    public TileLocation getLast() {
        return list.getLast();
    }

    /**
     * Removes the first element from this {@code LinkedList}.
     *
     * @return the removed object.
     * @throws NoSuchElementException if this {@code LinkedList} is empty.
     */
    public TileLocation removeFirst() {
        return list.removeFirst();
    }

    /**
     * Removes the last object from this {@code LinkedList}.
     *
     * @return the removed object.
     * @throws NoSuchElementException if this {@code LinkedList} is empty.
     */
    public TileLocation removeLast() {
        return list.removeLast();
    }

    /**
     * fretch from a particular index
     *
     * @param location index to fetch from
     * @return TileLocation at that location
     */
    public TileLocation get(int location) {
        return list.get(location);
    }

    /**
     * Returns the number of elements in this {@code LinkedList}.
     *
     * @return the number of elements in this {@code LinkedList}.
     */
    public int size() {
        return list.size();
    }


    /**
     * Returns an {@link Iterator} for the elements in this TileLocation.
     *
     * @return An {@code Iterator} instance.
     */
    @Override
    public Iterator<TileLocation> iterator() {
        return list.iterator();
    }

    /**
     * Compares the specified TileLocationList to this list and return true if they are
     * equal. Two lists are equal when they both contain the same TileLocations in the
     * same order.
     *
     * @param other the TileLocation to compare to this TileLocation.
     * @return {@code true} if the specified TileLocation is equal to this list,
     * {@code false} otherwise.
     * @see #hashCode
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        TileLocationList that = (TileLocationList) other;

        return list.equals(that.list);
    }

    /**
     * Returns the hash code of this list. The hash code is calculated by taking
     * each element's hashcode into account.
     *
     * @return the hash code.
     * @see #equals
     */
    @Override
    public int hashCode() {
        return list.hashCode();
    }

    /**
     * Returns if this {@code Collection} contains no elements. This implementation
     * tests, whether {@code size} returns 0.
     *
     * @return {@code true} if this {@code Collection} has no elements, {@code false}
     * otherwise.
     * @see #size
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns the string representation of this {@code Collection}. The presentation
     * has a specific format. It is enclosed by square brackets ("[]"). Elements
     * are separated by ', ' (comma and space).
     *

     * @return the string representation of this {@code Collection}.
     */
    @Override
    public String toString() {
        return list.toString();
    }

    /**
     * restore internal state
     *
     * @param bundle to restore from
     */
    public void restoreState(Bundle bundle) {
        StateBundler bundler = new StateBundler();
        bundler.restore(bundle);
    }

    /**
     * save internal state
     *
     * @param bundle where to save the state
     */
    public void saveState(Bundle bundle) {
        StateBundler bundler = new StateBundler();
        bundler.save(bundle);
    }

    /**
     * assists in mapping state in and out of a Bundle
     */
    private class StateBundler {
        private final static String keyB = "TileLocationList.";
        private final static String keyS = ".";
        private final static String keyX = "X";
        private final static String keyY = "Y";
        private final static String keyN = "N";
        private final static String keyT = "T";

        /**
         * save the relevant parts of the current state into the bundle
         *
         * @param map Bundle to save to
         */
        public void save(Bundle map) {
            if (map == null) {
                return;
            }
            for (int idx = 0; idx < list.size(); ++idx) {
                TileLocation tl = list.get(idx);
                String key = keyB + idx + keyS;
                map.putInt(key + keyX, tl.getX());
                map.putInt(key + keyY, tl.getY());
                map.putString(key + keyN, tl.getTile().getName());
                map.putString(key + keyT, tl.getTile().getTileType().name());
            }
        }

        /**
         * restore the animation state from the supplied bundle
         *
         * @param map Bundle to read state from, assumes all entries are for the list
         */
        public void restore(Bundle map) {
            list.clear();

            if (map == null) {
                return;
            }

            // load state from the supplied bundle, in index order
            for (int idx = 0; idx < map.size(); ++idx) {
                String key = keyB + idx + keyS;
                int x = map.getInt(key + keyX);
                int y = map.getInt(key + keyY);
                String name = map.getString(key + keyN);
                String type = map.getString(key + keyT);

                Tile tile = Tile.get(TileType.valueOf(type), name);
                TileLocation tl = new TileLocation(new Location(x, y), tile);
                list.add(tl);
            }
        }
    } // private class StateBundler

}
