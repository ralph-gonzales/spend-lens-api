package dev.ralphgonzales.spendlens.shared.persistence.constraints.translator;

import dev.ralphgonzales.spendlens.shared.constraints.DbConstraintMapper;
import dev.ralphgonzales.spendlens.shared.enums.CommonErrorCode;
import dev.ralphgonzales.spendlens.shared.exceptions.DatabaseConstraintException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbConstraintTranslator {

    private final DbConstraintMapper mapper;

    private String extractConstraintName(Throwable t) {
        Throwable curr = t;
        while (curr != null) {
            if(curr instanceof PSQLException p && p.getServerErrorMessage() != null) {
                log.warn("DB constraint violation detected: constraint={}, sqlState={}",
                        p.getServerErrorMessage().getConstraint(), p.getSQLState());
                return p.getServerErrorMessage().getConstraint();
            }
            curr = curr.getCause();
        }
        return null;
    }

    private DatabaseConstraintException mapToException(String constraint) {
        CommonErrorCode error = CommonErrorCode.valueOf(mapper.mapConstraintToCode(constraint));
        return new DatabaseConstraintException(error.getCode(), error.getMessageKey(), error.getStatus());
    }

    public DatabaseConstraintException map(Throwable ex){
        String constraint = extractConstraintName(ex);
        return mapToException(constraint);
    }
}
