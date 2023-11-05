package com.example.backend.pks;

import com.example.backend.models.Product;
import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
import java.time.LocalDateTime;

@Setter @Getter
public class ProductPricePK implements Serializable {
    private Product product;
    private LocalDateTime price_date_time;
}
