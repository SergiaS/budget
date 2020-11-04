package com.budget.web;

import com.budget.enums.CategoryEnum;
import com.budget.enums.OperationTypeEnum;
import com.budget.model.Account;
import com.budget.repository.AccountRepository;
import com.budget.repository.InMemoryAccountRepository;
import com.budget.util.AccountsUtil;
import org.slf4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class AccountServlet extends HttpServlet {
    private static final Logger log = getLogger(AccountServlet.class);

    private AccountRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryAccountRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        Account account = new Account(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("cardName"),
                OperationTypeEnum.valueOf(request.getParameter("operationType")),
                OperationTypeEnum.valueOf(request.getParameter("operationType")) == OperationTypeEnum.EXPENSE ? new BigDecimal(request.getParameter("amount")).abs().negate() : new BigDecimal(request.getParameter("amount")).abs(),
                request.getParameter("notes").equals("") ? "" : "",
                request.getParameter("companyPayment"),
                CategoryEnum.valueOf(request.getParameter("category"))
                );

        log.info(account.isNew() ? "Create {}" : "Update {}", account);
        repository.save(account);
        response.sendRedirect("accounts");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete" -> {
                int id = getId(request);
                log.info("Delete {}", id);
                repository.delete(id);
                response.sendRedirect("accounts");
            }
            case "create", "update" -> {
                Account account = action.equals("create") ?
                        new Account(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "someCardName", OperationTypeEnum.EXPENSE, new BigDecimal("500.0"), "", "", CategoryEnum.OTHERS) :
                        repository.get(getId(request));
                request.setAttribute("account", account);
                request.setAttribute("operations", new ArrayList<>(Arrays.asList(OperationTypeEnum.values())));
                request.setAttribute("categories", new ArrayList<>(Arrays.asList(CategoryEnum.values())));
                request.getRequestDispatcher("/accountForm.jsp").forward(request, response);
            }
            default -> {
                log.info("getAll");
                request.setAttribute("accounts", AccountsUtil.getTos(repository.getAll(), AccountsUtil.MONEY_LIMIT_PER_DAY));
                request.getRequestDispatcher("/accounts.jsp").forward(request, response);
            }
        }
    }

    private int getId(HttpServletRequest request) {
        String id = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(id);
    }
}
