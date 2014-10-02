<!--  
<div class="col-sm-3 col-md-2 sidebar">
  <ul class="nav nav-sidebar">
    <li class="active"><a href="#">Overview</a></li>
    <li><a href="#">Reports</a></li>
    <li><a href="#">Analytics</a></li>
    <li><a href="#">Export</a></li>
  </ul>
  <ul class="nav nav-sidebar">
    <li><a href="">Nav item</a></li>
    <li><a href="">Nav item again</a></li>
    <li><a href="">One more nav</a></li>
    <li><a href="">Another nav item</a></li>
    <li><a href="">More navigation</a></li>
  </ul>
  <ul class="nav nav-sidebar">
    <li><a href="">Nav item again</a></li>
    <li><a href="">One more nav</a></li>
    <li><a href="">Another nav item</a></li>
  </ul>
</div>
-->
<div class="col-sm-3 col-md-2 sidebar">
	<div id="jstree-div">
		<ul>
			<li data-jstree='{"icon":"fa fa-database"}'>schema01
				<ul>
					<li data-jstree='{"icon":"fa fa-cubes"}'>table01
						<ul>
							<li data-jstree='{"icon":"fa fa-cube"}'>column01</li>
							<li data-jstree='{"icon":"fa fa-cube"}'>column02</li>
							<li data-jstree='{"icon":"fa fa-cube"}'>column03</li>
							<li data-jstree='{"icon":"fa fa-cube"}'>column04</li>
						</ul>
					</li>
				</ul>
				<ul>
					<li>table02</li>
				</ul>
				<ul>
					<li>
					table03
					</li>
					
				</ul>
			</li>
			<li data-jstree='{"icon":"fa fa-database"}'>schema02</li>
		</ul>
	</div>
</div>

<script>
	$(document).ready(
		function(){
			$("#jstree-div").jstree(
				{
					"core" : {
					    "themes" : {
					    	/*
					      "variant" : "large"
					      */
					    }
					  },
					  "checkbox" : {
					    "keep_selected_style" : false
					  },
					  "plugins" : [ "wholerow", "checkbox" ]
				}		
			);
			return;
		}		
	);
	
	$('#jstree-div').on("changed.jstree", function (e, data) {
	  console.log(data.selected);
	});
</script>
