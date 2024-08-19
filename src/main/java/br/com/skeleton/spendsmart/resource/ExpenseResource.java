package br.com.skeleton.spendsmart.resource;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.mapper.ExpenseMapper;
import br.com.skeleton.spendsmart.resource.request.ExpenseRequest;
import br.com.skeleton.spendsmart.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expense")
public class ExpenseResource {

    private final ExpenseMapper expenseMapper;
    private final ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<Expense>> findAll() {
        return ResponseEntity.ok(expenseService.findAll());
    }

    @PostMapping
    public ResponseEntity<Expense> save(@RequestBody @Valid ExpenseRequest expenseRequest) {
        return ResponseEntity.ok(expenseService.save(expenseMapper.toExpense(expenseRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> update(@PathVariable final Long id) {
        return ResponseEntity.ok(expenseService.update(id));
    }

    @PatchMapping("/{id}/pay")
    public ResponseEntity<Expense> payExpense(@PathVariable final Long id) {
        return ResponseEntity.ok(expenseService.pay(id));
    }

}
