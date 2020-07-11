package org.despessoalapp.service;

import org.despessoalapp.model.Balance;
import org.despessoalapp.model.Transaction;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@ApplicationScoped
public class BalanceService {

    public List<Balance> getList() {
        return Balance.list("active", true);
    }

    /**
     * Se a operação da atualização do saldo for 'save' o último saldo bancário é acrescido da transação e se for
     * 'cancel' é reduzido o último saldo bancário .
     */
    public void updateBalance(Transaction transaction, String operation) {
        Balance lastBalance = Balance.find("active = ?1 and bank = ?2 and user = ?3",
                true, transaction.getBank().getId(), transaction.getUser().getId()).singleResult();
        Balance balance = new Balance(lastBalance);
        switch (operation) {
            case "save":
                setBalance(transaction, balance, balance.getDebit() + transaction.getValue(),
                        balance.getCredit() + transaction.getValue(), balance.getDebit() - transaction.getValue(), balance.getCredit() - transaction.getValue());
                break;
            case "cancel":
                setBalance(transaction, balance, balance.getDebit() - transaction.getValue(),
                        balance.getCredit() - transaction.getValue(), balance.getDebit() + transaction.getValue(), balance.getCredit() + transaction.getValue());
                break;
        }
        balance.persistAndFlush();
        Balance.update("active = ?1 where id = ?2", false, lastBalance.getId());
    }

    private void setBalance(Transaction transaction, Balance balance, double entDeb, double entCred, double saiDeb, double saiCred) {
        switch (transaction.getOperation()) {
            case "ENT":
                if (transaction.getDebCred().equals("DEB"))
                    balance.setDebit(entDeb);
                else
                    balance.setCredit(entCred);
                break;
            case "SAI":
                if (transaction.getDebCred().equals("DEB"))
                    balance.setDebit(saiDeb);
                else
                    balance.setCredit(saiCred);
                break;
            case "TRA":
                if (transaction.getDebCred().equals("DEB"))
                    balance.setDebit(balance.getDebit() - transaction.getValue());
                else
                    balance.setCredit(balance.getCredit() - transaction.getValue());
                break;
        }
    }
}
