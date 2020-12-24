package com.demo.module.buypolicy.component.impl;

import com.dap.dao.BaseDao;
import com.dap.flow.common.exception.BusinessException;
import com.demo.demo.buypolicy.component.BuyPolicy;
import com.demo.demo.buypolicy.component.param.BuyPolicyInput;
import com.demo.demo.buypolicy.component.param.BuyPolicyOutput;
import com.demo.module.buypolicy.po.PolicyPo;
import com.demo.module.buypolicy.utils.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuyPolicyImpl implements BuyPolicy {

    static final Logger LOGGER = LoggerFactory.getLogger(BuyPolicyImpl.class);

    @Autowired
    private BaseDao dao;

    @Override public BuyPolicyOutput execute(BuyPolicyInput input) {
        BuyPolicyOutput output = new BuyPolicyOutput();
        System.out.println("测试购买保险");
        PolicyPo policy = new PolicyPo();
        String policyId = UUIDGenerator.getUUID();
        policy.setPolicyId(policyId);
        policy.setApplicant(input.getApplicant().trim());
        policy.setInsurant(input.getInsurant().trim());
        policy.setInsurace(input.getInsurance().trim());
        policy.setAppAmount(input.getAppAmount());
        policy.setUpdateOper("zhou");
        try {
            dao.insert(policy);
            output.setBuyPolicyResult("购买保险成功");
        } catch (Exception e) {
            output.setBuyPolicyResult("购买保险失败");
            throw new BusinessException("EM30000001", "购买保单失败");
        }
        return output;
    }

}
