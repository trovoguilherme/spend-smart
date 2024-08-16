package br.com.skeleton.spendsmart.service;

import br.com.skeleton.spendsmart.entity.Purchase;
import br.com.skeleton.spendsmart.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    public Purchase save() {
        Purchase purchase = new Purchase();
        purchase.setName("sa");
        return purchaseRepository.save(purchase);
    }

    public Purchase update(final Long id) {
        Purchase purchase = purchaseRepository.findById(id).get();
        purchase.setName("alterado");
        return purchaseRepository.save(purchase);
    }

}
