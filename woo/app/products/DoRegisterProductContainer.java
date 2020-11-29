package woo.app.products;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;

import woo.app.exception.DuplicateProductKeyException;
import woo.app.exception.UnknownSupplierKeyException;
import woo.app.exception.UnknownServiceTypeException;
import woo.app.exception.UnknownServiceLevelException;

import woo.core.exception.DuplicateKeyException;
import woo.core.exception.UnknownSupplierException;
import woo.core.exception.InvalidPriceException;
import woo.core.exception.InvalidServiceLevelException;
import woo.core.exception.InvalidServiceQualityException;

/**
 * Register container.
 */
public class DoRegisterProductContainer extends Command<StoreManager> {

  private Input<String> _id;
  private Input<Integer> _price;
  private Input<Integer> _critical;
  private Input<String> _supplierId;
  private Input<String> _serviceType;
  private Input<String> _serviceLevel;

  public DoRegisterProductContainer(StoreManager receiver) {
    super(Label.REGISTER_CONTAINER, receiver);
    _id = _form.addStringInput(Message.requestProductKey());
    _price = _form.addIntegerInput(Message.requestPrice());
    _critical = _form.addIntegerInput(Message.requestStockCriticalValue());
    _supplierId = _form.addStringInput(Message.requestSupplierKey());
    _serviceType = _form.addStringInput(Message.requestServiceType());
    _serviceLevel = _form.addStringInput(Message.requestServiceLevel());
  }

  @Override
  public final void execute() throws DialogException {
  	_form.parse();

  	try {
    	_receiver.registerContainer(_id.value(), _serviceType.value(), _serviceLevel.value(),
    		_supplierId.value(), _price.value(), _critical.value(), 0);
  	}
  	catch (DuplicateKeyException x) {
  		throw new DuplicateProductKeyException(_id.value());
    }
    catch (UnknownSupplierException x) {
    	throw new UnknownSupplierKeyException(_supplierId.value());
  	}
  	catch (InvalidServiceLevelException x) {
  		throw new UnknownServiceTypeException(_serviceType.value());
  	}
  	catch (InvalidServiceQualityException x) {
  		throw new UnknownServiceLevelException(_serviceLevel.value());
  	}
  	catch (InvalidPriceException x) {
  		/* Fails silently. */
  	}
  }
}
