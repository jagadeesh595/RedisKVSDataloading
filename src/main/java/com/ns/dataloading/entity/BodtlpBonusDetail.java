package com.ns.dataloading.entity;
// Generated Sep 4, 2017 5:49:28 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * BodtlpBonusDetail generated by hbm2java
 */
@Entity
@Table(name = "BODTLP_BonusDetail")
public class BodtlpBonusDetail implements Serializable {
    private Long id;
    private long bddstrDistributorcontrolnumber;
    private long bdspnrSponsorcontrolnumber;
    private int bdslevSponsorlevel;
    private int bddlevDistributorlevel;
    private BigDecimal bdapctAccumulatedpercentagepaid;
    private Integer bdmgrManagerspaidsofar;
    private BigDecimal bdccfCasecreditfactor;
    private BigDecimal bdccCasecreditsatthislevel;
    private BigDecimal bdbpctBonuspercentatthislevel;
    private BigDecimal bdbamtBonuspaidatthislevel;
    private String bdalphAlphabeticsortfield;
    private long bdordOrdernumber;
    private String bdordtOrderdate;
    private long bdparaPurchasearea;
    private String bdleadProcessleadership;
    private Long bdordsOrdersourcecode;
    private Long bdshpsShippingsourcecode;
    private Long bdotypOrdertypecode;
    private String bdnmgfNonmgrccflag;
    private String bdnewfNewdistccflag;
    private String bdnpsfNewdistccpsflag;
    private String memberFile;

    public BodtlpBonusDetail() {
    }

    public BodtlpBonusDetail(long bddstrDistributorcontrolnumber, long bdspnrSponsorcontrolnumber,
                             int bdslevSponsorlevel, int bddlevDistributorlevel, long bdordOrdernumber, String bdordtOrderdate,
                             long bdparaPurchasearea) {
        this.bddstrDistributorcontrolnumber = bddstrDistributorcontrolnumber;
        this.bdspnrSponsorcontrolnumber = bdspnrSponsorcontrolnumber;
        this.bdslevSponsorlevel = bdslevSponsorlevel;
        this.bddlevDistributorlevel = bddlevDistributorlevel;
        this.bdordOrdernumber = bdordOrdernumber;
        this.bdordtOrderdate = bdordtOrderdate;
        this.bdparaPurchasearea = bdparaPurchasearea;
    }

    public BodtlpBonusDetail(long bddstrDistributorcontrolnumber, long bdspnrSponsorcontrolnumber,
                             int bdslevSponsorlevel, int bddlevDistributorlevel, BigDecimal bdapctAccumulatedpercentagepaid,
                             Integer bdmgrManagerspaidsofar, BigDecimal bdccfCasecreditfactor, BigDecimal bdccCasecreditsatthislevel,
                             BigDecimal bdbpctBonuspercentatthislevel, BigDecimal bdbamtBonuspaidatthislevel,
                             String bdalphAlphabeticsortfield, long bdordOrdernumber, String bdordtOrderdate, long bdparaPurchasearea,
                             String bdleadProcessleadership, Long bdordsOrdersourcecode, Long bdshpsShippingsourcecode,
                             Long bdotypOrdertypecode, String bdnmgfNonmgrccflag, String bdnewfNewdistccflag,
                             String bdnpsfNewdistccpsflag) {
        this.bddstrDistributorcontrolnumber = bddstrDistributorcontrolnumber;
        this.bdspnrSponsorcontrolnumber = bdspnrSponsorcontrolnumber;
        this.bdslevSponsorlevel = bdslevSponsorlevel;
        this.bddlevDistributorlevel = bddlevDistributorlevel;
        this.bdapctAccumulatedpercentagepaid = bdapctAccumulatedpercentagepaid;
        this.bdmgrManagerspaidsofar = bdmgrManagerspaidsofar;
        this.bdccfCasecreditfactor = bdccfCasecreditfactor;
        this.bdccCasecreditsatthislevel = bdccCasecreditsatthislevel;
        this.bdbpctBonuspercentatthislevel = bdbpctBonuspercentatthislevel;
        this.bdbamtBonuspaidatthislevel = bdbamtBonuspaidatthislevel;
        this.bdalphAlphabeticsortfield = bdalphAlphabeticsortfield;
        this.bdordOrdernumber = bdordOrdernumber;
        this.bdordtOrderdate = bdordtOrderdate;
        this.bdparaPurchasearea = bdparaPurchasearea;
        this.bdleadProcessleadership = bdleadProcessleadership;
        this.bdordsOrdersourcecode = bdordsOrdersourcecode;
        this.bdshpsShippingsourcecode = bdshpsShippingsourcecode;
        this.bdotypOrdertypecode = bdotypOrdertypecode;
        this.bdnmgfNonmgrccflag = bdnmgfNonmgrccflag;
        this.bdnewfNewdistccflag = bdnewfNewdistccflag;
        this.bdnpsfNewdistccpsflag = bdnpsfNewdistccpsflag;
    }

