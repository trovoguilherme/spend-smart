package br.com.skeleton.spendsmart.resource;

import br.com.skeleton.spendsmart.entity.Purchase;
import br.com.skeleton.spendsmart.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
public class PurchaseResource {

    private final PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<List<Purchase>> findAll() {
        return ResponseEntity.ok(purchaseService.findAll());
    }

    @PostMapping
    public ResponseEntity<Purchase> save() {
        return ResponseEntity.ok(purchaseService.save());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Purchase> update(@PathVariable final Long id) {
        return ResponseEntity.ok(purchaseService.update(id));
    }

}
