package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
import woo.core.Order;

import woo.app.exception.UnknownSupplierKeyException;
import woo.app.exception.UnauthorizedSupplierException;
import woo.app.exception.UnknownProductKeyException;
import woo.app.exception.WrongSupplierException;

import woo.core.exception.UnknownSupplierException;
import woo.core.exception.DisabledSupplierException;
import woo.core.exception.UnknownProductException;
import woo.core.exception.BadSupplierException;

/**
 * Register order.
 */
public class DoRegisterOrderTransaction extends Command<StoreManager> {

  private Input<String> _supplierKey;
  private Input<String> _productKey;
  private Input<Integer> _amount;
  private Input<Boolean> _more;

  public DoRegisterOrderTransaction(StoreManager receiver) {
    super(Label.REGISTER_ORDER_TRANSACTION, receiver);
    _supplierKey = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    
  	try {
  		Order order = _receiver.createOrder(_supplierKey.value());

  		do {
		    _productKey = _form.addStringInput(Message.requestProductKey());
		    _amount = _form.addIntegerInput(Message.requestAmount());
		    _more = _form.addBooleanInput(Message.requestMore());
		    _form.parse();

		    _receiver.addItemToOrder(order, _productKey.value(), _amount.value());
  		}
  		while (_more.value());

  		_receiver.registerOrder(order);
  	}
  	catch (UnknownSupplierException x) {
  		throw new UnknownSupplierKeyException(_supplierKey.value());
  	}
  	catch (DisabledSupplierException x) {
  		throw new UnauthorizedSupplierException(_supplierKey.value());
  	}
  	catch (UnknownProductException x) {
  		throw new UnknownProductKeyException(_productKey.value());
  	}
  	catch (BadSupplierException x) {
  		throw new WrongSupplierException(_supplierKey.value(), _productKey.value());
  	}
  }
}
