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
package org.openacs;

import org.openacs.datamodel.Parameter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openacs.utils.CreateException;
import org.openacs.utils.FinderException;
import org.openacs.utils.RemoveException;
import org.openacs.datamodel.Type;
import org.openacs.js.Script;
import org.openacs.message.AddObject;
import org.openacs.message.AddObjectResponse;
import org.openacs.message.DeleteObject;
import org.openacs.message.Download;
import org.openacs.message.Fault;
import org.openacs.message.GetParameterNames;
import org.openacs.message.GetParameterNamesResponse;
import org.openacs.message.GetParameterValues;
import org.openacs.message.GetParameterValuesResponse;
import org.openacs.message.GetRPCMethods;
import org.openacs.message.GetRPCMethodsResponse;
import org.openacs.message.Inform;
import org.openacs.message.Reboot;
import org.openacs.message.SetParameterValues;
import org.openacs.message.SetParameterValuesResponse;
import org.openacs.message.TransferComplete;
import org.openacs.message.X_00000C_SetConfiguration;
import org.openacs.repository.HostsLocalRepository;
import org.openacs.service.DaoService;
import org.openacs.utils.Ejb;
import org.openacs.utils.Version;
import org.openacs.vendors.Vendor;
import org.openacs.entity.*;
import org.openacs.repository.ScriptLocalRepository;
import org.openacs.repository.DSLStatsLocalRepository;
import org.openacs.repository.ATMErrorsStatsLocalRepository;
import org.openacs.repository.SoftwareDetailLocalRepository;

public class Configurator extends Thread implements ICpe {

    protected static final int MIN_CALL_TIMEOUT = 15;
    protected static final int DEFAULT_CALL_TIMEOUT = 60 * 2;
    protected static final int MAX_CALL_TIMEOUT = 60 * 10;
    protected static final String DSL_IFC_CFG = ".WANDevice.1.WANDSLInterfaceConfig.";
    protected long timeout = 180;
    private Inform lastInform;
    private HostsLocal host;
    private DeviceProfileLocal deviceProfile;
    private ArrayList<TransferComplete> transferComplete;
    private String fwpath;
    private String urlServer;
    //private CPELocal cpe;
    private String paramConfigVersion;
    private String KEY_SOFTWARE = "dlSoftware";
    private String KEY_CONFIG = "dlConfig";
    private String paramConfigName;
    private Long hostid;
    private String sessionid;
    private DaoService daoService;
  
    //private static final String OUI_Thomson = "00147F";
    //private static final String OUI_Thomson2 = "0090D0";

    public Configurator(Inform lastInform, Long hostid, ArrayList<TransferComplete> transferComplete, String fwpath, String urlServer, String sessionid, DaoService daoService) {
        this.lastInform = lastInform;
        this.hostid = hostid;
        this.transferComplete = transferComplete;
        this.fwpath = fwpath;
        this.urlServer = urlServer;
        //this.cpe = Ejb.lookupCPEBean();
        String vcf = lastInform.getRoot() + ".DeviceInfo.VendorConfigFile.1.";
        this.paramConfigVersion = vcf + "Version";
        this.paramConfigName = vcf + "Name";
        this.sessionid = sessionid;
        this.daoService = daoService;
    }

    private Message Call(Message request, long timeout) {
        return Call(host, request, timeout);
    }

