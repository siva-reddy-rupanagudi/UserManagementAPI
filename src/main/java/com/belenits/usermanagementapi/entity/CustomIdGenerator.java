package com.belenits.usermanagementapi.entity;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;


public class CustomIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        @SuppressWarnings("deprecation")
        Number number = (Number) session.createNativeQuery("select next_val from userseq for update").getSingleResult();
        session.createNativeQuery("update userseq set next_val = next_val + 1").executeUpdate();
        return "USER" + number.longValue();
    }
}
