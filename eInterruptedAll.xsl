<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:xs="http://www.w3.org/2001/XMLSchema"
xmlns:fn="http://www.w3.org/2005/xpath-functions">

<xsl:variable name="newline">
	<xsl:text> <br />
	</xsl:text>
</xsl:variable>

<xsl:variable name="task1" select="/usabilityRecord/tasksAnalized/taskName[1]/@name">
</xsl:variable> 	
	        	  
<xsl:template match="/">
  <html>
  <body>
   <h2>USABILITY METRICS FOR DESKTOP APPLICATION: <xsl:value-of select="/usabilityRecord/@appName"/> version <xsl:value-of select="/usabilityRecord/@version"/> </h2>
    <h3>Effectivity Dimension</h3> 
      
    <table border="1">
      <tr bgcolor="#9acd32">
      	<th>Session Id</th>
        <th>Percentage Tasks Interrupted during execution per Session and Task Name</th>        
      </tr>
      <xsl:for-each select="usabilityRecord/session">
        <tr>
          	<td>
	          <b>ID: </b><xsl:value-of select="./@id"/><br />
	          <b>Date: </b><xsl:value-of select="./@date"/><br />
	          <b>Sex: </b><xsl:value-of select="./@sex"/><br />
	          <b>Age: </b><xsl:value-of select="./@age"/><br />          
        	</td>
        	<td> 	        	
	        	<b>Task Name:</b> <xsl:value-of select="/usabilityRecord/tasksAnalized/taskName[1]/@name" /> <br />
	        	
				<b>COUNT Task Interrupted: </b> <xsl:variable name="count_task_CrearInterrupted" select="count(./task[starts-with(@id, /usabilityRecord/tasksAnalized/taskName[1]/@name) and @state='En Ejecución'])"> 
								  																		
								  </xsl:variable> <xsl:copy-of select="$count_task_CrearInterrupted" /><br />
				
				<b>TOTAL Tasks: </b> <xsl:variable name="count_task_Crear" select="count(./task[starts-with(@id, /usabilityRecord/tasksAnalized/taskName[1]/@name)])"> 
									
									</xsl:variable> <xsl:copy-of select="$count_task_Crear" /> <br />
				
				<b>Task Interrupted percent: </b> (COUNT/TOTAL)*100 = <xsl:copy-of select="($count_task_CrearInterrupted div $count_task_Crear) * 100" /> %<br />
				
				<xsl:value-of select="$newline"/>
				 
		        <b>Task Name: </b>	<xsl:value-of select="/usabilityRecord/tasksAnalized/taskName[2]/@name"/> <br />
				 		 
				<b>COUNT Task Interrupted: </b> <xsl:variable name="count_task_EditarInterrupted" select="count(./task[starts-with(@id, /usabilityRecord/tasksAnalized/taskName[2]/@name) and @state='En Ejecución'])"> 
								  
								  </xsl:variable> <xsl:copy-of select="$count_task_EditarInterrupted" /><br />
				<b>TOTAL Tasks: </b> <xsl:variable name="count_task_Editar" select="count(./task[starts-with(@id, /usabilityRecord/tasksAnalized/taskName[2]/@name)])"> 
									
									</xsl:variable> <xsl:copy-of select="$count_task_Editar" /> <br />
				<b>Task Interrupted percent: </b> (COUNT/TOTAL)*100  = <xsl:copy-of select="($count_task_EditarInterrupted div $count_task_Editar)* 100" /> % <br />
        	</td>
        </tr>
      </xsl:for-each>
    </table>
  </body>
  </html> 
 
</xsl:template>

</xsl:stylesheet>