package com.dne.ctrip.syndata.service;

import com.dne.ctrip.entity.CompanyCodeAndCostCenterRef;
import com.dne.ctrip.syndata.dao.CompanyDao;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private static final Logger log = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private CompanyDao companyDao;


    public CompanyCodeAndCostCenterRef getCompanyCodeByIpin(String ipin) {
        return companyDao.getCompanyCodeByIpin(ipin);
    }

    public List<CompanyCodeAndCostCenterRef> batchGetCompanyCodeByRefId(List<String> refIds, String refType, int batchQueryCount) {
        log.info("RefType: {}, refId size: {}", refType, refIds.size());
        List<List<String>> partitions = Lists.partition(refIds,batchQueryCount);
        log.info("Sub query partition size: {}", partitions.size());
        List<CompanyCodeAndCostCenterRef> companyCodeAndCostCenterRefs = Lists.newArrayList();
        for(List<String> subRefIds : partitions){
            List<CompanyCodeAndCostCenterRef> refs = null;
            switch (refType){
                case "cwId":
                    refs = companyDao.getCompanyCodeByCwIds(subRefIds);
                    break;
                case "orderId":
                    refs = companyDao.getCompanyCodeByOrderIds(subRefIds);
                    break;
                case "centerCode":
                    refs = companyDao.getCompanyCodeByCenterCodes(subRefIds);
                    break;
                default:
                    break;
            }
            if(CollectionUtils.isNotEmpty(refs)){
                log.info("Sub get CompanyCodeAndCostCenterRef size: {}", refs.size());
                companyCodeAndCostCenterRefs.addAll(refs);
            }
        }
        return companyCodeAndCostCenterRefs;
    }
}
