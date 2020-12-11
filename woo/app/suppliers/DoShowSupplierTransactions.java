package woo.app.suppliers;

import java.util.List;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
import woo.core.Supplier;
import woo.core.Transaction;

import woo.core.exception.UnknownSupplierException;
import woo.app.exception.UnknownSupplierKeyException;

/**
 * Show all transactions for specific supplier.
 */
public class DoShowSupplierTransactions extends Command<StoreManager> {

  private Input<String> _supplierKey;

  public DoShowSupplierTransactions(StoreManager receiver) {
    super(Label.SHOW_SUPPLIER_TRANSACTIONS, receiver);
    _supplierKey = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();

    try {
      Supplier s = _receiver.getSupplier(_supplierKey.value());
      List<Transaction> transactions = s.getTransactions();

      for (Transaction t : transactions) _display.addLine(t.toString());
      _display.display();
    }
      catch (UnknownSupplierException x) {
      throw new UnknownSupplierKeyException(_supplierKey.value());
    }
  }
}
