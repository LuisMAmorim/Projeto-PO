package woo.core;

import woo.core.exception.InvalidServiceLevelException;
import woo.core.exception.InvalidPriceException;

enum ServiceLevel {
	NORMAL,
	AIR,
	EXPRESS,
	PERSONAL;

	public static ServiceLevel parse(String s) throws InvalidServiceLevelException {
	    if (s.equals("NORMAL"))
	      return NORMAL;

	    if (s.equals("AIR"))
	      return AIR;

	    if (s.equals("EXPRESS"))
	      return EXPRESS;

	    if (s.equals("PERSONAL"))
	      return PERSONAL;

	    throw new InvalidServiceLevelException(s);
  	}
}

public class Box extends Product {
	private ServiceLevel _serviceLevel;

	public Box(String id, String s, Supplier supplier, int price, int crit, int q)
	throws InvalidServiceLevelException {
		super(id, supplier, price, crit, q);
		_serviceLevel = ServiceLevel.parse(s);
	}

	public ServiceLevel getServiceLevel() {
		return _serviceLevel;
	}

	/*
	@Override
	public String toString() {
		return String.join("|", "BOX",
			getId(),
			getSupplier().getId(),
			Integer.toString(getPrice()),
			Integer.toString(getCriticalValue()),
			Integer.toString(getCurrentQuantity()),
			_serviceLevel.toString()
		);
	}
	*/

	public String getProductType() {
		return "BOX";
	}

	public String getExtraInformation() {
		return _serviceLevel.toString();
	}
}