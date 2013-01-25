<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${title}</title>
<#import "../views/macro/StaticFileHelper.ftl" as helper>
<#import "../views/macro/dic_macro.ftl" as dic>

<@helper.tagStaticResource filename="foundation.css"/> 
<@helper.tagStaticResource filename="exam.css"/>
<@helper.tagStaticResource filename="core.css"/> 
<@helper.tagStaticResource filename="pagination.css"/>
<@helper.tagStaticResource filename="foundation/jquery-1.8.3.js"/>
<@helper.tagStaticResource filename="foundation/jquery.foundation.alerts.js"/>
<@helper.tagStaticResource filename="foundation/jquery.foundation.accordion.js"/>
<@helper.tagStaticResource filename="foundation/jquery.foundation.buttons.js"/>
<@helper.tagStaticResource filename="foundation/jquery.foundation.clearing.js"/>
<@helper.tagStaticResource filename="foundation/jquery.foundation.forms.js"/>
<@helper.tagStaticResource filename="foundation/jquery.foundation.joyride.js"/>
<@helper.tagStaticResource filename="foundation/jquery.foundation.magellan.js"/>
<@helper.tagStaticResource filename="foundation/jquery.foundation.mediaQueryToggle.js"/>
<@helper.tagStaticResource filename="foundation/jquery.foundation.navigation.js"/>
<@helper.tagStaticResource filename="foundation/jquery.foundation.orbit.js"/>
<@helper.tagStaticResource filename="foundation/jquery.foundation.reveal.js"/>
<@helper.tagStaticResource filename="foundation/jquery.foundation.tabs.js"/>
<@helper.tagStaticResource filename="foundation/jquery.foundation.tooltips.js"/>
<@helper.tagStaticResource filename="foundation/jquery.foundation.topbar.js"/>
<@helper.tagStaticResource filename="foundation/jquery.placeholder.js"/>
<@helper.tagStaticResource filename="jquery.pagination.js"/>

${head}
<script>
	application_root='${base}';
</script>
