package com.diplo.sharedkernel.amqp.masstransit;

public class HostInfo {

	private String MachineName;
	private String ProcessName;
	private int ProcessId;
	private String Assembly;
	private String AssemblyVersion;
	private String FrameworkVersion;
	private String MassTransitVersion;
	private String OperatingSystemVersion;

	public HostInfo() {
		super();
	}

	public HostInfo(
		String machineName,
		String processName,
		int processId,
		String assembly,
		String assemblyVersion,
		String frameworkVersion,
		String massTransitVersion,
		String operatingSystemVersion
	) {
		super();
		MachineName = machineName;
		ProcessName = processName;
		ProcessId = processId;
		Assembly = assembly;
		AssemblyVersion = assemblyVersion;
		FrameworkVersion = frameworkVersion;
		MassTransitVersion = massTransitVersion;
		OperatingSystemVersion = operatingSystemVersion;
	}

	public String getMachineName() {
		return MachineName;
	}

	public void setMachineName(String machineName) {
		MachineName = machineName;
	}

	public String getProcessName() {
		return ProcessName;
	}

	public void setProcessName(String processName) {
		ProcessName = processName;
	}

	public int getProcessId() {
		return ProcessId;
	}

	public void setProcessId(int processId) {
		ProcessId = processId;
	}

	public String getAssembly() {
		return Assembly;
	}

	public void setAssembly(String assembly) {
		Assembly = assembly;
	}

	public String getAssemblyVersion() {
		return AssemblyVersion;
	}

	public void setAssemblyVersion(String assemblyVersion) {
		AssemblyVersion = assemblyVersion;
	}

	public String getFrameworkVersion() {
		return FrameworkVersion;
	}

	public void setFrameworkVersion(String frameworkVersion) {
		FrameworkVersion = frameworkVersion;
	}

	public String getMassTransitVersion() {
		return MassTransitVersion;
	}

	public void setMassTransitVersion(String massTransitVersion) {
		MassTransitVersion = massTransitVersion;
	}

	public String getOperatingSystemVersion() {
		return OperatingSystemVersion;
	}

	public void setOperatingSystemVersion(String operatingSystemVersion) {
		OperatingSystemVersion = operatingSystemVersion;
	}
}
