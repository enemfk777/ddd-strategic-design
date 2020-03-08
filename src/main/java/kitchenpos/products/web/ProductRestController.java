package kitchenpos.products.web;

import java.net.URI;
import java.util.List;
import kitchenpos.products.domain.usecase.ProductBo;
import kitchenpos.products.domain.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {

    private final ProductBo productBo;

    public ProductRestController(final ProductBo productBo) {
        this.productBo = productBo;
    }

    @PostMapping("/api/products")
    public ResponseEntity<Product> create(@RequestBody final Product product) {
        final Product created = productBo.create(product);
        final URI uri = URI.create("/api/products/" + created.getId());
        return ResponseEntity.created(uri)
            .body(created)
            ;
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok()
            .body(productBo.list())
            ;
    }
}