    @Basic
    @Column(name = "BDDSTR_DISTRIBUTORCONTROLNUMBER", nullable = false)
    public long getBddstrDistributorcontrolnumber() {
        return this.bddstrDistributorcontrolnumber;
    }

    public void setBddstrDistributorcontrolnumber(Long bddstrDistributorcontrolnumber) {
        this.bddstrDistributorcontrolnumber = bddstrDistributorcontrolnumber;
    }

    public void setBddstrDistributorcontrolnumber(long bddstrDistributorcontrolnumber) {
        this.bddstrDistributorcontrolnumber = bddstrDistributorcontrolnumber;
    }

    @Basic
    @Column(name = "BDSPNR_SPONSORCONTROLNUMBER", nullable = false)
    public long getBdspnrSponsorcontrolnumber() {
        return this.bdspnrSponsorcontrolnumber;
    }

    public void setBdspnrSponsorcontrolnumber(Long bdspnrSponsorcontrolnumber) {
        this.bdspnrSponsorcontrolnumber = bdspnrSponsorcontrolnumber;
    }

    public void setBdspnrSponsorcontrolnumber(long bdspnrSponsorcontrolnumber) {
        this.bdspnrSponsorcontrolnumber = bdspnrSponsorcontrolnumber;
    }

    @Basic
    @Column(name = "BDSLEV_SPONSORLEVEL", nullable = false)
    public int getBdslevSponsorlevel() {
        return this.bdslevSponsorlevel;
    }

    public void setBdslevSponsorlevel(Integer bdslevSponsorlevel) {
        this.bdslevSponsorlevel = bdslevSponsorlevel;
    }

    public void setBdslevSponsorlevel(int bdslevSponsorlevel) {
        this.bdslevSponsorlevel = bdslevSponsorlevel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "BDDLEV_DISTRIBUTORLEVEL", nullable = false)
    public int getBddlevDistributorlevel() {
        return this.bddlevDistributorlevel;
    }

    public void setBddlevDistributorlevel(Integer bddlevDistributorlevel) {
        this.bddlevDistributorlevel = bddlevDistributorlevel;
    }

    public void setBddlevDistributorlevel(int bddlevDistributorlevel) {
        this.bddlevDistributorlevel = bddlevDistributorlevel;
    }

    @Basic
    @Column(name = "BDAPCT_ACCUMULATEDPERCENTAGEPAID", precision = 3, scale = 3, nullable = true)
    public BigDecimal getBdapctAccumulatedpercentagepaid() {
        return this.bdapctAccumulatedpercentagepaid;
    }

    public void setBdapctAccumulatedpercentagepaid(BigDecimal bdapctAccumulatedpercentagepaid) {
        this.bdapctAccumulatedpercentagepaid = bdapctAccumulatedpercentagepaid;
    }

    @Basic
    @Column(name = "BDMGR_MANAGERSPAIDSOFAR", nullable = true)
    public Integer getBdmgrManagerspaidsofar() {
        return this.bdmgrManagerspaidsofar;
    }

    public void setBdmgrManagerspaidsofar(Integer bdmgrManagerspaidsofar) {
        this.bdmgrManagerspaidsofar = bdmgrManagerspaidsofar;
    }

    @Basic
    @Column(name = "BDCCF_CASECREDITFACTOR", precision = 3, scale = 3, nullable = true)
    public BigDecimal getBdccfCasecreditfactor() {
        return this.bdccfCasecreditfactor;
    }

    public void setBdccfCasecreditfactor(BigDecimal bdccfCasecreditfactor) {
        this.bdccfCasecreditfactor = bdccfCasecreditfactor;
    }

