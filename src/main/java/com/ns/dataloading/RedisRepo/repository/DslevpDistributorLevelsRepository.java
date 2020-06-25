package com.ns.dataloading.RedisRepo.repository;

import com.ns.dataloading.entityredis.entity.DslevpDistributorLevels;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;@Qualifier("DslevpDistributorLevelsRepository")
@Repository
public interface DslevpDistributorLevelsRepository extends CrudRepository<DslevpDistributorLevels,Long> {
}
