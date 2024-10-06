package br.com.skeleton.spendsmart.service;

import br.com.skeleton.spendsmart.entity.Investment;
import br.com.skeleton.spendsmart.entity.Wallet;
import br.com.skeleton.spendsmart.exception.NotFoundException;
import br.com.skeleton.spendsmart.repository.InvestmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestmentService {

    private final InvestmentRepository investmentRepository;
    private final UserDetailsServiceImpl userDetailsService;

    public Investment save(Investment investment) {
        Wallet wallet = userDetailsService.getActualUser().getWallet();
        wallet.setInvestments(List.of(investment));
        investment.setStatus(Investment.InvestmentStatus.ACTIVE);

        return investmentRepository.save(investment);
    }

    public Investment update(Long id, Investment investment) {
        Wallet wallet = userDetailsService.getActualUser().getWallet();

        Investment investmentFound = wallet.getInvestments().stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Investment not found for user"));

        investmentFound.setBalance(investment.getBalance());
        investmentFound.setInvestmentType(investment.getInvestmentType());
        investmentFound.setBankType(investment.getBankType());
        investmentFound.setYield(investment.getYield());
        investmentFound.setDueDate(investment.getDueDate());
        investmentFound.setRedemptionDate(investment.getRedemptionDate());

        return investmentRepository.save(investmentFound);
    }

    public void deleteById(Long id) {
        Wallet wallet = userDetailsService.getActualUser().getWallet();

        boolean hasInvestment = wallet.getInvestments().stream().anyMatch(i -> i.getId().equals(id));

        if (hasInvestment) {
            investmentRepository.deleteById(id);
        } else {
            throw new NotFoundException("Investment not found for his user");
        }
    }
}
