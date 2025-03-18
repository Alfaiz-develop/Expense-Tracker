package in.smart.tracker.controller;



import in.smart.tracker.entity.Expense;
import in.smart.tracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/viewExpense.html")
    public String viewExpense(Model model){
        List<Expense> expense = expenseService.getAllExpense();
        BigDecimal totalAmount = expense.stream().map(Expense::getAmount).reduce(BigDecimal.ZERO,BigDecimal::add);
        model.addAttribute("expense",expense);
        model.addAttribute("totalAmount", totalAmount);
        return "viewExpense";
    }


    @GetMapping("/addExpense.html")
    public String openAddExpensePage(Model model){
        Expense expense = new Expense();
        model.addAttribute("expense", expense);
        return "addExpense";
    }

    @PostMapping("/saveExpense")
    public String  saveExpense(@ModelAttribute("expense") Expense expense,Model model){
        expenseService.saveExpense(expense);
        return "viewExpense";
    }
    @GetMapping("editExpense/{id}")
    public String showUpadatePage(@PathVariable("id") long id , Model model){
        Expense expense = expenseService.getExpenseById(id);
        model.addAttribute("expense",  expense);
        return "updateExpense";
    }

    @PostMapping("/updateExpense/{id}")
    public String updateExpense(@PathVariable("id") long id, @ModelAttribute("expense") Expense expense){
        Expense existingExpense = expenseService.getExpenseById(id);
        existingExpense.setDescription(expense.getDescription());
        existingExpense.setAmount(expense.getAmount());
        expenseService.saveExpense(existingExpense);
        return "viewExpense";
    }

    @GetMapping("/deleteExpense/{id}")
    public String deleteExpense(@PathVariable("id") long id){
        expenseService.deleteExpenseById(id);
        return "viewExpense";
    }
}