    @Basic
    @Column(name = "BDCC_CASECREDITSATTHISLEVEL", precision = 3, scale = 3, nullable = true)
    public BigDecimal getBdccCasecreditsatthislevel() {
        return this.bdccCasecreditsatthislevel;
    }

    public void setBdccCasecreditsatthislevel(BigDecimal bdccCasecreditsatthislevel) {
        this.bdccCasecreditsatthislevel = bdccCasecreditsatthislevel;
    }

    @Basic
    @Column(name = "BDBPCT_BONUSPERCENTATTHISLEVEL", precision = 3, scale = 3, nullable = true)
    public BigDecimal getBdbpctBonuspercentatthislevel() {
        return this.bdbpctBonuspercentatthislevel;
    }

    public void setBdbpctBonuspercentatthislevel(BigDecimal bdbpctBonuspercentatthislevel) {
        this.bdbpctBonuspercentatthislevel = bdbpctBonuspercentatthislevel;
    }

    @Basic
    @Column(name = "BDBAMT_BONUSPAIDATTHISLEVEL", precision = 3, scale = 3, nullable = true)
    public BigDecimal getBdbamtBonuspaidatthislevel() {
        return this.bdbamtBonuspaidatthislevel;
    }

    public void setBdbamtBonuspaidatthislevel(BigDecimal bdbamtBonuspaidatthislevel) {
        this.bdbamtBonuspaidatthislevel = bdbamtBonuspaidatthislevel;
    }

    @Basic
    @Column(name = "BDALPH_ALPHABETICSORTFIELD", nullable = true, length = 255)
    public String getBdalphAlphabeticsortfield() {
        return this.bdalphAlphabeticsortfield;
    }

    public void setBdalphAlphabeticsortfield(String bdalphAlphabeticsortfield) {
        this.bdalphAlphabeticsortfield = bdalphAlphabeticsortfield;
    }

    @Basic
    @Column(name = "BDORD_ORDERNUMBER", nullable = false)
    public long getBdordOrdernumber() {
        return this.bdordOrdernumber;
    }

    public void setBdordOrdernumber(Long bdordOrdernumber) {
        this.bdordOrdernumber = bdordOrdernumber;
    }

    public void setBdordOrdernumber(long bdordOrdernumber) {
        this.bdordOrdernumber = bdordOrdernumber;
    }

    @Basic
    @Column(name = "BDORDT_ORDERDATE", nullable = false, length = 15)
    public String getBdordtOrderdate() {
        return this.bdordtOrderdate;
    }

    public void setBdordtOrderdate(String bdordtOrderdate) {
        this.bdordtOrderdate = bdordtOrderdate;
    }

    @Basic
    @Column(name = "BDPARA_PURCHASEAREA", nullable = false)
    public long getBdparaPurchasearea() {
        return this.bdparaPurchasearea;
    }

    public void setBdparaPurchasearea(Long bdparaPurchasearea) {
        this.bdparaPurchasearea = bdparaPurchasearea;
    }

    public void setBdparaPurchasearea(long bdparaPurchasearea) {
        this.bdparaPurchasearea = bdparaPurchasearea;
    }

    @Basic
    @Column(name = "BDLEAD_PROCESSLEADERSHIP", length = 1, nullable = true)
    public String getBdleadProcessleadership() {
        return this.bdleadProcessleadership;
    }

    public void setBdleadProcessleadership(String bdleadProcessleadership) {
        this.bdleadProcessleadership = bdleadProcessleadership;
    }

    @Basic
    @Column(name = "BDORDS_ORDERSOURCECODE", nullable = true)
    public Long getBdordsOrdersourcecode() {
        return this.bdordsOrdersourcecode;
    }

    public void setBdordsOrdersourcecode(Long bdordsOrdersourcecode) {
        this.bdordsOrdersourcecode = bdordsOrdersourcecode;
    }

    @Basic
    @Column(name = "BDSHPS_SHIPPINGSOURCECODE", nullable = true)
    public Long getBdshpsShippingsourcecode() {
        return this.bdshpsShippingsourcecode;
    }

    public void setBdshpsShippingsourcecode(Long bdshpsShippingsourcecode) {
        this.bdshpsShippingsourcecode = bdshpsShippingsourcecode;
    }

    @Basic
    @Column(name = "BDOTYP_ORDERTYPECODE", nullable = true)
    public Long getBdotypOrdertypecode() {
        return this.bdotypOrdertypecode;
    }

