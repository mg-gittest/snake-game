/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

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
            for (TileLocation local : list) {
                if (local.sameLocation(target)) {
                    return true;
                }
            }
        }
        return false;
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

}
