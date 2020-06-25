package com.ns.dataloading.repository;

import com.ns.dataloading.entity.BohstpBonusHistory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Qualifier("Bohstp")
@Repository
public interface BohstpBonusHistoryRepositorymysql extends JpaRepository<BohstpBonusHistory,Long> {
}
