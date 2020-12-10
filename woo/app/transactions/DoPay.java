package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;

import woo.core.exception.UnknownTransactionException;
import woo.app.exception.UnknownTransactionKeyException;

/**
 * Pay transaction (sale).
 */
public class DoPay extends Command<StoreManager> {

  private Input<Integer> _id;
  
  public DoPay(StoreManager storefront) {
    super(Label.PAY, storefront);
    _id = _form.addIntegerInput(Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    
    try {
    	_receiver.pay(_id.value());
    }
    catch (UnknownTransactionException x) {
    	throw new UnknownTransactionKeyException(_id.value());
    }
  }
}
