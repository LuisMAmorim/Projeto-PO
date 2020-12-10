package woo.app.suppliers;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;

import woo.core.exception.UnknownSupplierException;
import woo.app.exception.UnknownSupplierKeyException;

/**
 * Enable/disable supplier transactions.
 */
public class DoToggleTransactions extends Command<StoreManager> {

  private Input<String> _id;

  public DoToggleTransactions(StoreManager receiver) {
    super(Label.TOGGLE_TRANSACTIONS, receiver);
    _id = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();

    try {
    	boolean isActive = _receiver.toggleTransactions(_id.value());

    	if (isActive)
    		_display.popup(Message.transactionsOn(_id.value()));
    	else
    		_display.popup(Message.transactionsOff(_id.value()));
    }
    catch (UnknownSupplierException x) {
    	throw new UnknownSupplierKeyException(_id.value());
    }
  }

}
