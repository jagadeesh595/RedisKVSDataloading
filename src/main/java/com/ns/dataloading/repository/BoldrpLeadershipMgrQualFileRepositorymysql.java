package com.ns.dataloading.repository;


import com.ns.dataloading.entity.BoldrpLeadershipMgrQualFile;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
@Qualifier("Boldrp")
@Repository
public interface BoldrpLeadershipMgrQualFileRepositorymysql extends JpaRepository<BoldrpLeadershipMgrQualFile,Long> {
}
