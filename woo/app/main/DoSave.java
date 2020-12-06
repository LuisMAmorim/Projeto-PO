package woo.app.main;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import java.io.IOException;
import woo.core.StoreManager;

import woo.core.exception.MissingFileAssociationException;
import woo.app.exception.FileOpenFailedException;

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<StoreManager> {

  private Input<String> _fileName;

  /** @param receiver */
  public DoSave(StoreManager receiver) {
    super(Label.SAVE, receiver);
    _fileName = _form.addStringInput(Message.openFile());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    try {
      try {
        _receiver.save();   
      }
      catch (MissingFileAssociationException x) {
        _form.parse();
        _receiver.saveAs(_fileName.value());
      }
    }
    catch (IOException | MissingFileAssociationException x) {
      /* ??????????????????????????????????? */
    }
  }
}
