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
package org.openacs.web;

import java.util.Collection;
import org.openacs.utils.CreateException;
import org.openacs.utils.FinderException;
import org.openacs.utils.RemoveException;
import org.openacs.entity.HostPropertyLocal;
import org.openacs.entity.HostPropertyPK;
import org.openacs.repository.HostPropertyLocalRepository;
import org.openacs.utils.Ejb;

public class HostPropertySet extends PropertySet<Property> {

    private HostPropertyLocalRepository home;
    private Integer hostid;

    public HostPropertySet(Integer hostid) {
        this.home = Ejb.lookupHostPropertyBean();
        this.hostid = hostid;
    }

    public void Load(Collection<HostPropertyLocal> props) {
        //System.out.println ("HostPropertySet::Load");
        for (HostPropertyLocal p : props) {
            //System.out.println ("HostPropertySet::Load add property "+p.getName()+"="+p.getValue());
            original.put(p.getName(), new Property(p.getName(), p.getValue()));
        }
        super.Load();
    }

    @Override
    protected void DeleteEntry(Property p) throws RemoveException {
        //System.out.println ("HostPropertySet::DeleteEntry "+hostid+" "+p.getName());
    	home.delete(new HostPropertyPK(hostid, p.getName()));
    }

    @Override
    protected void SetEntry(Property p) throws FinderException {
        //System.out.println ("HostPropertySet::SetEntry "+hostid+" "+p.getName());
        HostPropertyLocal pl = home.findOne(new HostPropertyPK(hostid, p.getName()));
        pl.setValue(p.getValue());
    }

    @Override
    protected void InsertEntry(Property p) throws CreateException {
        home.save(new HostPropertyLocal(hostid, p.getName(), p.getValue())); 
    }
}
