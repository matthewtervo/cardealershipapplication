package com.itrelliscardealership.web.util;

import com.google.common.base.Joiner;
import com.itrelliscardealership.dao.CarSpecificationsBuilder;
import com.itrelliscardealership.dao.model.Car;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RequestParamResolver {

    public Specification<Car> resolveSpecification(String params) {
        CarSpecificationsBuilder builder = new CarSpecificationsBuilder();
        String operationSet = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\w+?)("+operationSet+")(\\w+?),");
        Matcher matcher = pattern.matcher(params + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        return builder.build();
    }

}
