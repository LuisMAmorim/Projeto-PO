package woo.app.clients;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;

import woo.core.exception.UnknownClientException;
import woo.core.exception.UnknownProductException;

import woo.app.exception.UnknownClientKeyException;
import woo.app.exception.UnknownProductKeyException;

/**
 * Toggle product-related notifications.
 */
public class DoToggleProductNotifications extends Command<StoreManager> {

  private Input<String> _clientKey;
  private Input<String> _productKey;

  public DoToggleProductNotifications(StoreManager storefront) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, storefront);
    _clientKey = _form.addStringInput(Message.requestClientKey());
    _productKey = _form.addStringInput(Message.requestProductKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();

    try {
      boolean result = _receiver.toggleNotifications(_clientKey.value(), _productKey.value());

      if (result)
      	_display.popup(Message.notificationsOn(_clientKey.value(), _productKey.value()));
      else      	
      	_display.popup(Message.notificationsOff(_clientKey.value(), _productKey.value()));
    }
    catch (UnknownClientException x) {
      throw new UnknownClientKeyException(_clientKey.value());
    }
    catch (UnknownProductException x) {
      throw new UnknownProductKeyException(_productKey.value());
    }
  }

}
