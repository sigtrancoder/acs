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
import org.openacs.entity.ProfilePropertyLocal;
import org.openacs.entity.ProfilePropertyPK;
import org.openacs.repository.ProfilePropertyLocalRepository;
import org.openacs.utils.Ejb;

public class ProfilePropertySet extends PropertySet<Property> {

    private ProfilePropertyLocalRepository home;
    private String profilename;

    public ProfilePropertySet(String profilename) {
        this.home = Ejb.lookupProfilePropertyBean();
        this.profilename = profilename;
    }

    public void Load(Collection<ProfilePropertyLocal> props) {
        for (ProfilePropertyLocal p : props) {
            original.put(p.getName(), new Property(p.getName(), p.getValue()));
        }
        Load();
    }

    @Override
    public void Load() {
        //System.out.println ("ProfilePropertySet::Load name="+profilename);
        ProfilePropertyLocalRepository pplh = Ejb.lookupProfilePropertyBean();
        Collection<ProfilePropertyLocal> props;
        try {
            props = pplh.findByProfilename(profilename);
            for (ProfilePropertyLocal p : props) {
                //System.out.println ("ProfilePropertySet::Load "+p.getName()+"->"+p.getValue());
                original.put(p.getName(), new Property(p.getName(), p.getValue()));
            }
            super.Load();
        } catch (FinderException ex) {
            //System.out.println ("ProfilePropertySet::Load ex="+ex.getMessage());
        }
    }

    @Override
    protected void DeleteEntry(Property p) throws RemoveException {
        home.delete(new ProfilePropertyPK(profilename, p.getName()));
    }

    @Override
    protected void SetEntry(Property p) throws FinderException {
        ProfilePropertyLocal pl = home.findOne(new ProfilePropertyPK(profilename, p.getName()));
        pl.setValue(p.getValue());
    }

    @Override
    protected void InsertEntry(Property p) throws CreateException {
    	ProfilePropertyLocal pp = new ProfilePropertyLocal(profilename, p.getName(), p.getValue());
        home.save(pp);
    }
}
