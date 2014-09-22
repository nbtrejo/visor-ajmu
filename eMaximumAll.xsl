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
    <h3>Efficiency Dimension</h3> 
    <table border="1">
      <tr bgcolor="#9acd32">
      	<th>Session Id</th>
        <th>MAX Task Execution Time per Session and Task Name</th>        
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
	        	<xsl:for-each select="./task[starts-with(@id, 'Crear') and @state='Finalizada']">
	        	<xsl:sort select="./log/logFinal/@totalExecTime" data-type="number" order="descending"/>
	        		 <xsl:if test="position() = 1"> 
		        		<b>Task Name:   </b>   	<xsl:value-of select="@id"/> <br />
				 		<b>Final State:    </b>  	<xsl:value-of select="@state"/> <br />
				 		<b>Execution Time:    </b> <xsl:value-of select="./log/logFinal/@totalExecTime"/> <br /> 
				 	</xsl:if>	 
				</xsl:for-each>
				<xsl:value-of select="$newline"/>
				<xsl:for-each select="./task[starts-with(@id, 'Editar') and @state='Finalizada']">
	        	<xsl:sort select="./log/logFinal/@totalExecTime" data-type="number" order="descending"/>
	        		<xsl:if test="position() = 1"> 
		        		<b>Task Name:   </b>   	<xsl:value-of select="@id"/> <br />
				 		<b>Final State:    </b>  	<xsl:value-of select="@state"/> <br />
				 		<b>Execution Time:    </b> <xsl:value-of select="./log/logFinal/@totalExecTime"/> <br /> 
				 	 </xsl:if>	
				</xsl:for-each>
        	</td>
        </tr>
      </xsl:for-each>
    </table>
  </body>
  </html> 
 
</xsl:template>

 

</xsl:stylesheet>