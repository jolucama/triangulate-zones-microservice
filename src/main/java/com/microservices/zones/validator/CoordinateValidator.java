package com.microservices.zones.validator;

import com.microservices.zones.entity.Coordinate;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by jlcardosa on 07/05/2017.
 */
public class CoordinateValidator implements Validator {

    /**
     * This Validator validates *just* Coordinate instances
     */
    public boolean supports(Class clazz) {
        return Coordinate.class.equals(clazz);
    }

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