    public void setBdotypOrdertypecode(Long bdotypOrdertypecode) {
        this.bdotypOrdertypecode = bdotypOrdertypecode;
    }

    @Basic
    @Column(name = "BDNMGF_NONMGRCCFLAG", length = 1, nullable = true)
    public String getBdnmgfNonmgrccflag() {
        return this.bdnmgfNonmgrccflag;
    }

    public void setBdnmgfNonmgrccflag(String bdnmgfNonmgrccflag) {
        this.bdnmgfNonmgrccflag = bdnmgfNonmgrccflag;
    }

    @Basic
    @Column(name = "BDNEWF_NEWDISTCCFLAG", length = 1, nullable = true)
    public String getBdnewfNewdistccflag() {
        return this.bdnewfNewdistccflag;
    }

    public void setBdnewfNewdistccflag(String bdnewfNewdistccflag) {
        this.bdnewfNewdistccflag = bdnewfNewdistccflag;
    }

    @Basic
    @Column(name = "BDNPSF_NEWDISTCCPSFLAG", length = 1, nullable = true)
    public String getBdnpsfNewdistccpsflag() {
        return this.bdnpsfNewdistccpsflag;
    }

    public void setBdnpsfNewdistccpsflag(String bdnpsfNewdistccpsflag) {
        this.bdnpsfNewdistccpsflag = bdnpsfNewdistccpsflag;
    }

    public BodtlpBonusDetail copy() {
        BodtlpBonusDetail bodtlpBonusDetail = new BodtlpBonusDetail();
        bodtlpBonusDetail.setBddstrDistributorcontrolnumber(bddstrDistributorcontrolnumber);
        bodtlpBonusDetail.setBdspnrSponsorcontrolnumber(bdspnrSponsorcontrolnumber);
        bodtlpBonusDetail.setBdslevSponsorlevel(bdslevSponsorlevel);
        bodtlpBonusDetail.setBddlevDistributorlevel(bddlevDistributorlevel);
        bodtlpBonusDetail.setBdapctAccumulatedpercentagepaid(bdapctAccumulatedpercentagepaid);
        bodtlpBonusDetail.setBdmgrManagerspaidsofar(bdmgrManagerspaidsofar);
        bodtlpBonusDetail.setBdccfCasecreditfactor(bdccfCasecreditfactor);
        bodtlpBonusDetail.setBdccCasecreditsatthislevel(bdccCasecreditsatthislevel);
        bodtlpBonusDetail.setBdbpctBonuspercentatthislevel(bdbpctBonuspercentatthislevel);
        bodtlpBonusDetail.setBdbamtBonuspaidatthislevel(bdbamtBonuspaidatthislevel);
        bodtlpBonusDetail.setBdalphAlphabeticsortfield(bdalphAlphabeticsortfield);
        bodtlpBonusDetail.setBdordOrdernumber(bdordOrdernumber);
        bodtlpBonusDetail.setBdordtOrderdate(bdordtOrderdate);
        bodtlpBonusDetail.setBdparaPurchasearea(bdparaPurchasearea);
        bodtlpBonusDetail.setBdleadProcessleadership(bdleadProcessleadership);
        bodtlpBonusDetail.setBdordsOrdersourcecode(bdordsOrdersourcecode);
        bodtlpBonusDetail.setBdshpsShippingsourcecode(bdshpsShippingsourcecode);
        bodtlpBonusDetail.setBdotypOrdertypecode(bdotypOrdertypecode);
        bodtlpBonusDetail.setBdnmgfNonmgrccflag(bdnmgfNonmgrccflag);
        bodtlpBonusDetail.setBdnewfNewdistccflag(bdnewfNewdistccflag);
        bodtlpBonusDetail.setBdnpsfNewdistccpsflag(bdnpsfNewdistccpsflag);
        return bodtlpBonusDetail;
    }

    @Basic
    @Column(name = "MemberFile", nullable = true, length = 10)
    public String getMemberFile() {
        return memberFile;
    }

