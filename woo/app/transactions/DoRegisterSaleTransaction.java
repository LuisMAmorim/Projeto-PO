package woo.app.transactions;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;
import woo.core.Product;

import woo.app.exception.UnknownClientKeyException;
import woo.app.exception.UnknownProductKeyException;
import woo.app.exception.UnavailableProductException;

import woo.core.exception.UnknownClientException;
import woo.core.exception.UnknownProductException;
import woo.core.exception.NotEnoughStockException;

/**
 * Register sale.
 */
public class DoRegisterSaleTransaction extends Command<StoreManager> {

  private Input<String> _clientKey;
  private Input<Integer> _deadline;
  private Input<String> _productKey;
  private Input<Integer> _amount;

  public DoRegisterSaleTransaction(StoreManager receiver) {
    super(Label.REGISTER_SALE_TRANSACTION, receiver);
    _clientKey = _form.addStringInput(Message.requestClientKey());
    _deadline = _form.addIntegerInput(Message.requestPaymentDeadline());
    _productKey = _form.addStringInput(Message.requestProductKey());
    _amount = _form.addIntegerInput(Message.requestAmount());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    int available = 0;
    
  	try {
      available = _receiver.getProduct(_productKey.value()).getCurrentQuantity();

  		_receiver.registerSale(
  			_clientKey.value(),
  			_deadline.value(),
  			_productKey.value(),
  			_amount.value()
  		);
  	}
  	catch (UnknownClientException x) {
  		throw new UnknownClientKeyException(_clientKey.value());
  	}
  	catch (UnknownProductException x) {
  		throw new UnknownProductKeyException(_productKey.value());
  	}
    catch (NotEnoughStockException x) {
      throw new UnavailableProductException(
        _productKey.value(),
        _amount.value(),
        available
      );
    }
  }

}
