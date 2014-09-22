<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:xs="http://www.w3.org/2001/XMLSchema"
xmlns:fn="http://www.w3.org/2005/xpath-functions">

<xsl:variable name="newline">
	<xsl:text>
	</xsl:text>
</xsl:variable>
  
<xsl:template match="/">
  <html>
  <body>
   <h2>USABILITY METRICS FOR DESKTOP APPLICATION: <xsl:value-of select="/usabilityRecord/@appName"/> version <xsl:value-of select="/usabilityRecord/@version"/> </h2>
    <h3>Effectivity Dimension</h3> 
    <table border="1">
      <tr bgcolor="#9acd32">
      	<th>Session Id</th>
        <th>TOTAL Dialogues visualized per Task Execution per Session</th>        
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
	        	<xsl:for-each select="./task[starts-with(@id, /usabilityRecord/tasksAnalized/taskName[1]/@name)]/log">
 		        		<b>Task Name:   </b>   	<xsl:value-of select="../@id"/> 
 		        		<!-- sacar la variable de la tarea -->
 		        		<xsl:variable name="id_task" select="../@id"></xsl:variable> <br />
				 		<b>Final State:    </b>  	<xsl:value-of select="../@state"/> <br />
				 		<xsl:for-each select="./logParcial">
				 			<xsl:if test="position() = last()">  
				 				<b>----Dialogues visualized in Parcial Log:   </b>   	<xsl:value-of select="./@totalDialog"/> <br />
				 				<small><b>---------- TotalMessWithoutIcon: </b><xsl:value-of select="./@totalMessWithoutIcon"/>
				 				<b>--- TotalMessError: </b><xsl:value-of select="./ @totalMessError"/>
				 				<b>--- TotalMessWarn:</b><xsl:value-of select="./@totalMessWarn"/>
				 				<b>--- TotalMessInfo: </b><xsl:value-of select="./@totalMessInfo"/>
				 				<b>--- TotalMessQuestion:</b> <xsl:value-of select="./@totalMessQuestion"/> </small><br />
				 			</xsl:if>	
				 		</xsl:for-each>
				 		<xsl:for-each select="./logFinal">
				 			<xsl:if test="position() = last()">  
				 				<b>----Dialogues visualized in Final Log:   </b>   	<xsl:value-of select="./@totalDialog"/> <br />
				 			</xsl:if>	
				 		</xsl:for-each>		
				 	<xsl:for-each select="//task[@id=$id_task]/dialogues/dialog">
 		        		<b>Dialog Id:   </b>   	<xsl:value-of select="./@id"/> 
				 		<b> Dialog message:    </b>  	<xsl:value-of select="./@message"/> <br />
				 	</xsl:for-each>		 		
				</xsl:for-each>
				
				<xsl:value-of select="$newline"/>
				<br /><br />
				<xsl:for-each select="./task[starts-with(@id, /usabilityRecord/tasksAnalized/taskName[2]/@name)]/log">
 		        		<b>Task Name:   </b>   	<xsl:value-of select="../@id"/> 
 		        		<xsl:variable name="id_task" select="../@id"></xsl:variable> <br />
				 		<b>Final State:    </b>  	<xsl:value-of select="../@state"/> <br />
				 		<xsl:for-each select="./logParcial">
				 			<xsl:if test="position() = last()">  
				 				<b>----Dialogues visualized in Parcial Log:   </b>   	<xsl:value-of select="./@totalDialog"/> <br />
				 			</xsl:if>	
				 		</xsl:for-each>
				 		<xsl:for-each select="./logFinal">
				 			<xsl:if test="position() = last()">  
				 				<b>----Dialogues visualized in Final Log:   </b>   	<xsl:value-of select="./@totalDialog"/> <br />
				 			</xsl:if>	
				 		</xsl:for-each>			
				 		<xsl:for-each select="//task[@id=$id_task]/dialogues/dialog">
 		        		<b>Dialog Id: </b>   	<xsl:value-of select="./@id"/> 
				 		<b> Dialog message:    </b>  	<xsl:value-of select="./@message"/> <br />
				 	</xsl:for-each>		 		
				</xsl:for-each>
        	</td>
        </tr>
      </xsl:for-each>
    </table>
  </body>
  </html> 
 
</xsl:template>

 

</xsl:stylesheet>