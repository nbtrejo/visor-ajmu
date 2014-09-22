<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:xs="http://www.w3.org/2001/XMLSchema">
	<xsl:template match="/">
		<html>
			<head><title>Usability Analysis for Application: <xsl:value-of select="/usabilityRecord/@appName"/> - Version: <xsl:value-of select="/usabilityRecord/@version"/></title></head>
			<body>
				<table style='border-collapse:collapse'>
					<tr>
						<th style='border:1px solid black'> Session </th>
						<th style='border:1px solid black'> Task </th>
						<!-- <th style='border:1px solid black'> Maximum execution time</th>-->
					</tr>
					<!-- <xsl:for-each select="usabilityRecord/session/task">
					<tr>
						<td style='border:1px solid black'><xsl:value-of select="../@id"/></td>
						<td style='border:1px solid black'><xsl:value-of select="./@id"/> <xsl:value-of select="./@state"/> </td>
						<td style='border:1px solid black'><xsl:value-of select="./log/logFinal/@totalExecTime"/> </td>
					</tr>
					</xsl:for-each> -->
					<xsl:for-each select="usabilityRecord/session">
					<tr>
						<td style='border:1px solid black'><xsl:value-of select="./@id"/></td>
						<td style='border:1px solid black'><xsl:apply-templates select="task"/></td>						
					</tr>
					<tr>
						<td style='border:1px solid black'>Max tiempo de ejecucion: <xsl:copy-of select="$max"/></td>
					</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="task">
		 <b>Task Name:   </b>   	<xsl:value-of select="./@id"/> <br />
		 <b>Final State:    </b>  	<xsl:value-of select="./@state"/> <br />
		 <b>Execution Time:    </b> <xsl:value-of select="./log/logFinal/@totalExecTime"/> <br /> 
	 
	</xsl:template>
	
	<!-- <xsl:template match="max-task">	 -->	 
		 <xsl:variable name="max">
		  <xsl:for-each select="../task">
		    <xsl:sort select="//@totalExecTime" data-type="number" order="descending"/>
		    <!-- <xsl:if test="position() = 1"><xsl:value-of select="."/></xsl:if> -->
		    <xsl:value-of select="@totalExecTime"/>
		  </xsl:for-each>
		</xsl:variable>
	 
	<!-- </xsl:template> -->
</xsl:stylesheet>