    public boolean testing() {
        /*
        GetRPCMethods grpcm = new GetRPCMethods();
        Message m = Call(grpcm, 30);
        if (m instanceof GetRPCMethodsResponse) {
        GetRPCMethodsResponse gpnr = (GetRPCMethodsResponse) m;
        for (String method : gpnr.methods) {
        System.out.println("Method: " + method);
        }
        }
        X_00000C_ShowStatus sm = new X_00000C_ShowStatus();
        //sm.addCommand("show cwmp map");
        //sm.addCommand("show cwmp parameter all");
        //sm.addCommand("show cwmp persistent");
        sm.addCommand("show cwmp session");
        m = Call(sm, 30);
         */
        X_00000C_SetConfiguration sc = new X_00000C_SetConfiguration();
        sc.addCommand("clock timezone CST 8");
//        sc.addCommand(" enable");
        //       sc.addCommand("!");
        Call(sc, 30);
        /*
        String b = lastInform.getRoot() + ".ManagementServer.";
        String pii = b + "PeriodicInformInterval";
        String[] n = new String[]{            pii        };
        GetParameterValues gpv = new GetParameterValues(n);
        m = Call(gpv, 30);
        System.out.println("Getting Periodic inform ");
        if (m instanceof GetParameterValuesResponse) {
        GetParameterValuesResponse gpvr = (GetParameterValuesResponse) m;
        int c = gpvr.getParamInt(pii);
        System.out.println("Periodic int" + c);
        
        SetParameterValues spv = new SetParameterValues();
        spv.AddValue(pii, c);
        Message m2 = Call(spv, 30);
        }
         */
        /*
        Upload up = new Upload();
        up.CommandKey="ck";
        up.FileType = Upload.FT_LOG;
        up.URL = "http://78.60.200.151:8080/openacs/upload";
        
        Call(up, 30);
         */
        return true;
    }
    private Logger logger = Logger.getLogger(Configurator.class.getName());

    private void log(Level level, String msg) {
        if (lastInform != null) {
            logger.log(level, lastInform.getOui() + ":" + lastInform.sn + " " + msg);
        } else {
            logger.log(level, msg);
        }
    }

    private void log(Level level, String msg, Throwable ex) {
        if (lastInform != null) {
            logger.log(level, lastInform.getOui() + ":" + lastInform.sn + " " + msg, ex);
        } else {
            logger.log(level, msg, ex);
        }
    }

