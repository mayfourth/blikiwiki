<%--

  Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, version 2.1, dated February 1999.

  This program is free software; you can redistribute it and/or modify
  it under the terms of the latest version of the GNU Lesser General
  Public License as published by the Free Software Foundation;

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License
  along with this program (LICENSE.txt); if not, write to the Free Software
  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.

--%>
<%@ page import="
        org.jamwiki.DataHandler,
        org.jamwiki.Environment
    "
    contentType="text/html; charset=utf-8"
    isELIgnored="false"
%>
<script type="text/javascript">
// <![CDATA[
var DATABASE_ELEMENT_IDS = [
	"<%= Environment.PROP_DB_TYPE %>", "<%= Environment.PROP_DB_DRIVER %>", "<%= Environment.PROP_DB_URL %>",
	"<%= Environment.PROP_DB_USERNAME %>", "<%= Environment.PROP_DB_PASSWORD %>"
];
function onPersistenceType() {
	var disabled = (document.getElementById("<%= Environment.PROP_BASE_PERSISTENCE_TYPE %>").options[document.getElementById("<%= Environment.PROP_BASE_PERSISTENCE_TYPE %>").selectedIndex].value == "<%= WikiBase.PERSISTENCE_INTERNAL %>");
	for (var i = 0; i < DATABASE_ELEMENT_IDS.length; i++) {
		document.getElementById(DATABASE_ELEMENT_IDS[i]).disabled = disabled;
	}
}
var DATABASE_SAMPLE_VALUES = new Array();
 
function onDatabaseType() {
	var databaseType = (document.getElementById("<%= Environment.PROP_DB_TYPE %>").options[document.getElementById("<%= Environment.PROP_DB_TYPE %>").selectedIndex].value);
	var sampleDriver = ((DATABASE_SAMPLE_VALUES[databaseType]) ? DATABASE_SAMPLE_VALUES[databaseType]["<%= Environment.PROP_DB_DRIVER %>"] : "");
	var sampleUrl = ((DATABASE_SAMPLE_VALUES[databaseType]) ? DATABASE_SAMPLE_VALUES[databaseType]["<%= Environment.PROP_DB_URL %>"] : "");
	document.getElementById("<%= Environment.PROP_DB_DRIVER %>").value = sampleDriver;
	document.getElementById("<%= Environment.PROP_DB_URL %>").value = sampleUrl;
}
// ]]>
</script>
