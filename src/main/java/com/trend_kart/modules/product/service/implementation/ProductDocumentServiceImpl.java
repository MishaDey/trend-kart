package com.trend_kart.modules.product.service.implementation;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import com.trend_kart.modules.product.dto.ProductDocumentDTO;
import com.trend_kart.modules.product.entity.ProductDocument;
import com.trend_kart.modules.product.service.ProductDocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ProductDocumentServiceImpl implements ProductDocumentService {
    private final ElasticsearchClient elasticsearchClient;

    public ProductDocumentServiceImpl(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    /*
     * Insert into Product
     */
    @Override
    public void indexProduct(ProductDocument product) throws IOException {
        IndexResponse response = elasticsearchClient.index(
                index -> index
                        .index("products")
                        .id(product.getId())
                        .document(product));
        log.info("ProductDocumentServiceImpl::indexProduct::response::{}", response);
    }

    @Override
    public List<ProductDocumentDTO> searchProducts(Integer pageSize, Integer pageNo, String searchKey, String brand, Double maxPrice) throws IOException {
        // For Exact Match on name
        // Query nameQuery = MatchQuery.of(match -> match.field("name").query(searchKey))._toQuery(); // For Exact Matches

        // For Prefix match on name
        Query namePrefixMatchQuery = MatchPhrasePrefixQuery.of(
                match -> match.field("name").query(searchKey).boost(2.0f)
        )._toQuery(); // Behaves like "searchKey%"

        // For Fuzzy match on name -> it makes our system typo tolerant
        Query nameFuzzyQuery = FuzzyQuery.of(field -> field
                .field("name")
                .value(searchKey)
                .fuzziness("2") // Can also be "1" or "2" -> to denote the edit distance, AUTO automatically chooses between 1 or 2 on the basis of the input length
//                .prefixLength(0) // At least the 1 character should match from beginning
                .maxExpansions(50) // How many similar terms elasticsearch will explore during fizzy search
                .boost(1.0f)
        )._toQuery();

        Query brandFilter = TermQuery.of(term -> term.field("brand").value(brand))._toQuery();

        // lte - query for price less than or equal to maxPrice
        Query priceFilter = RangeQuery.of(range -> range
                .number(
                        number -> number
                                .field("price")
                                .lte(maxPrice)
                )
        )._toQuery();

        SearchResponse<ProductDocument> response = elasticsearchClient.search(
                search -> search
                        .index("products")
                        .query(query -> query
                                .bool(
                                        bool -> bool
                                                .should(namePrefixMatchQuery) // Because of .boost(2.0f) -> the results of this will be given more preference
                                                .should(nameFuzzyQuery)
                                                .minimumShouldMatch("1") // Means that either one of the above shoulds should macth before applying filter
                                                .filter(List.of(brandFilter, priceFilter))
                                )
                        ).sort(sort -> sort
                                .field(
                                        field -> field.field("price").order(SortOrder.Asc)
                                )
                        )
                        .from((pageNo - 1) * pageSize) // Page No
                        .size(pageSize) // Page Size
                        .explain(true),
                ProductDocument.class);

        // SAMPLE - Listing all elements
        // SearchResponse<ProductDocument> response = elasticsearchClient.search(
        //        search -> search
        //                .index("products")
        //                .query(query -> query.matchAll(match -> match))
        //                .size(100)
        //        , ProductDocument.class
        //);

        List<ProductDocument> productDocuments = response.hits().hits().stream().map(Hit::source)
                .filter(Objects::nonNull).distinct().toList();
        return productDocuments.stream().map(this::productDocumentToDTO).toList();
    }

    @Override
    public List<String> autoCompleteProductNames(String searchKey) throws IOException {
        Query nameAutoCompleteQuery = MatchPhrasePrefixQuery.of(match -> match.field("name").query(searchKey))._toQuery();
        SearchResponse<ProductDocument> response = elasticsearchClient.search(
                search -> search
                        .index("products")
                        .query(query -> query
                                .bool(
                                        bool -> bool
                                                .must(nameAutoCompleteQuery)
                                )
                        )
                        .size(10),
                ProductDocument.class
        );
        return response.hits().hits().stream()
                .map(Hit::source)
                .filter(Objects::nonNull)
                .map(ProductDocument::getName)
                .filter(Objects::nonNull)
                .distinct()
                .toList();
    }

    public ProductDocumentDTO productDocumentToDTO(ProductDocument productDocument) {
        return ProductDocumentDTO.builder()
                .id(productDocument.getId())
                .productID(productDocument.getProductID())
                .name(productDocument.getName())
                .code(productDocument.getCode())
                .description(productDocument.getDescription())
                .price(productDocument.getPrice())
                .category(productDocument.getCategory())
                .brand(productDocument.getBrand())
                .tags(productDocument.getTags().toArray(new String[0]))
                .build();
    }
}
