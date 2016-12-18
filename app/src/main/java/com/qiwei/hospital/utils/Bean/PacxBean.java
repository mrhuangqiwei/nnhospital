package com.qiwei.hospital.utils.Bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/15.
 */
public class PacxBean {
String NAME,SEX,PINYIN,CLINICNO,INPATIENTNO,PATIENTID,STUDYID,AGE,AGEUNIT,LODGESECTION,
        LODGEDOCTOR,LODGEDATE,BEDNO,applyNO,applySerialNumber,applyitem,applyitemAll,
        applyID,CLISINPAT,ENROLDOCTOR,ENOLDATE,SUGERYRSESULT,CHECKPURPOSE,STATUS,CLASSNAME,
        PHOTONO,TOTALFEE,INHOSPITALNO,MODALITYNAME,CHECKDATE,CHECKDOCTOR,PARTOFCHECK,reportDate,reportDoctor,
        accession_num;
    public PacxBean(){}
    public PacxBean(String NAME,String SEX,String PINYIN, String  CLINICNO,String INPATIENTNO,String PATIENTID,String  STUDYID,String AGE,String AGEUNIT,
                    String LODGESECTION,String LODGEDOCTOR,String LODGEDATE,String BEDNO,String applyNO,String applySerialNumber,String applyitem,
                    String applyitemAll,String applyID,String CLISINPAT,String ENROLDOCTOR,String ENOLDATE,String SUGERYRSESULT,String CHECKPURPOSE,
                    String STATUS,String CLASSNAME,String PHOTONO,String TOTALFEE,String INHOSPITALNO,String MODALITYNAME,String CHECKDATE,String CHECKDOCTOR,
                    String PARTOFCHECK,String reportDate,String reportDoctor,String accession_num ){
this.NAME=NAME;this.SEX=SEX;this.PINYIN=PINYIN;this.CLINICNO=CLINICNO;this.INPATIENTNO=INPATIENTNO;this.PATIENTID=PATIENTID;
        this.STUDYID=STUDYID;this.AGE=AGE;this.AGEUNIT=AGEUNIT;this.LODGESECTION=LODGESECTION;this.LODGEDOCTOR=LODGEDOCTOR;
        this.LODGEDATE=LODGEDATE;this.BEDNO=BEDNO;this.applyNO=applyNO;this.applySerialNumber=applySerialNumber;this.applyitem=applyitem;
        this.applyitemAll=applyitemAll;this.applyID=applyID;this.CLISINPAT=CLISINPAT;this.ENROLDOCTOR=ENROLDOCTOR;this.ENOLDATE=ENOLDATE;
        this.SUGERYRSESULT=SUGERYRSESULT;this.CHECKPURPOSE=CHECKPURPOSE;this.STATUS=STATUS;this.CLASSNAME=CLASSNAME;this.PHOTONO=PHOTONO;
        this.TOTALFEE=TOTALFEE;this.INHOSPITALNO=INHOSPITALNO;this.MODALITYNAME=MODALITYNAME;this.CHECKDATE=CHECKDATE;this.CHECKDOCTOR=CHECKDOCTOR;
        this.PARTOFCHECK=PARTOFCHECK;this.reportDate=reportDate;this.reportDoctor=reportDoctor;this.accession_num=accession_num;
    }

    public String getPHOTONO() {
        return PHOTONO;
    }

    public String getAccession_num() {
        return accession_num;
    }

    public void setAccession_num(String accession_num) {
        this.accession_num = accession_num;
    }

    public String getReportDoctor() {

        return reportDoctor;
    }

