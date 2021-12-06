package com.eqvypay.service.expense;

import com.eqvypay.persistence.Expense;
import com.eqvypay.service.database.DatabaseConnectionManagementService;
import com.eqvypay.util.DtoUtils;
import com.eqvypay.util.constants.DatabaseConstants;
import com.eqvypay.util.constants.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseDataManipulation implements IExpenseDataManipulation {

    @Autowired
    DatabaseConnectionManagementService dcms;

    @Autowired
    DtoUtils dtoUtils;

    @Override
    public void createTable() throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        Statement s = connection.createStatement();
        String query = "CREATE TABLE Expenses"
                + " ( id varchar(255)"
                + ",sourceUserId varchar(255)"
                + ",targetUserId varchar(266)"
                + ",groupId varchar(255)"
                + " ,expenseType varchar(255)"
                + " ,expenseAmt float"
                + " ,expenseDesc varchar(255)"
                + " ,currencyType varchar(255) );";

        if (!dtoUtils.tableExist(dcms, "Expenses")) {
            s.executeUpdate(query);
        }
    }

    @Override
    public Expense save(Expense expense) throws Exception {
        System.out.println("trying to save expense");
        expense.setId(UUID.randomUUID().toString());
        return expense;
    }

    @Override
    public void saveAll(List<Expense> expenses) throws Exception {
        Connection connection = dcms.getConnection(dcms.parseEnvironment());
        if (!dtoUtils.tableExist(dcms, "Expenses")) {
            createTable();
        }

        for (Expense expense : expenses) {
            PreparedStatement preparedStatement = connection.prepareStatement(DatabaseConstants.INSERT_EXPENSE);
            preparedStatement.setString(1, expense.getId());
            preparedStatement.setString(2, expense.getSourceUserId());
            preparedStatement.setString(3, expense.getTargetUserId());
            preparedStatement.setString(4, expense.getGroupId());
            preparedStatement.setString(5, expense.getExpenseType().getType());
            preparedStatement.setFloat(6, expense.getExpenseAmt());
            preparedStatement.setString(7, expense.getExpenseDesc());
            preparedStatement.setString(8, expense.getCurrencyType());
            try {
                int count = preparedStatement.executeUpdate();
                if (count > 0) {
                    System.out.println("Expense added");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
