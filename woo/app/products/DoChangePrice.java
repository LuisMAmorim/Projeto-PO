package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;

import woo.core.exception.UnknownProductException;
import woo.core.exception.InvalidPriceException;

/**
 * Change product price.
 */
public class DoChangePrice extends Command<StoreManager> {

  private Input<String> _id;
  private Input<Integer> _price;
  
  public DoChangePrice(StoreManager receiver) {
    super(Label.CHANGE_PRICE, receiver);
    _id = _form.addStringInput(Message.requestProductKey());
    _price = _form.addIntegerInput(Message.requestPrice());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    
    try {
    	_receiver.changePrice(_id.value(), _price.value());
    }
    catch (UnknownProductException x) {
    	/* Fails silently. */
    }
  }
}