    public void setReportDoctor(String reportDoctor) {
        this.reportDoctor = reportDoctor;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getPARTOFCHECK() {

        return PARTOFCHECK;
    }

    public void setPARTOFCHECK(String PARTOFCHECK) {
        this.PARTOFCHECK = PARTOFCHECK;
    }

    public String getCHECKDOCTOR() {
        return CHECKDOCTOR;

    }

    public void setCHECKDOCTOR(String CHECKDOCTOR) {
        this.CHECKDOCTOR = CHECKDOCTOR;
    }

    public String getCHECKDATE() {

        return CHECKDATE;
    }

    public void setCHECKDATE(String CHECKDATE) {
        this.CHECKDATE = CHECKDATE;
    }

    public String getMODALITYNAME() {
        return MODALITYNAME;
    }

    public void setMODALITYNAME(String MODALITYNAME) {
        this.MODALITYNAME = MODALITYNAME;
    }

    public String getINHOSPITALNO() {

        return INHOSPITALNO;
    }

    public void setINHOSPITALNO(String INHOSPITALNO) {
        this.INHOSPITALNO = INHOSPITALNO;
    }

    public String getTOTALFEE() {
        return TOTALFEE;
    }

    public void setTOTALFEE(String TOTALFEE) {
        this.TOTALFEE = TOTALFEE;
    }

    public void setPHOTONO(String PHOTONO) {
        this.PHOTONO = PHOTONO;
    }

    public String getCLASSNAME() {
        return CLASSNAME;

    }

    public void setCLASSNAME(String CLASSNAME) {
        this.CLASSNAME = CLASSNAME;
    }

    public String getSTATUS() {
        return STATUS;

    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getCHECKPURPOSE() {
        return CHECKPURPOSE;

    }

    public void setCHECKPURPOSE(String CHECKPURPOSE) {
        this.CHECKPURPOSE = CHECKPURPOSE;
    }

    public String getSUGERYRSESULT() {
        return SUGERYRSESULT;
    }

    public void setSUGERYRSESULT(String SUGERYRSESULT) {
        this.SUGERYRSESULT = SUGERYRSESULT;
    }

    public String getENOLDATE() {
        return ENOLDATE;

    }

    public void setENOLDATE(String ENOLDATE) {
        this.ENOLDATE = ENOLDATE;
    }

    public String getENROLDOCTOR() {
        return ENROLDOCTOR;

    }

    public void setENROLDOCTOR(String ENROLDOCTOR) {
        this.ENROLDOCTOR = ENROLDOCTOR;
    }

    public String getApplyID() {
        return applyID;

    }

    public String getCLISINPAT() {
        return CLISINPAT;
    }

    public void setCLISINPAT(String CLISINPAT) {
        this.CLISINPAT = CLISINPAT;
    }

    public void setApplyID(String applyID) {
        this.applyID = applyID;
    }

    public String getApplyitem() {
        return applyitem;
    }

    public String getApplyitemAll() {
        return applyitemAll;
    }

    public void setApplyitemAll(String applyitemAll) {
        this.applyitemAll = applyitemAll;
    }

    public void setApplyitem(String applyitem) {
        this.applyitem = applyitem;
    }

    public String getApplySerialNumber() {

        return applySerialNumber;
    }

    public void setApplySerialNumber(String applySerialNumber) {
        this.applySerialNumber = applySerialNumber;
    }

    public String getApplyNO() {

        return applyNO;
    }

    public void setApplyNO(String applyNO) {
        this.applyNO = applyNO;
    }

    public String getBEDNO() {

        return BEDNO;
    }

    public void setBEDNO(String BEDNO) {
        this.BEDNO = BEDNO;
    }

    public String getLODGEDATE() {
        return LODGEDATE;
    }

    public void setLODGEDATE(String LODGEDATE) {
        this.LODGEDATE = LODGEDATE;
    }

    public String getLODGEDOCTOR() {
        return LODGEDOCTOR;
    }

    public void setLODGEDOCTOR(String LODGEDOCTOR) {
        this.LODGEDOCTOR = LODGEDOCTOR;
    }

    public String getLODGESECTION() {

        return LODGESECTION;
    }

    public void setLODGESECTION(String LODGESECTION) {
        this.LODGESECTION = LODGESECTION;
    }

    public String getAGEUNIT() {
        return AGEUNIT;
    }

    public void setAGEUNIT(String AGEUNIT) {
        this.AGEUNIT = AGEUNIT;
    }

    public String getAGE() {
        return AGE;

    }

    public void setAGE(String AGE) {
        this.AGE = AGE;
    }

    public String getSTUDYID() {

        return STUDYID;
    }

    public void setSTUDYID(String STUDYID) {
        this.STUDYID = STUDYID;
    }

    public String getPATIENTID() {
        return PATIENTID;

    }

    public void setPATIENTID(String PATIENTID) {
        this.PATIENTID = PATIENTID;
    }

    public String getINPATIENTNO() {
        return INPATIENTNO;
    }

    public void setINPATIENTNO(String INPATIENTNO) {
        this.INPATIENTNO = INPATIENTNO;
    }

    public String getCLINICNO() {
        return CLINICNO;
    }

    public void setCLINICNO(String CLINICNO) {
        this.CLINICNO = CLINICNO;
    }

    public String getPINYIN() {

        return PINYIN;
    }

    public void setPINYIN(String PINYIN) {
        this.PINYIN = PINYIN;
    }

    public String getSEX() {

        return SEX;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX;
    }

    public String getNAME() {

        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
}
