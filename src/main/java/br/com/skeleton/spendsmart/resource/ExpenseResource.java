package br.com.skeleton.spendsmart.resource;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.mapper.ExpenseMapper;
import br.com.skeleton.spendsmart.resource.request.ExpenseRequest;
import br.com.skeleton.spendsmart.resource.response.ExpenseResponse;
import br.com.skeleton.spendsmart.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expense")
public class ExpenseResource {

    private final ExpenseMapper expenseMapper;
    private final ExpenseService service;

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(expenseMapper::toExpenseResponse).toList());
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> save(@RequestBody @Valid ExpenseRequest expenseRequest) {
        Expense expense = service.save(expenseMapper.toExpense(expenseRequest));
        return ResponseEntity.created(URI.create("/" + expense.getId())).body(expenseMapper.toExpenseResponse(expense));
    }

    @PatchMapping("/{id}/pay")
    public ResponseEntity<ExpenseResponse> pay(@PathVariable final Long id) {
        return ResponseEntity.ok(expenseMapper.toExpenseResponse(service.pay(id)));
    }

}
