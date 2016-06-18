/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org>
 */
package de.xn__ho_hia.utils.storage_unit;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import de.xn__ho_hia.utils.storage_unit.Exabyte;
import de.xn__ho_hia.utils.storage_unit.Exbibyte;
import de.xn__ho_hia.utils.storage_unit.Gibibyte;
import de.xn__ho_hia.utils.storage_unit.Gigabyte;
import de.xn__ho_hia.utils.storage_unit.Kibibyte;
import de.xn__ho_hia.utils.storage_unit.Kilobyte;
import de.xn__ho_hia.utils.storage_unit.Mebibyte;
import de.xn__ho_hia.utils.storage_unit.Megabyte;
import de.xn__ho_hia.utils.storage_unit.Pebibyte;
import de.xn__ho_hia.utils.storage_unit.Petabyte;
import de.xn__ho_hia.utils.storage_unit.StorageUnit;
import de.xn__ho_hia.utils.storage_unit.Tebibyte;
import de.xn__ho_hia.utils.storage_unit.Terabyte;
import de.xn__ho_hia.utils.storage_unit.Yobibyte;
import de.xn__ho_hia.utils.storage_unit.Yottabyte;
import de.xn__ho_hia.utils.storage_unit.Zebibyte;
import de.xn__ho_hia.utils.storage_unit.Zettabyte;

/**
 *
 *
 */
@RunWith(Theories.class)
@SuppressWarnings({ "static-method", "nls" })
public class StorageUnitArithmeticLongTest {

    /**
     *
     */
    @DataPoints
    public static long[] BYTES_TO_ADD = { 1, 2, 3, 5, 8, 13, 100, 500, -500, 123456789 };

    /**
     * @return The factory methods to create storage units to test.
     */
    @DataPoints
    public static List<Function<Long, StorageUnit<?>>> storageUnits() {
        final List<Function<Long, StorageUnit<?>>> units = new ArrayList<>();
        units.add(Exabyte::valueOf);
        units.add(Exbibyte::valueOf);
        units.add(Gibibyte::valueOf);
        units.add(Gigabyte::valueOf);
        units.add(Kibibyte::valueOf);
        units.add(Kilobyte::valueOf);
        units.add(Mebibyte::valueOf);
        units.add(Megabyte::valueOf);
        units.add(Pebibyte::valueOf);
        units.add(Petabyte::valueOf);
        units.add(Tebibyte::valueOf);
        units.add(Terabyte::valueOf);
        units.add(Yobibyte::valueOf);
        units.add(Yottabyte::valueOf);
        units.add(Zebibyte::valueOf);
        units.add(Zettabyte::valueOf);
        return units;
    }

    /**
     * @param bytes
     *            The number of bytes to add.
     * @param constructor
     *            The constructor function for the storage unit under test.
     */
    @Theory
    public void shouldAddNumberOfBytes(
            final long bytes,
            final Function<Long, StorageUnit<?>> constructor) {
        // Given
        final StorageUnit<?> unit = constructor.apply(Long.valueOf(1));

        // When
        final StorageUnit<?> calculatedResult = unit.add(bytes);
        final BigInteger expectedResult = BigInteger.valueOf(1 + bytes);

        // Then
        Assert.assertEquals(unit.getClass().getSimpleName() + "cannot add '1' and '" + bytes + "'.",
                expectedResult, calculatedResult.inByte());
    }

    /**
     * @param bytes
     *            The number of bytes to add.
     * @param constructor
     *            The constructor function for the storage unit under test.
     */
    @Theory
    public void shouldAddStorageUnit(
            final long bytes,
            final Function<Long, StorageUnit<?>> constructor) {
        // Given
        final StorageUnit<?> unit = constructor.apply(Long.valueOf(1));
        final StorageUnit<?> unitToAdd = constructor.apply(Long.valueOf(bytes));

        // When
        final StorageUnit<?> calculatedResult = unit.add(unitToAdd);
        final BigInteger expectedResult = BigInteger.valueOf(1 + bytes);

        // Then
        Assert.assertEquals(unit.getClass().getSimpleName() + "cannot add '1' and '" + bytes + "'.",
                expectedResult, calculatedResult.inByte());
    }

    /**
     * @param constructor
     *            The constructor function for the storage unit under test.
     */
    @Theory
    public void shouldReturnNewInstanceAfterAddLong(final Function<Long, StorageUnit<?>> constructor) {
        // Given
        final StorageUnit<?> first = constructor.apply(Long.valueOf(1));

        // When
        final StorageUnit<?> second = first.add(1000);

        // Then
        Assert.assertNotSame("The add(long) method must return a new instance.", first, second);
    }