    @Override
    public void run() {
        try {
            log(Level.INFO, "Configurator::run");
            HostsLocalRepository hsthome = daoService.getHostsRepository();
            HostsLocal host = hsthome.findById(hostid);
            if(null != host){
                if (host.getForcePasswords() == null) {
                    host.setForcePasswords(false);
                }
            } else {
                log(Level.SEVERE, "cann't find HostsLocal ");
                return;
            }

            if (host.getReboot() != null && host.getReboot()) {
                if (!(lastInform.isEvent(Inform.EVENT_BOOT) || lastInform.isEvent(Inform.EVENT_BOOT_STRAP))) {
                    log(Level.INFO, "force reboot");
                    Message m = Call(new Reboot("forced_reboot"), DEFAULT_CALL_TIMEOUT);
                    host.setReboot(false);
                    return;
                } else {
                    host.setReboot(false);
                }
            }
            /* MING TODO
            HardwareModelLocal hw = host.getModel();
            if (host.getProfileName() == null) {
                host.setProfileName("Default");
            }
            
            try {
                deviceProfile = Ejb.lookupDeviceProfileBean().findByPrimaryKey(host.getProfileName());
            } catch (FinderException ex) {
                log(Level.SEVERE, "Device profile '" + host.getProfileName() + "' not found.", ex);
            }
            if (SyncCwmpVariables(host.getForcePasswords()) && host.getForcePasswords()) {
                host.setForcePasswords(false);
            }

            log(Level.INFO, "Backup cwmp tree");
            BackupLocalHome bckHome = Ejb.lookupBackupBean();
            Timestamp tsBackup = null;
            try {
                tsBackup = bckHome.getTimeOfLastBackup((Integer) host.getId());
            } catch (FinderException ex) {
                log(Level.INFO, "No cfg backup found.");
            }
            onTransferComplete();

            if (!lastInform.isEvent(Inform.EVENT_CONNECTION_REQUEST)) {
                if (deviceProfile != null
                        && (lastInform.isEvent(Inform.EVENT_PERIODIC) || lastInform.isEvent(Inform.EVENT_BOOT) || lastInform.isEvent(Inform.EVENT_BOOT_STRAP))) {
                    updateSoftware();
                    Vendor vendor = Vendor.getVendor(lastInform.getOui(), hw.getHclass(), hw.getVersion());
                    switch (vendor.getConfigUpdateMethod()) {
                        case Vendor.CFG_UPDATE_METHOD_2:
                            System.out.println("Configurator: UPDATE by name/version");
                            updateConfigThomson();
                            break;
                        case Vendor.CFG_UPDATE_METHOD_1:
                        default:
                            System.out.println("Configurator: UPDATE ProvisioningCode");
                            updateConfigByProvisioningCode();
                            break;
                    }
                    if (lastInform.isEvent(Inform.EVENT_PERIODIC)) {
                        CheckParameters();
                        RemoveDSLStats();
                        RemoveATMErrorStats();
                        Integer d = deviceProfile.getDayskeepstats();
                        if (d != null && d > 0) {
                            SaveDSLStats();
                            SaveATMErrorsStats();
                        }
                        Integer i = deviceProfile.getSaveParamValuesInterval();
                        if (i == null) {
                            i = 0;
                        }
                        //System.out.println("getSaveParamValuesInterval: " + i);
                        //System.out.println("tsBackup: " + tsBackup);
                        //System.out.println ("now: "+Calendar.getInstance().getTimeInMillis()+ " tsBackup "+tsBackup.getTime());
                        if (i > 0 && (tsBackup == null || (Calendar.getInstance().getTimeInMillis() - tsBackup.getTime() > 3600 * 1000 * i))) {
                            //System.out.println("Do backup");
                            // cache parameters for browsing
                            BackupCWMPTree(bckHome, tsBackup);
                        }
                        GetSoftwareDetails();
                        UpdateVoiceCaps();
                    }
                } else if (deviceProfile != null && (lastInform.isEvent(Inform.EVENT_VALUE_CHANGE) || lastInform.isEvent(Inform.EVENT_BOOT))) {
                    CheckParameters();
                    // cache parameter values for browsing
                    if ((lastInform.isEvent(Inform.EVENT_VALUE_CHANGE) && deviceProfile.getSaveParamValuesOnChange()) || (lastInform.isEvent(Inform.EVENT_BOOT) && deviceProfile.getSaveParamValuesOnBoot())) {
                        BackupCWMPTree(bckHome, tsBackup);
                    }
                }
            } else {
                CheckParameters();
            }*/
        } catch (Exception e) {
            log(Level.SEVERE, e.getMessage());
        }

        /*
        try {
            String sn = deviceProfile.getScriptname();
            if (sn == null) {
                deviceProfile.setScriptname("Default");
                sn = "Default";
            }
            runScript(sn, (transferComplete.size() > 0) ? transferComplete.get(0) : null);
        } catch (Exception e) {
            log(Level.SEVERE, e.getMessage());
        }
        */
    }

    public static String getProvisioningCode(String name, String version) {
        return Integer.toString(name.hashCode(), 16) + "." + Integer.toString(version.hashCode(), 16);
//        return version;
    }

    private void runScript(String scriptName, TransferComplete tc) {

        try {
        	ScriptLocalRepository scriptHome = daoService.getScriptRepository();
            ScriptLocal sb;
            sb = scriptHome.findByName(scriptName);
            Script script = new Script(lastInform, new String(sb.getScript()), host, tc, this, sessionid);
            script.run();
        } catch (FinderException ex) {
            log(Level.WARNING, "Configuration script '" + scriptName + "' not found in db.");
        }
    }

    private void onTransferComplete() {
        for (TransferComplete tc : transferComplete) {
            String result = (tc.FaultCode != 0) ? tc.FaultCode + ": " + tc.FaultString : "OK";
            if (tc.CommandKey.startsWith(KEY_CONFIG)) {
                host.setCfgupdres(result);

                if (tc.FaultCode == 0) {
                    host.setCfgforce(false);
                    int ix = tc.CommandKey.indexOf('.');
                    if (ix != -1) {
                        SetParameterValues spv = new SetParameterValues();
                        spv.key = "onTransferComplete";
                        String provisioningCode = tc.CommandKey.substring(ix + 1);
                        spv.AddValue(lastInform.getRoot() + ".DeviceInfo.ProvisioningCode", provisioningCode);
                        System.out.println("OnTransfercomplete: Set " + lastInform.getRoot() + ".DeviceInfo.ProvisioningCode -> " + tc.CommandKey.substring(ix + 1));
                        Call(spv, timeout);
                        lastInform.setProvisiongCode(provisioningCode);
                    }
                }

            } else if (tc.CommandKey.equals(KEY_SOFTWARE)) {
                host.setSfwupdres(result);
            } else {
                runScript("OnTransferComplete", tc);
            }
        }
    }

