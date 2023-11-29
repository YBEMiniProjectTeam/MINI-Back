package com.fastcampus.mini9.domain.accommodation.repository;

import com.fastcampus.mini9.domain.accommodation.entity.accommodation.Accommodation;
import com.fastcampus.mini9.domain.accommodation.exception.NoSuchAccommodationType;
import com.fastcampus.mini9.domain.accommodation.vo.AccommodationType;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import static com.fastcampus.mini9.domain.accommodation.entity.accommodation.QAccommodation.accommodation;
import static com.fastcampus.mini9.domain.accommodation.entity.room.QStock.stock;

@Repository
public class AccommodationQueryDslRepositoryImpl implements AccommodationQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public AccommodationQueryDslRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<Accommodation> searchByConditions(
            String regionReq, String districtReq,
            LocalDate startDateReq, LocalDate endDateReq,
            String categoryReq, String keywordReq,
            PageRequest pageReq
    ) throws NoSuchAccommodationType {
        QueryResults<Accommodation> results = jpaQueryFactory
                .select(accommodation)
                .from(accommodation)
                .innerJoin(stock)
                .on(
                        stock.room.in(accommodation.rooms),
                        dateBetween(startDateReq, endDateReq),
                        stock.quantity.gt(0)
                )
                .where(
                        locationEq(regionReq, districtReq),
                        categoryEq(categoryReq),
                        keywordEq(keywordReq)
                )
                .offset(pageReq.getOffset())
                .limit(pageReq.getPageSize())
                .fetchResults();

        PageImpl<Accommodation> pagedResult = new PageImpl<>(
                results.getResults(),
                PageRequest.of(Long.valueOf(results.getOffset() / results.getLimit()).intValue(), Long.valueOf(results.getLimit()).intValue()),
                results.getTotal());
        return pagedResult;
    }

    private BooleanExpression dateBetween(LocalDate startDateReq, LocalDate endDateReq) {
        if (startDateReq != null && endDateReq != null) {
            return stock.date.between(startDateReq, endDateReq);
        }
        return stock.date.goe(LocalDate.now());
    }

    private BooleanExpression keywordEq(String keywordReq) {
        return keywordReq != null ? accommodation.name.contains(keywordReq) : null;
    }

    private BooleanExpression categoryEq(String categoryReq) throws NoSuchAccommodationType {
        return categoryReq != null ? accommodation.type.eq(AccommodationType.getTypeKor(categoryReq)) : null;
    }

    private BooleanExpression locationEq(final String regionReq, final String districtReq) {
        if (regionReq != null && districtReq != null) {
            return accommodation.location.region.name.eq(regionReq)
                    .and(accommodation.location.district.name.eq(districtReq));
        }
        if (regionReq != null) {
            return accommodation.location.region.name.eq(regionReq);
        }
        if (districtReq != null) {
            return accommodation.location.district.name.eq(districtReq);
        }
        return null;
    }
}
