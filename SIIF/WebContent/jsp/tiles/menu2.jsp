


<html>
<head>


<style type="text/css">
/*Credits: Dynamic Drive CSS Library */
	/*URL: http://www.dynamicdrive.com/style/ */
#modernbricksmenu {
	padding: 0;
	width: 100%;
	background: transparent;
	voice-family: "\"}\"";
	voice-family: inherit;
}

#modernbricksmenu ul {
	font: bold 11px Arial;
	margin: 0;
	margin-left: 50px;
	/*margin between first menu item and left browser edge*/
	padding: 0;
	list-style: none;
}

#modernbricksmenu li {
	display: inline;
	margin: 0 2px 0 0;
	padding: 0;
	text-transform: uppercase;
}

#modernbricksmenu a {
	float: left;
	display: block;
	color: white;
	margin: 0 1px 0 0; /*Margin between each menu item*/
	padding: 5px 10px;
	text-decoration: none;
	letter-spacing: 1px;
	background-color: #4B8A08; /*Default menu color*/
	border-bottom: 1px solid white;
}

#modernbricksmenu a:hover {
	background-color: #5FB404; /*Menu hover bgcolor*/
	border-color: #5FB404;
}

/* Sub level menus*/
#modernbricksmenu ul li ul {
	position: absolute;
	z-index: 100;
	left: 8;
	top: 33;
	background-color: #5FB404;
	visibility: block;
}

a:active {
	background-color: #0000FF;
}

#modernbricksmenu #current a { /*currently selected tab*/
	background-color: #D25A0B; /*Brown color theme*/
	border-color: #D25A0B; /*Brown color theme*/
}

#modernbricksmenuline {
	clear: both;
	padding: 0;
	width: 100%;
	height: 15px;
	margin-left: 0px;
	line-height: 5px;
	/*background: #D25A0B;*/ /*Brown color theme*/
	background: #5FB404;
}

#modernbricksmenuline2 {
	clear: both;
	padding: 0;
	width: 100%;
	height: 24px;
	margin-left: 0px;
	line-height: 7px;
	/*background: #D25A0B;*/ /*Brown color theme*/
	background: #5FB404;
}

#modernbricksmenuline2 ul {
	font: bold 11px Arial;
	margin: 0;
	margin-left: 50px;
	/*margin between first menu item and left browser edge*/
	padding: 0;
	list-style: none;
}

#modernbricksmenuline2 li {
	display: inline;
	margin: 0 2px 0 0;
	padding: 0;
	text-transform: uppercase;
}

#modernbricksmenuline2 a {
	float: left;
	display: block;
	color: white;
	margin: 0 0 0 0; /*Margin between each menu item*/
	padding: 5px 10px;
	text-decoration: none;
	letter-spacing: 1px;
	background: #5FB404;
	border-bottom: 0px solid white;
}

#modernbricksmenuline2 a:hover {
	background-color: #4B8A08; /*Menu hover bgcolor*/
	/*border-color: #5FB404;
color: #4B8A08;*/
}

#myform { /*CSS for sample search box. Remove if desired */
	float: right;
	margin: 0;
	padding: 0;
}

#myform .textinput {
	width: 190px;
	border: 1px solid gray;
}

#myform .submit {
	font: bold 11px Verdana;
	height: 22px;
	background-color: lightyellow;
}
</style>


<link rel="stylesheet" type="text/css" href="../css/jqueryslidemenu.css" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
<script type="text/javascript" src="../js/jqueryslidemenu.js"></script>

</head>
<body>

<table border="0" cellpadding="0" cellspacing="0" width="100%"
	bgcolor="#4B8A08">
	<tr>
		<td>
		<div id="myslidemenu" class="jqueryslidemenu">
		<ul>
			<li><a href="http://www.dynamicdrive.com">Item 1</a></li>
			<li><a href="#">Item 2</a></li>
			<li><a href="#">Folder 1</a>
			<ul>
				<li><a href="#">Sub Item 1.1</a></li>
				<li><a href="#">Sub Item 1.2</a></li>
				<li><a href="#">Sub Item 1.3</a></li>
				<li><a href="#">Sub Item 1.4</a></li>
			</ul>
			</li>
			<li><a href="#">Item 3</a></li>
			<li><a href="#">Folder 2</a>
			<ul>
				<li><a href="#">Sub Item 2.1</a></li>
				<li><a href="#">Folder 2.1</a>
				<ul>
					<li><a href="pruebaTilesSub.jsp">Prueba Tiles Sub</a></li>
					<li><a href="pruebaTiles.jsp">Prueba Tiles</a></li>
					<li><a href="#">Folder 3.1.1</a>
					<ul>
						<li><a href="#">Sub Item 3.1.1.1</a></li>
						<li><a href="#">Sub Item 3.1.1.2</a></li>
						<li><a href="#">Sub Item 3.1.1.3</a></li>
						<li><a href="../prueba3.html">Prueba 3</a></li>
						<li><a href="prueba4b.html">Prueba 4b</a></li>
					</ul>
					</li>
					<li><a href="#">Sub Item 2.1.4</a></li>
				</ul>
				</li>
			</ul>
			</li>
			<li><a href="http://www.dynamicdrive.com/style/">Item 4</a></li>
		</ul>
		</div>
		</td>
	</tr>
</table>
</body>
</html>
