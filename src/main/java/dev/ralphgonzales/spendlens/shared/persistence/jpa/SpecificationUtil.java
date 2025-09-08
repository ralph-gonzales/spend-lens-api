package dev.ralphgonzales.spendlens.shared.persistence.jpa;

import org.springframework.data.jpa.domain.Specification;

public final class SpecificationUtil {

    private SpecificationUtil(){}

    public static <T> Specification<T> isEquals(String fieldName, Object value){
        return (root, query, cb) -> {
            if (value == null) return null;
            return cb.equal(root.get(fieldName), value);
        };
    }

    public static <T,U extends Comparable<? super U>>Specification<T> isGreaterThanOrEqual(String fieldName, U value){
        return (root, query, cb) -> {
            if (value == null) return null;
            return cb.greaterThanOrEqualTo(root.get(fieldName), value);
        };
    }

    public static <T,U extends Comparable<? super U>>Specification<T> isLessThanOrEqual(String fieldName, U value){
        return (root, query, cb) -> {
            if (value == null) return null;
            return cb.lessThanOrEqualTo(root.get(fieldName), value);
        };
    }
}
