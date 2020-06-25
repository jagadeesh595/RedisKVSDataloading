package com.ns.dataloading;

import com.google.gson.Gson;
import com.ns.dataloading.RedisRepo.repository.*;
import com.ns.dataloading.Utils.Utils;
import com.ns.dataloading.entityredis.entity.*;
import com.ns.dataloading.redisconfig.ModelMapperConverter;
import com.ns.dataloading.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RepositoryCall {
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


        List<com.ns.dataloading.entity.RunConfig> runConfigList = new ArrayList<>();
        runConfigList = runconfigmysql.findAll();

        List<RunConfig> runConfigList1 = runConfigList.parallelStream().map(x ->
        {
            RunConfig runconfigredis = new RunConfig();
            ModelMapperConverter mapperConverter = new ModelMapperConverter();
            runconfigredis = mapperConverter.convert(x);
            return runconfigredis;
        }).collect(Collectors.toList());

        System.out.println("Run config loaded-started");
        runconfigredisRepo.saveAll(runConfigList1);
        System.out.println("Run config loaded");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''");

        List<com.ns.dataloading.entity.RunLog> Runlogmysql = new ArrayList<>();
        Runlogmysql = runlogrepomysql.findAll();

        List<RunLog> runLogList = Runlogmysql.parallelStream().map(x ->
        {
            RunLog runLogredis = new RunLog();
            ModelMapperConverter mapperConverter = new ModelMapperConverter();
            runLogredis = mapperConverter.convert(x);
            return runLogredis;
        }).collect(Collectors.toList());

        System.out.println("Runlogload-started");
        runlogReporedis.saveAll(runLogList);
        System.out.println("Runlogloaded");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''");

        List<com.ns.dataloading.entity.OrmstpOrder> orderlistmysql = new ArrayList<>();
        orderlistmysql = ordermysql.findAll();
        List<OrmstpOrder> ormstpOrders = new ArrayList();
        orderlistmysql.parallelStream().forEach(x ->
        {
            ModelMapperConverter mapperConverter = new ModelMapperConverter();
            ormstpOrders.add(mapperConverter.convert(x));
        });
        System.out.println("Orderloaded-started");
        orderredisrepo.saveAll(ormstpOrders);
        System.out.println("Orderloaded");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''");

        start = 0;
        currentCount = 0;
        do {
            List<com.ns.dataloading.entity.DsainpDistAddInfo> dsainpDistAddInfoList = dsainpDistAddInfoRepositorymysql.findDsainpDistAddInfo(start, limit);
            currentCount = dsainpDistAddInfoList.size();
            List<DsainpDistAddInfo> dsainprediss = dsainpDistAddInfoList.parallelStream().map(x ->
            {
                DsainpDistAddInfo dsainpredis = new DsainpDistAddInfo();
                ModelMapperConverter mapperConverter = new ModelMapperConverter();
                dsainpredis = mapperConverter.convert(x);
                return dsainpredis;
            }).collect(Collectors.toList());

            System.out.println("Distributor addinfo Loaded-started");
            dsainpDistAddInfoRepository.saveAll(dsainprediss);
            start = start + currentCount;
            System.out.println("Distributor addinfo Loaded");
            System.out.println("'''''''''''''''''''''''''''''''''''''''''''");
        }
        while (currentCount == limit);


        start = 0;
        currentCount = 0;
        do {
            List<com.ns.dataloading.entity.DsmstpDistributor> Distributorlistmysql = dsmstpDistributorRepositorymysql.findDsmstpDistributor(start, limit);
            currentCount = Distributorlistmysql.size();
            List<DsmstpDistributor> dsmstpDistributorList =
                    Distributorlistmysql.parallelStream().map(x ->
                    {
                        DsmstpDistributor distrepo = new DsmstpDistributor();
                        ModelMapperConverter mapperConverter = new ModelMapperConverter();
                        distrepo = mapperConverter.convert(x);
                        return distrepo;
                    }).collect(Collectors.toList());

            System.out.println("DsmstpDistributor Loaded-started");
            dsmstpDistributorRepository.saveAll(dsmstpDistributorList);
            start = start + currentCount;
            System.out.println("DsmstpDistributor Loaded");
            System.out.println("'''''''''''''''''''''''''''''''''''''''''''");
        }
        while (currentCount == limit);


        List<com.ns.dataloading.entity.BoqalpBonusQualification> boqalpBonusQualificationListmysql = bonusQualificationRepositorymysql.findAll();
        List<BoqalpBonusQualification> boqalp = boqalpBonusQualificationListmysql.parallelStream().map(x ->
        {
            BoqalpBonusQualification bonusQualification = new BoqalpBonusQualification();
            ModelMapperConverter mapperConverter = new ModelMapperConverter();
            bonusQualification = mapperConverter.convert(x);
            return bonusQualification;
        }).collect(Collectors.toList());

        System.out.println("BoqalpBonusQualification Loaded-started");
        boqalredis.saveAll(boqalp);
        System.out.println("BoqalpBonusQualification Loaded");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''");


        List<com.ns.dataloading.entity.BohstpBonusHistory> bohstpBonusHistoryListmysql = bohstpBonusHistoryRepositorymysql.findAll();
        List<BohstpBonusHistory> bohstpBonusHistoryLongList = bohstpBonusHistoryListmysql.parallelStream().map(x ->
        {
            BohstpBonusHistory bohstpBonusHistory = new BohstpBonusHistory();
            ModelMapperConverter mapperConverter = new ModelMapperConverter();
            bohstpBonusHistory = mapperConverter.convert(x);
            return bohstpBonusHistory;
        }).collect(Collectors.toList());

        System.out.println("BohstpBonusHistory Loaded-started");
        bohstpBonusHistoryRepository.saveAll(bohstpBonusHistoryLongList);
        System.out.println("BohstpBonusHistory Loaded");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''");


        List<com.ns.dataloading.entity.DistdccDailyCcDownloadFile> distdccDailyCcDownloadFiles = distdccDailyCcDownloadFilemysql.findAll();
        List<DistdccDailyCcDownloadFile> distdccDailyCcDownloadFileLonglist = distdccDailyCcDownloadFiles.parallelStream().map(x ->
        {
            DistdccDailyCcDownloadFile distdccDailyCcDownloadFile = new DistdccDailyCcDownloadFile();
            Gson gson = new Gson();
            ModelMapperConverter mapperConverter = new ModelMapperConverter();
            distdccDailyCcDownloadFile = mapperConverter.convert(x);
            return distdccDailyCcDownloadFile;
        }).collect(Collectors.toList());

        System.out.println("DISTDCC_DailyCCDownloadFile Loaded-started");
        distdccDailyCcDownloadFileRepository.saveAll(distdccDailyCcDownloadFileLonglist);
        System.out.println("DISTDCC_DailyCCDownloadFile Loaded");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''");

        List<com.ns.dataloading.entity.BofitpFitStartOrder> bofitpFitStartOrderList = bofitpFitStartOrderRepositorymysql.findAll();

        List<BofitpFitStartOrder> bofitpFitStartOrders = bofitpFitStartOrderList.parallelStream().map(x ->
        {
            BofitpFitStartOrder bofitpFitStartOrder = new BofitpFitStartOrder();
            ModelMapperConverter mapperConverter = new ModelMapperConverter();
            bofitpFitStartOrder = mapperConverter.convert(x);
            return bofitpFitStartOrder;
        }).collect(Collectors.toList());

        System.out.println("BofitpFitStartOrder Loaded-strted");
        bofitpFitStartOrderRepository.saveAll(bofitpFitStartOrders);
        System.out.println("BofitpFitStartOrder Loaded");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''");


        List<com.ns.dataloading.entity.FlcmppCompanyConfig> flcmppCompanyConfigListmysql = flcmppCompanyConfigmysql.findAll();
        List<FlcmppCompanyConfig> flcmppCompanyConfigList = flcmppCompanyConfigListmysql.parallelStream().map(x ->
        {
            FlcmppCompanyConfig flcmppCompanyConfig = new FlcmppCompanyConfig();
            ModelMapperConverter mapperConverter = new ModelMapperConverter();
            flcmppCompanyConfig = mapperConverter.convert(x);
            return flcmppCompanyConfig;
        }).collect(Collectors.toList());

        System.out.println("FlcmppCompanyConfig Loaded-started");
        flcmppCompanyConfigRepository.saveAll(flcmppCompanyConfigList);
        System.out.println("FlcmppCompanyConfig Loaded");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''");

        List<com.ns.dataloading.entity.DslevpDistributorLevels> dslevelp = new ArrayList<>();
        dslevelp = dslevpDistributorLevelsRepositorymysql.findAll();

        List<DslevpDistributorLevels> dslevprediss = dslevelp.parallelStream().map(x ->
        {
            DslevpDistributorLevels dslevpredis = new DslevpDistributorLevels();
            ModelMapperConverter mapperConverter = new ModelMapperConverter();
            dslevpredis = mapperConverter.convert(x);
            return dslevpredis;
        }).collect(Collectors.toList());

        System.out.println("Dslev loaded-started");
        dslevpDistributorLevelsRepository.saveAll(dslevprediss);
        System.out.println("Dslev loaded");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''");


        start = 0;
        currentCount = 0;
        do {
            List<com.ns.dataloading.entity.DshstpDistributorHistory> dshstpDistributorHistoryList = dshstpDistributorHistoryRepositorymysql.findDshstpDistributorHistory(start, limit);
            currentCount = dshstpDistributorHistoryList.size();
            List<DshstpDistributorHistory> dshstpDistributorHistorys = dshstpDistributorHistoryList.parallelStream().map(x ->
            {
                DshstpDistributorHistory dshstpDistributorHistory = new DshstpDistributorHistory();
                ModelMapperConverter mapperConverter = new ModelMapperConverter();
                dshstpDistributorHistory = mapperConverter.convert(x);
                return dshstpDistributorHistory;
            }).collect(Collectors.toList());

            System.out.println("DshstpDistributorHistory Loaded-started");
            dshstpDistributorHistoryRepository.saveAll(dshstpDistributorHistorys);
            start = start + currentCount;
            System.out.println("DshstpDistributorHistory Loaded");
            System.out.println("'''''''''''''''''''''''''''''''''''''''''''");
        }
        while (currentCount == limit);

        start = 0;
        currentCount = 0;
        do {
            List<com.ns.dataloading.entity.BowqlpWhsQualOrdTrack> bowqlpWhsQualOrdTrackList = bowqlpWhsQualOrdTrackRepositorymysql.findBowqlpWhsQualOrdTrack(start, limit);
            currentCount = bowqlpWhsQualOrdTrackList.size();
            List<BowqlpWhsQualOrdTrack> bowqlpWhsQualOrdTracks = bowqlpWhsQualOrdTrackList.parallelStream().map(x ->
            {
                BowqlpWhsQualOrdTrack bowqlpWhsQualOrdTrack = new BowqlpWhsQualOrdTrack();
                ModelMapperConverter mapperConverter = new ModelMapperConverter();
                bowqlpWhsQualOrdTrack = mapperConverter.convert(x);
                return bowqlpWhsQualOrdTrack;
            }).collect(Collectors.toList());

            System.out.println("BowqlpWhsQualOrdTrack Loaded-started");
            bowqlpWhsQualOrdTrackRepository.saveAll(bowqlpWhsQualOrdTracks);
            start = start + currentCount;
            System.out.println("BowqlpWhsQualOrdTrack Loaded");
            System.out.println("'''''''''''''''''''''''''''''''''''''''''''");
        }
        while (currentCount == limit);


        List<com.ns.dataloading.entity.BomcfgGlobalMultMonthMoveupConfig> bomcfgGlobalMultMonthMoveupConfigslist = new ArrayList<>();
        bomcfgGlobalMultMonthMoveupConfigslist = bomcfgGlobalMultMonthMoveupConfigRepositorymysql.findAll();

        List<BomcfgGlobalMultMonthMoveupConfig> bomcfgGlobalMultMonthMoveupConfigs = bomcfgGlobalMultMonthMoveupConfigslist.parallelStream().map(x ->
        {
            BomcfgGlobalMultMonthMoveupConfig bomcfgGlobalMultMonthMoveupConfig = new BomcfgGlobalMultMonthMoveupConfig();
            ModelMapperConverter mapperConverter = new ModelMapperConverter();
            bomcfgGlobalMultMonthMoveupConfig = mapperConverter.convert(x);
            return bomcfgGlobalMultMonthMoveupConfig;
        }).collect(Collectors.toList());

        System.out.println("BomcfgGlobalMultMonthMoveupConfig loaded-started");
        bomcfgGlobalMultMonthMoveupConfigRepository.saveAll(bomcfgGlobalMultMonthMoveupConfigs);
        System.out.println("BomcfgGlobalMultMonthMoveupConfig loaded");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''");


        List<com.ns.dataloading.entity.DspwvpWaiver> dspwvpWaiverList = new ArrayList<>();
        dspwvpWaiverList = dspwvpWaiverRepositorymysql.findAll();
        List<DspwvpWaiver> dspwvpWaivers = dspwvpWaiverList.parallelStream().map(x ->
        {
            DspwvpWaiver dspwvpWaiver = new DspwvpWaiver();
            ModelMapperConverter mapperConverter = new ModelMapperConverter();
            dspwvpWaiver = mapperConverter.convert(x);
            return dspwvpWaiver;
        }).collect(Collectors.toList());
        System.out.println("DspwvpWaiver loaded-started");
        dspwvpWaiverRepository.saveAll(dspwvpWaivers);
        System.out.println("DspwvpWaiver loaded");
        System.out.println("'''''''''''''''''''''''''''''''''''''''''''");
    }
}














