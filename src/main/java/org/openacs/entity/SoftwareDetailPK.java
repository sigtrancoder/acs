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

public class SoftwareDetailPK implements Serializable {

    public Long hwid;
    public String version;

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public SoftwareDetailPK() {
    }

    public SoftwareDetailPK(Long hwid, String version) {
        this.hwid = hwid;
        this.version = version;
    }

    @Override
    public boolean equals(java.lang.Object otherOb) {

        if (this == otherOb) {
            return true;
        }
        if (!(otherOb instanceof org.openacs.entity.SoftwareDetailPK)) {
            return false;
        }
        org.openacs.entity.SoftwareDetailPK other = (org.openacs.entity.SoftwareDetailPK) otherOb;
        return ((hwid == null ? other.hwid == null : hwid == other.hwid)
                && (version == null ? other.version == null : version.equals(other.version)));
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return ((hwid == null ? 0 : hwid.hashCode())
                ^ (version == null ? 0 : version.hashCode()));
    }

    @Override
    public String toString() {
        StringBuffer b = new StringBuffer();
        b.append("hwid=");
        b.append(hwid);
        b.append(" version=");
        b.append(version);
        return b.toString();
    }
}
