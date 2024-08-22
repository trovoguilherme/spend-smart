package br.com.skeleton.spendsmart.service;

import br.com.skeleton.spendsmart.entity.Installment;
import br.com.skeleton.spendsmart.exception.BusinessException;
import br.com.skeleton.spendsmart.exception.NoUnpaidInstallmentException;
import br.com.skeleton.spendsmart.repository.InstallmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstallmentService {

    private final InstallmentRepository repository;

    public List<Installment> findAll() {
        return repository.findAll();
    }

    public List<Installment> saveAll(List<Installment> installments) {
        return repository.saveAll(installments);
    }

    public List<Installment> pay(List<Installment> installments) {

        Installment unpaidInstallment = installments.stream().filter(installment -> !installment.getPaid())
                .min(Comparator.comparingLong(Installment::getId)).orElseThrow(NoUnpaidInstallmentException::new);

        unpaidInstallment.pay();
        repository.save(unpaidInstallment);

        boolean allPaid = installments.stream().allMatch(Installment::getPaid);

        if (allPaid) {
            //TODO Salvar no histórico
        }

        return installments;
    }
}