    public void SaveDSLStats() {
    	DSLStatsLocalRepository statsHome = daoService.getdSLStatsRepository();
        String b = lastInform.getRoot() + DSL_IFC_CFG;
        /*
        String[] n = new String[]{
        b + "DownstreamAttenuation",
        b + "DownstreamCurrRate",
        b + "DownstreamMaxRate",
        b + "DownstreamNoiseMargin",
        b + "DownstreamPower",
        b + "UpstreamAttenuation",
        b + "UpstreamCurrRate",
        b + "UpstreamMaxRate",
        b + "UpstreamNoiseMargin",
        b + "UpstreamPower"
        };
         */
        GetParameterValues gpv = new GetParameterValues(b);
        Message m = Call(gpv, DEFAULT_CALL_TIMEOUT);
        if (m instanceof GetParameterValuesResponse) {
            GetParameterValuesResponse gpvr = (GetParameterValuesResponse) m;
            try {
            	DSLStatsLocal dslStats = new DSLStatsLocal();
            	dslStats.setHostid(this.host.getId());
            	dslStats.setTime(host.getLastcontact());
            	dslStats.setDownstreamAttenuation( gpvr.getParamInt(b + "DownstreamAttenuation"));
            	dslStats.setDownstreamCurrRate(gpvr.getParamInt(b + "DownstreamCurrRate"));
            	dslStats.setDownstreamMaxRate(gpvr.getParamInt(b + "DownstreamMaxRate"));
            	dslStats.setDownstreamNoiseMargin(gpvr.getParamInt(b + "DownstreamNoiseMargin"));
            	dslStats.setDownstreamPower(gpvr.getParamInt(b + "DownstreamPower"));
            	dslStats.setUpstreamAttenuation(gpvr.getParamInt(b + "UpstreamAttenuation"));
            	dslStats.setUpstreamCurrRate(gpvr.getParamInt(b + "UpstreamCurrRate"));
            	dslStats.setUpstreamMaxRate(gpvr.getParamInt(b + "UpstreamMaxRate"));
            	dslStats.setUpstreamNoiseMargin(gpvr.getParamInt(b + "UpstreamNoiseMargin"));
            	dslStats.setUpstreamPower(gpvr.getParamInt(b + "UpstreamPower"));
            	dslStats.setStatus(gpvr.getParam(b + "Status"));
            	dslStats.setModulationType(gpvr.getParam(b + "ModulationType"));
            	dslStats = statsHome.save(dslStats); 
            } catch (CreateException ex) {
                log(Level.SEVERE, null, ex);
            }
        } else {
            log(Level.SEVERE, m.toString());
        }
    }

    private Long getStat(String b, String type, String name, Hashtable<String, String> v) {
        String fn = b + type + "." + name;
        String value = v.get(fn);

//        System.out.println ("getStat: "+fn+"="+value);
        if (value != null) {
            return Long.valueOf(value);
        } else if (type.equals("Showtime")) {
            return getStat(b, "ShowTime", name, v);  // fix for thomson misspell
        }
        return null;
    }

