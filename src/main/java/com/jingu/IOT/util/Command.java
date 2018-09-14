package com.jingu.IOT.util;


public class Command {
	public static byte[] getSentCommand(byte cmd,String dataValue,String deviceId){
		return getSentCommand(cmd,dataValue.getBytes(),deviceId);
	}
    public static byte[] getSentCommand(byte cmd,byte[] dataValue,String deviceId){
    	byte[] data=getData(dataValue,Client.getDeviceId(deviceId));
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = cmd;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		byte high = (byte)((data.length & 0xff00)>>8);
		byte low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
    public static byte[] getSentCommand(byte cmd,byte[] data){
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = cmd;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		byte high = (byte)((data.length & 0xff00)>>8);
		byte low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
    public static byte[] getSentCommand(byte cmd,String dataValue){
    	byte[] data=getData(dataValue.getBytes());
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = cmd;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		byte high = (byte)((data.length & 0xff00)>>8);
		byte low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
	public static byte[] getSentCommand(byte cmd){
		byte[] command = new byte[12];
		command[0] = 0x55;
		command[1] = cmd;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;

		command[10] = 0x00;
		command[11] = 0x00;
		return command;
	}
	
	/*
	 * TODO 获取日志文件个数
	 */
	public static byte[] getVRALogFileNum() {
		byte[] command = new byte[12];
		command[0] = 0x55;
		command[1] = 0x09;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;

		command[10] = 0x00;
		command[11] = 0x00;
		return command;
	}

	/**
	 * TODO 获取OK指令
	 */
	public static byte[] getVRASendOK(byte[] DeviceId,byte type) {
		byte[] command = new byte[21];
		command[0] = 0x55;
		command[1] = type;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		byte[] data=getData("ok",DeviceId);
		//加密长度
		byte high = (byte)((data.length & 0xff00)>>8);
		byte low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[20]=getCRC(data);
		return command;
	}
	/**
	 *TODO 获取设备参数 
	 * @return
	 */
	public static byte[] getVRAConfigData() {
		byte[] command = new byte[12];
		command[0] = 0x55;
		command[1] = 0x04;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;

		command[10] = 0x00;
		command[11] = 0x00;
		return command;
	}
	/**
	 * TODO 设置设备参数
	 * @param DeviceIdBtye
	 * @return
	 */
	public static byte[] setVRAConfigData(String config,byte[] DeviceIdBtye) {
		byte[] data=getData(config,DeviceIdBtye);
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = 0x05;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		byte high = (byte)((data.length & 0xff00)>>8);
		byte low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
	/**
	 * TODO VRA软件更新
	 * @param DeviceIdBtye
	 * @return
	 */
	public static byte[] setVRASoftWareUpdate(byte[] dataValue,byte[] DeviceIdBtye) {
		byte[] data=getData(dataValue,DeviceIdBtye);
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = 0x05;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		byte high = (byte)((data.length & 0xff00)>>8);
		byte low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
	/**
	 *TODO 显示视频菜单 
	 * @return
	 */
	public static byte[] cameraMenu() {
		//FF 01 00 03 00 5F 63
		byte[] data = new byte[13];
		byte high = (byte) (((data.length - 2) & 0xff00) >> 8);
		byte low = (byte) ((data.length - 2) & 0x00ff);
		data[0] = high;
		data[1] = low;
		
		data[2] = 0x00;
		data[3] = 0x00;
		data[4] = 0x00;
		data[5] = 0x00;
		data[6] = (byte)Integer.parseInt("FF",16);
		data[7] = 0x01;
		data[8] = 0x00;
		data[9] = 0x03;
		data[10] = 0x00;
		data[11] = 0x5F;
		data[12] = 0x63;
		
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = 0x0F;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		high = (byte)((data.length & 0xff00)>>8);
		low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
	/**
	 * 视频上移命令
	 * @return
	 */
	public static byte[] cameraUp() {
		//FF 01 00 08 00 2F 38
		byte[] data = new byte[13];
		byte high = (byte) (((data.length - 2) & 0xff00) >> 8);
		byte low = (byte) ((data.length - 2) & 0x00ff);
		data[0] = high;
		data[1] = low;
		
		data[2] = 0x00;
		data[3] = 0x00;
		data[4] = 0x00;
		data[5] = 0x00;
		data[6] = (byte)Integer.parseInt("FF",16);
		data[7] = 0x01;
		data[8] = 0x00;
		data[9] = 0x08;
		data[10] = 0x00;
		data[11] = 0x2F;
		data[12] = 0x38;		
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = 0x0F;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		high = (byte)((data.length & 0xff00)>>8);
		low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
		
	}
	/**
	 * 视频下移命令
	 * @return
	 */
	public static byte[]cameraDown() {
		//FF 01 00 10 00 3F 50
		byte[] data = new byte[13];
		byte high = (byte) (((data.length - 2) & 0xff00) >> 8);
		byte low = (byte) ((data.length - 2) & 0x00ff);
		data[0] = high;
		data[1] = low;
		
		data[2] = 0x00;
		data[3] = 0x00;
		data[4] = 0x00;
		data[5] = 0x00;
		data[6] = (byte)Integer.parseInt("FF",16);
		data[7] = 0x01;
		data[8] = 0x00;
		data[9] = 0x10;
		data[10] = 0x00;
		data[11] = 0x3F;
		data[12] = 0x50;

		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = 0x0F;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		high = (byte)((data.length & 0xff00)>>8);
		low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
		
	}
	/**
	 * 视频上翻屏命令
	 * @return
	 */
	public static byte[]cameraLeft() {
		//55:0f:00:00:00:00:00:00:00:00:00:0d:00:0b:00:00:00:00:ff:01:00:04:3f:00:44:92
		//FF 01 00 04 3F 00 44
		byte[] data = new byte[13];
		byte high = (byte) (((data.length - 2) & 0xff00) >> 8);
		byte low = (byte) ((data.length - 2) & 0x00ff);
		data[0] = high;
		data[1] = low;
		
		data[2] = 0x00;
		data[3] = 0x00;
		data[4] = 0x00;
		data[5] = 0x00;
		data[6] = (byte)Integer.parseInt("FF",16);
		data[7] = 0x01;
		data[8] = 0x00;
		data[9] = 0x04;
		data[10] = 0x3F;
		data[11] = 0x00;
		data[12] = 0x44;
		
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = 0x0F;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		high = (byte)((data.length & 0xff00)>>8);
		low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
	/**
	 * 视频下翻屏命令
	 * @return
	 */
	public static byte[]cameraRight() {
		//55:0f:00:00:00:00:00:00:00:00:00:0d:00:0b:00:00:00:00:ff:01:00:02:3f:00:42:8e
		//FF 01 00 02 3F 00 42
		byte[] data = new byte[13];
		byte high = (byte) (((data.length - 2) & 0xff00) >> 8);
		byte low = (byte) ((data.length - 2) & 0x00ff);
		data[0] = high;
		data[1] = low;
		
		data[2] = 0x00;
		data[3] = 0x00;
		data[4] = 0x00;
		data[5] = 0x00;
		data[6] = (byte)Integer.parseInt("FF",16);
		data[7] = 0x01;
		data[8] = 0x00;
		data[9] = 0x02;
		data[10] = 0x3F;
		data[11] = 0x00;
		data[12] = 0x42;
	
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = 0x0F;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		high = (byte)((data.length & 0xff00)>>8);
		low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
	/**
	 * 视频选择命令
	 * @return
	 */
	public static byte[]cameraChoose() {
		//FF 01 00 20 00 00 21
		//55:0f:00:00:00:00:00:00:00:00:     00:0d:00:0b:00:00:00:00:  ff:01:02:00:00:00:03:10
		byte[] data = new byte[13];
		byte high = (byte) (((data.length - 2) & 0xff00) >> 8);
		byte low = (byte) ((data.length - 2) & 0x00ff);
		data[0] = high;
		data[1] = low;
		
		data[2] = 0x00;
		data[3] = 0x00;
		data[4] = 0x00;
		data[5] = 0x00;
		data[6] = (byte)Integer.parseInt("FF",16);
		data[7] = 0x01;
		data[8] = 0x02;
		data[9] = 0x00;
		data[10] = 0x00;
		data[11] = 0x00;
		data[12] = 0x03;
		
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = 0x0F;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		high = (byte)((data.length & 0xff00)>>8);
		low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
	/**
	 * 视频确定命令
	 * @return
	 */
	public static byte[]cameraOk() {
		//FF 01 00 40 00 00 41  55:0f:00:00:00:00:00:00:00:00:     00:0d:00:0b:00:00:00:00:  ff:01:02:00:00:00:03:10
		byte[] data = new byte[13];
		byte high = (byte) (((data.length - 2) & 0xff00) >> 8);
		byte low = (byte) ((data.length - 2) & 0x00ff);
		data[0] = high;
		data[1] = low;
		
		data[2] = 0x00;
		data[3] = 0x00;
		data[4] = 0x00;
		data[5] = 0x00;
		data[6] = (byte)Integer.parseInt("FF",16);
		data[7] = 0x01;
		data[8] = 0x02;
		data[9] = 0x00;
		data[10] = 0x00;
		data[11] = 0x00;
		data[12] = 0x03;
		
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = 0x0F;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		high = (byte)((data.length & 0xff00)>>8);
		low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
	/**
	 * 处理数据区
	 * @param data
	 * @param deviceId
	 * @return
	 */
	private static byte[] getData(String data, byte[] deviceId) {
		byte[] value = data.getBytes();
		byte[] newData = new byte[value.length + 2 + deviceId.length];
		byte high = (byte) (((newData.length - 2) & 0xff00) >> 8);
		byte low = (byte) ((newData.length - 2) & 0x00ff);
		newData[0] = high;
		newData[1] = low;
		newData[2] = deviceId[0];
		newData[3] = deviceId[1];
		newData[4] = deviceId[2];
		newData[5] = deviceId[3];
		for (int i = 0; i < value.length; i++) {
			newData[i + 6] = value[i];
		}
		return newData;
	}
	private static byte[] getData(byte[] data, byte[] deviceId) {
		byte[] value = data;
		byte[] newData = new byte[value.length + 2 + deviceId.length];
		byte high = (byte) (((newData.length - 2) & 0xff00) >> 8);
		byte low = (byte) ((newData.length - 2) & 0x00ff);
		newData[0] = high;
		newData[1] = low;
		newData[2] = deviceId[0];
		newData[3] = deviceId[1];
		newData[4] = deviceId[2];
		newData[5] = deviceId[3];
		for (int i = 0; i < value.length; i++) {
			newData[i + 6] = value[i];
		}
		return newData;
	}
	private static byte[] getData(byte[] data) {
		byte[] value = data;
		byte[] newData = new byte[value.length + 2];
		byte high = (byte) (((newData.length - 2) & 0xff00) >> 8);
		byte low = (byte) ((newData.length - 2) & 0x00ff);
		newData[0] = high;
		newData[1] = low;
		for (int i = 0; i < value.length; i++) {
			newData[i + 2] = value[i];
		}
		return newData;
	}
	/**
	 *TODO 获取设备参数 
	 * @return
	 */
	public static byte[] getVRAZgBeConfigData() {
		byte[] command = new byte[12];
		command[0] = 0x55;
		command[1] = 0x10;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;

		command[10] = 0x00;
		command[11] = 0x00;
		return command;
	}
	/**
	 * TODO 设置设备参数
	 * @param DeviceIdBtye
	 * @return
	 */
	public static byte[] setVRAZgBeConfigData(String config,byte[] DeviceIdBtye) {
		byte[] data=getData(config,DeviceIdBtye);
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = 0x10;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		byte high = (byte)((data.length & 0xff00)>>8);
		byte low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
	/**
	 * TODO 获取开关量输出/输出开关
	 * @param cmd 类型
	 * @param switchType 开关量操作类型 0x1输出，0x5输入
	 * @param dataValue 欲获取的开关量组号
	 * @param deviceId 设备ID
	 * @return 发送byte数组
	 */
    public static byte[] getSwitch(byte cmd,byte switchType,byte[] dataValue,String deviceId){
    	byte[] dataForType=new byte[dataValue.length+1];
    	dataForType[0]=switchType;//类型1，获取输出开关    	
    	System.arraycopy(dataValue, 0, dataForType, 1, dataValue.length);
    	return getSentCommand(cmd,dataForType,deviceId);

	}
	/**
	 * TODO 获取开关量输出/输出配置
	 * @param cmd 类型
	 * @param switchType 开关量操作类型 0x4获取输出配置，0x7获取输入配置
	 * @param deviceId 设备ID
	 * @return 发送byte数组
	 */
    public static byte[] getSwitchConf(byte cmd,byte switchType,String deviceId){
    	byte[] dataForType=new byte[1];
    	dataForType[0]=switchType;//类型1，获取输出开关    	
    	return getSentCommand(cmd,dataForType,deviceId);
	}
	/**
	 * TODO 设置开关量”控制”，“配置”
	 *  @param groupCtrl,要发送的控制字节数组
	 * @param DeviceIdBtye
	 * @return
	 */
	public static byte[] setVRASwitchData(byte[] groupCtrl,byte[] DeviceIdBtye) {
		byte[] data=getData(groupCtrl,DeviceIdBtye);
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = 0x15;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		byte high = (byte)((data.length & 0xff00)>>8);
		byte low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
    /**
     * 处理验证区
     * @param data
     * @return
     */
	public static byte getCRC(byte[] data) {
		byte s = 0x00;
		for (int i = 0; i < data.length; i++) {
			s += data[i];
		}
		return s;
	}
//	public static void main(String[] args) throws Exception {
//		byte[] getGroups=new byte[24];//测试用
//		for(int i=0;i<24;i++) getGroups[i]=(byte)(i+1);////测试用
//		getSwitch((byte)0x15,(byte)0x1,getGroups,"10.01.10.11");
//	}
	
	////////////////////////IPC
	public static byte[] getIpcDev(byte[] DeviceIdBtye) {
		byte[] data=getData("",DeviceIdBtye);
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = (byte)0xc8;//200,//onvif device get
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		byte high = (byte)((data.length & 0xff00)>>8);
		byte low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
	
	public static byte[] addIpcDev(String config,byte[] DeviceIdBtye) {
		byte[] data=getData(config,DeviceIdBtye);
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = (byte)0xc9;//201,//onvif device add
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		byte high = (byte)((data.length & 0xff00)>>8);
		byte low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
	public static byte[] editIpcDev(String config,byte[] DeviceIdBtye) {
		byte[] data=getData(config,DeviceIdBtye);
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = (byte)0xca;//202,//onvif device add
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		byte high = (byte)((data.length & 0xff00)>>8);
		byte low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
	public static byte[] delIpcDev(String config,byte[] DeviceIdBtye) {
		byte[] data=getData(config,DeviceIdBtye);
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = (byte)0xcb;//203,//onvif device add
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		byte high = (byte)((data.length & 0xff00)>>8);
		byte low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
	public static byte[] getIpcProxy(String config,byte[] DeviceIdBtye) {
		byte[] data=getData(config,DeviceIdBtye);
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = (byte)0xd3;//211,//onvif device add
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		byte high = (byte)((data.length & 0xff00)>>8);
		byte low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
	public static byte[] addIpcProxy(String config,byte[] DeviceIdBtye) {
		byte[] data=getData(config,DeviceIdBtye);
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = (byte)0xd2;//210,//onvif device add
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		byte high = (byte)((data.length & 0xff00)>>8);
		byte low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
	
	
	public static byte[] getDMAConfigData() {
		byte[] command = new byte[12];
		command[0] = 0x55;
		command[1] = (byte)0xd8;//216
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;

		command[10] = 0x00;
		command[11] = 0x00;
		return command;
	}
	/**
	 * TODO 设置设备参数
	 * @param DeviceIdBtye
	 * @return
	 */
	public static byte[] setDMAConfigData(String config,byte[] DeviceIdBtye) {
		byte[] data=getData(config,DeviceIdBtye);
		byte[] command = new byte[13+data.length];
		command[0] = 0x55;
		command[1] = (byte)0xd7;
		command[2] = 0x00;
		command[3] = 0x00;
		command[4] = 0x00;
		command[5] = 0x00;
		command[6] = 0x00;
		command[7] = 0x00;
		command[8] = 0x00;
		command[9] = 0x00;
		
		//加密长度
		byte high = (byte)((data.length & 0xff00)>>8);
		byte low = (byte)(data.length & 0x00ff);
		command[10]=high;
		command[11]=low;
		int i=0;
		for(i=0;i<data.length;i++){
			command[12+i]=data[i];
		}
		//CRC
		command[command.length-1]=getCRC(data);
		return command;
	}
}
