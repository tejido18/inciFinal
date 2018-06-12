package main.asw.agents;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.asw.encryption.EncryptionUtils;
import main.asw.location.LatLng;
import main.asw.util.Checker;

/**
 * 
 * @author Sergio Faya Fernandez
 *
 */
public class Agent {

	private String name;
	private String email;
	private String id;
	private int agentKind;
	private LatLng location;
	private String password, unencryptedPass;
	
	public final static int TIPO_SENSOR = 3;

	public Agent(int agentKind, String name, String email, String id) {
		checkConstructor(name, email, id);
		generatePassword();
		this.name = name;
		this.agentKind = agentKind;
		setEmail(email);
		setId(id);
	}

	public Agent(int agentKind, String name, String email, String id, LatLng location) {
		this(agentKind, name, email, id);
		this.location = location;
	}
	/**
	 * La location no puede ser nula en el tipo sensor
	 * @param kind
	 */
	@SuppressWarnings("unused")
	private void checkKindSensor(int kind)
	{
		if(kind==TIPO_SENSOR){
			Checker.isNull(location);
		}
	}
	
	private void checkConstructor(String name, String email, String id) {
		Checker.isNull(name);
		Checker.isEmpty(name);
		Checker.isNull(email);
		Checker.isEmpty(email);
		Checker.isNull(id);
		Checker.isEmpty(id);
	}

	private void generatePassword() {
		this.unencryptedPass = EncryptionUtils.getInstance().generatePassword();
		this.password = EncryptionUtils.getInstance().encryptPassword(unencryptedPass);
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}

	public int getAgentKind() {
		return agentKind;
	}

	public LatLng getLocation() {
		return location;
	}

	public String getPasswordUnencripted() {
		return unencryptedPass;
	}
	
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "Agent{ Name='" + name + "'" + ",Email='" + email + "'" + ",ID='" + id + "'" + ",AgentKind='" + agentKind
				+ "'" + ",Location='" + location + "'}";
	}

	/**
	 * This is a copy of a 2017 class called user.
	 * 
	 * @author nicolas
	 * @author MIGUEL
	 * 
	 */
	public void setEmail(String email) {
		if (validateEmail(email))
			this.email = email;
		else
			throw new IllegalArgumentException("The email is not well formed");
	}

	/**
	 * This is a copy of a 2017 class called user.
	 * 
	 * @author nicolas
	 * @author MIGUEL
	 * 
	 */
	private boolean validateEmail(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * This is a copy of a 2017 class called user.
	 * 
	 * @author nicolas
	 * @author MIGUEL
	 * 
	 */
	public void setId(String nif) {
		if (validateId(nif))
			this.id = nif;
		else
			throw new IllegalArgumentException("The nif is not well formed");
	}

	/**
	 * This is a copy of a 2017 class called user.
	 * 
	 * @author nicolas
	 * @author MIGUEL Template method, is redefined in the sensor as sensor uses
	 *         another ID
	 * @param nif
	 * @return true if the validation is correct, false otherwise
	 * 
	 */
	protected boolean validateId(String nif) {
		Boolean res = true;
		if (nif.length() == 9) {
			int dni = Integer.parseInt(nif.substring(0, 8));
			String juegoCaracteres = "TRWAGMYFPDXBNJZSQVHLCKE";
			int modulo = dni % 23;
			char letra = juegoCaracteres.charAt(modulo);
			if (nif.charAt(8) != letra)
				res = false;
		} else {
			res = false;
		}
		return res;
	}
}
