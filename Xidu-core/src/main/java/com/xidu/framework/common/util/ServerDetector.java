package com.xidu.framework.common.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerDetector {
	public static final String GERONIMO_ID = "geronimo";
	public static final String GLASSFISH_ID = "glassfish";
	public static final String JBOSS_ID = "jboss";
	public static final String JETTY_ID = "jetty";
	public static final String JONAS_ID = "jonas";
	public static final String OC4J_ID = "oc4j";
	public static final String RESIN_ID = "resin";
	public static final String TOMCAT_ID = "tomcat";
	public static final String WEBLOGIC_ID = "weblogic";
	public static final String WEBSPHERE_ID = "websphere";
	private static Logger _log = LoggerFactory.getLogger(ServerDetector.class);
	private static ServerDetector _instance;
	private boolean _geronimo;
	private boolean _glassfish;
	private boolean _jBoss;
	private boolean _jetty;
	private boolean _jonas;
	private boolean _oc4j;
	private boolean _resin;
	private String _serverId;
	private boolean _supportsComet;
	private boolean _supportsHotDeploy;
	private boolean _tomcat;
	private boolean _webLogic;
	private boolean _webSphere;

	public static ServerDetector getInstance() {
		if (_instance == null) {
			_instance = new ServerDetector();

			_instance._init();
		}

		return _instance;
	}

	public static String getServerId() {
		return getInstance()._serverId;
	}

	public static void init(String serverId) {
		ServerDetector serverDetector = new ServerDetector();

		serverDetector._serverId = serverId;

		if (serverId.equals("geronimo")) {
			serverDetector._geronimo = true;
		} else if (serverId.equals("glassfish")) {
			serverDetector._glassfish = true;
		} else if (serverId.equals("jboss")) {
			serverDetector._jBoss = true;
		} else if (serverId.equals("jetty")) {
			serverDetector._jetty = true;
		} else if (serverId.equals("jonas")) {
			serverDetector._jonas = true;
		} else if (serverId.equals("oc4j")) {
			serverDetector._oc4j = true;
		} else if (serverId.equals("resin")) {
			serverDetector._resin = true;
		} else if (serverId.equals("tomcat")) {
			serverDetector._tomcat = true;
		} else if (serverId.equals("weblogic")) {
			serverDetector._webLogic = true;
		} else if (serverId.equals("websphere")) {
			serverDetector._webSphere = true;
		} else {
			serverDetector._init();
		}

		_instance = serverDetector;
	}

	public static boolean isGeronimo() {
		return getInstance()._geronimo;
	}

	public static boolean isGlassfish() {
		return getInstance()._glassfish;
	}

	public static boolean isJBoss() {
		return getInstance()._jBoss;
	}

	public static boolean isJetty() {
		return getInstance()._jetty;
	}

	public static boolean isJOnAS() {
		return getInstance()._jonas;
	}

	public static boolean isOC4J() {
		return getInstance()._oc4j;
	}

	public static boolean isResin() {
		return getInstance()._resin;
	}

	public static boolean isSupportsComet() {
		return getInstance()._supportsComet;
	}

	public static boolean isSupportsHotDeploy() {
		return getInstance()._supportsHotDeploy;
	}

	public static boolean isTomcat() {
		return getInstance()._tomcat;
	}

	public static boolean isWebLogic() {
		return getInstance()._webLogic;
	}

	public static boolean isWebSphere() {
		return getInstance()._webSphere;
	}

	public static void setSupportsHotDeploy(boolean supportsHotDeploy) {
		getInstance()._supportsHotDeploy = supportsHotDeploy;

		if (_log.isInfoEnabled())
			if (supportsHotDeploy) {
				_log.info("Server supports hot deploy");
			} else
				_log.info("Server does not support hot deploy");
	}

	private boolean _detect(String className) {
		try {
			ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();

			systemClassLoader.loadClass(className);

			return true;
		} catch (ClassNotFoundException localClassNotFoundException) {
			Class clazz = getClass();

			if (clazz.getResource(className) != null) {
				return true;
			}
		}
		return false;
	}

	private boolean _hasSystemProperty(String key) {
		String value = System.getProperty(key);

		return value != null;
	}

	private void _init() {
		if (_isGeronimo()) {
			this._serverId = "geronimo";
			this._geronimo = true;
		} else if (_isGlassfish()) {
			this._serverId = "glassfish";
			this._glassfish = true;
		} else if (_isJBoss()) {
			this._serverId = "jboss";
			this._jBoss = true;
		} else if (_isJOnAS()) {
			this._serverId = "jonas";
			this._jonas = true;
		} else if (_isOC4J()) {
			this._serverId = "oc4j";
			this._oc4j = true;
		} else if (_isResin()) {
			this._serverId = "resin";
			this._resin = true;
		} else if (_isWebLogic()) {
			this._serverId = "weblogic";
			this._webLogic = true;
		} else if (_isWebSphere()) {
			this._serverId = "websphere";
			this._webSphere = true;
		}

		if (this._serverId == null) {
			if (_isJetty()) {
				this._serverId = "jetty";
				this._jetty = true;
			} else if (_isTomcat()) {
				this._serverId = "tomcat";
				this._tomcat = true;
			}
		}

		if ((System.getProperty("external-properties") == null)
				&& (_log.isInfoEnabled()))
			if (this._serverId != null) {
				_log.info("Detected server " + this._serverId);
			} else
				_log.info("No server detected");
	}

	private boolean _isGeronimo() {
		return _hasSystemProperty("org.apache.geronimo.home.dir");
	}

	private boolean _isGlassfish() {
		return _hasSystemProperty("com.sun.aas.instanceRoot");
	}

	private boolean _isJBoss() {
		return _hasSystemProperty("jboss.home.dir");
	}

	private boolean _isJetty() {
		return _hasSystemProperty("jetty.home");
	}

	private boolean _isJOnAS() {
		return _hasSystemProperty("jonas.base");
	}

	private boolean _isOC4J() {
		return _detect("oracle.oc4j.util.ClassUtils");
	}

	private boolean _isResin() {
		return _hasSystemProperty("resin.home");
	}

	private boolean _isTomcat() {
		return _hasSystemProperty("catalina.base");
	}

	private boolean _isWebLogic() {
		return _detect("/weblogic/Server.class");
	}

	private boolean _isWebSphere() {
		return _detect("/com/ibm/websphere/product/VersionInfo.class");
	}
}