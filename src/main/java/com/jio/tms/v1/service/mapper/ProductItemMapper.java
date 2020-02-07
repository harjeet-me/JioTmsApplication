package com.jio.tms.v1.service.mapper;


import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.ProductItemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProductItem} and its DTO {@link ProductItemDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductItemMapper extends EntityMapper<ProductItemDTO, ProductItem> {



    default ProductItem fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductItem productItem = new ProductItem();
        productItem.setId(id);
        return productItem;
    }
}
