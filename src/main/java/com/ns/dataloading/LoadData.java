package com.ns.dataloading;

import com.google.gson.Gson;
import com.ns.dataloading.RedisRepo.repository.*;
import com.ns.dataloading.Utils.Utils;
import com.ns.dataloading.entityredis.entity.*;
import com.ns.dataloading.redisconfig.ModelMapperConverter;
import com.ns.dataloading.repository.*;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LoadData {
    @Qualifier("Ormstp")
    @Autowired
    OrmstpOrderRepositorymysql ordermysql;
    @Autowired
    FlcmppCompanyConfigRepository flcmppCompanyConfigRepository;
    @Autowired
    FlcmppCompanyConfigmysql flcmppCompanyConfigmysql;
    @Qualifier("OrmstpOrderRepository")
    @Autowired
    com.ns.dataloading.RedisRepo.repository.OrmstpOrderRepository orderredisrepo;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    BomcfgGlobalMultMonthMoveupConfigRepository bomcfgGlobalMultMonthMoveupConfigRepository;
    @Autowired
    BomcfgGlobalMultMonthMoveupConfigRepositorymysql bomcfgGlobalMultMonthMoveupConfigRepositorymysql;
    @Autowired
    Gson jsonconvertor = new Gson();
    // @Qualifier("dsmstpDistributorRepository")
    @Autowired
    DspwvpWaiverRepository dspwvpWaiverRepository;
    @Autowired
    DspwvpWaiverRepositorymysql dspwvpWaiverRepositorymysql;
    @Autowired
    DshstpDistributorHistoryRepository dshstpDistributorHistoryRepository;
    @Autowired
    DshstpDistributorHistoryRepositorymysql dshstpDistributorHistoryRepositorymysql;
    @Autowired
    DslevpDistributorLevelsRepository dslevpDistributorLevelsRepository;
    @Autowired
    DslevpDistributorLevelsRepositorymysql dslevpDistributorLevelsRepositorymysql;
    @Autowired
    DsmstpDistributorRepositorymysql dsmstpDistributorRepositorymysql;
    @Autowired
    DsmstpDistributorRepository dsmstpDistributorRepository;
    @Autowired
    DsainpDistAddInfoRepository dsainpDistAddInfoRepository;
    @Autowired
    DsainpDistAddInfoRepositorymysql dsainpDistAddInfoRepositorymysql;
    @Autowired
    Runlogrepomysql runlogrepomysql;
    @Autowired
    RunlogRepo runlogReporedis;
    @Autowired
    Runconfigmysql runconfigmysql;
    @Autowired
    RunconfigredisRepo runconfigredisRepo;
    @Autowired
    DistdccDailyCcDownloadFilemysql distdccDailyCcDownloadFilemysql;
    @Autowired
    DistdccDailyCcDownloadFileRepository distdccDailyCcDownloadFileRepository;
    @Autowired
    BowqlpWhsQualOrdTrackRepositorymysql bowqlpWhsQualOrdTrackRepositorymysql;
    @Autowired
    BowqlpWhsQualOrdTrackRepository bowqlpWhsQualOrdTrackRepository;
    @Autowired
    BoqalpBonusQualificationRepository boqalredis;
    @Autowired
    BoqalpBonusQualificationRepositorymysql bonusQualificationRepositorymysql;
    @Autowired
    BohstpBonusHistoryRepository bohstpBonusHistoryRepository;
    @Autowired
    BohstpBonusHistoryRepositorymysql bohstpBonusHistoryRepositorymysql;
    @Autowired
    BofitpFitStartOrderRepository bofitpFitStartOrderRepository;
    @Autowired
    BofitpFitStartOrderRepositorymysql bofitpFitStartOrderRepositorymysql;

    int start = 0;
    int limit = 50000;
    int currentCount = 0;
/*
    @Autowired
    BodtldpBonusDetailRepository bodtldpBonusDetailRepository;
    @Autowired
    BodtldpBonusDetailRepositorymysql bodtldpBonusDetailRepositorymysql;
    @Autowired
    BodtlpBonusDetailRepository bodtlpBonusDetailRepository;
    @Autowired
    BodtlpBonusDetailRepositorymysql bodtlpBonusDetailRepositorymysql;
*/

    public void init() {

        System.out.println("Dsainp_DistAddInfo - Start :" + System.currentTimeMillis());
        try (
                Reader reader =  new BufferedReader(new InputStreamReader(new FileInputStream("Dsainp_DistAddInfo.csv"),"utf-8"));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            int i = 1;
            List<DsainpDistAddInfo> distAddInfoList = new ArrayList<>();
            long startTime = System.currentTimeMillis();
            while ((nextRecord = csvReader.readNext()) != null) {
                DsainpDistAddInfo dsainpObject = new DsainpDistAddInfo();
                dsainpObject.setId(NumberUtils.toLong(nextRecord[0].trim()));
                dsainpObject.setDactlDistributorctl(Long.parseLong(nextRecord[1].trim()));
                dsainpObject.setDapswdPassword(nextRecord[2].trim());
                dsainpObject.setDaemadEmailaddress(nextRecord[3].trim());
                dsainpObject.setDaebknBankname(nextRecord[4].trim());
                dsainpObject.setDabrnmBranchname(nextRecord[5].trim());
                dsainpObject.setDabrcdBranchcode(NumberUtils.toLong(nextRecord[6].trim()));
                dsainpObject.setDaphn1Phonenumber(NumberUtils.toLong(nextRecord[7].trim()));
                dsainpObject.setDaphn2Phonenumber(NumberUtils.toLong(nextRecord[8].trim()));
                dsainpObject.setDablovBnslimitoverride(nextRecord[9].trim());
                dsainpObject.setDafut1Vippricing(nextRecord[10].trim());
                dsainpObject.setDafut225ccoverride(nextRecord[11].trim());
                dsainpObject.setDauserChangeuser(nextRecord[12].trim());
                dsainpObject.setDapidChgprogramid(nextRecord[13].trim());
                dsainpObject.setDaupdtChangedate(nextRecord[14].trim());
                dsainpObject.setDauptmChangetime(nextRecord[15].trim());
                dsainpObject.setDarctpBanktypecode(nextRecord[16].trim());
                dsainpObject.setDatrnsBankrtgtransit(nextRecord[17].trim());
                dsainpObject.setDaactBankacct(nextRecord[18].trim());
                dsainpObject.setDaswftSwiftcode(nextRecord[19].trim());
                dsainpObject.setDaprenPrenotecode(nextRecord[20].trim());
                dsainpObject.setDacksvChkgorsvgsacct(nextRecord[21].trim());
                dsainpObject.setDadusrDirdepchguser(nextRecord[22].trim());
                dsainpObject.setDadpidDirdepchgpgmid(nextRecord[23].trim());
                dsainpObject.setDadudtDirdepchgdate(nextRecord[24].trim());
                dsainpObject.setDadutmDirdepchgtime(nextRecord[25].trim());
                dsainpObject.setDaohcoOverridinghomecountry(nextRecord[26].trim());
                dsainpObject.setDawebrWebrecap(nextRecord[27].trim());
                dsainpObject.setDadbdtDistbirthdate(nextRecord[28].trim());
                dsainpObject.setDasbdtSpoubirthdate(nextRecord[29].trim());
                dsainpObject.setDamstsMartialstatus(nextRecord[30].trim());
                dsainpObject.setDalspkLanguagespoken(nextRecord[31].trim());
                dsainpObject.setDaatrAppltransactionid(NumberUtils.toLong(nextRecord[32].trim()));
                dsainpObject.setDandpfNdppricingyn(nextRecord[33].trim());
                dsainpObject.setDabalfBalancefactor(new BigDecimal(nextRecord[34].trim()));
                dsainpObject.setDafut3Futureuse3(new BigDecimal(nextRecord[35].trim()));
                dsainpObject.setDafut4Futureuse4(nextRecord[36].trim());
                dsainpObject.setDafut5Futureuse5(nextRecord[37].trim());
                dsainpObject.setDafut6Futureuse6(nextRecord[38].trim());
                dsainpObject.setDanltrNewletteronweb(nextRecord[39].trim());
                dsainpObject.setDapipaPersonalinfoprotectionact(nextRecord[40].trim());
                dsainpObject.setDapyinPaymentinstrument(nextRecord[41].trim());
                dsainpObject.setDaoptiOptInReq(nextRecord[42].trim());
                dsainpObject.setDaoptfOptInFlag(nextRecord[43].trim());
                dsainpObject.setDaopdtOptInDate(nextRecord[44].trim());
                dsainpObject.setDascemSpnContbyEml(nextRecord[45].trim());
                dsainpObject.setDascphSpnContbyPhn(nextRecord[46].trim());
                dsainpObject.setDadavfAdrVerfdFlag(nextRecord[47].trim());
                dsainpObject.setDadlatAdrLatitude(new BigDecimal(nextRecord[48].trim()));
                dsainpObject.setDadlntAdrLongitude(new BigDecimal(nextRecord[49].trim()));
                distAddInfoList.add(dsainpObject);
            }
            long endTime = System.currentTimeMillis();
            System.out.println(distAddInfoList.size());

            long start = System.currentTimeMillis();
            distAddInfoList.parallelStream()
                    .map(x -> {
                        dsainpDistAddInfoRepository.save(x);
                        return x;
                    }).collect(Collectors.toList());

            long end = System.currentTimeMillis();
            System.out.println(end - start);
            System.out.println("inserted");

            System.out.println(endTime - startTime);
        } catch (MalformedInputException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Dsainp_DistAddInfo - End: " + System.currentTimeMillis());

        System.out.println("RunConfig - Start :" + System.currentTimeMillis());
        try (
                Reader reader =  new BufferedReader(new InputStreamReader(new FileInputStream("RunConfig.csv"),"utf-8"));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            int i = 1;
            List<RunConfig> runConfigList = new ArrayList<>();
            long startTime = System.currentTimeMillis();
            while ((nextRecord = csvReader.readNext()) != null) {
                RunConfig object = new RunConfig();
                object.setId(NumberUtils.toLong(nextRecord[0].trim()));
                object.setGroup(nextRecord[1].trim());
                object.setKey(nextRecord[2].trim());
                object.setValue(nextRecord[3].trim());
                runConfigList.add(object);
            }
            long endTime = System.currentTimeMillis();
            System.out.println(runConfigList.size());

            long start = System.currentTimeMillis();
            runConfigList.parallelStream()
                    .map(x -> {
                        runconfigredisRepo.save(x);
                        return x;
                    }).collect(Collectors.toList());

            long end = System.currentTimeMillis();
            System.out.println(end - start);
            System.out.println("inserted");

            System.out.println(endTime - startTime);
        } catch (MalformedInputException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("runConfigList - End: " + System.currentTimeMillis());

        System.out.println("OrmstpOrder - Start :" + System.currentTimeMillis());
        try (
                Reader reader =  new BufferedReader(new InputStreamReader(new FileInputStream("OrmstpOrder.csv"),"utf-8"));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            int i = 1;
            List<OrmstpOrder> list = new ArrayList<>();
            long startTime = System.currentTimeMillis();
            while ((nextRecord = csvReader.readNext()) != null) {
                OrmstpOrder object = new OrmstpOrder();
                object.setId(NumberUtils.toLong(nextRecord[0].trim()));
                object.setOmordNumber(NumberUtils.toLong(nextRecord[1].trim()));
                object.setOmctlDistCtrl(NumberUtils.toLong(nextRecord[2].trim()));
                object.setOmpyrYear(NumberUtils.toInt(nextRecord[3].trim()));
                object.setOmpmoMonth(NumberUtils.toInt(nextRecord[4].trim()));
                object.setOmodatDate(nextRecord[5].trim());
                object.setOmdidDistId(NumberUtils.toLong(nextRecord[6].trim()));
                object.setOmpsrpProdSrp(new BigDecimal(nextRecord[7].trim()));
                object.setOmpndpProdNdp(new BigDecimal(nextRecord[8].trim()));
                object.setOmpwhsProdWhs(new BigDecimal(nextRecord[9].trim()));
                object.setOmtsrpTotSrp(new BigDecimal(nextRecord[10].trim()));
                object.setOmptypPriceType(nextRecord[11].trim());
                object.setOmccrdCredits(new BigDecimal(nextRecord[12].trim()));
                object.setOmpcasPhyCases(new BigDecimal(nextRecord[13].trim()));
                object.setOmtwhsTotWhs(new BigDecimal(nextRecord[14].trim()));
                object.setOmtndpTotNdp(new BigDecimal(nextRecord[15].trim()));
                object.setOmcoidCompanyCode(nextRecord[16].trim());
                object.setOmctycCntryCode(NumberUtils.toLong(nextRecord[17].trim()));
                object.setOmcurcCurrCode(nextRecord[18].trim());
                object.setOmdiscPersonalDiscAmt(new BigDecimal(nextRecord[19].trim()));
                object.setOmptaxProdTax(new BigDecimal(nextRecord[20].trim()));
                object.setOmlsrpLitSrp(new BigDecimal(nextRecord[21].trim()));
                object.setOmlwhsLitrWhsCost(new BigDecimal(nextRecord[22].trim()));
                object.setOmlndpLitrNdp(new BigDecimal(nextRecord[23].trim()));
                object.setOmltaxLitrTax(new BigDecimal(nextRecord[24].trim()));
                object.setOmtslpTotSalesPrice(new BigDecimal(nextRecord[25].trim()));
                object.setOmttaxTotTax(new BigDecimal(nextRecord[26].trim()));
                object.setOmifeeImportFee(new BigDecimal(nextRecord[27].trim()));
                object.setOmhchgShipHandChg(new BigDecimal(nextRecord[28].trim()));
                object.setOmwghtTotWeight(new BigDecimal(nextRecord[29].trim()));
                object.setOmshipShippingCharge(new BigDecimal(nextRecord[30].trim()));
                object.setOmhandHandlingCharge(new BigDecimal(nextRecord[31].trim()));
                object.setOmareaDistArea(NumberUtils.toLong(nextRecord[32].trim()));
                object.setOmparaPurchaseArea(NumberUtils.toLong(nextRecord[33].trim()));
                object.setOmenvEnvNum(NumberUtils.toLong(nextRecord[34].trim()));
                object.setOmdateLastChangeDate(nextRecord[35].trim());
                object.setOmuserLastChangeUser(nextRecord[36].trim());
                object.setOmtxblNetTaxAmt(new BigDecimal(nextRecord[37].trim()));
                object.setOmntxbNetNonTaxAmt(new BigDecimal(nextRecord[38].trim()));
                object.setOmgmbnPayGmasmbon(nextRecord[39].trim());
                object.setOmdmDistMgrId(NumberUtils.toLong(nextRecord[40].trim()));
                object.setOmntxfNonTaxFlag(nextRecord[41].trim());
                object.setOmordsOrderSrcCode(NumberUtils.toLong(nextRecord[42].trim()));
                object.setOmshpsShippingSrcCode(NumberUtils.toLong(nextRecord[43].trim()));
                object.setOmotypOrderTypeCode(NumberUtils.toLong(nextRecord[44].trim()));
                object.setOmjdefJdeFlag(nextRecord[45].trim());
                object.setOmndpfNdpFlag(nextRecord[46].trim());
                object.setOmwebWebNum(nextRecord[47].trim());
                object.setOmexrtCurrExchRate(new BigDecimal(nextRecord[48].trim()));
                object.setOmedteEntryTimeStamp(nextRecord[49].trim());
                object.setOmcdteChangeTimeStamp(nextRecord[50].trim());
                object.setOmdbalDiscBalConsumed(new BigDecimal(nextRecord[51].trim()));
                object.setOmdpctPerDiscPerc(new BigDecimal(nextRecord[52].trim()));
                object.setOmdtrnPerDiscTran(NumberUtils.toLong(nextRecord[53].trim()));
                object.setOmgflgGenericFlag(nextRecord[54].trim());
                list.add(object);
            }
            long endTime = System.currentTimeMillis();
            System.out.println(list.size());

            long start = System.currentTimeMillis();
            list.parallelStream()
                    .map(x -> {
                        orderredisrepo.save(x);
                        return x;
                    }).collect(Collectors.toList());

            long end = System.currentTimeMillis();
            System.out.println(end - start);
            System.out.println("inserted");

            System.out.println(endTime - startTime);
        } catch (MalformedInputException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("OrmstpOrder - End: " + System.currentTimeMillis());

        System.out.println("DsmstpDistributor - Start :" + System.currentTimeMillis());
        try (
                Reader reader =  new BufferedReader(new InputStreamReader(new FileInputStream("Dsmstp_Distributor.csv"),"utf-8"));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            int i = 1;
            List<DsmstpDistributor> list = new ArrayList<>();
            long startTime = System.currentTimeMillis();
            while ((nextRecord = csvReader.readNext()) != null) {
                DsmstpDistributor object = new DsmstpDistributor();
                object.setId(NumberUtils.toLong(nextRecord[0].trim()));
                object.setDsctlControl(NumberUtils.toLong(nextRecord[1].trim()));
                object.setDsdidId(NumberUtils.toLong(nextRecord[2].trim()));
                object.setDsfnamFirstName(nextRecord[3].trim());
                object.setDslnamLastName(nextRecord[4].trim());
                object.setDsspnSponsorControl(NumberUtils.toLong(nextRecord[5].trim()));
                object.setDscntyCountry(nextRecord[6].trim());
                object.setDsctycCntrCode(NumberUtils.toLong(nextRecord[7].trim()));
                object.setDsstasStatus(nextRecord[8].trim());
                object.setDswaiv4CcWaiver(nextRecord[9].trim());
                object.setDslevlLevel(NumberUtils.toInt(nextRecord[10].trim()));
                object.setDsmgrlMgrLevel(NumberUtils.toInt(nextRecord[11].trim()));
                object.setDsgmlvGemMgrLevel(NumberUtils.toInt(nextRecord[12].trim()));
                object.setDsqudsForiegnQual(nextRecord[13].trim());
                object.setDsdqmgRecognizedManager(nextRecord[14].trim());
                object.setDswk1CGrandFatherFlag(nextRecord[15].trim());
                object.setDsspsNonFederalTaxId(NumberUtils.toLong(nextRecord[16].trim()));
                object.setDsrslResale(nextRecord[17].trim());
                object.setDstitlTitle(nextRecord[18].trim());
                object.setDsspfnSpouseFname(nextRecord[19].trim());
                object.setDssplnSpouseLname(nextRecord[20].trim());
                object.setDsadd1Add1(nextRecord[21].trim());
                object.setDsadd2Add2(nextRecord[22].trim());
                object.setDsadd3Add3(nextRecord[23].trim());
                object.setDsadd4Add4(nextRecord[24].trim());
                object.setDscityCity(nextRecord[25].trim());
                object.setDsstatState(nextRecord[26].trim());
                object.setDszipcZipCode(nextRecord[27].trim());
                object.setDsctyCounty(nextRecord[28].trim());
                object.setDsvtxVertexFlag(nextRecord[29].trim());
                object.setDsgeocGeoCode(NumberUtils.toLong(nextRecord[30].trim()));
                object.setDspcntCompany(nextRecord[31].trim());
                object.setDsphonPhone(NumberUtils.toLong(nextRecord[32].trim()));
                object.setDstrfmTransferredMgr(nextRecord[33].trim());
                object.setDsinhmInheritMgr(nextRecord[34].trim());
                object.setDsareaWarehouseorArea(NumberUtils.toLong(nextRecord[35].trim()));
                object.setDswh2020PerWithHold(nextRecord[36].trim());
                object.setDswh3030PerWithHold(nextRecord[37].trim());
                object.setDssetdSetUpDate(nextRecord[38].trim());
                object.setDsamgrTotActiveMgrs(NumberUtils.toInt(nextRecord[39].trim()));
                object.setDstmgrTotMgrs(NumberUtils.toInt(nextRecord[40].trim()));
                object.setDschgdLstChgDate(nextRecord[41].trim());
                object.setDsusidUserId(nextRecord[42].trim());
                object.setDsarlkAreaSalesLock(nextRecord[43].trim());
                object.setDsfspmFrstPurchMnth(NumberUtils.toInt(nextRecord[44].trim()));
                object.setDsfspyFrstPurchYear(NumberUtils.toInt(nextRecord[45].trim()));
                object.setDslprfLangPrefCode(nextRecord[46].trim());
                object.setDsfrdcForiegnDom(nextRecord[47].trim());
                object.setDsqumgForiegnQualMgr(nextRecord[48].trim());
                object.setDsfedCorpFedTaxNum(nextRecord[49].trim());
                object.setDsssrcSponsoringSrc(NumberUtils.toInt(nextRecord[50].trim()));
                object.setDssssrSponsoringSubSrc(NumberUtils.toInt(nextRecord[51].trim()));
                object.setDssfdSecFedTaxId(nextRecord[52].trim());
                object.setDspfdPrimFedTaxId(nextRecord[53].trim());
                object.setDsadtpAddrType(nextRecord[54].trim());
                object.setDsw8EdExpiryDate(nextRecord[55].trim());
                object.setDsmnamMiddleName(nextRecord[56].trim());
                object.setDsspmnSpouceMiddleName(nextRecord[57].trim());

                list.add(object);
            }
            long endTime = System.currentTimeMillis();
            System.out.println(list.size());

            long start = System.currentTimeMillis();
            list.parallelStream()
                    .map(x -> {
                        dsmstpDistributorRepository.save(x);
                        return x;
                    }).collect(Collectors.toList());

            long end = System.currentTimeMillis();
            System.out.println(end - start);
            System.out.println("inserted");

            System.out.println(endTime - startTime);
        } catch (MalformedInputException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("DsmstpDistributor - End: " + System.currentTimeMillis());

        System.out.println("BoqalpBonusQualification - Start :" + System.currentTimeMillis());
        try (
                Reader reader =  new BufferedReader(new InputStreamReader(new FileInputStream("BoqalpBonusQualification.csv"),"utf-8"));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            int i = 1;
            List<BoqalpBonusQualification> list = new ArrayList<>();
            long startTime = System.currentTimeMillis();
            while ((nextRecord = csvReader.readNext()) != null) {
                BoqalpBonusQualification object = new BoqalpBonusQualification();
                object.setId(NumberUtils.toLong(nextRecord[0].trim()));
                object.setBqctlDistCtrl(NumberUtils.toLong(nextRecord[1].trim()));
                object.setBqlayrLastActiveYear(NumberUtils.toInt(nextRecord[2].trim()));
                object.setBqlamoLastActiveMonth(NumberUtils.toInt(nextRecord[3].trim()));
                object.setBqcnsaConsecutiveActive(NumberUtils.toInt(nextRecord[4].trim()));
                object.setBqlqyrLastQualYear(NumberUtils.toInt(nextRecord[5].trim()));
                object.setBqlqmoLastQualMonth(NumberUtils.toInt(nextRecord[6].trim()));
                object.setBqcnsqConsecutiveQual(NumberUtils.toInt(nextRecord[7].trim()));
                object.setBq4Ccw4CcWaiver(nextRecord[8].trim());
                object.setBqldbwLdrshipBonWaiver(nextRecord[9].trim());
                object.setBqpaypLdrshipBonElig(nextRecord[10].trim());
                object.setMemberFile(nextRecord[11].trim());

                list.add(object);
            }
            long endTime = System.currentTimeMillis();
            System.out.println(list.size());

            long start = System.currentTimeMillis();
            list.parallelStream()
                    .map(x -> {
                        boqalredis.save(x);
                        return x;
                    }).collect(Collectors.toList());

            long end = System.currentTimeMillis();
            System.out.println(end - start);
            System.out.println("inserted");

            System.out.println(endTime - startTime);
        } catch (MalformedInputException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("BoqalpBonusQualification - End: " + System.currentTimeMillis());

        System.out.println("BohstpBonusHistory - Start :" + System.currentTimeMillis());
        try (
                Reader reader =  new BufferedReader(new InputStreamReader(new FileInputStream("BOHSTP_BonusHistory.csv"),"utf-8"));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            int i = 1;
            List<BohstpBonusHistory> list = new ArrayList<>();
            long startTime = System.currentTimeMillis();
            while ((nextRecord = csvReader.readNext()) != null) {
                BohstpBonusHistory object = new BohstpBonusHistory();
                object.setId(NumberUtils.toLong(nextRecord[0].trim()));
                object.setBhctlDistCtrl(NumberUtils.toLong(nextRecord[1].trim()));
                object.setBhpyrYear(NumberUtils.toInt(nextRecord[2].trim()));
                object.setBhpmoMonth(NumberUtils.toInt(nextRecord[3].trim()));
                object.setBhtamtTotBonAmt(new BigDecimal(nextRecord[4].trim()));
                object.setBhtamt1PerDiscAmt(new BigDecimal(nextRecord[5].trim()));
                object.setBhtamt2RetPerBonAmt(new BigDecimal(nextRecord[6].trim()));
                object.setBhtamt3GrpBonAmt(new BigDecimal(nextRecord[7].trim()));
                object.setBhpcasPerCasCre(new BigDecimal(nextRecord[8].trim()));
                object.setBhdcasDistCasCre(new BigDecimal(nextRecord[9].trim()));
                object.setBhtacsTotActCasCre(new BigDecimal(nextRecord[10].trim()));
                object.setBhactfActiveFlag(nextRecord[11].trim());
                object.setBhncasNonMgrCasCre(new BigDecimal(nextRecord[12].trim()));
                object.setBhtccrTotCasCre(new BigDecimal(nextRecord[13].trim()));
                object.setBhphycPhyCasCre(new BigDecimal(nextRecord[14].trim()));
                object.setBhcoidCompCode(nextRecord[15].trim());
                object.setBhwaivVolWaiv(nextRecord[16].trim());
                object.setBhldbwLbWaiv(nextRecord[17].trim());
                object.setBhqumgLbQual(nextRecord[18].trim());
                object.setBhpaypLbElig(nextRecord[19].trim());
                object.setBhpuccPushupCasCre(new BigDecimal(nextRecord[20].trim()));
                object.setBhlbmgLbMgrs(NumberUtils.toInt(nextRecord[21].trim()));
                object.setBhblevBegLev(NumberUtils.toInt(nextRecord[22].trim()));
                object.setBhelevEndLev(NumberUtils.toInt(nextRecord[23].trim()));
                object.setBhdqmgRecMgr(nextRecord[24].trim());
                object.setBhmgrlMgrLev(NumberUtils.toInt(nextRecord[25].trim()));
                object.setBhgmlvGemLev(NumberUtils.toInt(nextRecord[26].trim()));
                object.setBhaytdTotBonAmtYtd(new BigDecimal(nextRecord[27].trim()));
                object.setBhpytdPerCasCreYtd(new BigDecimal(nextRecord[28].trim()));
                object.setBhdytdDistCasCreYtd(new BigDecimal(nextRecord[29].trim()));
                object.setBhtaydTotActCasCreYtd(new BigDecimal(nextRecord[30].trim()));
                object.setBhnytdNonMgrCasCreYtd(new BigDecimal(nextRecord[31].trim()));
                object.setBhtytdTotCasCreYtd(new BigDecimal(nextRecord[32].trim()));
                object.setBhhytdPhyCasCreYtd(new BigDecimal(nextRecord[33].trim()));
                object.setBhchkCheckNum(NumberUtils.toLong(nextRecord[34].trim()));
                object.setBhadjnAdjNon1099(new BigDecimal(nextRecord[35].trim()));
                object.setBhadjAdj1099(new BigDecimal(nextRecord[36].trim()));
                object.setBhareaDistArea(NumberUtils.toLong(nextRecord[37].trim()));
                object.setBh1GamFrtsGenActMgrs25Cc(NumberUtils.toInt(nextRecord[38].trim()));
                object.setBhnwccNewNonMgrCc(new BigDecimal(nextRecord[39].trim()));
                object.setBhnwpsNewNmccPs(new BigDecimal(nextRecord[40].trim()));
                object.setBhwytdNewNmccForPsYtd(new BigDecimal(nextRecord[41].trim()));

                list.add(object);
            }
            long endTime = System.currentTimeMillis();
            System.out.println(list.size());

            long start = System.currentTimeMillis();
            list.parallelStream()
                    .map(x -> {
                        bohstpBonusHistoryRepository.save(x);
                        return x;
                    }).collect(Collectors.toList());

            long end = System.currentTimeMillis();
            System.out.println(end - start);
            System.out.println("inserted");

            System.out.println(endTime - startTime);
        } catch (MalformedInputException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("BohstpBonusHistory - End: " + System.currentTimeMillis());

        System.out.println("BomcfgGlobalMultMonthMoveupConfig - Start :" + System.currentTimeMillis());
        try (
                Reader reader =  new BufferedReader(new InputStreamReader(new FileInputStream("BOMCFG_GlobalMultMonthMoveupConfig.csv"),"utf-8"));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            int i = 1;
            List<BomcfgGlobalMultMonthMoveupConfig> list = new ArrayList<>();
            long startTime = System.currentTimeMillis();
            while ((nextRecord = csvReader.readNext()) != null) {
                BomcfgGlobalMultMonthMoveupConfig object = new BomcfgGlobalMultMonthMoveupConfig();
                object.setId(NumberUtils.toLong(nextRecord[0].trim()));
                object.setIcopcyOpCmpy(nextRecord[1].trim());
                object.setIccthrCaseCrThreshold(new BigDecimal(nextRecord[2].trim()));
                object.setIcmnbamMaxNbActMonthsBack(NumberUtils.toInt(nextRecord[3].trim()));
                object.setIcstryrStartYear(NumberUtils.toInt(nextRecord[4].trim()));
                object.setIcstrmoStartMonth(NumberUtils.toInt(nextRecord[5].trim()));
                object.setIcedteEntryDate(nextRecord[6].trim());
                object.setIceusrEntryUser(nextRecord[7].trim());
                object.setIccdteChangeDate(nextRecord[8].trim());
                object.setIccusrChangeUser(nextRecord[9].trim());

                list.add(object);
            }
            long endTime = System.currentTimeMillis();
            System.out.println(list.size());

            long start = System.currentTimeMillis();
            list.parallelStream()
                    .map(x -> {
                        bomcfgGlobalMultMonthMoveupConfigRepository.save(x);
                        return x;
                    }).collect(Collectors.toList());

            long end = System.currentTimeMillis();
            System.out.println(end - start);
            System.out.println("inserted");

            System.out.println(endTime - startTime);
        } catch (MalformedInputException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("BomcfgGlobalMultMonthMoveupConfig - End: " + System.currentTimeMillis());

        System.out.println("BowqlpWhsQualOrdTrack - Start :" + System.currentTimeMillis());
        try (
                Reader reader =  new BufferedReader(new InputStreamReader(new FileInputStream("BowqlpWhsQualOrdTrack.csv"),"utf-8"));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            int i = 1;
            List<BowqlpWhsQualOrdTrack> list = new ArrayList<>();
            long startTime = System.currentTimeMillis();
            while ((nextRecord = csvReader.readNext()) != null) {
                BowqlpWhsQualOrdTrack object = new BowqlpWhsQualOrdTrack();
                object.setId(NumberUtils.toLong(nextRecord[0].trim()));
                object.setBwcoidCompanyId(nextRecord[1].trim());
                object.setBwpyrProcYear(NumberUtils.toInt(nextRecord[2].trim()));
                object.setBwpmoProcMonth(NumberUtils.toInt(nextRecord[3].trim()));
                object.setbwprccProcCycle(nextRecord[4].trim());
                object.setBwctlDistCtl(NumberUtils.toLong(nextRecord[5].trim()));
                object.setBwodatOrdDate(nextRecord[6].trim());
                object.setBwordOrdNum(NumberUtils.toLong(nextRecord[7].trim()));
                object.setBwwqlfWqlFlag(nextRecord[8].trim());
                object.setBwlmpccLstMnthPerCc(new BigDecimal(nextRecord[9].trim()));
                object.setBwcmpccMtdPerCc(new BigDecimal(nextRecord[10].trim()));
                object.setBwordccCasCreOrder(new BigDecimal(nextRecord[11].trim()));
                object.setBwwqlnccNotWqlCasCre(new BigDecimal(nextRecord[12].trim()));
                object.setBwwqlyccWqlCasCre(new BigDecimal(nextRecord[13].trim()));
                object.setBwpdatCreatedDate(nextRecord[14].trim());
                object.setBwcusrLstChgUser(nextRecord[15].trim());
                object.setBwordsOrderSrcCode(NumberUtils.toLong(nextRecord[16].trim()));
                object.setBwshpsShippingSrcCode(NumberUtils.toLong(nextRecord[17].trim()));
                object.setBwotypOrdTypCode(NumberUtils.toLong(nextRecord[18].trim()));
                object.setBwedteEntryTimeStamp(nextRecord[19].trim());
                object.setBwcdteChgTimeStamp(nextRecord[20].trim());

                list.add(object);
            }
            long endTime = System.currentTimeMillis();
            System.out.println(list.size());

            long start = System.currentTimeMillis();
            list.parallelStream()
                    .map(x -> {
                        bowqlpWhsQualOrdTrackRepository.save(x);
                        return x;
                    }).collect(Collectors.toList());

            long end = System.currentTimeMillis();
            System.out.println(end - start);
            System.out.println("inserted");

            System.out.println(endTime - startTime);
        } catch (MalformedInputException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("BowqlpWhsQualOrdTrack - End: " + System.currentTimeMillis());
//
        System.out.println("DshstpDistributorHistory - Start :" + System.currentTimeMillis());
        try (
                Reader reader =  new BufferedReader(new InputStreamReader(new FileInputStream("DshstpDistHistory.csv"),"utf-8"));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            int i = 1;
            List<DshstpDistributorHistory> list = new ArrayList<>();
            long startTime = System.currentTimeMillis();
            while ((nextRecord = csvReader.readNext()) != null) {
                DshstpDistributorHistory object = new DshstpDistributorHistory();
                object.setId(NumberUtils.toLong(nextRecord[0].trim()));
                object.setHtctlDistCtrl(NumberUtils.toLong(nextRecord[1].trim()));
                object.setHthscdHistCode(nextRecord[2].trim());
                object.setHtseqSequence(NumberUtils.toLong(nextRecord[3].trim()));
                object.setHtdscgOldData(nextRecord[4].trim());
                object.setHtpyrYearOfChange(NumberUtils.toInt(nextRecord[5].trim()));
                object.setHtpmoMonthOfChange(NumberUtils.toInt(nextRecord[6].trim()));
                object.setHtdatcChangeDate(nextRecord[7].trim());
                object.setHtuserChangeUserId(nextRecord[8].trim());
                object.setHtwprcWebProcessed(nextRecord[9].trim());
                object.setHtiprcICentrisProcessed(nextRecord[10].trim());


                list.add(object);
            }
            long endTime = System.currentTimeMillis();
            System.out.println(list.size());

            long start = System.currentTimeMillis();
            list.parallelStream()
                    .map(x -> {
                        dshstpDistributorHistoryRepository.save(x);
                        return x;
                    }).collect(Collectors.toList());

            long end = System.currentTimeMillis();
            System.out.println(end - start);
            System.out.println("inserted");

            System.out.println(endTime - startTime);
        } catch (MalformedInputException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("DshstpDistributorHistory - End: " + System.currentTimeMillis());

        System.out.println("DslevpDistributorLevels - Start :" + System.currentTimeMillis());
        try (
                Reader reader =  new BufferedReader(new InputStreamReader(new FileInputStream("DslevpDistributorLevels.csv"),"utf-8"));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            int i = 1;
            List<DslevpDistributorLevels> list = new ArrayList<>();
            long startTime = System.currentTimeMillis();
            while ((nextRecord = csvReader.readNext()) != null) {
                DslevpDistributorLevels object = new DslevpDistributorLevels();
                object.setId(NumberUtils.toLong(nextRecord[0].trim()));
                object.setDllevlLevel(NumberUtils.toInt(nextRecord[1].trim()));
                object.setDlmgrlMgrLev(NumberUtils.toInt(nextRecord[2].trim()));
                object.setDlgmlvGemLev(NumberUtils.toInt(nextRecord[3].trim()));
                object.setDldescLevDesc(nextRecord[4].trim());
                object.setDlsdscShortDesc(nextRecord[5].trim());
                object.setDlabbrAbbr(nextRecord[6].trim());
                object.setDlccrdMoveUpCc(new BigDecimal(nextRecord[7].trim()));
                object.setDlbonpBonPer(new BigDecimal(nextRecord[8].trim()));
                object.setDlsmgrNoOfFrstGenMgr(NumberUtils.toInt(nextRecord[9].trim()));

                list.add(object);
            }
            long endTime = System.currentTimeMillis();
            System.out.println(list.size());

            long start = System.currentTimeMillis();
            list.parallelStream()
                    .map(x -> {
                        dslevpDistributorLevelsRepository.save(x);
                        return x;
                    }).collect(Collectors.toList());

            long end = System.currentTimeMillis();
            System.out.println(end - start);
            System.out.println("inserted");

            System.out.println(endTime - startTime);
        } catch (MalformedInputException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("DslevpDistributorLevels - End: " + System.currentTimeMillis());

        System.out.println("DspwvpWaiver - Start :" + System.currentTimeMillis());
        try (
                Reader reader =  new BufferedReader(new InputStreamReader(new FileInputStream("DspwvpWaiver.csv"),"utf-8"));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            int i = 1;
            List<DspwvpWaiver> list = new ArrayList<>();
            long startTime = System.currentTimeMillis();
            while ((nextRecord = csvReader.readNext()) != null) {
                DspwvpWaiver object = new DspwvpWaiver();
                object.setId(NumberUtils.toLong(nextRecord[0].trim()));
                object.setPwctlDistCtrl(NumberUtils.toLong(nextRecord[1].trim()));
                object.setPwonemOneMonthWaiver(nextRecord[2].trim());
                object.setPwcdatLastChangeDate(nextRecord[3].trim());
                object.setPwcusrLastChangeUser(nextRecord[4].trim());
                object.setPwctycCountryCode(NumberUtils.toLong(nextRecord[5].trim()));
                object.setPwdidDistId(NumberUtils.toLong(nextRecord[6].trim()));
                object.setPwlbwfLdrshipWaiverFlag(nextRecord[7].trim());
                object.setPwcntyAlphaCountryCode(nextRecord[8].trim());
                object.setMemberFile(nextRecord[9].trim());

                list.add(object);
            }
            long endTime = System.currentTimeMillis();
            System.out.println(list.size());

            long start = System.currentTimeMillis();
            list.parallelStream()
                    .map(x -> {
                        dspwvpWaiverRepository.save(x);
                        return x;
                    }).collect(Collectors.toList());

            long end = System.currentTimeMillis();
            System.out.println(end - start);
            System.out.println("inserted");

            System.out.println(endTime - startTime);
        } catch (MalformedInputException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("DspwvpWaiver - End: " + System.currentTimeMillis());

        System.out.println("FlcmppCompanyConfig - Start :" + System.currentTimeMillis());
        try (
                Reader reader =  new BufferedReader(new InputStreamReader(new FileInputStream("FlcmppCompanyConfig.csv"),"utf-8"));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            int i = 1;
            List<FlcmppCompanyConfig> list = new ArrayList<>();
            long startTime = System.currentTimeMillis();
            while ((nextRecord = csvReader.readNext()) != null) {
                FlcmppCompanyConfig object = new FlcmppCompanyConfig();
                object.setId(NumberUtils.toLong(nextRecord[0].trim()));
                object.setFlcoidCompanyCode(nextRecord[1].trim());
                object.setFlcmpnCompanyName(nextRecord[2].trim());
                object.setFlcmshShortCmpnyName(nextRecord[3].trim());
                object.setFlstatStateCode(nextRecord[4].trim());
                object.setFlcntyCountryCode(nextRecord[5].trim());
                object.setFlcurrCurrencySymbol(nextRecord[6].trim());
                object.setFlcrvbCurrencyVerbage(nextRecord[7].trim());
                object.setFldecpDecimalPosition(NumberUtils.toInt(nextRecord[8].trim()));
                object.setFlcrncCurrencyCasCre(new BigDecimal(nextRecord[9].trim()));
                object.setFllbedLbBonEffDate(nextRecord[10].trim());
                object.setFlgfedGrandFatherEffDate(nextRecord[11].trim());
                object.setFldwthDomWhPct(new BigDecimal(nextRecord[12].trim()));
                object.setFlfwthForiegnWhPct(new BigDecimal(nextRecord[13].trim()));
                object.setFlcmcnCmpnyNameCenter(nextRecord[14].trim());
                object.setFladd1AddrLine1(nextRecord[15].trim());
                object.setFladd2AddrLine3(nextRecord[16].trim());
                object.setFladd3AddrLine4(nextRecord[17].trim());
                object.setFladd4AddrLine5(nextRecord[18].trim());
                object.setFlcityCity(nextRecord[19].trim());
                object.setFlzipcZipCode(nextRecord[20].trim());
                object.setFlphonPhoneNum(NumberUtils.toLong(nextRecord[21].trim()));
                object.setFlfnamCfoFrstName(nextRecord[22].trim());
                object.setFllnamCfoLastName(nextRecord[23].trim());
                object.setFlalbtAllowBottleProc(nextRecord[24].trim());
                object.setFlcorcCCorCurrency(nextRecord[25].trim());
                object.setFlmnamMerchName(nextRecord[26].trim());
                object.setFlmacMerchAcct(nextRecord[27].trim());
                object.setFlmdcManagingDirCtrl(NumberUtils.toLong(nextRecord[28].trim()));
                object.setFldfmtDateDispFrmt(nextRecord[29].trim());
                object.setFlfm01EarningsOne(NumberUtils.toInt(nextRecord[30].trim()));
                object.setFlfm02ProfitSharing(NumberUtils.toInt(nextRecord[31].trim()));
                object.setFlfm03MonthEndReports(NumberUtils.toInt(nextRecord[32].trim()));
                object.setFlfm04EarnedRallyTrips(NumberUtils.toInt(nextRecord[33].trim()));
                object.setFlfm05FutureOne(NumberUtils.toInt(nextRecord[34].trim()));
                object.setFlfm06FutureTwo(NumberUtils.toInt(nextRecord[35].trim()));

                list.add(object);
            }
            long endTime = System.currentTimeMillis();
            System.out.println(list.size());

            long start = System.currentTimeMillis();
            list.parallelStream()
                    .map(x -> {
                        flcmppCompanyConfigRepository.save(x);
                        return x;
                    }).collect(Collectors.toList());

            long end = System.currentTimeMillis();
            System.out.println(end - start);
            System.out.println("inserted");

            System.out.println(endTime - startTime);
        } catch (MalformedInputException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("FlcmppCompanyConfig - End: " + System.currentTimeMillis());

    }
}