    /**
     * @param constructor
     *            The constructor function for the storage unit under test.
     */
    @Theory
    public void shouldReturnNewInstanceAfterDivide(final Function<Long, StorageUnit<?>> constructor) {
        // Given
        final StorageUnit<?> first = constructor.apply(Long.valueOf(100));

        // When
        final StorageUnit<?> second = first.divide(5);

        // Then
        Assert.assertNotSame("The divide(long) method must return a new instance.", first, second);
    }

    /**
     * @param constructor
     *            The constructor function for the storage unit under test.
     */
    @Theory
    public void shouldReturnNewInstanceAfterMultiply(final Function<Long, StorageUnit<?>> constructor) {
        // Given
        final StorageUnit<?> first = constructor.apply(Long.valueOf(100));

        // When
        final StorageUnit<?> second = first.multiply(5);

        // Then
        Assert.assertNotSame("The multiply(long) method must return a new instance.", first, second);
    }

    /**
     * @param constructor
     *            The constructor function for the storage unit under test.
     */
    @Theory
    public void shouldReturnNewInstanceAfterSubtractLong(final Function<Long, StorageUnit<?>> constructor) {
        // Given
        final StorageUnit<?> first = constructor.apply(Long.valueOf(100));

        // When
        final StorageUnit<?> second = first.subtract(20);

        // Then
        Assert.assertNotSame("The subtract(long) method must return a new instance.", first, second);
    }

    /**
     * @param bytes
     *            The number of bytes to add.
     * @param constructor
     *            The constructor function for the storage unit under test.
     */
    @Theory
    public void shouldSubtractStorageUnit(
            final long bytes,
            final Function<Long, StorageUnit<?>> constructor) {
        // Given
        final StorageUnit<?> unit = constructor.apply(Long.valueOf(bytes));
        final StorageUnit<?> unitToSubtract = constructor.apply(Long.valueOf(1));

        // When
        final StorageUnit<?> calculatedResult = unit.subtract(unitToSubtract);
        final BigInteger expectedResult = BigInteger.valueOf(bytes - 1);

        // Then
        Assert.assertEquals(unit.getClass().getSimpleName() + "cannot subtract '1' and '" + bytes + "'.",
                expectedResult, calculatedResult.inByte());
    }

    /**
     * @param bytes
     *            The number of bytes to add.
     * @param constructor
     *            The constructor function for the storage unit under test.
     */
    @Theory
    public void shouldSubtractLong(
            final long bytes,
            final Function<Long, StorageUnit<?>> constructor) {
        // Given
        final StorageUnit<?> unit = constructor.apply(Long.valueOf(bytes));

        // When
        final StorageUnit<?> calculatedResult = unit.subtract(1);
        final BigInteger expectedResult = BigInteger.valueOf(bytes - 1);

        // Then
        Assert.assertEquals(unit.getClass().getSimpleName() + "cannot subtract '1' and '" + bytes + "'.",
                expectedResult, calculatedResult.inByte());
    }

    /**
     * @param bytes
     *            The number of bytes to add.
     * @param constructor
     *            The constructor function for the storage unit under test.
     */
    @Theory
    public void shouldDivideLong(
            final long bytes,
            final Function<Long, StorageUnit<?>> constructor) {
        // Given
        final StorageUnit<?> unit = constructor.apply(Long.valueOf(bytes));

        // When
        final StorageUnit<?> calculatedResult = unit.divide(1);
        final BigInteger expectedResult = BigInteger.valueOf(bytes);

        // Then
        Assert.assertEquals(unit.getClass().getSimpleName() + "cannot divide '" + bytes + "' by '1'.",
                expectedResult, calculatedResult.inByte());
    }

    /**
     * @param bytes
     *            The number of bytes to add.
     * @param constructor
     *            The constructor function for the storage unit under test.
     */
    @Theory
    public void shouldMultiplyLong(
            final long bytes,
            final Function<Long, StorageUnit<?>> constructor) {
        // Given
        final StorageUnit<?> unit = constructor.apply(Long.valueOf(bytes));

        // When
        final StorageUnit<?> calculatedResult = unit.multiply(1);
        final BigInteger expectedResult = BigInteger.valueOf(bytes);

        // Then
        Assert.assertEquals(unit.getClass().getSimpleName() + "cannot multiply '" + bytes + "' by '1'.",
                expectedResult, calculatedResult.inByte());
    }

}