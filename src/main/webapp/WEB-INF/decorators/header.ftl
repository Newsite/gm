<#import "../views/macro/StaticFileHelper.ftl" as helper>
<#import "../views/macro/dic_macro.ftl" as dic>
<!doctype html>
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang="en"> <!--<![endif]-->
<head>
	<meta charset="utf-8" />
	<!-- Set the viewport width to device width for mobile -->
  	<meta name="viewport" content="width=device-width" />
  	
	<title>${title}</title>
	
	<!-- Included CSS Files, use foundation.css if you do not want minified code -->
	<@helper.tagStaticResource filename="app.css"/>
	
	<!-- Custom Modernizr for Foundation -->
  	<@helper.tagStaticResource filename="modernizr.foundation.js"/>
  	
  	<!-- Latest version of jQuery -->
  	<@helper.tagStaticResource filename="jquery.js"/>
  	<!-- Included JS Files (Minified) -->
  	<@helper.tagStaticResource filename="foundation.min.js"/>
  	<!-- Initialize JS Plugins -->
 	<@helper.tagStaticResource filename="app.js"/>
 	
	<!-- bughurd -->
	<script type='text/javascript'>
		(function (d, t) {
		  var bh = d.createElement(t), s = d.getElementsByTagName(t)[0];
		  bh.type = 'text/javascript';
		  bh.src = '//www.bugherd.com/sidebarv2.js?apikey=ykcseztykfcqezbtluewkg';
		  s.parentNode.insertBefore(bh, s);
		  })(document, 'script');
	</script>
	<script>
		application_root='${base}';
	</script>
	
	${head}
</head>