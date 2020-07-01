package com.ns.dataloading.Utils;
import com.ns.dataloading.entityredis.entity.DsainpDistAddInfo;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReadWrite {
//    public static void mapJavaBeanExample() throws FileNotFoundException {
//        ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
//        strat.setType(DsainpDistAddInfo.class);
//        String[] columns = new String[]{"id","icopcyOpCmpy","iccthrCaseCrThreshold","icmnbamMaxNbActMonthsBack","icstryrStartYear","icstrmoStartMonth","icedteEntryDate","iceusrEntryUser","iccdteChangeDate","iccusrChangeUser"};
//        strat.setColumnMapping(columns);
//        CsvToBean csv = new CsvToBean();
//        String csvFilename = "/home/ec2-user/dsmstp/CSV_Data/RedisgraphTablesworldcup/BomcfgGlobalMultMonthMoveupConfig.csv";
//        CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
//        List list = csv.parse(strat, csvReader);
//        for (Object object : list) {
//            DsainpDistAddInfo country = (DsainpDistAddInfo) object;
//        }
//    }
}