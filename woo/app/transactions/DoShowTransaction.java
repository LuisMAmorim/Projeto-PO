package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
import woo.core.Transaction;

import woo.core.exception.UnknownTransactionException;
import woo.app.exception.UnknownTransactionKeyException;

/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<StoreManager> {

  private Input<Integer> _key;

  public DoShowTransaction(StoreManager receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
    _key = _form.addIntegerInput(Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();

    try {
    	Transaction t = _receiver.getTransaction(_key.value());
    	int date = _receiver.getDate();

    	_display.popup(t.toString(date));
    }
    catch (UnknownTransactionException x) {
    	throw new UnknownTransactionKeyException(_key.value());
    }
  }
}
