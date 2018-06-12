package main.asw.location;

import main.asw.util.Checker;

/**
 * 
 * @author Sergio Faya Fernández
 *
 */
public class LatLng {

	public double latitude;
	public double longitude;
	
	public LatLng(double latitude, double longitude) {
		Checker.isNull(latitude);
		Checker.isNull(longitude);
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "Location{Latitude='"+latitude+"',"+
				"Longitude='"+longitude+"'}";
	}
}
