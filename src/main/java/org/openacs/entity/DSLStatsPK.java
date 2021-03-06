/*
 * 
 * Copyright 2007-2012 Audrius Valunas
 * 
 * This file is part of LibreACS.

 * LibreACS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * LibreACS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with LibreACS.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.openacs.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class DSLStatsPK implements Serializable {

    public Long hostid;
    public Timestamp time;

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public DSLStatsPK() {
    }

    public DSLStatsPK(Long hostid, Timestamp time) {
        this.hostid = hostid;
        this.time = time;
    }

    @Override
    public boolean equals(java.lang.Object otherOb) {

        if (this == otherOb) {
            return true;
        }
        if (!(otherOb instanceof org.openacs.entity.DSLStatsPK)) {
            return false;
        }
        org.openacs.entity.DSLStatsPK other = (org.openacs.entity.DSLStatsPK) otherOb;
        return ((hostid == null ? other.hostid == null : hostid == other.hostid)
                && (time == null ? other.time == null : time.equals(other.time)));
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return ((hostid == null ? 0 : hostid.hashCode())
                ^ (time == null ? 0 : time.hashCode()));
    }
}
