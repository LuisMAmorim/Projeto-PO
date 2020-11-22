package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.core.StoreManager;

import woo.app.exception.InvalidDateException;
import woo.app.main.Message;

/**
 * Advance current date.
 */
public class DoAdvanceDate extends Command<StoreManager> {
  
  Input<Integer> _numDays;

  public DoAdvanceDate(StoreManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    _numDays = _form.addIntegerInput(Message.requestDaysToAdvance());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
  	if (_numDays.value() > 0)
    	_receiver.advanceDate(_numDays.value());
    else
  		throw new InvalidDateException(_receiver.getDate() + _numDays.value());
  }
}
