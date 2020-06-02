package com.web.test.stat;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.web.test.common.constants.DateUtil;
import com.web.test.common.constants.PcInfoVo;
import com.web.test.common.constants.PushVo;
import com.web.test.common.constants.TimeType;

public class CronProcess {	

	private String timeType;
	

	private CronWorker cronWorker;
	
	private static final long MEGA_BYTE = 1024 * 1024;

	
	public CronProcess(String timeType, CronWorker cronWorker) {
		this.timeType = timeType;
		this.cronWorker = cronWorker;
	}
	
	public void run() {
		processSystemOperating();

		
//		Set<String> tableNameSet = new HashSet<>();
//		
//		for ( Cron oneCron : Cron.values()) {
//			
//			List<Cron> unitList = Cron.getCompressUnitEnumList();
//			
//			for ( Cron unitItem : unitList ) {
//				String oid = unitItem.getPmItemOid();
//				
//				Map<String,String> dataColumnNameList = new HashMap<>();
//				
//				try {
//				
//					Class commandClass = Class.forName(unitItem.getUnitClassName());
//					
//					Object oneClass = commandClass.newInstance();
//
//					Method methods[] = commandClass.getDeclaredMethods();
//					
//					for ( Method oneMethod : methods ) {
//						
//						String findMethod = oneMethod.getName();
//						
//						if ( findMethod.equals("getDataColumnNameList") ) {
////							Object result = oneMethod.invoke(oneClass);
////							dataColumnNameList =  (Map<String, String>) result;
////							
////							processDbTable(unitItem.getPmItemName(),this.timeType, dataColumnNameList);
//						}
//					}
//					
//					
//				}
//				catch(Exception ex) {
//					System.out.println(ex.getMessage());
//				}
//			}
//			
//		}
	}
	
	public void processDbTable( String tableName, String timeType, Map<String,String> dataColumnNameList ) {
		String sqlQuery = "";
		StringBuffer selectStrBuf = new StringBuffer();
		
		Iterator<Map.Entry<String,String>> entries = dataColumnNameList.entrySet().iterator();

		while(entries.hasNext()){

			Entry<String,String> entry = (Entry<String,String>)entries.next();
	
			//System.out.println("key : " + entry.getKey() + " , value : " + entry.getValue());

		}
	}
	
	public void processSystemOperating() {
		final OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();

		String currentTime = DateUtil.getCurrentDateString(DateUtil.yyMMddHHmm);

		ProcessDateInfo processDateInfo = new ProcessDateInfo(currentTime, timeType);
		try {
			if (Class.forName("com.sun.management.OperatingSystemMXBean").isInstance(osBean)) {
					
				final Method memTotalMethod = osBean.getClass().getDeclaredMethod("getTotalPhysicalMemorySize");
				memTotalMethod.setAccessible(true);
					
				final Method memFreeMethod = osBean.getClass().getDeclaredMethod("getFreePhysicalMemorySize");
				memFreeMethod.setAccessible(true);

				final Method cpuLoadMethod = osBean.getClass().getDeclaredMethod("getSystemCpuLoad");
				cpuLoadMethod.setAccessible(true);
				

				final double memTotal = (Long) memTotalMethod.invoke(osBean) / MEGA_BYTE;
				final double memFree = (Long) memFreeMethod.invoke(osBean) / MEGA_BYTE;
				final double cpuLoad = (Double) cpuLoadMethod.invoke(osBean) * 100;
				
				final double jvmUsed = Runtime.getRuntime().totalMemory() / MEGA_BYTE;
				final double jvmMax = Runtime.getRuntime().maxMemory() / MEGA_BYTE;
				
				final String yymmdd = DateUtil.currentDateTime();
				
				PcInfoVo pcInfoVo = new PcInfoVo();
				pcInfoVo.setMemTotal(Double.parseDouble(String.format("%.2f", memTotal)));
				pcInfoVo.setMemFree(Double.parseDouble(String.format("%.2f", memFree)));
				pcInfoVo.setCpuUsedrate(Double.parseDouble(String.format("%.2f", cpuLoad)));
				pcInfoVo.setJvmUsedrate(Double.parseDouble(String.format("%.2f", jvmUsed)))
				;
				pcInfoVo.setJvmMax(Double.parseDouble(String.format("%.2f", jvmMax)));
				pcInfoVo.setYymmdd(yymmdd);
				
				dbProcessPcInfo(pcInfoVo, processDateInfo);
			}
		}
		catch(Exception ex) {
			
		}
	}
	
	public void dbProcessPcInfo(PcInfoVo pcInfoVo, ProcessDateInfo processdateInfo) {
		
		StringBuffer buf = new StringBuffer();
		String insertTableName = "";
		String selectTableName = "";
		switch (timeType) {
			case TimeType.PT_ST_10SCEC:
				buf.append("INSERT INTO TB_ST_10SEC_PC_INFO (CPU_USEDRATE, YYMMDD, MEM_TOTAL, MEM_FREE, JVM_USEDRATE,JVM_MAX)"
						+ " VALUES("+pcInfoVo.getCpuUsedrate()+","+"'"+pcInfoVo.getYymmdd()+"'"+","+pcInfoVo.getMemTotal()+","
								+ pcInfoVo.getMemFree()+","+ pcInfoVo.getJvmUsedrate()+","+ pcInfoVo.getJvmMax()+")");
				break;
			case TimeType.PT_ST_1MIN:
				insertTableName = "TB_ST_1MIN_PC_INFO";
				selectTableName = "TB_ST_10SEC_PC_INFO";
				break;
			case TimeType.PT_ST_5MIN:
				insertTableName = "TB_ST_5MIN_PC_INFO";
				selectTableName = "TB_ST_1MIN_PC_INFO";
				break;
			case TimeType.PT_ST_1H:
				insertTableName = "TB_ST_1H_PC_INFO";
				selectTableName = "TB_ST_5MIN_PC_INFO";
				break;
			case TimeType.PT_ST_1D:
				insertTableName = "TB_ST_1D_PC_INFO";
				selectTableName = "TB_ST_1H_PC_INFO";
				break;
			default :
				break;
		}
		
		if ( buf.length() == 0 ) {
			buf.append("INSERT INTO "+insertTableName +"(CPU_USEDRATE, YYMMDD, MEM_TOTAL, MEM_FREE, JVM_USEDRATE,JVM_MAX)");
			buf.append(" SELECT ROUND(AVG(CPU_USEDRATE),2), MAX(YYMMDD), AVG(MEM_TOTAL), AVG(MEM_FREE), AVG(JVM_USEDRATE), AVG(JVM_MAX)");
			buf.append(" FROM " + selectTableName +"");
			buf.append(" WHERE YYMMDD BETWEEN " + "'"+processdateInfo.startTime +"'" + " AND " +  "'" + processdateInfo.endTime + "'");
		}
		
		PushVo pushVo = new PushVo();
		pushVo.setTimeType(timeType);
		pushVo.setQuery(buf.toString());
		pushVo.setPayload(pcInfoVo);
		
		cronWorker.push(pushVo);
	}
}
