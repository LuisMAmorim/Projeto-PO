package woo.core;

import woo.core.exception.InvalidServiceLevelException;
import woo.core.exception.InvalidPriceException;


public class Box extends Product {
	private enum ServiceLevel {
		NORMAL, AIR, EXPRESS, PERSONAL
	}

	private ServiceLevel _serviceLevel;

	public Box(String id, String s, Supplier supplier, int price, int crit, int q)
	throws InvalidServiceLevelException {
		super(id, supplier, price, crit, q);

		try {
			_serviceLevel = ServiceLevel.valueOf(s);
		} catch (IllegalArgumentException x) {
			throw new InvalidServiceLevelException(s);
		}
	}

	public String getServiceLevel() {
		return _serviceLevel.name();
	}

	public String getProductType() {
		return "BOX";
	}

	public String getExtraInformation() {
		return _serviceLevel.name();
	}
}