    public void setMemberFile(String memberFile) {
        this.memberFile = memberFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BodtlpBonusDetail that = (BodtlpBonusDetail) o;

        if (bddstrDistributorcontrolnumber != that.bddstrDistributorcontrolnumber) return false;
        if (bdspnrSponsorcontrolnumber != that.bdspnrSponsorcontrolnumber) return false;
        if (bdslevSponsorlevel != that.bdslevSponsorlevel) return false;
        if (bddlevDistributorlevel != that.bddlevDistributorlevel) return false;
        if (bdordOrdernumber != that.bdordOrdernumber) return false;
        if (bdparaPurchasearea != that.bdparaPurchasearea) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (bdapctAccumulatedpercentagepaid != null ? !bdapctAccumulatedpercentagepaid.equals(that.bdapctAccumulatedpercentagepaid) : that.bdapctAccumulatedpercentagepaid != null)
            return false;
        if (bdmgrManagerspaidsofar != null ? !bdmgrManagerspaidsofar.equals(that.bdmgrManagerspaidsofar) : that.bdmgrManagerspaidsofar != null)
            return false;
        if (bdccfCasecreditfactor != null ? !bdccfCasecreditfactor.equals(that.bdccfCasecreditfactor) : that.bdccfCasecreditfactor != null)
            return false;
        if (bdccCasecreditsatthislevel != null ? !bdccCasecreditsatthislevel.equals(that.bdccCasecreditsatthislevel) : that.bdccCasecreditsatthislevel != null)
            return false;
        if (bdbpctBonuspercentatthislevel != null ? !bdbpctBonuspercentatthislevel.equals(that.bdbpctBonuspercentatthislevel) : that.bdbpctBonuspercentatthislevel != null)
            return false;
        if (bdbamtBonuspaidatthislevel != null ? !bdbamtBonuspaidatthislevel.equals(that.bdbamtBonuspaidatthislevel) : that.bdbamtBonuspaidatthislevel != null)
            return false;
        if (bdalphAlphabeticsortfield != null ? !bdalphAlphabeticsortfield.equals(that.bdalphAlphabeticsortfield) : that.bdalphAlphabeticsortfield != null)
            return false;
        if (bdordtOrderdate != null ? !bdordtOrderdate.equals(that.bdordtOrderdate) : that.bdordtOrderdate != null)
            return false;
        if (bdleadProcessleadership != null ? !bdleadProcessleadership.equals(that.bdleadProcessleadership) : that.bdleadProcessleadership != null)
            return false;
        if (bdordsOrdersourcecode != null ? !bdordsOrdersourcecode.equals(that.bdordsOrdersourcecode) : that.bdordsOrdersourcecode != null)
            return false;
        if (bdshpsShippingsourcecode != null ? !bdshpsShippingsourcecode.equals(that.bdshpsShippingsourcecode) : that.bdshpsShippingsourcecode != null)
            return false;
        if (bdotypOrdertypecode != null ? !bdotypOrdertypecode.equals(that.bdotypOrdertypecode) : that.bdotypOrdertypecode != null)
            return false;
        if (bdnmgfNonmgrccflag != null ? !bdnmgfNonmgrccflag.equals(that.bdnmgfNonmgrccflag) : that.bdnmgfNonmgrccflag != null)
            return false;
        if (bdnewfNewdistccflag != null ? !bdnewfNewdistccflag.equals(that.bdnewfNewdistccflag) : that.bdnewfNewdistccflag != null)
            return false;
        if (bdnpsfNewdistccpsflag != null ? !bdnpsfNewdistccpsflag.equals(that.bdnpsfNewdistccpsflag) : that.bdnpsfNewdistccpsflag != null)
            return false;
        if (memberFile != null ? !memberFile.equals(that.memberFile) : that.memberFile != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (int) (bddstrDistributorcontrolnumber ^ (bddstrDistributorcontrolnumber >>> 32));
        result = 31 * result + (int) (bdspnrSponsorcontrolnumber ^ (bdspnrSponsorcontrolnumber >>> 32));
        result = 31 * result + bdslevSponsorlevel;
        result = 31 * result + bddlevDistributorlevel;
        result = 31 * result + (bdapctAccumulatedpercentagepaid != null ? bdapctAccumulatedpercentagepaid.hashCode() : 0);
        result = 31 * result + (bdmgrManagerspaidsofar != null ? bdmgrManagerspaidsofar.hashCode() : 0);
        result = 31 * result + (bdccfCasecreditfactor != null ? bdccfCasecreditfactor.hashCode() : 0);
        result = 31 * result + (bdccCasecreditsatthislevel != null ? bdccCasecreditsatthislevel.hashCode() : 0);
        result = 31 * result + (bdbpctBonuspercentatthislevel != null ? bdbpctBonuspercentatthislevel.hashCode() : 0);
        result = 31 * result + (bdbamtBonuspaidatthislevel != null ? bdbamtBonuspaidatthislevel.hashCode() : 0);
        result = 31 * result + (bdalphAlphabeticsortfield != null ? bdalphAlphabeticsortfield.hashCode() : 0);
        result = 31 * result + (int) (bdordOrdernumber ^ (bdordOrdernumber >>> 32));
        result = 31 * result + (bdordtOrderdate != null ? bdordtOrderdate.hashCode() : 0);
        result = 31 * result + (int) (bdparaPurchasearea ^ (bdparaPurchasearea >>> 32));
        result = 31 * result + (bdleadProcessleadership != null ? bdleadProcessleadership.hashCode() : 0);
        result = 31 * result + (bdordsOrdersourcecode != null ? bdordsOrdersourcecode.hashCode() : 0);
        result = 31 * result + (bdshpsShippingsourcecode != null ? bdshpsShippingsourcecode.hashCode() : 0);
        result = 31 * result + (bdotypOrdertypecode != null ? bdotypOrdertypecode.hashCode() : 0);
        result = 31 * result + (bdnmgfNonmgrccflag != null ? bdnmgfNonmgrccflag.hashCode() : 0);
        result = 31 * result + (bdnewfNewdistccflag != null ? bdnewfNewdistccflag.hashCode() : 0);
        result = 31 * result + (bdnpsfNewdistccpsflag != null ? bdnpsfNewdistccpsflag.hashCode() : 0);
        result = 31 * result + (memberFile != null ? memberFile.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{\"BodtlpBonusDetail\":{"
                + "                        \"id\":\"" + id + "\""
                + ",                         \"bddstrDistributorcontrolnumber\":\"" + bddstrDistributorcontrolnumber + "\""
                + ",                         \"bdspnrSponsorcontrolnumber\":\"" + bdspnrSponsorcontrolnumber + "\""
                + ",                         \"bdslevSponsorlevel\":\"" + bdslevSponsorlevel + "\""
                + ",                         \"bddlevDistributorlevel\":\"" + bddlevDistributorlevel + "\""
                + ",                         \"bdapctAccumulatedpercentagepaid\":\"" + bdapctAccumulatedpercentagepaid + "\""
                + ",                         \"bdmgrManagerspaidsofar\":\"" + bdmgrManagerspaidsofar + "\""
                + ",                         \"bdccfCasecreditfactor\":\"" + bdccfCasecreditfactor + "\""
                + ",                         \"bdccCasecreditsatthislevel\":\"" + bdccCasecreditsatthislevel + "\""
                + ",                         \"bdbpctBonuspercentatthislevel\":\"" + bdbpctBonuspercentatthislevel + "\""
                + ",                         \"bdbamtBonuspaidatthislevel\":\"" + bdbamtBonuspaidatthislevel + "\""
                + ",                         \"bdalphAlphabeticsortfield\":\"" + bdalphAlphabeticsortfield + "\""
                + ",                         \"bdordOrdernumber\":\"" + bdordOrdernumber + "\""
                + ",                         \"bdordtOrderdate\":\"" + bdordtOrderdate + "\""
                + ",                         \"bdparaPurchasearea\":\"" + bdparaPurchasearea + "\""
                + ",                         \"bdleadProcessleadership\":\"" + bdleadProcessleadership + "\""
                + ",                         \"bdordsOrdersourcecode\":\"" + bdordsOrdersourcecode + "\""
                + ",                         \"bdshpsShippingsourcecode\":\"" + bdshpsShippingsourcecode + "\""
                + ",                         \"bdotypOrdertypecode\":\"" + bdotypOrdertypecode + "\""
                + ",                         \"bdnmgfNonmgrccflag\":\"" + bdnmgfNonmgrccflag + "\""
                + ",                         \"bdnewfNewdistccflag\":\"" + bdnewfNewdistccflag + "\""
                + ",                         \"bdnpsfNewdistccpsflag\":\"" + bdnpsfNewdistccpsflag + "\""
                + ",                         \"memberFile\":\"" + memberFile + "\""
                + "}}";
    }
}
