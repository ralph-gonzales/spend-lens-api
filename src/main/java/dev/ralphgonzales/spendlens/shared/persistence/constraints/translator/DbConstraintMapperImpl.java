package dev.ralphgonzales.spendlens.shared.persistence.constraints.translator;

import dev.ralphgonzales.spendlens.shared.config.DbConstraintProperties;
import dev.ralphgonzales.spendlens.shared.constraints.DbConstraintMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DbConstraintMapperImpl implements DbConstraintMapper {

    private final DbConstraintProperties props;

    @Override
    public String mapConstraintToCode(String name) {
        String code = null;
        if(props.appUser() != null ) code = props.asset().get(name);
        if(code == null && props.expense() != null) code = props.expense().get(name);
        if(code == null && props.asset() != null) code = props.asset().get(name);
        if(code == null && props.timeDeposit() != null) code = props.timeDeposit().get(name);
        if(code == null && props.cashFlow() != null) code = props.cashFlow().get(name);
        if(code == null && props.expense() != null) code = props.expense().get(name);

        if(code == null) {
            code = props.other() != null
                    ? props.other().getOrDefault("default", null)
                    :null;
        }
        return code;
    }
}
