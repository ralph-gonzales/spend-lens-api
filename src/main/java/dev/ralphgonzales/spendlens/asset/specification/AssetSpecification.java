package dev.ralphgonzales.spendlens.asset.specification;

import dev.ralphgonzales.spendlens.asset.entity.Asset;
import dev.ralphgonzales.spendlens.shared.domain.BaseEntity;
import dev.ralphgonzales.spendlens.shared.domain.UserOwnedEntity;
import dev.ralphgonzales.spendlens.shared.persistence.jpa.SpecificationUtil;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class AssetSpecification {

    public static Specification<Asset> isActive(){
        return (root, query, cb) -> cb.isTrue(root.get(BaseEntity.Fields.isActive));
    }

    public static Specification<Asset> isUserIdEqual(Long userId){
        return SpecificationUtil.isEquals(UserOwnedEntity.Fields.appUserId, userId);
    }

    public static Specification<Asset> isAssetMonthEquals(LocalDate assetMonth){
        return SpecificationUtil.isEquals(Asset.Fields.assetMonth, assetMonth);
    }

    public static Specification<Asset> isAssetTypeEquals(String assetType){
        return SpecificationUtil.isEquals(Asset.Fields.assetType, assetType);
    }

    public static Specification<Asset> isBankIdEquals(Long bankId){
        return SpecificationUtil.isEquals(Asset.Fields.bankId, bankId);
    }

    public static Specification<Asset> isIdEquals(Long id) {
        return  SpecificationUtil.isEquals(Asset.Fields.id, id);
    }

    public static Specification<Asset> isBankIdNull(){
        return ((root, query, cb) -> cb.isNull(root.get(Asset.Fields.bankId)));
    }
}
