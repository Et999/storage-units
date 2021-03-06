/*
 * This file is part of storage-units. It is subject to the license terms in the LICENSE file found in the top-level
 * directory of this distribution and at http://creativecommons.org/publicdomain/zero/1.0/. No part of storage-units,
 * including this file, may be copied, modified, propagated, or distributed except according to the terms contained
 * in the LICENSE file.
 */
package de.xn__ho_hia.storage_unit;

import static de.xn__ho_hia.quality.null_analysis.Nullsafe.addNullsafe;
import static de.xn__ho_hia.quality.null_analysis.Nullsafe.asBigInteger;
import static de.xn__ho_hia.quality.null_analysis.Nullsafe.divideNullsafe;
import static de.xn__ho_hia.quality.null_analysis.Nullsafe.multiplyNullsafe;
import static de.xn__ho_hia.quality.null_analysis.Nullsafe.subtractNullsafe;

import java.math.BigInteger;
import java.util.function.Function;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Gigabyte as specified in ISO IEC 80000-13:2008 (1 Gigabyte = 1 000 000 000 Byte).
 */
public class Gigabyte extends StorageUnit<Gigabyte> {

    private static final long serialVersionUID = 7581075190529125530L;

    Gigabyte(@NonNull final BigInteger bytes) {
        super(bytes);
    }

    /**
     * @param numberOfBytes
     *            The amount of bytes the Gigabyte contains.
     * @return A new Gigabyte unit with the given value.
     */
    @NonNull
    public static Gigabyte valueOf(@NonNull final BigInteger numberOfBytes) {
        return new Gigabyte(numberOfBytes);
    }

    /**
     * @param numberOfBytes
     *            The amount of bytes the Gigabyte contains.
     * @return A new Gigabyte unit with the given value.
     */
    @NonNull
    public static Gigabyte valueOf(final long numberOfBytes) {
        return new Gigabyte(asBigInteger(numberOfBytes));
    }

    /**
     * @param numberOfBytes
     *            The amount of bytes the Gigabyte contains.
     * @return A new Gigabyte unit with the given value.
     */
    @NonNull
    public static Gigabyte valueOf(@NonNull final Long numberOfBytes) {
        return valueOf(numberOfBytes.longValue());
    }

    @Override
    public Gigabyte add(final long bytesToAdd) {
        return new Gigabyte(addNullsafe(bytes, asBigInteger(bytesToAdd)));
    }

    @Override
    public Gigabyte add(final StorageUnit<?> storageAmount) {
        return new Gigabyte(addNullsafe(bytes, storageAmount.bytes));
    }

    @Override
    public Gigabyte divide(final long divisor) {
        return new Gigabyte(divideNullsafe(bytes, asBigInteger(divisor)));
    }

    @Override
    public Gigabyte multiply(final long factor) {
        return new Gigabyte(multiplyNullsafe(bytes, asBigInteger(factor)));
    }

    @Override
    public Gigabyte subtract(final long bytesToSubtract) {
        return new Gigabyte(subtractNullsafe(bytes, asBigInteger(bytesToSubtract)));
    }

    @Override
    public Gigabyte subtract(final StorageUnit<?> storageAmount) {
        return new Gigabyte(subtractNullsafe(bytes, storageAmount.bytes));
    }

    @Override
    protected BigInteger getNumberOfBytesPerUnit() {
        return StorageUnit.BYTES_IN_A_GIGABYTE;
    }

    @Override
    protected String getSymbol() {
        return "GB"; //$NON-NLS-1$
    }

    @Override
    protected Function<@NonNull BigInteger, @NonNull StorageUnit<?>> converter() {
        return StorageUnits::decimalValueOf;
    }

}
