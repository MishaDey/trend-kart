package com.trend_kart.modules.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.List;

import static com.trend_kart.constants.TrendKartConstants.ELASTICSEARCH_PRODUCT_MAPPING_PATH;
import static com.trend_kart.constants.TrendKartConstants.ELASTICSEARCH_PRODUCT_SETTING_PATH;

@Document(indexName = "products")
@Setting(settingPath = ELASTICSEARCH_PRODUCT_SETTING_PATH)
/*
Mapping which defines: type, custom analysers for each field, normalisers for case-insensitive search,
disable search, autocomplete settings by edge_ngram
Without this, Spring will not restrict the data-types that are being inserted into the DB
*/
@Mapping(mappingPath = ELASTICSEARCH_PRODUCT_MAPPING_PATH)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDocument {
    @Id
    @Field(type = FieldType.Keyword, analyzer = "autocomplete")
    private String id;

    @Field(value = "product_id", type = FieldType.Keyword) // Exact Match Filter
    private String productID;

    @Field(type = FieldType.Text, analyzer = "standard") // Analysed field for full text search
    private String name;

    @Field(type = FieldType.Keyword)
    private String code;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Keyword)
    private String brand;

    @Field(type = FieldType.Keyword)
    private List<String> tags;
}
