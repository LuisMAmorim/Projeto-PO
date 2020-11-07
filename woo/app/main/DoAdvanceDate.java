package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;

import woo.core.exception.AdvanceDateException;
import woo.app.exception.InvalidDateException;

/**
 * Advance current date.
 */
public class DoAdvanceDate extends Command<StoreManager> {
  
  Input<Integer> numDays = _form.addIntegerInput(Message.requestDaysToAdvance());

  public DoAdvanceDate(StoreManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    numDays = _form.addIntegerInput(Message.requestDaysToAdvance());
  }

  @Override
  public final void execute() throws DialogException {
  	if (numDays.value() > 0)
    	_receiver.advanceDate(numDays.value());
    else
  		throw new InvalidDateException(_receiver.getDate() + numDays.value());
  }
}
