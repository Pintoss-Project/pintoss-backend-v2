package com.pintoss.gitftmall.infra.persistence.product;

import static com.pintoss.gitftmall.domain.product.model.QProduct.product;
import static com.querydsl.core.group.GroupBy.list;

import com.pintoss.gitftmall.domain.product.controller.request.ProductSearchRequest;
import com.pintoss.gitftmall.domain.product.controller.response.ProductSearchResponse;
import com.pintoss.gitftmall.domain.product.controller.response.QProductSearchResponse;
import com.pintoss.gitftmall.domain.product.model.QProduct;
import com.pintoss.gitftmall.domain.product.model.value.QImage;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductQueryDslRepositoryImpl implements ProductQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ProductSearchResponse> search(ProductSearchRequest request, Pageable pageable) {
        QProduct product = QProduct.product;
        QImage image = QImage.image;
        List<ProductSearchResponse> fetch = queryFactory.select(
                new QProductSearchResponse(
                    product.id,
                    product.name,
                    product.discount.cardDiscount,
                    product.discount.phoneDiscount,
                    JPAExpressions
                        .select(image.url)
                        .from(image)
                        .where(image.product.id.eq(product.id))
                )
            )
            .from(product)
            .where(
                searchCondition(request)
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(product.createdAt.desc())
            .fetch();

        long count = searchForCount(request);

        return new PageImpl<>(fetch, pageable, count);
    }

    private long searchForCount(ProductSearchRequest request) {
        Long count = queryFactory
            .select(product.count())
            .from(product)
            .fetchOne();

        if(count == null){
            count = 0L;
        }
        return count;
    }

    private BooleanBuilder searchCondition(ProductSearchRequest request) {
        return new BooleanBuilder()
            .and(nameContains(request.getKeyword()));
    }

    private BooleanExpression nameContains(String keyword) {
        return keyword != null ? product.name.contains(keyword) : null;
    }

    private BooleanExpression categoryEq(String category){
        // TODO : 카테고리 별 조회
        return null;
    }
}
