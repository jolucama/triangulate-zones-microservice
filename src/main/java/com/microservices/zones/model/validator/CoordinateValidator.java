package com.microservices.zones.model.validator;

import com.microservices.zones.model.Coordinate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by jlcardosa on 07/05/2017.
 */
public class CoordinateValidator implements Validator {

    /**
     * This validator validates *just* Coordinate instances
     * @param clazz
     * @return 
     */
    @Override
    public boolean supports(Class clazz) {
        return Coordinate.class.equals(clazz);
    }

    /**
     *
     * @param obj
     * @param e
     */
    @Override
    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "latitude", "latitude.empty");
        ValidationUtils.rejectIfEmpty(e, "longitude", "longitude.empty");
        Coordinate coordinate = (Coordinate) obj;
        if (coordinate.getLatitude() < -90 || coordinate.getLatitude() > 90 ) {
            e.rejectValue("latitude", "latitude.out.range");
        }
        if (coordinate.getLatitude() < -180 || coordinate.getLatitude() > 180 ) {
            e.rejectValue("longitude", "longitude.out.range");
        }
    }
}
