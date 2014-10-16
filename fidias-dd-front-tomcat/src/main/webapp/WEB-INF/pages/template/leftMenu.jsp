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
<div class="col-md-2 sidebar" style="overflow-x:scroll;">
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
			$("#jstree-div").hide();
			drawTreeView();

			return;
		}		
	);
	
	initTreeView = function(){
		
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
		$('#jstree-div').on("changed.jstree", function (e, data) {
		  console.log(data.selected);
		});
			
		return;
	}
	
	drawTreeView = function(){
 		$.ajax({ // ajax call starts
			url: '${pageContext.request.contextPath}/controller/projects', // JQuery loads serverside.php
 	        dataType: 'json', // Choosing a JSON datatype
 	        success: function(data){
 	        	
 	        	var branches = $("#jstree-div > ul");
 	        	$("#jstree-div").jstree('destroy');
 	        	if(branches.size() > 0){
 	        		branches.remove();
 	        	}
 	        	if(data.length > 0){
	 	        	
	 	        	var ulGral = $("<ul></ul>");
	 	        	
	 	        	$.each(data, function(idx, elem){
						var counter = 0;
 	        			var project = elem;
	 	        		
 	        			var liProj = $("<li id='proj_" + project.id + "' data-jstree='{\"icon\":\"fa fa-database\"}'>" + project.name + "</li>");
 	        			
 	        			$.each(project.tables, function(idx, elem){
 	        				
 	        				var table = elem;
 	        				
 	        				var ulTable = $("<ul></ul>");
 	        				
	 	        			var liTable = $("<li id='table_" + table.id + "' data-jstree='{\"icon\":\"fa fa-cubes\"}'>" + table.name + "</li>");
	 	        			
	 	        			$.each(table.columns, function(idx, elem){
	 	        				
	 	        				var column = elem;
	 	        		
	 	        				var ulColumn = $("<ul></ul>");
	 	        				
	 	        				var li1 = $("<li id='column_" + column.id + "' data-jstree='{\"icon\":\"fa fa-cube\"}'></li>").append(column.name);
		 	        			/*
	 	        				var li2 = $("<li data-jstree='{\"icon\":\"fa fa-cube\"}'></li>").append(column.type);
		 	        			var li3 = $("<li data-jstree='{\"icon\":\"fa fa-cube\"}'></li>").append(column.size);
		 	        			var li4 = $("<li data-jstree='{\"icon\":\"fa fa-cube\"}'></li>").append(column.unique);
		 	        			var li5 = $("<li data-jstree='{\"icon\":\"fa fa-cube\"}'></li>").append(column.nullable);
		 	        			var li6 = $("<li data-jstree='{\"icon\":\"fa fa-cube\"}'></li>").append(column.pk);
		 	        			var li7 = $("<li data-jstree='{\"icon\":\"fa fa-cube\"}'></li>").append(column.fk);
		 	        			var li8 = $("<li data-jstree='{\"icon\":\"fa fa-cube\"}'></li>").append(column.description);
	 	        				*/
		 	        			ulColumn.append(li1)/*.append(li2).append(li3).append(li4).append(li5).append(li6).append(li7)
		 	        				.append(li8)*/;
		 	        			
		 	        			liTable.append(ulColumn);
	 	        				
	 	        				return;
	 	        			})
	 	        			
	 	        			ulTable.append(liTable);
	 	        			
	 	        			liProj.append(ulTable);
	 	        			
 	        				return;
 	        			})
 	        			
		 	        	ulGral.append(liProj);
 	        			
 	        			return;
 	        		});
	 	        	
 	        		var treeDiv = $("#jstree-div").append(ulGral);
	 	        	
	 	        	initTreeView();
	 	        	
	 	        	treeDiv.show();
 	        	}
				
 	            return;
 	        }
 	    });
 		
 		return;
 	}
</script>
