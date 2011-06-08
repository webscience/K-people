<xsl:stylesheet   xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>
    
    <xsl:template match="/">
            <sendEvent xmlns="http://service.kpeople.webscience.it">            
                <xsl:copy-of select="*"/>
            </sendEvent>
    </xsl:template>

</xsl:stylesheet>