/**
 * <h1>Short.java</h1>
 * <p>
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version; or, at your choice, under the terms of the
 * Mozilla Public License, v. 2.0. SPDX GPL-3.0+ or MPL-2.0+.
 * </p>
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License and the Mozilla Public License for more details.
 * </p>
 * <p>
 * You should have received a copy of the GNU General Public License and the Mozilla Public License
 * along with this program. If not, see <a href="http://www.gnu.org/licenses/">http://www.gnu.org/licenses/</a> and at
 * <a href="http://mozilla.org/MPL/2.0">http://mozilla.org/MPL/2.0</a> .
 * </p>
 * <p>
 * NB: for the © statement, include Easy Innova SL or other company/Person contributing the code.
 * </p>
 * <p>
 * © 2015 Easy Innova, SL
 * </p>
 *
 * @author Antonio Manuel Lopez Arjona
 * @version 1.0
 * @since 6/7/2015
 */
package com.easyinnova.iptc;

import java.util.List;

import com.easyinnova.tiff.model.types.Byte;

/**
 * The Class Short.
 */
public class Short extends abstractIptcType {

    /**
     * The value.
     */
    private int value;


    /**
     * Instantiates a new Short.
     */
    public Short() {
        super();
        this.value = 0;
        setType(1);
    }

    /**
     * Instantiates a new Short.
     *
     * @param value the value represented in List<Byte>
     */
    public void read(List<Byte> value) {
        if (value.size() == 2) {
            this.value =
                ((value.get(0).toInt() << 8) & 0x0000ff00) | (value.get(1).toInt() & 0x000000ff);
        }
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public int getValue() {
        return (value & 0xffff);
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(short value) {
        this.value = value;
    }

    @Override public java.lang.String toString() {
        return "" + (value & 0xffff);
    }

}