    public void SaveATMErrorsStats() {
        ATMErrorsStatsLocalRepository statsHome = daoService.getATMErrorsStatsRepository();
        String b = lastInform.getRoot() + ".WANDevice.1.WANDSLInterfaceConfig.Stats.";
        //System.out.println("Get vars: " + b);
        String[] nms = {b, lastInform.getRoot() + DSL_IFC_CFG};
        GetParameterValues gpv = new GetParameterValues(nms);
        Message m = Call(gpv, DEFAULT_CALL_TIMEOUT);
        if (m instanceof GetParameterValuesResponse) {
//            System.out.println("Got vars: " + m);
            GetParameterValuesResponse gpvr = (GetParameterValuesResponse) m;
            String typenames[] = {
                "Showtime", "Total", "QuarterHour", "CurrentDay", "LastShowtime"
            };
            int types[] = {
                ATMErrorsStatsLocal.TYPE_SHOWTIME, ATMErrorsStatsLocal.TYPE_TOTAL, ATMErrorsStatsLocal.TYPE_QUARTERHOUR, ATMErrorsStatsLocal.TYPE_CURRENTDAY, ATMErrorsStatsLocal.TYPE_LASTSHOWTIME
            };
            for (int i = 0; i < typenames.length; i++) {
                String type = typenames[i];
//                System.out.println("Type name=" + typenames[i]);
                String is = gpvr.values.get(lastInform.getRoot() + DSL_IFC_CFG + type + "Start");
                /*
                boolean f = false;
                for (String n : ns) {
                //String name = b + typenames[i] + "." + n;
                //System.out.println("Is key? " + name);
                //if (gpvr.values.get(name) != null) {
                if (getStat(b, type, n, gpvr.values) != null) {
                f = true;
                break;
                }
                }
                System.out.println("f=" + f);
                
                if (f) {
                 */
                if (is != null) {
                    try {
                        String n = lastInform.getRoot() + DSL_IFC_CFG + type + "Start";
//                        System.out.println ("n="+n+" v="+is);
                        Timestamp intervalStart = new Timestamp(host.getLastcontact().getTime() - Long.valueOf(is) * 1000);
                        ATMErrorsStatsLocal ss = new ATMErrorsStatsLocal();
                        ss.setHostid(this.host.getId());
                        ss.setTime(host.getLastcontact());
                        ss.setType(types[i]);
                        ss.setIntervalStart(intervalStart);
                        ss.setATUCCRCErrors(getStat(b, type, "ATUCCRCErrors", gpvr.values));
                        ss.setATUCFECErrors(getStat(b, type, "ATUCFECErrors", gpvr.values));
                        ss.setATUCHECErrors(getStat(b, type, "ATUCHECErrors", gpvr.values));
                        ss.setCellDelin(getStat(b, type, "CellDelin", gpvr.values));
                        ss.setCRCErrors(getStat(b, type, "CRCErrors", gpvr.values));
                        ss.setFECErrors(getStat(b, type, "FECErrors", gpvr.values));
                        ss.setHECErrors(getStat(b, type, "HECErrors", gpvr.values));
                        ss.setErroredSecs(getStat(b, type, "ErroredSecs", gpvr.values));
                        ss.setInitErrors(getStat(b, type, "InitErrors", gpvr.values));
                        ss.setInitTimeouts(getStat(b, type, "InitTimeouts", gpvr.values));
                        ss.setLinkRetrain(getStat(b, type, "LinkRetrain", gpvr.values));
                        ss.setLossOfFraming(getStat(b, type, "LossOfFraming", gpvr.values));
                     	ss.setReceiveBlocks(getStat(b, type, "ReceiveBlocks", gpvr.values));
                        ss.setSeverelyErroredSecs(getStat(b, type, "SeverelyErroredSecs", gpvr.values));
                        ss.setTransmitBlocks(getStat(b, type, "TransmitBlocks", gpvr.values));
                        ss.setLossOfPower(getStat(b, type, "X_000E50_LossOfPower", gpvr.values));
                        ss.setLossOfSignal(getStat(b, type, "X_000E50_LossOfSignal", gpvr.values));
                        ss = statsHome.save(ss); 
                    } catch (CreateException ex) {
                        log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            log(Level.SEVERE, m.toString());
        }
    }

    private void RemoveDSLStats() {
        DSLStatsLocalRepository statsHome = daoService.getdSLStatsRepository();
        try {
            long t = Calendar.getInstance().getTimeInMillis();
            if (deviceProfile.getDayskeepstats() != null && deviceProfile.getDayskeepstats() < 0) {
                deviceProfile.setDayskeepstats(0);
            }

            if (deviceProfile.getDayskeepstats() != null) {
                t -= 3600 * 24 * 1000 * deviceProfile.getDayskeepstats();
            } else {
                t -= 60 * 15 * 1000;
            }
            Timestamp ts = new Timestamp(t);
            //System.out.println ("Remove stats before: "+ts+" (keep for "+deviceProfile.getDayskeepstats()+" profile="+deviceProfile.getName());
            Collection<DSLStatsLocal> ss = statsHome.findByTimeBeforeAndHostid(ts, host.getId());
            for (DSLStatsLocal s : ss) {
                try {
                    //System.out.println ("Remove:"+s.getTime());
                	statsHome.delete(s);
                } catch (RemoveException ex) {
                    log(Level.SEVERE, null, ex);
                }
            }

        } catch (FinderException ex) {
            log(Level.SEVERE, null, ex);
        }

    }

    private void RemoveATMErrorStats() {
        ATMErrorsStatsLocalRepository statsHome = daoService.getATMErrorsStatsRepository();
        try {
            long t = Calendar.getInstance().getTimeInMillis();
            if (deviceProfile.getDayskeepstats() != null && deviceProfile.getDayskeepstats() < 0) {
                deviceProfile.setDayskeepstats(0);
            }

            if (deviceProfile.getDayskeepstats() != null) {
                t -= 3600 * 24 * 1000 * deviceProfile.getDayskeepstats();
            } else {
                t -= 60 * 15 * 1000;
            }
            Timestamp ts = new Timestamp(t);
            //System.out.println ("Remove stats before: "+ts+" (keep for "+deviceProfile.getDayskeepstats()+" profile="+deviceProfile.getName());
            Collection<ATMErrorsStatsLocal> ss = statsHome.findByTimeBeforeAndHostid(ts, host.getId());
            for (ATMErrorsStatsLocal s : ss) {
                try {
                    //System.out.println ("Remove:"+s.getTime());
                	statsHome.delete(s);
                } catch (RemoveException ex) {
                    log(Level.SEVERE, null, ex);
                }
            }

        } catch (FinderException ex) {
            log(Level.SEVERE, null, ex);
        }

    }

    private void CheckParameters() {
        log(Level.INFO, "Configurator::CheckParameters");
        String b = lastInform.getRoot() + ".ManagementServer.";
        Properties parameters = new Properties();
        if (deviceProfile != null) {
            Integer i = deviceProfile.getInforminterval();
            if (i != null && i > 0) {
                parameters.put(b + "PeriodicInformInterval", i.toString());
                parameters.put(b + "PeriodicInformEnable", "1");
            }
        }

        SyncParameterValues(parameters);
    }

    private void _CheckParameters() {
        String b = lastInform.getRoot() + ".ManagementServer.";
        String pii = b + "PeriodicInformInterval";
        String[] n = new String[]{
            pii
        };
        GetParameterValues gpv = new GetParameterValues(n);
        Message m = Call(gpv, 30);
        System.out.println("Getting Periodic inform");

        if (m instanceof GetParameterValuesResponse) {
            GetParameterValuesResponse gpvr = (GetParameterValuesResponse) m;
            int c = gpvr.getParamInt(pii, 0);
            int t = deviceProfile.getInforminterval();
            System.out.println("Periodic inform int: " + c + " " + t);
            if (c != t) {
                System.out.println("Set Periodic inform to: " + t);

                SetParameterValues spv = new SetParameterValues();
                spv.AddValue(pii, t);
                Message m2 = Call(spv, 30);
                if (m2.isFault()) {
                    log(Level.SEVERE, m2.toString());
                } else {
                    System.out.println("Set success");
                }

            }
        } else {
            System.out.println("Getting Periodic inform FAULT " + m);
            log(Level.SEVERE, (m != null) ? m.toString() : "Getting Periodic inform timeout");
        }
    }

    private void CheckParameters(Map<String, String> parameters) {
        if (parameters.isEmpty()) {
            return;
        }

        GetParameterValues gpv = new GetParameterValues(parameters);
        Message m = Call(gpv, DEFAULT_CALL_TIMEOUT);
        if (m instanceof GetParameterValuesResponse) {
            GetParameterValuesResponse gpvr = (GetParameterValuesResponse) m;
            SetParameterValues spv = new SetParameterValues();
            for (Entry<String, String> e : parameters.entrySet()) {
                if (!gpvr.values.get(e.getKey()).equals(e.getValue())) {
                    spv.AddValue(e.getKey(), e.getValue());
                }
            }
            if (!spv.isEmpty()) {
                Message m2 = Call(spv, DEFAULT_CALL_TIMEOUT);
                if (m2.isFault()) {
                    System.out.println("Configurator::CheckParameters set values FAULT");
                    log(Level.SEVERE, m2.toString());
                } else {
                    System.out.println("Set success");
                }
            }
        } else {
            System.out.println("Configurator::CheckParameters get values FAULT");
            log(Level.SEVERE, m.toString());
        }
    }

    private void UpdateVoiceCaps() {
    	SoftwareDetailLocalRepository sdh = daoService.getSoftwareDetailLocalRepository();
        SoftwareDetailLocal sdl = null;
        try {
            sdl = sdh.findOne(new SoftwareDetailPK(host.getHwid(), host.getCurrentsoftware()));
        } catch (FinderException ex) {
            return;
        }

        if (sdl == null || sdl.getVoicecaps() != null) {
            return;
        }

        ByteArrayOutputStream streamVoiceCaps = null;
        GetParameterValues gpv = new GetParameterValues(lastInform.getRoot() + ".Services.VoiceService.");
        Message m = Call(gpv, timeout);
        if (m == null) {
            return;
        }
        if (m instanceof GetParameterValuesResponse) {
            GetParameterValuesResponse gpvr = (GetParameterValuesResponse) m;
            Properties caps = new Properties();
            for (Entry<String, String> e : gpvr.values.entrySet()) {
                if (e.getKey().contains(".Capabilities.") || e.getKey().contains(".PhyInterface.")) {
                    caps.put(e.getKey(), e.getValue());
                }
            }

            streamVoiceCaps = new ByteArrayOutputStream(1024 * 1024);
            try {
                caps.store(streamVoiceCaps, null);
            } catch (IOException ex) {
                log(Level.SEVERE, null, ex);
                return;
            }
            sdl.setVoicecaps(streamVoiceCaps.toByteArray());
        } else {
            //logger.log(Level.WARNING, "Failed get voice service caps: " + m.toString());
            sdl.setVoicecaps("".getBytes());
        }
    }
    private Message request;
    private Message response;

    public synchronized Message ReceiveRequest(long w) {
        Message r = request;
        //System.out.println ("Configurator::ReceiveRequest w="+w+" req="+request+" resp="+response);
        if (w != 0) {
            try {
                wait(w);
                //System.out.println ("Configurator::ReceiveRequest2 w="+w+" req="+request+" resp="+response);
            } catch (InterruptedException ex) {
                //System.out.println ("Configurator::ReceiveRequest3 w="+w+" req="+request+" resp="+response);
            }
            r = request;
        }
        request = null;
        response = null;
        return r;
    }

    public synchronized void SendResponse(Message response) {
        this.response = response;
        //System.out.println ("Configurator::SendResponse req="+request+" resp="+response);
        notify();
        //System.out.println ("Configurator::SendResponse2 req="+request+" resp="+response);
    }

    public synchronized Message Call(HostsLocal host, Message call, long timeout) {
    	return null;
    }
    private int callType = 1;

    public void SetCallType(int callType) {
        this.callType = callType;
    }
    public void BackupCWMPTree() {}
    public void SyncParameterValues(Properties vars) {}
}
