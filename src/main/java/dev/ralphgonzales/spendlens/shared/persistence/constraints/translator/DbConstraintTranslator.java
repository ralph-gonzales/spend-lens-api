package dev.ralphgonzales.spendlens.shared.persistence.constraints.translator;

import dev.ralphgonzales.spendlens.shared.constraints.DbConstraintMapper;
import dev.ralphgonzales.spendlens.shared.enums.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbConstraintTranslator {

    private final DbConstraintMapper mapper;

    public CommonErrorCode map(Throwable ex){
        String constraint = extractConstraintName(ex);

        String code = mapper.mapConstraintToCode(constraint);
        if(code == null){
            log.warn("Unknown DB constraint (or none provided). constraint={}", constraint);
            return CommonErrorCode.DB_CONSTRAINT_VIOLATION;
        }

        return CommonErrorCode.fromCode(code).orElse(CommonErrorCode.DB_CONSTRAINT_VIOLATION);
    }

    private String extractConstraintName(Throwable t) {
        for(Throwable curr = t; curr != null; curr.getCause()){
            // Hibernate
            if(curr instanceof ConstraintViolationException h){
                String name = h.getConstraintName();
                log.warn("DB constraint violation detected (Hibernate): constraint={}, sqlState={}",
                        name, h.getSQLState());
                return name;
            }

            // Postgres driver
            if(curr instanceof PSQLException p && p.getServerErrorMessage() != null){
                ServerErrorMessage msg = p.getServerErrorMessage();
                log.warn("DB constraint violation detected (Postres): constraint={}, sqlState={}",
                        msg.getConstraint(), p.getSQLState());
                if(log.isDebugEnabled()){
                    log.debug("PG detail: {}", msg.getDetail());
                }
                return msg.getConstraint();
            }

            if(curr instanceof SQLIntegrityConstraintViolationException j){
                log.warn("DB integrity constraint violation(JDBC): sqlState={}, errorCode={}",
                        j.getSQLState(), j.getErrorCode());
            }
        }

        return null;
    }
}
